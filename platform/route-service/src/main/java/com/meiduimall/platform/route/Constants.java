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

public class Constants {


  /**
   * 秘钥配置
   **/
  public static final String APP_SECRET_JSON = "appSecretJson";
  /**
   * 黑名单
   **/
  public static final String BLACK_LIST_JSON = "blackListJson";
  /**
   * http contentType
   **/
  public static final String CONTENTTYPE_JSON = "application/json";
  /**
   * http contentType
   **/
  public static final String CONTENTTYPE_MULTIPART = "multipart/form-data";
  /**
   * http contentType
   **/
  public static final String CONTENTTYPE_FORM = "application/x-www-form-urlencoded";
  /**
   * zuul过滤器顺序
   **/
  public static final Integer FILTER_ORDER_ZERO = 0;

  private Constants(){}
}
