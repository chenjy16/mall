package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfAgentWater;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;
import com.meiduimall.service.settlement.model.EcmMzfStoreRecord;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: AgentService.java
 * Author:   guidl
 * Date:     2017年3月24日 上午11:25:02
 * Description: 个代保证金分润、提现、流水相关
 */
public interface AgentService {
	
	
	/**
	 * 功能描述:  个代保证金分润  插入代理流水
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  agentWater 代理流水信息
	 * @return int
	 */
	public int insertAgentWater(EcmMzfAgentWater agentWater);
	
	
	/**
	 * 功能描述:  更新账户余额
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  account 账户信息
	 * @return int
	 */
	public int updateAccount(EcmMzfAccount account);
	
	
	/**
	 * 功能描述:  创建账户
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  account 账户信息
	 * @return int
	 */
	public int insertAccount(EcmMzfAccount account);
	

	/**
	 * 功能描述:  根据代理编号查询账户是否存在
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  code 代理编号
	 * @return EcmMzfAccount  
	 */
	public EcmMzfAccount findAccountByCode(String code);

	
	/**
	 * 功能描述:  个代保证金分润  插入总流水
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  water 总流水信息
	 * @return int
	 */
	public int insertWater(EcmMzfWater water);
	

	/**
	 * 功能描述:  更新积分成功时  修改积分状态为已更新
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02 
	 * @param  id 代理唯一标识id
	 * @param  code 代理编号
	 * @param  score 积分
	 * @return int
	 */
	public int updateScoreStatusByCode(int id, String code, int score);
	
	
	/**
	 * 功能描述:  根据代理编号获取代理流水
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  code 代理编号
	 * @return EcmMzfAgentWater
	 */
	public EcmMzfAgentWater findAgentWaterByCode(String code);
	
	
	/**
	 * 功能描述:  根据代理id获取代理流水列表
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  id 代理id
	 * @return EcmMzfAgentWater
	 */
	public List<EcmMzfAgentWater> findAgentWaterByAgentCode(int id);
	
	
	/**
	 * 功能描述:  获取积分未更新到会员系统的数据列表
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @return EcmMzfAgentWater
	 */
	public List<EcmMzfAgentWater> getAgentWaterScore();
	
	
	/**
	 * 功能描述:  插入商家送积分记录
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmMzfStoreRecord 商家送积分记录信息
	 * @return int
	 */
	public int insertStoreRecord(EcmMzfStoreRecord ecmMzfStoreRecord);
	
	
	/**
	 * 功能描述:  查询基本分润配置
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @return EcmSystemSetting 
	 */
	public Map<String, String> quertSharefit();
	
	
	/**
	 * 功能描述:  获取总流水列表
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02 
	 * @param  params 获取流水列表相关参数
	 * @return EcmMzfWater
	 */
	public List<EcmMzfWater> getWaterList(Map<String,Object> params);
	
	
	/**
	 * 功能描述:  获取流水总数
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  params 获取流水数量相关参数  
	 * @return int  
	 */
	public int getWaterCount(Map<String,Object> params);
	
	
	/**
	 * 功能描述:  根据流水编号、流水类型获取流水详情
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02 
	 * @param  waterId 流水id
	 * @param  waterType 流水类型
	 * @return EcmMzfWater
	 */
	public EcmMzfWater getWaterDetailByWaterId(String waterId, String waterType);
	

	/**
	 * 功能描述:  个代保证金分润  更新个代积分失败时  插入异常日志
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  shareProfitAgentLog 保证金分润异常日志
	 * @return int
	 */
	public int insertShareProfitAgentLog(ShareProfitAgentLog shareProfitAgentLog);
	
	
	/**
	 * 功能描述:  用于新个代送积分重试机制  根据代理编号修改异常日志为无需重试
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  agentNo 代理编号
	 * @return int  
	 */
	public int updateRetryFlag(String agentNo);
	

	/**
	 * 功能描述:  重试成功时 修改重试状态
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  agentNo 代理编号
	 * @return int
	 */
	public int updateStatusFlag(String agentNo);
	
	
	/**
	 * 功能描述:  新个代送积分重试机制  获取重新送积分的个代信息
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02 
	 * @param  currentTimestamp 时间戳
	 * @param  key 不同时间阶段标识
	 * @return ShareProfitAgentLog
	 */
	public List<ShareProfitAgentLog> getAgentsRetry(int currentTimestamp, String key);
	
	
	/**
	 * 功能描述:  根据推荐人编号和推荐单号获取推荐人的推荐费
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  params 获取推荐费条件参数  
	 * @return String 
	 */
	public String getRecommenderMoney(Map<String, Object> params);
	
	
	/**
	 * 功能描述:  获取分润结果 用于判断当前个代是否重新分润
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  id 主键id
	 * @param  recNo 推荐单号
	 * @return EcmMzfAgentWater
	 */
	public List<EcmMzfAgentWater> getShareProfitResult(int id, String recNo);
	
	
	/**
	 * 功能描述:  根据提现编号，获取提现流水详情
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  drawCode 提现编号
	 * @return EcmMzfDrawWater
	 */
	public EcmMzfDrawWater getDrawWaterInfo(String drawCode);
	
	
	/**
	 * 功能描述:  获取流水总数，用于生成流水编号
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  params(code、waterType、nowTime)
	 * @return int
	 */
	public int getCountCreateWaterId(Map<String,Object> params);
	
	
}
