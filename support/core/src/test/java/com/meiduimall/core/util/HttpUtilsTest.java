package com.meiduimall.core.util;
import static org.junit.Assert.assertEquals;


import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;

import com.google.common.collect.Maps;




 /**
 * Created by hadoop on 2017/3/24.
 */
public class HttpUtilsTest {
	

  @Rule
  public MockServerRule server = new MockServerRule(this, 5000);

  private String expected = "{ message: 'test ok' }";
  private String requestJson="{\"token\", \"12345\"}";

  @Before
  public void setUp(){
    MockServerClient mockClient = new MockServerClient("localhost", 5000);
    mockClient.when(
        request()
            .withPath("/util/test")
            .withMethod("POST")
            .withBody(requestJson)
     
    ).respond(
        response()
            .withStatusCode(200)
            .withBody(expected)
    );

    
    
    mockClient.when(
        request()
            .withPath("/util/test")
            .withMethod("GET")
            .withQueryStringParameter("token", "12345")
                       
    ).respond(
        response()
            .withStatusCode(200)
            .withBody(expected)
    );
    
    
    mockClient.when(
            request()
                .withPath("/util/test")
                .withMethod("PUT")
                .withBody(requestJson)
        ).respond(
            response()
                .withStatusCode(200)
                .withBody(expected)
        );
    
    
    mockClient.when(
            request()
                .withPath("/util/test")
                .withMethod("DELETE")
                .withQueryStringParameter("token", "12345")
        ).respond(
            response()
                .withStatusCode(200)
                .withBody(expected)
        );
    
    
    mockClient.when(
            request()
                .withPath("/util/test")
                .withMethod("POST")
                .withBody("token=12345")
         
        ).respond(
            response()
                .withStatusCode(200)
                .withBody(expected)
        );
    
  }

  @After
  public void setDown(){

  }
  
  
  @Test
  public void testGet() throws Exception {
    String result = HttpUtils.get("http://localhost:5000/util/test?token=12345","UTF-8");
    assertEquals(expected,result);
  }

  
  
  
  @Test
  public void testPost() throws Exception {
	Map<String,String> headers=Maps.newHashMap();
	headers.put(HttpHeaders.CONTENT_TYPE,"application/json");
    String result = HttpUtils.post("http://localhost:5000/util/test", requestJson, headers,"UTF-8","UTF-8");
    assertEquals(expected,result);
  }

  
  
  @Test
  public void testPut() throws Exception {
    Map<String, String> headers = Maps.newHashMap();
	headers.put(HttpHeaders.CONTENT_TYPE,"application/json");
    String result = HttpUtils.put("http://localhost:5000/util/test", requestJson, headers,"UTF-8","UTF-8");
    assertEquals(expected,result);
  }

  @Test
  public void testDelete() throws Exception {
	  String result = HttpUtils.delete("http://localhost:5000/util/test?token=12345","UTF-8");
	  assertEquals(expected,result);
  }
  
  @Test
  public void testForm() throws Exception {
	  Map<String,String> param=Maps.newHashMap();
	  param.put("token", "12345");
	  String result = HttpUtils.form("http://localhost:5000/util/test",param);
	  assertEquals(expected,result);
  }

}