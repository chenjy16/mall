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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.platform.route.ResponsePackUtil;
import com.meiduimall.platform.route.hanler.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.netflix.zuul.context.RequestContext;



public class FormParseHandler implements Handler{

	private static Logger log = LoggerFactory.getLogger(FormParseHandler.class);
	
	
	/**
	 * 功能描述: 解析封装post请求的kv参数
	 * Author: 陈建宇
	 * Date:   2017年3月13日 下午1:57:30
	 */
	@Override
	public Boolean process(RequestContext ctx) {
		HttpServletRequest request = ctx.getRequest();
		try {
			Map<String, String> param = new HashMap<>();
			Map<String, String[]> map=request.getParameterMap();
			Set<Map.Entry<String, String[]>> set=map.entrySet();
			if(!CollectionUtils.isEmpty(set)){
				log.info("form方式请求参数解析处理层,url:{},请求参数:{}",request.getRequestURL().toString(), JsonUtils.beanToJson(map));
				set.forEach(entry->{
					String key=entry.getKey();
					String[] value=entry.getValue();
					param.put(key, value[0]);
				});
			}
			ctx.set("param", param);
		} catch (Exception e) {
			log.error("form方式请求参数解析处理层,url:{},请求参数:{},异常信息:{}",request.getRequestURL().toString(),
					request.getQueryString(), ExceptionUtils.getFullStackTrace(e));
			ResponsePackUtil.responseWrapper(ctx, BaseApiCode.EXCEPTION_PRASE_FORM);
			return false;
		}
		return true;
	}

}
