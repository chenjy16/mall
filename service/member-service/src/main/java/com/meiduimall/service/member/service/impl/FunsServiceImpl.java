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
import com.meiduimall.service.member.service.FunsService;
import com.meiduimall.service.member.util.DateUtil;
import com.meiduimall.service.member.util.StringUtil;

/**
 * 推荐人和粉丝相关接口实现类
 * @author chencong
 *
 */
@Service
public class FunsServiceImpl implements FunsService{
	
	private final static Logger logger=LoggerFactory.getLogger(FunsServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	
	/**
	 * 查询粉丝明细
	 */
	@Override
	public JSONObject queryFansDetailsList(String memId, String fansLv, int limit, int pageNum) throws Exception {
		JSONObject json = new JSONObject();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> param = new HashMap<String, String>();
		if (limit > 0 && pageNum > 0 && StringUtil.checkStr(fansLv) && ("1".equals(fansLv) || "2".equals(fansLv))) {
			int startIndex = (limit * pageNum) - limit;
			int endIndex = limit * pageNum;
			if (StringUtil.checkStr(memId)) {
				param.put("memId", "'" + memId + "'");
				MSMembersGet member = baseDao.selectOne(param, "MSMembersMapper.getMemberBasicInfoByMemId");
				if (null != member && StringUtil.checkStr(member.getMemParentValue3())) {
					String lv1Ids = "";
					String[] lv1FansId = member.getMemParentValue3().split("[,]");
					for (int i = 0; i < lv1FansId.length; i++) {
						lv1Ids += "'" + lv1FansId[i] + "'";
						if (i != (lv1FansId.length - 1))
							lv1Ids += ",";
					}
					if ("1".equals(fansLv)) {
						param.clear();
						param.put("memId", lv1Ids);
						param.put("limit", "LIMIT " + startIndex + ", " + endIndex);
						List<MSMembersGet> lv1FansInfo = baseDao.selectList(param, "MSMembersMapper.getMemberBasicInfoByMemId");
						Map<String, String> lv1MemberInfo = new HashMap<String, String>();
						for (MSMembersGet memberInfo : lv1FansInfo) {
							lv1MemberInfo.put("memCreatedDate", StringUtil.checkObj(memberInfo.getMemCreatedDate()) == true ? DateUtil.getDateFormat().format(memberInfo.getMemCreatedDate()) : "");
							lv1MemberInfo.put("memLoginName", StringUtil.checkStr(memberInfo.getMemLoginName()) == true ? memberInfo.getMemLoginName() : "");
							lv1MemberInfo.put("memNickName", StringUtil.checkStr(memberInfo.getMemNickName()) == true ? memberInfo.getMemNickName() : "");
							lv1MemberInfo.put("memPhone", StringUtil.checkStr(memberInfo.getMemPhone()) == true ? memberInfo.getMemPhone() : "");
							lv1MemberInfo.put("memPic", StringUtil.checkStr(memberInfo.getMemPic()) == true ? memberInfo.getMemPic() : "");
							result.add(lv1MemberInfo);
						}
						json.put(SysParamsConst.STATUS, "0");
						json.put(SysParamsConst.MSG, "获取粉丝成功!");
						json.put("totalPages", "" + (lv1FansInfo.size() % limit == 0 ? lv1FansInfo.size() / limit : lv1FansInfo.size() / limit + 1));
						json.put(SysParamsConst.DATA, result);
						logger.info("获取1级粉丝成功!");
					} else if ("2".equals(fansLv)) {
						param.clear();
						param.put("memId", lv1Ids);
						String lv2Ids = "";
						List<MSMembersGet> lv1FansInfo = baseDao.selectList(param, "MSMembersMapper.getMemberBasicInfoByMemId");
						for (MSMembersGet memberInfo : lv1FansInfo) {
							if (StringUtil.checkStr(memberInfo.getMemParentValue3())) {
								String[] lv2FansId = memberInfo.getMemParentValue3().split("[,]");
								for (int i = 0; i < lv2FansId.length; i++) {
									lv2Ids += "'" + lv2FansId[i] + "'";
									if (i != (lv2FansId.length - 1))
										lv2Ids += ",";
								}
							}
						}
						param.clear();
						if (StringUtil.checkStr(lv2Ids)) {
							param.put("memId", lv2Ids);
							param.put("limit", "LIMIT " + startIndex + ", " + endIndex);
							List<MSMembersGet> lv2FansInfo = baseDao.selectList(param, "MSMembersMapper.getMemberBasicInfoByMemId");
							Map<String, String> lv2MemberInfo = new HashMap<String, String>();
							for (MSMembersGet memberInfo : lv2FansInfo) {
								lv2MemberInfo.put("memCreatedDate", StringUtil.checkObj(memberInfo.getMemCreatedDate()) == true ? DateUtil.getDateFormat().format(memberInfo.getMemCreatedDate()) : "");
								lv2MemberInfo.put("memLoginName", StringUtil.checkStr(memberInfo.getMemLoginName()) == true ? memberInfo.getMemLoginName() : "");
								lv2MemberInfo.put("memNickName", StringUtil.checkStr(memberInfo.getMemNickName()) == true ? memberInfo.getMemNickName() : "");
								lv2MemberInfo.put("memPhone", StringUtil.checkStr(memberInfo.getMemPhone()) == true ? memberInfo.getMemPhone() : "");
								lv2MemberInfo.put("memPic", StringUtil.checkStr(memberInfo.getMemPic()) == true ? memberInfo.getMemPic() : "");
								result.add(lv2MemberInfo);
							}
							json.put(SysParamsConst.STATUS, "0");
							json.put(SysParamsConst.MSG, "获取粉丝成功!");
							json.put("totalPages", "" + (lv2MemberInfo.size() % limit == 0 ? lv2MemberInfo.size() / limit : lv2MemberInfo.size() / limit + 1));
							json.put(SysParamsConst.DATA, result);
							logger.info("获取2级粉丝成功!");
						} else {
							json.put(SysParamsConst.STATUS, "0");
							json.put(SysParamsConst.MSG, "当前会员不存在" + fansLv + "级粉丝!");
							logger.info("当前会员ID:%s不存在" + fansLv + "级粉丝!", memId);
						}
					} else {
						json.put(SysParamsConst.STATUS, "9998");
						json.put(SysParamsConst.MSG, "粉丝级别输入有误!仅支持2级!粉丝级别:" + fansLv);
						logger.info("粉丝级别输入有误!fansLv=%s" + fansLv);
					}
					
				} else {
					json.put(SysParamsConst.STATUS, "0");
					json.put(SysParamsConst.MSG, "当前会员不存在" + fansLv + "级粉丝!");
					logger.info("当前会员ID:%s不存在" + fansLv + "级粉丝!", memId);
				}
			} else {
				json.put(SysParamsConst.STATUS, "1020");
				json.put(SysParamsConst.MSG, "当前会员不存在!");
				logger.info("当前会员ID:%s不存在!", memId);
			}
		} else {
			json.put(SysParamsConst.STATUS, "9998");
			json.put(SysParamsConst.MSG, "参数有误!");
			logger.info("参数有误!limit:" + limit + ";pageNum:" + pageNum + "; fansLv:" + fansLv);
		}
		return json;
	}

	/**
	 * 按照级别分别获取粉丝数量
	 */
	@Override
	public JSONObject queryFansNumber(String memId) throws Exception {
		JSONObject json = new JSONObject();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String> fansNumber = new HashMap<String, String>();
		if (StringUtil.checkStr(memId)) {
			param.put("memId", "'" + memId + "'");
			MSMembersGet member = baseDao.selectOne(param, "MSMembersMapper.getMemberBasicInfoByMemId");
			if (null != member && StringUtil.checkStr(member.getMemParentValue3())) {
				String lv1Ids = "";
				String[] lv1FansId = member.getMemParentValue3().split("[,]");
				for (int i = 0; i < lv1FansId.length; i++) {
					lv1Ids += "'" + lv1FansId[i] + "'";
					if (i != (lv1FansId.length - 1))
						lv1Ids += ",";
				}
				param.clear();
				param.put("memId", lv1Ids);
				int lv2FansNum = 0;
				List<MSMembersGet> lv1FansInfo = baseDao.selectList(param, "MSMembersMapper.getMemberBasicInfoByMemId");
				for (MSMembersGet memberInfo : lv1FansInfo) {
					if (StringUtil.checkStr(memberInfo.getMemParentValue3())) {
						String[] lv2FansId = memberInfo.getMemParentValue3().split("[,]");
						lv2FansNum += lv2FansId.length;
					}
				}
				json.put(SysParamsConst.STATUS, "0");
				json.put(SysParamsConst.MSG, "获取粉丝数量成功!");
				fansNumber.put("level_1_count", "" + lv1FansId.length);
				fansNumber.put("level_2_count", "" + lv2FansNum);
				json.put(SysParamsConst.DATA, fansNumber);
				logger.info("一级粉丝数量为:" + lv1FansId.length + ";二级粉丝数量为:" + lv2FansNum);
			} else {
				json.put(SysParamsConst.STATUS, "0");
				json.put(SysParamsConst.MSG, "获取粉丝数量成功!");
				fansNumber.put("level_1_count", "0");
				fansNumber.put("level_2_count", "0");
				json.put(SysParamsConst.DATA, fansNumber);
				logger.info("一级粉丝数量为:0;二级粉丝数量为:0");
			}
		} else {
			json.put(SysParamsConst.STATUS, "1020");
			json.put(SysParamsConst.MSG, "当前会员不存在!");
			logger.info("当前会员ID:%s不存在!", memId);
		}
		return json;
	}
}