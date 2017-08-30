package com.blueocean.common.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 日志记录AOP工具类，环绕通知
 * @author BBF
 */
@Aspect
@Configuration
public class LogAspectUtil {
	
  private static final Logger _log = LoggerFactory.getLogger(LogAspectUtil.class);

  /**
   * 定义切面，此方法不能有返回值，该方法只是一个标示，方法体不会被执行
   */
  @Pointcut("execution( * com.blueocean.web.*.service.impl.*.*(..))") //+
  //    " or execution( * com.blueocean.web.hellomanage.service.impl.*.*(..))")
  private void recordLog() {
  }

  /**
   * 环绕通知，决定真实的方法是否执行，而且必须有返回值。 同时在所拦截方法的前后执行一段逻辑。
   *
   * @param pjp 连接点
   * @return 执行方法的返回值
   * @throws Throwable 抛出异常
   */
  @Around("recordLog()")
  public Object aroundLogCalls(ProceedingJoinPoint pjp) throws Throwable {
    String className = pjp.getTarget().getClass().getName();
    String methodName = pjp.getSignature().getName(); // 获得方法名
    Object[] args = pjp.getArgs(); // 获得参数列表
    long threadId = Thread.currentThread().getId();
    if (_log.isDebugEnabled()) {
    	_log.debug("[AOP - LogAspect]调用开始 - {}.{}：[线程ID：{}]，参数JSON：{}",
          className, methodName, threadId, JsonUtil.toJson(args));
    }
    // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
    Object result = pjp.proceed();// procObject的值就是被拦截方法的返回值
    if (_log.isDebugEnabled()) {
    	_log.debug("[AOP - LogAspect]调用结束 - {}.{}：[线程ID：{}]，返回JSON：{}",
          className, methodName, threadId, JsonUtil.toJson(result));
    }
    return result;
  }
}
