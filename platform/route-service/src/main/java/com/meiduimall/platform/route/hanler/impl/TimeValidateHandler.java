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

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.Constants;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.platform.route.ResponsePackUtil;
import com.meiduimall.platform.route.hanler.Handler;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TimeValidateHandler implements Handler {
  private static Logger log = LoggerFactory.getLogger(TimeValidateHandler.class);


  /**
   * 功能描述:  验证时间戳
   * Author: 陈建宇
   * Date:   2016年12月20日 上午10:41:50
   * param ctx
   * return Boolean
   */
  @Override
  public Boolean process(RequestContext ctx) {
    HttpServletRequest request = ctx.getRequest();
    Map<String, String> param = (Map<String, String>) ctx.get("param");
    try {
      long requestTime = Long.parseLong(param.get("timestamp"));
      long curTime = System.currentTimeMillis();
      if ((curTime - requestTime) > Constants.CONSTANT_FIVEMINUTE) {
        log.info("时间戳是否超时验证处理层,url:{},请求参数:{}", request.getRequestURL().toString(),
            JsonUtils.beanToJson(param));
        ResponsePackUtil.responseWrapper(ctx, BaseApiCode.FAIL_TIMESTAMP);
        return false;
      }
    } catch (Exception e) {
      log.error("时间戳是否超时验证处理层,url:{},请求参数:{},异常:{}",
          request.getRequestURL().toString(), JsonUtils.beanToJson(param), ExceptionUtils.getFullStackTrace(e));
      ResponsePackUtil.responseWrapper(ctx, BaseApiCode.EXCEPTION_TIMESTAMP);
      return false;
    }
    return true;
  }

}
