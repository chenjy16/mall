package com.meiduimall.payment.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.BizException;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;

@ControllerAdvice(basePackages = "com.meiduimall.payment.api.controller")
@ResponseBody
public class GlobalExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = BindException.class)
	public Object methodArgumentNotValidHandler(HttpServletRequest request, BindException exception) {
		StringBuilder sb = new StringBuilder();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			sb.append(error.getDefaultMessage()).append(";");
		});
		return new ResBodyData(ServicePaymentApiCode.REQUEST_PARAMS_ERROR, sb.toString());
	}
	
	
	@ExceptionHandler(value = BizException.class)
	public ResBodyData serviceExceptionHandler(HttpServletRequest request, BizException exception) {
		logger.error(request.getContextPath() + request.getRequestURI() + " " + exception.getLocalizedMessage());
		return new ResBodyData(ServicePaymentApiCode.OPERAT_FAIL, exception.getLocalizedMessage());
	}

}
