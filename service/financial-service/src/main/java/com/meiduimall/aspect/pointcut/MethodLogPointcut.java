package com.meiduimall.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodLogPointcut {
	
	@Pointcut("execution(* com.meiduimall.service.financial.controller.*.*(..))")
	public void pointcutLog() {
		// Do nothing because of X and Y.
	}
}
