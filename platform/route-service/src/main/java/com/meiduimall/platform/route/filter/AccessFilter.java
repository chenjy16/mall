/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.platform.route.filter;

import com.google.common.base.Splitter;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.platform.route.Constants;
import com.meiduimall.platform.route.ResponsePackUtil;
import com.meiduimall.platform.route.hanler.impl.*;
import com.meiduimall.redis.util.spring.AppContextLauncher;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccessFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

  @Autowired
  private BlackListValidateHandler blackListValidateHandler;

  @Autowired
  private ParamPraseHandler paramPraseHandler;

  @Autowired
  private PraseJsonHandler praseJsonHandler;

  @Autowired
  private RequiredValidateHandler requiredValidateHandler;

  @Autowired
  private TimeValidateHandler timeValidateHandler;

  @Autowired
  private SignValidateHandler signValidateHandler;

  @Autowired
  private FormParseHandler formParseHandler;


  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return Constants.FILTER_ORDER_ZERO;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    long startTime = System.currentTimeMillis();
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    HttpServletResponse response = ctx.getResponse();
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    try {
      HandlerChain chain = AppContextLauncher.getBean("chain", HandlerChain.class);
      String contentType = ctx.getRequest().getContentType();
      List<String> header = null;
      if (contentType != null) {
        header = Splitter.on(";").trimResults().splitToList(contentType);
      }
      chain.addProcesser(blackListValidateHandler);
      if (header != null && Constants.CONTENTTYPE_JSON.equalsIgnoreCase(header.get(0))) {
    	  
        chain.addProcesser(praseJsonHandler);
        
      } else if (header != null && (Constants.CONTENTTYPE_FORM.equalsIgnoreCase(header.get(0))
          || Constants.CONTENTTYPE_MULTIPART.equalsIgnoreCase(header.get(0)))) {
    	  
        chain.addProcesser(formParseHandler);
        
      } else {
    	  
        chain.addProcesser(paramPraseHandler);
      }
      chain.addProcesser(requiredValidateHandler);
      chain.addProcesser(timeValidateHandler);
      chain.addProcesser(signValidateHandler);
      chain.process(ctx);
    } catch (Exception e) {
      log.error("网关层过滤器异常,url:{},异常信息:{}", request.getRequestURL().toString(), ExceptionUtils.getFullStackTrace(e));
      ResponsePackUtil.responseWrapper(ctx, BaseApiCode.EXCEPTION_GATEWAY);
    }
    long runTime = System.currentTimeMillis() - startTime;
    log.info("网关层过滤器,url:{},Content-Type:{},耗时:{}ms", request.getRequestURL().toString(), request.getHeader("Content-Type"), runTime);
    return null;
  }


}
