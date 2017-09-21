package com.meiduimall.service.settlement.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.SettlementApiCode;
import com.meiduimall.service.settlement.common.DrawCashConstants;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.model.EcmMzfDraw;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.DrawService;
import com.meiduimall.service.settlement.util.DateUtil;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: DrawController.java
 * Author:   guidl
 * Date:     2017年3月24日 下午14:14:28
 * Description: 提现相关接口
 */
@RestController
@RequestMapping("/settlementservice/drawservice/v1")
public class DrawController {
	
	private static final Logger log = LoggerFactory.getLogger(DrawController.class);
	
	private static final String BALANCE = "balance";
	
	private static final String ADMIN = "admin";
	
	@Autowired
	private DrawService drawService;
	
	@Autowired
	private AgentService agentService;
	
	
	/**
	 * 功能描述:  根据代理编号获取区代、个代或商家可提现金额
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * @param  code 代理编号
	 * @return ResBodyData
	 */
	@PostMapping(value="/queryaccoutbalance")
	public ResBodyData queryAccoutBalance(String code) {
		try {
		Map<String, Object> accountResult = drawService.queryAccoutBalance(code);
		return SettlementUtil.success(accountResult);
		} catch (DaoException e) {
			throw new ServiceException(e.getCode(),e.getMessage(),e);
		}
	}
	

