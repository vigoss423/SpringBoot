package com.blueocean.common.plugin;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blueocean.common.util.JsonUtil;

import java.util.Collection;

/**
 * MyBatis的SQL监控插件拦截器
 * <p>可监控慢sql，大集合<p>
 * @author 王欣宇
 */
public final class MyBatisSQLMonitorPlugin {
	
  private static final Logger _log = LogManager.getLogger(MyBatisSQLMonitorPlugin.class);
  /**
   * 是否监控显示SQL
   */
  private static final boolean SHOWSQL = false;
  /**
   * 慢SQL时间阀值，单位毫秒
   */
  private static final int SLOWER = 3000;
  /**
   * 大集合监控阀值，单位条
   */
  private static final int MAXCOUNT = 80;

  /**
   * MyBatis的SQL监控插件拦截器
   * @param invocation 调用方法
   * @return Object
   * @throws Throwable 异常
   */
  public static Object intercept(Invocation invocation) throws Throwable {
    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
    //请求参数
    Object parameter = null;
    if (1 < invocation.getArgs().length) {
      parameter = invocation.getArgs()[1];
    }
    BoundSql boundSql = mappedStatement.getBoundSql(parameter);
    String sql = boundSql.getSql();
    //执行的sql所在的mapper文件
    String resource = mappedStatement.getResource();
    //执行sql的dao文件的包名+方法名
    String daoMethod = mappedStatement.getId();
    //去除sql文中的换行
    sql = sql.replace("\n", "").replaceAll("\\s+", " ");
    if (SHOWSQL) {
      _log.warn("[SQLMonitorPlugin]SQL监控：{}|{}，执行SQL：{}，参数：{}", resource, daoMethod,
          sql, JsonUtil.toJson(parameter));
    }
    try {
      long start = System.currentTimeMillis();
      Object e = invocation.proceed();
      long timeTicket = System.currentTimeMillis() - start;
      if (SLOWER < timeTicket) {
    	  _log.warn("[SQLMonitorInterceptor]SQL监控：{}|{}，慢SQL（{}/{}ms）{}，参数：{}",
            resource, daoMethod, timeTicket, SLOWER, sql, JsonUtil.toJson(parameter));
      }
      if (e instanceof Collection) {
        int ct = ((Collection<?>) e).size();
        if (MAXCOUNT < ct) {
        	_log.warn("[SQLMonitorInterceptor]SQL监控：{}|{}，大集合（{}/{}）{}，参数：{}",
              resource, daoMethod, ct, MAXCOUNT, sql, JsonUtil.toJson(parameter));
        }
      }
      return e;
    } catch (Exception e) {
    	_log.warn("[SQLMonitorPlugin]SQL监控：{}|{}，执行出错：{}，参数：{}", resource, daoMethod,
          sql, JsonUtil.toJson(parameter), e);
      throw e;
    }
  }
}
