/*package com.meiduimall.payment.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("dev")
public class AlipayControllerTest {
	

	
	public MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	 
	 
	 @Before
	 public void setup() {
	  // 构建空WebApplicationContext对象, 并构建HelloController对象
	  mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	 
	 
	

	 @Test
	 public void testAlipay() throws Exception {
	  //无法使用junit测试该该类接口内容,从junit中请求的接口无法走Interceptor,导致json类型参数无法获取.
	  String requestUrl = "/alipay/app";
	  String json = "{\"memId\":\"94898596\",\"payType\":1,\"orderInfo\":{\"body\":\"商品\",\"orderNo\":\"oed87878\",\"orderTime\":\"\",\"tradeNo\":\"458758gh9\",\"orderAmount\":\"\",\"payAmount\":\"700\",\"cashAmount\":\"\",\"integral\":\"\",\"merchantId\":\"\"},\"notifyUrl\":\"\"}";
	  //创建测试请求
	 
	  String responseString = mvc.perform(post("/alipay/app").contentType(MediaType.APPLICATION_JSON).content(json)).
              andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	  System.out.println(responseString);
	 
	  
	 
	
    }
	 
	 
	  public static void main(String[] args){
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  long a =new Date().getTime();
		  Date date = new Date(a);
		  simpleDateFormat.format(date);
		  int i=5;
		  
		  try{
			 throw new Exception();
		  }catch(Exception e){
			  System.out.println("2222222");
			  
		  }
		
		
	  }
}
*/