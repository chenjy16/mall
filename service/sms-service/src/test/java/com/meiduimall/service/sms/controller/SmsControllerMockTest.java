package com.meiduimall.service.sms.controller;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "dev")
@Transactional
public class SmsControllerMockTest {

	public MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Rule
	public MockServerRule server1 = new MockServerRule(this, 9901);
	
	@Rule
	public MockServerRule server2 = new MockServerRule(this, 9902);
	
	private String aliSuccess = "{ \"success\":true }";
	@SuppressWarnings("unused")
	private String aliFail = "{ \"sub_code\":\"isv.BUSINESS_LIMIT_CONTROL\" }";
	
	private String mandaoSuccess = "<soap:Body><mdSmsSend_uResult>888888888888</mdSmsSend_uResult></soap:Body>";
	@SuppressWarnings("unused")
	private String mandaoFail = "<soap:Body><mdSmsSend_uResult>-2</mdSmsSend_uResult></soap:Body>";
	
	private final String phone = "188000000";
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		// 模拟阿里大于发送
		MockServerClient mockClient = new MockServerClient("localhost", 9901);
		
		mockClient.when(
		        request()
		            .withPath("/aliyun/test")
		                       
		    ).respond(
		        response()
		            .withStatusCode(200)
		            .withBody(aliSuccess)
		    );
		
		// 模拟漫道短信发送
		MockServerClient mockClient2 = new MockServerClient("localhost", 9902);
		mockClient2.when(
		        request()
		            .withPath("/mandao/test")
		    ).respond(
		        response()
		            .withStatusCode(200)
		            .withBody(mandaoSuccess)
		    );
	}

	// 阿里大于模板未注册，通过漫道发送短信
	@Test
	public void sendSmsMessage_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_common_sms_message")
				.param("phones", phone)
				.param("templateKey", "1GW_1001")
				.param("params", "188000000,DW123456789"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 发送普通短信，模板ID不存在
	@Test
	public void sendSmsMessage_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_common_sms_message")
				.param("phones", phone)
				.param("templateKey", "O2O_1111")
				.param("params", "DD201704271103111,666.66"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 阿里大于模板注册，通过阿里大于发送短信
	@Test
	public void sendSmsMessage_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_common_sms_message")
				.param("phones", phone)
				.param("templateKey", "1GW_1004")
				.param("params", "88.88,12元购物券"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 阿里大于模板未注册，通过漫道发送验证码
	@Test
	public void sendSmsVerificationCode_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_sms_verification_code")
				.param("phones", phone)
				.param("templateKey", "MEM_1002")
				.param("timeout", "1mn30s"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 阿里大于模板未注册，通过阿里大于发送验证码
	@Test
	public void sendSmsVerificationCode_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_sms_verification_code")
				.param("phones", phone)
				.param("templateKey", "O2O_1002")
				.param("timeout", "3mn"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 重复发送验证码
	@Test
	public void sendSmsVerificationCode_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_sms_verification_code")
				.param("phones", phone)
				.param("templateKey", "O2O_1002")
				.param("timeout", "3mn"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 校验验证码--验证码错误
	@Test
	public void checkSmsVerificationCode_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/check_sms_verification_code")
				.param("phones", phone)
				.param("templateKey", "O2O_1002")
				.param("verificationCode", "111222"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkSmsVerificationCode_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}

	// 校验验证码--验证码已过期
	@Test
	public void checkSmsVerificationCode_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/check_sms_verification_code")
				.param("phones", phone)
				.param("templateKey", "O2O_1111")
				.param("verificationCode", "111222"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkSmsVerificationCode_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 校验验证码--缺少请求参数
	@Test
	public void checkSmsVerificationCode_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/check_sms_verification_code")
				.param("phones", phone)
				.param("templateKey", "O2O_1002"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkSmsVerificationCode_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
}