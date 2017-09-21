package com.meiduimall.service.member.service;


import org.springframework.dao.DataAccessException;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.model.MSMembersGet;


public interface SecurityService {
	
	/***
	 * 设置支付密码开关状态
	 * @param RequestSetPaypwdStatus
	 * @return 1：开   0：关
	 * @throws Exception
	 */
	ResBodyData setPaypwdStatus(MSMembersGet msMembers);
	
	/**
	 * 会员修改登录密码
	 * @param obj
	 * @return
	 * @throws DataAccessException
	 */
	public String updateLoginPwd(JSONObject obj)throws DataAccessException,Exception;
	
	
	/**
	 * * 外部接口校验原始登录密码
	 * 
	 * @param id
	 *            会员编号
	 * @param pwd
	 *            原始登录密码
	 * @return true原始登录密码存在 false原始登录密码不存在
	 */
	public boolean validateOldPwd_wai(String id, String pwd)throws Exception;
	
	/**
	 * 手机找回登录密码
	 * @param obj
	 * @return
	 * @throws DataAccessException
	 */
	public String updateLoginPwdByPhone(JSONObject obj)throws Exception;
	
	public JSONObject updateMemberPhone(String token, String newPhone, String code, String password) throws Exception;
}