	/**
	 * 功能描述:  新增提现申请
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * @param  ecmMzfDraw 提现信息
	 * @return ResBodyData
	 */
	@PostMapping(value = "/drawcash")
	public ResBodyData drawCash(@Validated EcmMzfDraw ecmMzfDraw) {
		//提现手续费从配置表获取
		Map<String, String> systemSetting = agentService.quertSharefit();
		BigDecimal cashWithdrawalFee = new BigDecimal(systemSetting.get(ShareProfitConstants.CASH_WITHDRAWAL_FEE));
		
		//查询当天是否有提现记录
		Map<String, Object> params = Maps.newHashMap();
		params.put("code", ecmMzfDraw.getCode());
		params.put("drawType", ecmMzfDraw.getDrawType());
		params.put("nowTime", DateUtil.formatDate(new Date()));
		int drawCount = drawService.getCountByCode(params);
		
		//如果当天是第一次提现无需收取手续费，否则要收取手续费
		if(drawCount > 0){
			ecmMzfDraw.setCashWithdrawalFee(cashWithdrawalFee);
			ecmMzfDraw.setTotalMoney(ecmMzfDraw.getMoney().add(cashWithdrawalFee));
			ecmMzfDraw.setRemark("手续费"+cashWithdrawalFee+"元");
		}else{
			ecmMzfDraw.setCashWithdrawalFee(new BigDecimal("0.00"));
			ecmMzfDraw.setTotalMoney(ecmMzfDraw.getMoney());
			ecmMzfDraw.setRemark("提现");
		}
		
		//根据code查询账户余额
		BigDecimal balance = new BigDecimal("0");
		Map<String, Object> account = drawService.queryAccoutBalance(ecmMzfDraw.getCode());
		if(account.get(BALANCE) != null && !"".equals(account.get("BALANCE"))){
			balance = new BigDecimal(account.get("BALANCE").toString());
			ecmMzfDraw.setBalance(balance);
		}
		
		//如果提现总金额=提现金额+手续费 > 账户总金额时，返回提现金额不能大于账号可提现金额，请重新输入
		if(balance.compareTo(ecmMzfDraw.getTotalMoney()) < 0){//balance>totalMoney时返回1,-1是小于,0是等于
			log.error("提现金额不能大于账号可提现金额，请重新输入");
			throw new ServiceException(SettlementApiCode.BALANCE_NOT_ENOUGH, BaseApiCode.getZhMsg(SettlementApiCode.BALANCE_NOT_ENOUGH));
		}

		boolean result = drawService.insertDrawInfo(ecmMzfDraw);
		return SettlementUtil.success(result);
	}
	
	
	/**
	 * 功能描述:  获取提现管理列表
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28 
	 * @param  pageNumber 页数
	 * @param  pageSize 每页显示条数
	 * @param  type (list,export)
	 * @param  params params(drawCode,code,drawType,realname,userType,addTime,status,drawName)
	 * @return ResBodyData
	 */
	@PostMapping(value = "/querydrawcash")
	public ResBodyData queryDrawCash(
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "type", defaultValue = "list") String type,
			@RequestParam Map<String, Object> params) {
		
		if("list".equals(type)){
			PageHelper.startPage(pageNumber, pageSize);
		}
		List<EcmMzfDraw> ecmMzfDrawList = drawService.queryDrawCash(params);
		int total = drawService.getDrawCount(params);
		
		Map<String,Object> result = Maps.newHashMap();
		result.put("list", ecmMzfDrawList);
		result.put("total", total);
		return SettlementUtil.success(result);
	}
	
	
	/**
	 * 功能描述:  根据提现编号获取提现详情
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * @param  drawCode-提现编号
	 * @return ResBodyData
	 */
	@PostMapping(value="/querydrawcashbyid")
	public ResBodyData queryDrawCashById(String drawCode) {
		EcmMzfDraw drawDetail = drawService.queryDrawCashById(drawCode);
		return SettlementUtil.success(drawDetail);
	}
	
	
	/**
	 * 功能描述:  审核提现申请
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * @param  ecmmzfdraw 提现信息
	 * @return ResBodyData
	 */
	@PostMapping(value="/verifydrawcashbyid")
	public ResBodyData verifyDrawCashById(EcmMzfDraw ecmmzfdraw) {
		
		if(StringUtil.isEmpty(ecmmzfdraw.getDrawCode())){
			throw new ServiceException(SettlementApiCode.VERIFY_DRAWCODE_ISNULL, BaseApiCode.getZhMsg(SettlementApiCode.VERIFY_DRAWCODE_ISNULL));
		}

		ecmmzfdraw.setStatus(DrawCashConstants.STATUS_VERIFIED_SUCDESS);
		ecmmzfdraw.setVerifyName(StringUtil.isEmpty(ecmmzfdraw.getVerifyName())?ADMIN:ecmmzfdraw.getVerifyName());
		ecmmzfdraw.setVerifyStatus(DrawCashConstants.STATUS_VERIFIED_SUCDESS);
		ecmmzfdraw.setVerifyTime(DateUtil.getCurrentTimeSec());
		
		Map<String, Object> hashMap = drawService.verifyDrawCashById(ecmmzfdraw);
		return SettlementUtil.success(hashMap);
	}
	
	
	/**
	 * 功能描述:  驳回提现申请
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * @param  ecmmzfdraw 提现信息
	 * @return ResBodyData
	 */
	@PostMapping(value="/rejectdrawcashbyid")
	public ResBodyData rejectDrawCashById(EcmMzfDraw ecmmzfdraw) {
		
		if(StringUtil.isEmpty(ecmmzfdraw.getDrawCode()) || StringUtil.isEmpty(ecmmzfdraw.getRemark())){
			throw new ServiceException(SettlementApiCode.REJECT_DRAWCODE_REMARK_ISNULL, BaseApiCode.getZhMsg(SettlementApiCode.REJECT_DRAWCODE_REMARK_ISNULL));
		}
		
		ecmmzfdraw.setStatus(DrawCashConstants.STATUS_VERIFIED_REJECTED);
		ecmmzfdraw.setVerifyName(StringUtil.isEmpty(ecmmzfdraw.getVerifyName())?ADMIN:ecmmzfdraw.getVerifyName());
		ecmmzfdraw.setVerifyStatus(DrawCashConstants.STATUS_VERIFIED_REJECTED);
		ecmmzfdraw.setVerifyTime(DateUtil.getCurrentTimeSec());
		
		Map<String, Object> hashMap = drawService.rejectDrawCashById(ecmmzfdraw);
		return SettlementUtil.success(hashMap);
		
	}
	
	
	/**
	 * 功能描述:  确认提现转账成功或失败（更改提现状态）
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * @param  ecmmzfdraw 提现信息
	 * @return ResBodyData
	 */
	@PostMapping(value="/confirmdrawcashbyidbytype")
	public ResBodyData confirmDrawCashByIdByType(EcmMzfDraw ecmmzfdraw) {
		
		if(ecmmzfdraw.getType()!=null && ecmmzfdraw.getType()==1){  //1:转账成功；0：转账失败
			ecmmzfdraw.setStatus(DrawCashConstants.STATUS_TRANSFER_SUCCESS);
			ecmmzfdraw.setFinanceStatus(DrawCashConstants.STATUS_TRANSFER_SUCCESS);
		}else{
			if(StringUtil.isEmpty(ecmmzfdraw.getRemark())){
				throw new ServiceException(SettlementApiCode.FAILURE_REASON, BaseApiCode.getZhMsg(SettlementApiCode.FAILURE_REASON));
			}
			ecmmzfdraw.setStatus(DrawCashConstants.STATUS_TRANSFER_FAIL);
			ecmmzfdraw.setFinanceStatus(DrawCashConstants.STATUS_TRANSFER_FAIL);

		}
		ecmmzfdraw.setFinanceTime(DateUtil.getCurrentTimeSec());
		ecmmzfdraw.setFinanceName(StringUtil.isEmpty(ecmmzfdraw.getFinanceName())?ADMIN:ecmmzfdraw.getFinanceName());
		
		Map<String, Object> hashMap = drawService.confirmDrawCashByIdByType(ecmmzfdraw);
		return SettlementUtil.success(hashMap);
	}

	
}
