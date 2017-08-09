package com.blueocean.common.exception;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blueocean.common.util.RetInfoUtil;
import com.blueocean.common.vo.RetInfo;
import com.blueocean.web.hellomanage.controller.HelloController;


@RestControllerAdvice(basePackages = {"com.bluesea"})
public class ContextExceptionHandler {
	
	private static final RetInfo ret = RetInfoUtil.initRetInfo4Err();
	
	private static final Logger _log = LoggerFactory.getLogger(ContextExceptionHandler.class);
	
	@ExceptionHandler({RuntimeException.class})
	public RetInfo handleException(HttpServletRequest req,RuntimeException e){
		String message = e.getMessage();
		_log.error("服务异常请求路径:"+req.getRequestURI()+"服务异常信息:"+message);
		try {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	        Matcher m = p.matcher(message);
	        Long timeMillis = System.currentTimeMillis();
			if (message == null || !m.find()) {
				message = "服务器异常,请稍后再试!";
			}else {
				message = e.getMessage();
			}
			ret.setErrorMsg(message);
			ret.setDetail_msg(e.toString());
			ret.setAcceptTime(timeMillis.toString());
		} catch (Exception e1) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@ExceptionHandler({BadSqlGrammarException.class,NullPointerException.class})
	public RetInfo handleUnCatchException(HttpServletRequest req,Exception e){
		String message = e.getMessage();
		_log.error("服务异常请求路径:"+req.getRequestURI()+"服务异常信息:"+message);
		try {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	        Matcher m = p.matcher(message);
	        Long timeMillis = System.currentTimeMillis();
			if (message == null || !m.find()) {
				message = "服务器异常,请稍后再试!";
			}else {
				message = e.getMessage();
			}
			ret.setErrorMsg(message);
			ret.setDetail_msg(e.toString());
			ret.setAcceptTime(timeMillis.toString());
		} catch (Exception e1) {
			e.printStackTrace();
		}
		return ret;
	}
	@ExceptionHandler({UncategorizedSQLException.class})
	public RetInfo handleSQLExceptionException(SQLException e){
		String message = e.getMessage();
		ret.setDetail_msg(e.toString());
		if (message.startsWith("Incorrect string value")) {
			message = "输入内容不合法";
			ret.setErrorMsg(message);
		}
		return ret;
	}
	
	@ExceptionHandler({Exception.class})
	public RetInfo handleException(HttpServletRequest req,Exception e){
		String message = e.getMessage();
		_log.error("服务异常请求路径:"+req.getRequestURI()+"服务异常信息:"+message);
		try {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	        Matcher m = p.matcher(message);
	        Long timeMillis = System.currentTimeMillis();
			if (message == null || !m.find()) {
				message = "服务器异常,请稍后再试!";
			}else {
				message = e.getMessage();
			}
			ret.setErrorMsg(message);
			ret.setDetail_msg(e.toString());
			ret.setAcceptTime(timeMillis.toString());
		} catch (Exception e1) {
			e.printStackTrace();
		}
		return ret;
	}
}