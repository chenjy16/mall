/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.platform.route.hanler.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.platform.route.ResponsePackUtil;
import com.meiduimall.platform.route.hanler.Handler;
import com.netflix.zuul.context.RequestContext;

public class ParamPraseHandler implements Handler{

	private static Logger log = LoggerFactory.getLogger(ParamPraseHandler.class);
	
	
	/**
	 * 功能描述:  解析封装kv请求的参数
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午10:14:50
	 * param  ctx
	 * return Boolean     
	 */
	@Override
	public Boolean process(RequestContext ctx) {
		HttpServletRequest request = ctx.getRequest();
		log.info("kv方式请求参数解析处理层,url:{},请求参数:{}",request.getRequestURL().toString(),
				request.getQueryString());
		try {
			Map<String, String> param = new HashMap<>();
			Enumeration<String> enumeration = ctx.getRequest().getParameterNames();
			while (enumeration.hasMoreElements()) {
				String key = enumeration.nextElement();
				String value = ctx.getRequest().getParameter(key);
				param.put(key, value);
			}
			ctx.set("param", param);
		} catch (Exception e) {
			log.error("kv方式请求参数解析处理层,url:{},请求参数:{},异常信息:{}",request.getRequestURL().toString(),
					request.getQueryString(),ExceptionUtils.getFullStackTrace(e));
			ResponsePackUtil.responseWrapper(ctx, BaseApiCode.EXCEPTION_GATEWAY);
			return false;
		}
		return true;
	}

}
