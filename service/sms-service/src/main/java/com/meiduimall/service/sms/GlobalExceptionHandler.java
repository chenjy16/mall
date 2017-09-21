package com.meiduimall.service.sms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.sms.constant.SmsApiCode;

/**
 * 全局异常处理 
 * 
 * @author yangchang
 */
@ControllerAdvice(basePackages = "com.meiduimall.service.sms.controller")
@ResponseBody
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object methodJsonArgumentNotValidHandler(HttpServletRequest request,
			MethodArgumentNotValidException exception) {
		return new ResBodyData(SmsApiCode.REQUEST_PARAMS_ERROR, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = BindException.class)
	public Object methodFromArgumentNotValidHandler(HttpServletRequest request, BindException exception) {
		return new ResBodyData(SmsApiCode.REQUEST_PARAMS_ERROR, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR),
				JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = ApiException.class)
	public Object apiExceptionHandler(HttpServletRequest request, ApiException exception) {
		return new ResBodyData(exception.getCode(), exception.getMessage(), JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = ServiceException.class)
	public Object serviceExceptionHandler(HttpServletRequest request, ServiceException exception) {
		return new ResBodyData(exception.getCode(), exception.getMessage(), JsonUtils.getInstance().createObjectNode());
	}

	@ExceptionHandler(value = DaoException.class)
	public Object daoExceptionHandler(HttpServletRequest request, DaoException exception) {
		logger.error("全局捕获DaoException:   " + exception);
		return new ResBodyData(exception.getCode(), exception.getMessage(), JsonUtils.getInstance().createObjectNode());
	}
}
