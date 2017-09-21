package com.meiduimall.service.member.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.beans.BeansException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.BizException;
import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.constant.ApiStatusConst;

/**
 * 全局异常处理
 * @author chencong
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = BizException.class)
  public ResBodyData bizeExceptionHandler(HttpServletRequest request, BizException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+ApiStatusConst.getZhMsg(exception.getCode()));
    return new ResBodyData(exception.getCode(),exception.getLocalizedMessage());
  }
  
  @ExceptionHandler(value = SystemException.class)
  public ResBodyData systemExceptionHandler(HttpServletRequest request, SystemException exception) {
    logger.error(request.getContextPath()+request.getRequestURI()+" "+ApiStatusConst.getZhMsg(exception.getCode()));
    return new ResBodyData(exception.getCode(),ApiStatusConst.getZhMsg(exception.getCode()));
  }


  @ExceptionHandler(value = BindException.class)
  public ResBodyData errorHandlerOverJson(HttpServletRequest request, BindException exception) {
	  StringBuilder sb=new StringBuilder();
      exception.getBindingResult().getFieldErrors().forEach((error)->{
      	sb.append(error.getDefaultMessage()).append(";");
      });
      return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
  }
  
  /**
   * 处理api层copyProperties()方法抛出的BeansException异常
   * @param request HttpServletRequest对象
   * @param exception 属性复制失败抛出的异常
   * @return 统一数据返回格式
   */
  @ExceptionHandler(value = BeansException.class)
  public ResBodyData errorHandlerBeansCopy(HttpServletRequest request, BeansException exception) {
      return new ResBodyData(HttpStatus.SC_BAD_REQUEST,exception.getLocalizedMessage());  
  }
  
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResBodyData errorHandlerByJson(HttpServletRequest request, MethodArgumentNotValidException exception) {
	  StringBuilder sb=new StringBuilder();
      exception.getBindingResult().getFieldErrors().forEach((error)->{
      	sb.append(error.getDefaultMessage()).append(";");
      });
      return new ResBodyData(HttpStatus.SC_BAD_REQUEST, sb.toString());  
  }

}