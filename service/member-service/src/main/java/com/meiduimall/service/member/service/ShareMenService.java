package com.meiduimall.service.member.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 推荐人相关
 * @author chencong
 *
 */
public interface ShareMenService {

		public JSONObject queryShareMan(String memId) throws Exception;
}
