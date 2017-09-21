package com.meiduimall.service.settlement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfAgentWater;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;
import com.meiduimall.service.settlement.model.EcmMzfStoreRecord;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;
import com.meiduimall.service.settlement.service.AgentService;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: AgentServiceImpl.java
 * Author:   guidl
 * Date:     2017年3月24日 上午11:25:02
 * Description: 个代保证金分润、提现、流水相关
 */
@Service
public class AgentServiceImpl implements AgentService {
	
	@Autowired
	private BaseMapper baseMapper;

	
	@Override
	public int insertAgentWater(EcmMzfAgentWater agentWater){
		return baseMapper.insert(agentWater, "EcmMzfAgentWaterMapper.insertAgentWater");
	}

	
	/**
	 * 暂时用synchronized同步，后期再优化
	 */
	@Override
	public synchronized int updateAccount(EcmMzfAccount account) {
		return baseMapper.update(account, "EcmMzfAccountMapper.updateAccountByCode");
	}

	
	@Override
	public int insertWater(EcmMzfWater water) {
		return baseMapper.insert(water, "EcmMzfWaterMapper.insertWater");
	}
	
	
	@Override
	public int updateScoreStatusByCode(int id, String code, int score) {
		Map<String,Object> params = Maps.newHashMap();
		params.put("id", id);
		params.put("code", code);
		params.put("score", score);
		return baseMapper.update(params, "EcmMzfAgentWaterMapper.updateScoreStatusByCode");
	}
	
	
	@Override
	public int insertAccount(EcmMzfAccount account) {
		return baseMapper.insert(account, "EcmMzfAccountMapper.insertAccount");
	}
	
	
	@Override
	public EcmMzfAccount findAccountByCode(String code) {
		return baseMapper.selectOne(code, "EcmMzfAccountMapper.findAccountByCode");
	}
	
	
	@Override
	public EcmMzfAgentWater findAgentWaterByCode(String code) {
		return baseMapper.selectOne(code, "EcmMzfAgentWaterMapper.findAgentWaterByCode");
	}
	
	
	@Override
	public List<EcmMzfAgentWater> findAgentWaterByAgentCode(int id) {
		return baseMapper.selectList(id, "EcmMzfAgentWaterMapper.findAgentWaterByAgentCode");
	}
	
	
	@Override
	public List<EcmMzfAgentWater> getAgentWaterScore() {
		return baseMapper.selectList(null, "EcmMzfAgentWaterMapper.getAgentWaterScore");
	}

	
	@Override
	public int insertStoreRecord(EcmMzfStoreRecord ecmMzfStoreRecord) {
		return baseMapper.insert(ecmMzfStoreRecord, "EcmStoreMapper.insertStoreRecord");
	}
	
	
	@Override
	public Map<String, String> quertSharefit() {
		List<EcmSystemSetting> settingList = baseMapper.selectList(null, "ShareProfitMapper.quertSharefit");
		return ShareProfitUtil.queryShareProfit(settingList);
	}
	

	@Override
	public List<EcmMzfWater> getWaterList(Map<String, Object> params) {
		return baseMapper.selectList(params, "EcmMzfWaterMapper.getWaterList");
	}
	
	
	@Override
	public int getWaterCount(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getWaterCount");
	}

	
	@Override
	public EcmMzfWater getWaterDetailByWaterId(String waterId, String waterType) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("waterId", waterId);
		params.put("waterType", waterType);
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getWaterDetailByWaterId");
	}

	
	@Override
	public int insertShareProfitAgentLog(ShareProfitAgentLog shareProfitAgentLog) {
		return baseMapper.insert(shareProfitAgentLog, "ShareProfitAgentLogMapper.insertShareProfitAgentLog");
	}
	
	
	@Override
	public int updateRetryFlag(String agentNo) {
		return baseMapper.update(agentNo, "ShareProfitAgentLogMapper.updateRetryFlag");
	}
	
	
	@Override
	public int updateStatusFlag(String agentNo) {
		return baseMapper.update(agentNo, "ShareProfitAgentLogMapper.updateStatusFlag");
	}

	
	@Override
	public List<ShareProfitAgentLog> getAgentsRetry(int currentTimestamp, String key) {
		Map<String,Object> params = Maps.newHashMap();
		params.put("currentTimestamp", currentTimestamp);
		params.put("key", key);
		return baseMapper.selectList(params, "ShareProfitAgentLogMapper.getAgentsRetry");
	}

	
	@Override
	public String getRecommenderMoney(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getRecommenderMoney");
	}

	
	@Override
	public List<EcmMzfAgentWater> getShareProfitResult(int id, String recNo) {
		Map<String,Object> params = Maps.newHashMap();
		params.put("id", id);
		params.put("recNo", recNo);
		return baseMapper.selectList(params, "EcmMzfAgentWaterMapper.getShareProfitResult");
	}

	
	@Override
	public EcmMzfDrawWater getDrawWaterInfo(String drawCode) {
		return baseMapper.selectOne(drawCode, "EcmMzfWaterMapper.getDrawWaterInfo");
	}

	
	@Override
	public int getCountCreateWaterId(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getCountCreateWaterId");
	}
	
	
}
