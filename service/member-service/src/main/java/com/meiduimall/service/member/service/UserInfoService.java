package com.meiduimall.service.member.service;


import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;

/**
 * 会员信息操作接口
 * @author chencong
 *
 */
public interface UserInfoService {

	/**
	 * 获取
	 * @param memId
	 * @return
	 */
	 ResBodyData getBasicInfoByMemId(String memId);
	 
	 public JSONObject getMemberInfoByPhone(String phone) throws Exception;
	 
	 public JSONObject saveMemberBasicInfo(String token, String mem_sex, String mem_birthday, String mem_address_area, String mem_address, String mem_pic, String nick_name) throws Exception;
}
