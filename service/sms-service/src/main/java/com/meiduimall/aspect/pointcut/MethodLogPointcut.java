package com.meiduimall.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP配置
 * 
 * @author chencong
 */
@Aspect
public class MethodLogPointcut {

	@Pointcut("execution(* com.meiduimall.service.sms.controller.*.*(..))")
	public void pointcutLog() {
		// Do nothing because of X and Y.
	}
}
