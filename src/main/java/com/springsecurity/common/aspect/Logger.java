package com.springsecurity.common.aspect;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class Logger {
	private final Log logger = LogFactory.getLog(this.getClass());

	public Object aroundLoggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("************Method Started************");
		logger.info("Method Name : " + joinPoint.getSignature().getName());
		logger.info("Method Arguments : " + Arrays.toString(joinPoint.getArgs()));
		
		Object obj=joinPoint.proceed(); // continue on the intercepted method
		if(obj!=null)
		logger.info("Method Return  : " + obj.toString());
		
		logger.info("************Method End************");
		return obj;
	}
	
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {

		logger.info("Exception handler...");

	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    logger.info("Caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + ex.getMessage(), ex);
	  }
}
