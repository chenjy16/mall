package com.meiduimall.service.member.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 粉丝相关
 * @author chencong
 *
 */
public interface FunsService {

		
		public JSONObject queryFansDetailsList(String memId, String fansLv, int limit, int pageNum) throws Exception;
		
		public JSONObject queryFansNumber(String memId) throws Exception;
}
