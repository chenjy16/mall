package com.meiduimall.core.util;
import static org.junit.Assert.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import com.google.common.collect.Maps;
import com.meiduimall.core.GateWayBuilder;
import com.meiduimall.core.GateWayComponent;
import com.meiduimall.core.GateWayRequest;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.password.exception.Md5Exception;

public class GateWayComponentTest {
	

	@Rule
	public MockServerRule server = new MockServerRule(this, 5000);
	private String expected="{\"status\":0,\"msg\":\"test ok\",\"data\":\"{}\"}";
	private String requestJson="{\"clientID\":\"member_java\"}";
	private GateWayComponent component=GateWayBuilder.newBuilder(new GateWayRequest("member_java", "Test123")).build();
	private StringBuffer url=new StringBuffer("http://localhost:5000/util/test");
	
	
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
	            .withQueryStringParameter("clientId", "member_java")
	                       
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
	                .withQueryStringParameter("clientId", "member_java")
	        ).respond(
	            response()
	                .withStatusCode(200)
	                .withBody(expected)
	        );
	    
	    
	    mockClient.when(
	            request()
	                .withPath("/util/test")
	                .withMethod("POST")
	                .withBody("clientID=member_java")
	         
	        ).respond(
	            response()
	                .withStatusCode(200)
	                .withBody(expected)
	        );
	    
	  }
	
	/**
	 * 功能描述:  测试get方式请求
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午11:48:59 
	 * param   @throws ClientProtocolException
	 * param   @throws IOException   
	 * return  void
	 * @throws Md5Exception 
	 */
	@Test
	public void testDoGet() throws ClientProtocolException, IOException, Md5Exception{
		TreeMap<String, String> treeMap=new TreeMap<>();
		component.buildConfigParams(treeMap).buildSignParams(treeMap);
		
		url.append("?clientID=").append(treeMap.get("clientID"));
		
		ResBodyData res=component.doGet(url.toString());
		assertEquals("0",res.getStatus().toString());
	}
	
	
	
	/**
	 * 功能描述:  测试delete方式请求
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午11:49:18 
	 * param   @throws ClientProtocolException
	 * param   @throws IOException   
	 * return  void
	 * @throws Md5Exception 
	 */
	@Test
	public void testDoDelete() throws ClientProtocolException, IOException, Md5Exception{
		TreeMap<String, String> treeMap=new TreeMap<>();
		component.buildConfigParams(treeMap).buildSignParams(treeMap);
		
		url.append("?clientID=").append(treeMap.get("clientID"));
		
		ResBodyData res=component.doDelete(url.toString());
		assertEquals("0",res.getStatus().toString());
	}
	
	
	/**
	 * 功能描述:  测试post json方式请求
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午11:50:04 
	 * param   @throws ClientProtocolException
	 * param   @throws IOException   
	 * return  void
	 * @throws Md5Exception 
	 */
	@Test
	public void testDoPost() throws ClientProtocolException, IOException, Md5Exception{
		TreeMap<String, String> treeMap=new TreeMap<>();
		
		Map<String,String> headers=Maps.newHashMap();
		headers.put(HttpHeaders.CONTENT_TYPE,"application/json");
		component.buildConfigParams(treeMap).buildSignParams(treeMap);
		
		RequestJson requestJson=new RequestJson();
		requestJson.setClientID(treeMap.get("clientID"));
		
		ResBodyData res=component.doPost("http://localhost:5000/util/test", headers, JsonUtils.beanToJson(requestJson));
		assertEquals("0",res.getStatus().toString());
	}
	
	
	
	/**
	 * 功能描述: 测试put json方式请求
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午11:50:23 
	 * param   @throws ClientProtocolException
	 * param   @throws IOException   
	 * return  void
	 * @throws Md5Exception 
	 */
	@Test
	public void testDoPut() throws ClientProtocolException, IOException, Md5Exception{
		TreeMap<String, String> treeMap=new TreeMap<>();
		
		Map<String,String> headers=Maps.newHashMap();
		headers.put(HttpHeaders.CONTENT_TYPE,"application/json");
		component.buildConfigParams(treeMap).buildSignParams(treeMap);
		
		RequestJson requestJson=new RequestJson();
		requestJson.setClientID(treeMap.get("clientID"));
		
		ResBodyData res=component.doPut("http://localhost:5000/util/test", headers, JsonUtils.beanToJson(requestJson));
		assertEquals("0",res.getStatus().toString());
	}
	

	/**
	 * 功能描述:  测试表单方式请求
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午11:50:38 
	 * param   @throws Exception   
	 * return  void
	 */
	  @Test
	  public void testForm() throws Exception {
		  TreeMap<String, String> treeMap=new TreeMap<>();
		  Map<String,String> param=Maps.newHashMap();
		  component.buildConfigParams(treeMap).buildSignParams(treeMap);
		  param.put("clientID", treeMap.get("clientID"));	  
		  ResBodyData res=component.form("http://localhost:5000/util/test", param);
		  assertEquals("0",res.getStatus().toString());
	  }
	  
	  
	  
	  
	public static class RequestJson{
		
		private String clientID;

		public String getClientID() {
			return clientID;
		}

		public void setClientID(String clientID) {
			this.clientID = clientID;
		}
		
	}
	

}
