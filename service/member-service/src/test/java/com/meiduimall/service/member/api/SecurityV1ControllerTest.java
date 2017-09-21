package com.meiduimall.service.member.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.member.model.request.RequestSetPaypwdStatus;

/**
 * 账户安全
 * @author chencong
 *
 */
public class SecurityV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(SecurityV1ControllerTest.class);
	   
	   /**设置支付密码开关*/
	    @Test
	    public void setPaypwdStatus() throws Exception{
	    	RequestSetPaypwdStatus requestSetPaypwdStatus=new RequestSetPaypwdStatus();
	    	requestSetPaypwdStatus.setMemId(memId);
	    	requestSetPaypwdStatus.setEnable("0");
	    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/set_paypwd_status")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(requestSetPaypwdStatus)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(0)));
	    	
	    	postResultAction.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("单元测试>>设置支付密码开关API>>执行结果:{}",result.getResponse().getContentAsString());;
				}
			});
	    }
	    
	      
}