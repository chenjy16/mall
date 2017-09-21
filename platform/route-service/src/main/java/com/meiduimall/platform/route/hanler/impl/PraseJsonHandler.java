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
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.platform.route.ResponsePackUtil;
import com.meiduimall.platform.route.hanler.Handler;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class PraseJsonHandler implements Handler {

  private static Logger log = LoggerFactory.getLogger(PraseJsonHandler.class);

  /**
   * 功能描述:  解析封装json格式的参数
   * Author: 陈建宇
   * Date:   2016年12月19日 上午10:15:44
   * param  ctx
   * return Boolean
   */
  @Override
  public Boolean process(RequestContext ctx) {
    HttpServletRequest request = ctx.getRequest();
    String json = null;
    try{
      InputStream in = ctx.getRequest().getInputStream();
      json = CharStreams.toString(new InputStreamReader(in));
  	  Map<String, String> param =JsonUtils.jsonToBean(json, Map.class);
      ctx.set("param", param);
      Closeables.closeQuietly(in);
    } catch (Exception e) {
      log.error("请求参数Json格式解析处理层,url:{},请求body:{},异常信息:{}", request.getRequestURL().toString(),
          json, ExceptionUtils.getFullStackTrace(e));
      ResponsePackUtil.responseWrapper(ctx, BaseApiCode.EXCEPTION_PRASE_JSON);
      return false;
    }
    log.info("请求参数Json格式解析处理层,url:{},请求body:{}", request.getRequestURL().toString(),
        json);
    return true;
  }

}
