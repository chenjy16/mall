package com.meiduimall.core.advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.meiduimall.core.util.JsonUtils;


@Aspect
public class MethodLogAdvice {
	
	private static Logger logger = LoggerFactory.getLogger(MethodLogAdvice.class);
	
	/**
	 * 功能描述:  定义切入点allMethod()的环绕通知,环绕通知方法一定要按照下面的形式定义(只可以修改方法名和参数名)
	 * Author: 陈建宇
	 * Date:   2017年4月25日 上午10:04:06 
	 * param   @param point
	 * param   @return
	 * return  Object
	 */
	@Around("com.meiduimall.aspect.pointcut.MethodLogPointcut.pointcutLog()")
	public Object doSomethingAround(ProceedingJoinPoint point) throws Throwable {
		long startTime = System.currentTimeMillis();
		//拦截的方法名称
		String methodName = point.getSignature().getName();
		//拦截方法参数值
		Object[] args=point.getArgs();
		String reqStr=JsonUtils.beanToJson(args);
		logger.info(">>>>>>>>>>>>>>>>>>方法名：{}>>start,参数:{}",methodName,reqStr);
		//执行方法逻辑
		Object obj = point.proceed();
		long runTime = System.currentTimeMillis() - startTime;
		logger.info(">>>>>>>>>>>>>>>>>>方法名：{}>>end,返回值:{},耗时:"+runTime,methodName,reqStr);
		return obj;
	}
}	
