package com.meiduimall.service.member.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.model.request.RequestLogin;

/**
 * 用户基本操作，登录，注册，退出，检查token
 * @author chencong
 *
 */
public interface BasicOpService {

	/**给旧会员系统用的临时接口*/
	public Map<String, Object> getput(JSONObject jsonObject) throws Exception;
	public Map<String, Object> handlesignout(JSONObject jsonObject) throws Exception;
	
	public Map<String, Object> register(JSONObject jsonObject);
	public ResBodyData login(RequestLogin requestLogin) throws SystemException;
	public Map<String, Object> exit(JSONObject jsonObject);
	public Map<String, Object> checktoken(JSONObject jsonObject);
	public Map<String, Object> createValidateCode(JSONObject jsonObject) throws Exception;

}
