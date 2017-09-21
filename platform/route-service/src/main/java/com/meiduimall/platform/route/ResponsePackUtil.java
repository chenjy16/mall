/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.platform.route;


import com.meiduimall.core.BaseApiCode;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.netflix.zuul.context.RequestContext;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpStatus;

public class ResponsePackUtil {

  private ResponsePackUtil(){}

  /**
   * 功能描述:  封装网关层返回信息
   * Author: 陈建宇
   * Date:   2016年12月19日 上午10:11:07
   * param ctx
   * param responseCode
   */
  public static void responseWrapper(RequestContext ctx, Integer responseCode) {
    ctx.setSendZuulResponse(false);
    ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
    ResBodyData res = new ResBodyData(responseCode, BaseApiCode.getZhMsg(responseCode));
    ctx.setResponseBody(JsonUtils.beanToJson(res));
  }

}

