package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.SettlementApiCode;
import com.meiduimall.service.settlement.common.CodeRuleUtil;
import com.meiduimall.service.settlement.common.DrawCashConstants;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfDraw;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.DrawService;
import com.meiduimall.service.settlement.util.DateUtil;

@Service
public class DrawServiceImpl implements DrawService {
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private AgentService agentService;

	private static final Logger log = LoggerFactory.getLogger(DrawServiceImpl.class);
	
	@Override
	public Map<String, Object> queryAccoutBalance(String code) {
		return baseMapper.selectOne(code, "EcmMzfAccountMapper.queryaccoutbalance");
	}

	
	@Override
	public List<EcmMzfDraw> queryDrawCash(Map<String, Object> params) {
		return baseMapper.selectList(params, "EcmMzfDrawMapper.querydrawcash");
	}
	
	
	@Override
	public int getDrawCount(Map<String,Object> params) {
		return baseMapper.selectOne(params, "EcmMzfDrawMapper.getDrawCount");
	}
	

	@Override
	public EcmMzfDraw queryDrawCashById(String drawCode)  {
		return baseMapper.selectOne(drawCode, "EcmMzfDrawMapper.querydrawcashbyid");
	}

	
	@Override
	public Map<String, Object> verifyDrawCashById(EcmMzfDraw ecmmzfdraw) {
		Map<String, Object> hashMap = Maps.newHashMap();
		Integer update = baseMapper.update(ecmmzfdraw, "EcmMzfDrawMapper.verifydrawcashbyid");
		if (update > 0) {
			hashMap.put("drawCode", ecmmzfdraw.getDrawCode());
			hashMap.put("status", DrawCashConstants.STATUS_VERIFIED_SUCDESS);
		} else {
			log.error("审核提现申请异常：提现编号{}", ecmmzfdraw.getDrawCode());
			throw new ServiceException(SettlementApiCode.VERIFY_DRAWCASH_FAILURE, BaseApiCode.getZhMsg(SettlementApiCode.VERIFY_DRAWCASH_FAILURE));
		}
		return hashMap;
	}

	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public Map<String, Object> rejectDrawCashById(EcmMzfDraw ecmmzfdraw) {
		
		//组装参数
		Map<String, String> result = builderParams(ecmmzfdraw);
		EcmMzfDrawWater ecmMzfDrawWater = JsonUtils.jsonToBean(result.get("ecmMzfDrawWater"), EcmMzfDrawWater.class);
		EcmMzfDraw ecmMzfDraw = JsonUtils.jsonToBean(result.get("ecmMzfDraw"), EcmMzfDraw.class);
		EcmMzfWater ecmMzfWater = JsonUtils.jsonToBean(result.get("ecmMzfWater"), EcmMzfWater.class);
		
		//修改提现记录状态
		int drawUpdated = baseMapper.update(ecmmzfdraw, "EcmMzfDrawMapper.rejectdrawcashbyid");
		
		//插入  提现失败流水
		int flak = insertDrawWater(ecmMzfDrawWater);
		
		//插入  提现失败总流水
		int flaz = agentService.insertWater(ecmMzfWater);
		
		//更新账户余额   总金额 + 提现金额(提现失败退回提现金额)
		EcmMzfAccount account = new EcmMzfAccount();
		account.setCode(ecmMzfDraw.getCode());
		account.setBalance(ecmMzfDraw.getTotalMoney());
		int updateBalance = agentService.updateAccount(account);
		
		Map<String, Object> hashMap = Maps.newHashMap();
		if (drawUpdated > 0 && flak > 0 && flaz > 0 && updateBalance > 0) {
			hashMap.put("drawCode", ecmmzfdraw.getDrawCode());
			hashMap.put("status", DrawCashConstants.STATUS_VERIFIED_REJECTED);
		} else {
			log.error("驳回提现申请异常：提现编号{}", ecmmzfdraw.getDrawCode());
			throw new ServiceException(SettlementApiCode.REJECT_DRAWCASH_FAILURE, BaseApiCode.getZhMsg(SettlementApiCode.REJECT_DRAWCASH_FAILURE));
		}
		return hashMap;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public Map<String, Object> confirmDrawCashByIdByType(EcmMzfDraw ecmmzfdraw) {
		
		Map<String, Object> hashMap = Maps.newHashMap();
		
		//提现转账成功或失败都需要更新提现申请记录状态
		int drawCfm = baseMapper.update(ecmmzfdraw, "EcmMzfDrawMapper.confirmdrawcashbyidbytype");
		
		//转账成功    需要更新提现流水状态
		if (ecmmzfdraw.getStatus().equals(DrawCashConstants.STATUS_TRANSFER_SUCCESS)
				&& ecmmzfdraw.getFinanceStatus().equals(DrawCashConstants.STATUS_TRANSFER_SUCCESS)) {
			
			int drawWaterStatus = DrawCashConstants.STATUS_TRANSFER_SUCCESS == ecmmzfdraw.getStatus()
					? DrawCashConstants.STATUS_TRANSFER_SUCCESS : DrawCashConstants.STATUS_VERIFIED_SUCDESS;
			
			EcmMzfDrawWater drawWater = new EcmMzfDrawWater(ecmmzfdraw.getDrawCode(), String.valueOf(drawWaterStatus));
			
			int waterCrm = baseMapper.update(drawWater, "EcmMzfDrawMapper.updDrawWaterStatusById");
			
			if(drawCfm > 0 && waterCrm > 0){
				hashMap.put("drawCode", ecmmzfdraw.getDrawCode());
				hashMap.put("status", ecmmzfdraw.getStatus());
			}
			
		}else if(ecmmzfdraw.getStatus().equals(DrawCashConstants.STATUS_TRANSFER_FAIL)//转账失败 生成新的提现流水和提现总流水
				&& ecmmzfdraw.getFinanceStatus().equals(DrawCashConstants.STATUS_TRANSFER_FAIL)){
			//组装参数
			Map<String, String> result = builderParams(ecmmzfdraw);
			EcmMzfDrawWater ecmMzfDrawWater = JsonUtils.jsonToBean(result.get("ecmMzfDrawWater"), EcmMzfDrawWater.class);
			EcmMzfDraw ecmMzfDraw = JsonUtils.jsonToBean(result.get("ecmMzfDraw"), EcmMzfDraw.class);
			EcmMzfWater ecmMzfWater = JsonUtils.jsonToBean(result.get("ecmMzfWater"), EcmMzfWater.class);
			
			//插入  提现失败流水
			int flak = insertDrawWater(ecmMzfDrawWater);
			
			//插入  提现失败总流水
			int flaz = agentService.insertWater(ecmMzfWater);
			
			//更新账户余额   总金额 + 提现金额(提现失败退回提现金额)
			EcmMzfAccount account = new EcmMzfAccount();
			account.setCode(ecmMzfDraw.getCode());
			account.setBalance(ecmMzfDraw.getTotalMoney());
			int updateBalance = agentService.updateAccount(account);
			
			if (drawCfm > 0 && flak > 0 && flaz > 0 && updateBalance > 0) {
				hashMap.put("drawCode", ecmmzfdraw.getDrawCode());
				hashMap.put("status", ecmmzfdraw.getStatus());
			} else {
				log.error("确认转账成功或失败操作异常：提现编号{}", ecmmzfdraw.getDrawCode());
				throw new ServiceException(SettlementApiCode.CONFIRM_DRAWCASH_FAILURE, BaseApiCode.getZhMsg(SettlementApiCode.CONFIRM_DRAWCASH_FAILURE));
			}
		}
		
		return hashMap;
	}
	
	
	private Map<String,String> builderParams(EcmMzfDraw ecmmzfdraw){
		
		//根据提现编号获取提现信息，目的是将查询出来的数据重新生成提现流水和总流水记录
		EcmMzfDraw ecmMzfDraw = queryDrawCashById(ecmmzfdraw.getDrawCode());

		//查询当天是否有提现记录，此段代码目的是生成提现编号的后两位 总数+1
		Map<String, Object> params = Maps.newHashMap();
		params.put("code", ecmMzfDraw.getCode());
		params.put("waterType", ShareProfitConstants.WATER_TYPE_DRAW_CASH);
		params.put("nowTime", DateUtil.formatDate(new Date()));
		int drawCount = getDrawWaterCount(params);
		String drawCountStr = String.valueOf(drawCount);
		//生成流水编号
		String waterId = "";
		//重新生成提现编号
		String drawCode = "";
		int waterCount = agentService.getCountCreateWaterId(params);
		String waterCountStr = String.valueOf(waterCount);
		//根据不同的角色类型生成流水编号
		if(ecmMzfDraw.getDrawType().equals(ShareProfitConstants.ROLE_TYPE_PERSONAL_AGENT)){//提现角色类型为个代
			waterId = CodeRuleUtil.getGLWaterId(ecmMzfDraw.getCode(), waterCountStr);
			drawCode = CodeRuleUtil.getGZDrawCode(ecmMzfDraw.getCode(), drawCountStr);
		}else if(ecmMzfDraw.getDrawType().equals(ShareProfitConstants.ROLE_TYPE_AREA_AGENT)){//提现角色类型为区代
			waterId = CodeRuleUtil.getQLWaterId(ecmMzfDraw.getCode(), waterCountStr);
			drawCode = CodeRuleUtil.getQZDrawCode(ecmMzfDraw.getCode(), drawCountStr);
		}else if(ecmMzfDraw.getDrawType().equals(ShareProfitConstants.ROLE_TYPE_STORE)){//提现角色类型为商家
			waterId = CodeRuleUtil.getSLWaterId(ecmMzfDraw.getCode(), waterCountStr);
			drawCode = CodeRuleUtil.getSTDrawCode(ecmMzfDraw.getCode(), drawCountStr);
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		//提现流水
		EcmMzfDrawWater ecmMzfDrawWater = new EcmMzfDrawWater();
		ecmMzfDrawWater.setDrawCode(drawCode);
		ecmMzfDrawWater.setCode(ecmMzfDraw.getCode());
		ecmMzfDrawWater.setStatus(String.valueOf(ecmMzfDraw.getStatus()));
		ecmMzfDrawWater.setMoney(ecmMzfDraw.getTotalMoney());
		ecmMzfDrawWater.setRemark(""+ecmMzfDraw.getCashWithdrawalFee());
		ecmMzfDrawWater.setDrawTime(timestamp);
		
		//提现总流水
		EcmMzfWater ecmMzfWater = new EcmMzfWater();
		ecmMzfWater.setWaterId(waterId);
		ecmMzfWater.setCode(ecmMzfDraw.getCode());
		ecmMzfWater.setMoney(ecmMzfDraw.getTotalMoney());
		ecmMzfWater.setWaterType(ShareProfitConstants.WATER_TYPE_DRAW_CASH);//流水类型为：1-提现
		ecmMzfWater.setExtId(drawCode);
		ecmMzfWater.setOpTime(timestamp);
		
		//根据code查询账户余额(2017-04-01)
		Map<String, Object> accountMap = queryAccoutBalance(ecmMzfDraw.getCode());
		if(accountMap.get("balance") != null && !"".equals(accountMap.get("balance"))){
			BigDecimal balance = new BigDecimal(accountMap.get("balance").toString());
			ecmMzfWater.setBalance(balance);
		}
				
		String remark = "提现失败";
		if(ecmMzfDraw.getCashWithdrawalFee().compareTo(BigDecimal.ZERO) > 0){
			remark = remark + "(退" + ecmMzfDraw.getCashWithdrawalFee() + "元手续费)";
		}
		ecmMzfWater.setRemark(remark);
		
		Map<String,String> result = Maps.newHashMap();
		result.put("ecmMzfDrawWater", JsonUtils.beanToJson(ecmMzfDrawWater));
		result.put("ecmMzfWater", JsonUtils.beanToJson(ecmMzfWater));
		result.put("ecmMzfDraw", JsonUtils.beanToJson(ecmMzfDraw));
		
		return result;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public boolean insertDrawInfo(EcmMzfDraw ecmMzfDraw) {
		
		//不管是存时间戳还是日期，必须保持一致
		int time = DateUtil.getCurrentTimeSec();
		Timestamp timestamp = Timestamp.valueOf(DateUtil.timeStamp2Date(String.valueOf(time), DateUtil.YYYY_MM_DD_HH_MM_SS));
		ecmMzfDraw.setAddTime(time);
		ecmMzfDraw.setDrawTime(time);
		
		BigDecimal init = new BigDecimal("0");
		
		Map<String, Object> params = Maps.newHashMap();
		params.put("code", ecmMzfDraw.getCode());
		params.put("waterType", ShareProfitConstants.WATER_TYPE_DRAW_CASH);
		params.put("nowTime", DateUtil.formatDate(new Date()));
		
		int waterCount = agentService.getCountCreateWaterId(params);
		int drawWaterCount = getDrawWaterCount(params);
		
		String drawWaterCountStr = String.valueOf(drawWaterCount);
		String waterCountStr = String.valueOf(waterCount);
		
		//根据不同的角色类型生成流水编号
		String waterId = "";
		String drawCode = "";
		
		//根据不同的角色类型生成流水编号
		if(ecmMzfDraw.getDrawType().equals(ShareProfitConstants.ROLE_TYPE_PERSONAL_AGENT)){//提现角色类型为个代
			waterId = CodeRuleUtil.getGLWaterId(ecmMzfDraw.getCode(), waterCountStr);
			drawCode = CodeRuleUtil.getGZDrawCode(ecmMzfDraw.getCode(), drawWaterCountStr);
		}else if(ecmMzfDraw.getDrawType().equals(ShareProfitConstants.ROLE_TYPE_AREA_AGENT)){//提现角色类型为区代
			waterId = CodeRuleUtil.getQLWaterId(ecmMzfDraw.getCode(), waterCountStr);
			drawCode = CodeRuleUtil.getQZDrawCode(ecmMzfDraw.getCode(), drawWaterCountStr);
		}else if(ecmMzfDraw.getDrawType().equals(ShareProfitConstants.ROLE_TYPE_STORE)){//提现角色类型为商家
			waterId = CodeRuleUtil.getSLWaterId(ecmMzfDraw.getCode(), waterCountStr);
			drawCode = CodeRuleUtil.getSTDrawCode(ecmMzfDraw.getCode(), drawWaterCountStr);
		}
		
		ecmMzfDraw.setDrawCode(drawCode);
		
		EcmMzfDrawWater ecmMzfDrawWater = new EcmMzfDrawWater();
		ecmMzfDrawWater.setDrawCode(ecmMzfDraw.getDrawCode());
		ecmMzfDrawWater.setCode(ecmMzfDraw.getCode());
		ecmMzfDrawWater.setStatus(String.valueOf(ecmMzfDraw.getStatus()));
		ecmMzfDrawWater.setMoney(init.subtract(ecmMzfDraw.getTotalMoney()));
		ecmMzfDrawWater.setRemark(""+ecmMzfDraw.getCashWithdrawalFee());
		ecmMzfDrawWater.setDrawTime(timestamp);
		
		EcmMzfWater ecmMzfWater = new EcmMzfWater();
		ecmMzfWater.setWaterId(waterId);
		ecmMzfWater.setCode(ecmMzfDraw.getCode());
		ecmMzfWater.setMoney(init.subtract(ecmMzfDraw.getTotalMoney()));
		ecmMzfWater.setRemark(ecmMzfDraw.getRemark());
		ecmMzfWater.setWaterType(ShareProfitConstants.WATER_TYPE_DRAW_CASH);//流水类型为：1-提现
		ecmMzfWater.setExtId(ecmMzfDraw.getDrawCode());
		ecmMzfWater.setOpTime(timestamp);
		ecmMzfWater.setBalance(ecmMzfDraw.getBalance());//账号可提现金额(2017-04-01)
		
		//插入  提现申请
		int flag = insertDraw(ecmMzfDraw);
		
		//插入  初始化提现流水
		int flak = insertDrawWater(ecmMzfDrawWater);
		
		//插入  初始化总流水
		int flaz = agentService.insertWater(ecmMzfWater);
		
		//更新账户余额   总金额 - 提现金额
		EcmMzfAccount account = new EcmMzfAccount();
		account.setCode(ecmMzfDraw.getCode());
		account.setBalance(init.subtract(ecmMzfDraw.getTotalMoney()));
		int updateBalance = agentService.updateAccount(account);
		
		if(flag > 0 && flak > 0 && flaz > 0 && updateBalance > 0){
			return true;
		} else {
			log.error("提现申请异常：提现编号{}", ecmMzfDraw.getDrawCode());
			throw new ServiceException(SettlementApiCode.DRAWCASH_FAILURE, BaseApiCode.getZhMsg(SettlementApiCode.DRAWCASH_FAILURE));
		}
	}
	
	
	@Override
	public int insertDraw(EcmMzfDraw ecmMzfDraw) {
		return baseMapper.insert(ecmMzfDraw, "EcmMzfDrawMapper.insertDraw");
	}

	
	@Override
	public int insertDrawWater(EcmMzfDrawWater ecmMzfDrawWater) {
		return baseMapper.insert(ecmMzfDrawWater, "EcmMzfDrawMapper.insertDrawWater");
	}
	
	
	@Override
	public int getDrawWaterCount(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfDrawMapper.getDrawWaterCount");
	}

	
	@Override
	public int getCountByCode(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfDrawMapper.getCountByCode");
	}

	
}
