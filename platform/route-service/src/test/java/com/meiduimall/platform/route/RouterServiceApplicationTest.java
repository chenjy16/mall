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
import org.hamcrest.Matchers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meiduimall.platform.route.RouterServiceApplication;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by hadoop on 2017/3/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RouterServiceApplicationTest.class)
public class RouterServiceApplicationTest {

  private TestRestTemplate template = new TestRestTemplate();

  private int port=9050;

  @BeforeClass
  public static void setUp(){
    RouterServiceApplication.main(new String[]{""});
  }
  @AfterClass
  public static void setDown(){

  }

  @Test
  public void test1(){
    String url = "http://localhost:"+port+"/routes";
    String result = template.getForObject(url,  String.class);
    System.out.println(result);
    assertNotNull(result);
    assertThat(result, Matchers.containsString("{\"/mall/mall_user_service/v1/**\":\"mall-user-service\",\"/mall/mall_commodity_service/v1/**\":\"mall-commodity-service\",\"/mall/mall_shoppingcart_service/v1/**\":\"mall-shoppingcart-service\",\"/mall/mall_order_service/v1/**\":\"mall-order-service\",\"/bus/refresh/**\":\"service-config\",\"/member/member_service/v1/**\":\"member-service\",\"/member/account_service/v1/**\":\"account-service\",\"/notify/short_msg_service/**\":\"short_msg_service\",\"/push/push-service/v1/**\":\"service-push\"}"));
  }
}
