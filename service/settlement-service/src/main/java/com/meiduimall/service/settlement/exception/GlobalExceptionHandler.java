package com.meiduimall.service.settlement.exception;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.settlement.common.ShareProfitConstants;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: GlobalExceptionHandler.java
 * Author:   Administrator
 * Date:     2017年3月17日 下午4:11:30
 * Description: 全局参数验证不通过的处理
 */
@ControllerAdvice(basePackages = "com.meiduimall.service.settlement.api")
@ResponseBody
public class GlobalExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 请求参数验证，返回异常
	 * @param  request 客户端发出请求
	 * @param  exception 绑定异常
	 * @return ResBodyData
	 */
	@ExceptionHandler(value = BindException.class)
	public ResBodyData methodArgumentNotValidHandler(HttpServletRequest request, BindException exception) {
		StringBuilder sb = new StringBuilder();
		exception.getBindingResult().getFieldErrors().forEach(error -> sb.append(error.getDefaultMessage()).append(";"));
		return new ResBodyData(ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE, sb.toString());
	}
	
	/**
	 * 业务逻辑处理，返回异常
	 * @param  request 客户端发出请求
	 * @param  exception 绑定异常
	 * @return ResBodyData
	 */
	@ExceptionHandler(value = ServiceException.class)
	public ResBodyData serviceExceptionHandler(HttpServletRequest request, ServiceException exception) {
		logger.error(request.getContextPath() + request.getRequestURI() + " " + exception.getLocalizedMessage());
		return new ResBodyData(ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE, exception.getLocalizedMessage());
	}

}
