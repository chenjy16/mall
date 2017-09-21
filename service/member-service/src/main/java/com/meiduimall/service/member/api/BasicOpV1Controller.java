package com.meiduimall.service.member.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.model.request.RequestLogin;
import com.meiduimall.service.member.service.BasicOpService;
import com.meiduimall.service.member.util.HttpResolveUtils;

/**
 * 用户基本操作相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class BasicOpV1Controller {

	private final static Logger logger=LoggerFactory.getLogger(BasicOpV1Controller.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private BasicOpService basicOpService;
	
	/**旧会员系统获取token或创建token(临时接口)
	 * 因为APP可能没升级，还会请求旧会员系统的登录和注册，所以这两个接口的put token要走这个接口
	 * 其他的接口get token也是走这个接口，两种操作用type区分，1是get，2是put
	 * */
	@GetMapping(value = "/getput")
	String getput() { 
		String result=null;
		try {
			//从本地变量获取已解析过的json
			JSONObject j=HttpResolveUtils.readGetStringToJsonObject(request);
			logger.info("旧会员系统请求接口的JSON：{}"+j.toString());
			//处理请求
			Map<String, Object> map=basicOpService.getput(j);			
			result=JSON.toJSONString(map);
			logger.info("旧会员系统getput请求结果：{}"+result);
		} catch (Exception e) {
			
		}
		return result;
	    }
	
	/**
	 * 旧会员系统登出接口调用新会员系统的接口
	 * */
	@GetMapping(value = "/handlesignout")
	String handlesignout() { 
		String result=null;
		try {
			//从本地变量获取已解析过的json
			JSONObject j=HttpResolveUtils.readGetStringToJsonObject(request);
			logger.info("旧会员系统请求接口的JSON：{}"+j.toString());
			//处理请求
			Map<String, Object> map=basicOpService.handlesignout(j);
			result=JSON.toJSONString(map);
			logger.info("旧会员系统登出处理请求结果：{}"+result);
		} catch (Exception e) {
			
		}
		return result;
	    }
	
	/**用户登录*/
	@PostMapping(value = "/login")
	ResBodyData login(@RequestBody @Valid RequestLogin requestLogin){
		logger.info("收到用户登录API请求：",requestLogin.toString());
		response.setCharacterEncoding("UTF-8");
		try {
			requestLogin.setIp(request.getRemoteAddr());
			ResBodyData resBodyData = basicOpService.login(requestLogin);
			logger.info("用户登录API请求结果  ：{}",resBodyData.toString());
			return resBodyData;
		}
		catch (SystemException e) {
			logger.error("用户登录API请求异常：{}",e.toString());
		
		}
		return null;
	}
	
	
	/**用户注册*/
	@PostMapping(value = "/register")
	String register() { 
		//得到IP地址
		String result=null;
		try {
			//从本地变量获取已解析过的json
			JSONObject j=HttpResolveUtils.readGetStringToJsonObject(request);
			Map<String, Object> map=basicOpService.register(j);
			result=JSON.toJSONString(map);
		} catch (Exception e) {
			
		}
		return result;
	    }
	
	
	/**token校验*/
	@RequestMapping(value = "/baseop/checktoken",method=RequestMethod.POST)
	String checktoken() {	
		String result=null;
		try {
			JSONObject j=HttpResolveUtils.readStreamToJsonObject(request);
			//处理打开APP时检查token的逻辑
			Map<String, Object> map=basicOpService.checktoken(j);
			result=JSON.toJSONString(map);
			logger.info("用户{}请求结果:{}",j.getString("user_id"),result);
		} catch (Exception e) {
			
		}
		return result;
}
	
}
