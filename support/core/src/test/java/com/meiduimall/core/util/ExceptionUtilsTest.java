/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.core.util;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hadoop on 2017/3/23.
 */
public class ExceptionUtilsTest {

  @Test
  public void testGetFullStackTrace() throws Exception {
    String msg = ExceptionUtils.getFullStackTrace(new RuntimeException("testGetFullStackTrace"));
    List<List> list=JsonUtils.jsonToList(msg,List.class);
    List e=list.get(0);
    Assert.assertEquals("testGetFullStackTrace",e.get(3));
  }

}