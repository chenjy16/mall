package com.meiduimall.service.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.service.ShareMenService;
import com.meiduimall.service.member.util.StringUtil;

/**
 * 推荐人和粉丝相关接口实现类
 * @author chencong
 *
 */
@Service
public class ShareMenServiceImpl implements ShareMenService{
	
	private final static Logger logger=LoggerFactory.getLogger(ShareMenServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;

	/**
	 * 查询二级推荐人
	 */
	@Override
	public JSONObject queryShareMan(String memId) throws Exception {
		JSONObject json = new JSONObject();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> param = new HashMap<String, String>();
		if (StringUtil.checkStr(memId)) {
			param.put("memId", "'" + memId + "'");
			MSMembersGet lv1 = baseDao.selectOne(param, "MSMembersMapper.getMemberBasicInfoByMemId");
			if (null != lv1 && StringUtil.checkStr(lv1.getMemId())) {
				Map<String, String> lv1ShareMember = new HashMap<String, String>();
				lv1ShareMember.put("level", "1");
				lv1ShareMember.put("login_name", StringUtil.checkStr(lv1.getMemLoginName()) == true ? lv1.getMemLoginName() : "");
				lv1ShareMember.put("mem_id", StringUtil.checkStr(lv1.getMemId()) == true ? lv1.getMemId() : "");
				lv1ShareMember.put("nick_name", StringUtil.checkStr(lv1.getMemNickName()) == true ? lv1.getMemNickName() : "");
				lv1ShareMember.put("mem_name", StringUtil.checkStr(lv1.getMemName()) == true ? lv1.getMemName() : "");
				lv1ShareMember.put("phone", StringUtil.checkStr(lv1.getMemPhone()) == true ? lv1.getMemPhone() : "");
				System.out.println(lv1.getMemLoginName());
				result.add(lv1ShareMember);
				logger.info("查询出一级推荐人");
				param.clear();
				param.put("memId", "'" + lv1.getMemParentId() + "'");
				MSMembersGet lv2 = baseDao.selectOne(param, "MSMembersMapper.getMemberInfoByCondition");
				if (null != lv2 && StringUtil.checkStr(lv2.getMemId())) {
					Map<String, String> lv2ShareMember = new HashMap<String, String>();
					lv2ShareMember.put("level", "2");
					lv2ShareMember.put("login_name", StringUtil.checkStr(lv2.getMemLoginName()) == true ? lv2.getMemLoginName() : "");
					lv2ShareMember.put("mem_id", StringUtil.checkStr(lv2.getMemId()) == true ? lv2.getMemId() : "");
					lv2ShareMember.put("nick_name", StringUtil.checkStr(lv2.getMemNickName()) == true ? lv2.getMemNickName() : "");
					lv2ShareMember.put("mem_name", StringUtil.checkStr(lv2.getMemName()) == true ? lv2.getMemName() : "");
					lv2ShareMember.put("phone", StringUtil.checkStr(lv2.getMemPhone()) == true ? lv2.getMemPhone() : "");
					result.add(lv2ShareMember);
					logger.info("查询出二级推荐人");
				}
				json.put(SysParamsConst.STATUS, "0");
				json.put(SysParamsConst.MSG, "获取推荐人成功!");
				json.put(SysParamsConst.DATA, result);
			} else {
				json.put(SysParamsConst.STATUS, "1020");
				json.put(SysParamsConst.MSG, "当前会员的推荐人不存在!");
				logger.info("当前会员ID:{}的推荐人不存在!", memId);
			}
		} else {
			json.put(SysParamsConst.STATUS, "1020");
			json.put(SysParamsConst.MSG, "当前会员不存在!");
			logger.info("当前会员ID:{}不存在!", memId);
		}
		return json;
	}

	
}