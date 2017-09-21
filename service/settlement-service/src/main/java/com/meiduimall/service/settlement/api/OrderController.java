package com.meiduimall.service.settlement.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.SettlementApiCode;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmOrder;
import com.meiduimall.service.settlement.service.OrderService;
import com.meiduimall.service.settlement.task.AsyncTaskService;
import com.meiduimall.service.settlement.vo.ShareProfitVO;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: OrderController.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 订单分润和分润查询服务
 */
@RestController
@RequestMapping("/settlementservice/orderservice/v1")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AsyncTaskService asyncTaskService;


	/**
	 * 功能描述:  订单分润接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * @param  ecmOrder 订单信息
	 * @return ResBodyData
	 */
	@PostMapping("/shareprofit")
	public ResBodyData shareProfit(@Validated EcmOrder ecmOrder){
		
		long start = System.currentTimeMillis();
		log.info("share profit for order start:{}", start);
		
		//shareStatus:0-未分润,1-已分润
		Integer shareStatus = 1;
		
		//判断该订单号是否为重复分润
		boolean isExisted = orderService.checkShareProfitExisted(ecmOrder.getOrderSn());
		if(isExisted){
			throw new ServiceException(SettlementApiCode.ORDER_ALREADY_SHAREPROFIT, BaseApiCode.getZhMsg(SettlementApiCode.ORDER_ALREADY_SHAREPROFIT));
		}
		
		EcmMzfShareProfit shareProfit = orderService.buildShareProfit(ecmOrder);
		
		//保存分润数据到DB
		if (shareProfit == null) {
			log.error("订单分润数据为空(shareProfit)");
			throw new ServiceException(SettlementApiCode.ORDER_SHARE_DATA_EMPTY, BaseApiCode.getZhMsg(SettlementApiCode.ORDER_SHARE_DATA_EMPTY));
		}
		
		orderService.saveShareProfit(shareProfit);
		
		//异步同步积分到会员系统
		asyncTaskService.updateScore2MemberSystem(shareProfit, ShareProfitConstants.SHARE_PROFIT_SOURCE_O2O, null);
		
		long end = System.currentTimeMillis();
		log.info("share profit for order end:{}",end);
		log.info("total time(second) for shareprofit:{}", (end-start)/1000);
		
		return SettlementUtil.success(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(), "shareStatus", shareStatus));
	}


	/**
	 * 功能描述:  根据订单号列表查询订单状态接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * @param  orderSns 订单号
	 * @return ResBodyData
	 */
	@PostMapping(value="/queryorderstatus")
	public ResBodyData queryOrderStatus(String[] orderSns) {
		
		List<EcmMzfOrderStatus> queryorderstatus = new ArrayList<>();
		
		if (orderSns != null && orderSns.length > 0) {
			queryorderstatus = orderService.queryOrderStatus(Arrays.asList(orderSns));
		}
		return SettlementUtil.success(queryorderstatus);
		
	}
	
	
	/**
	 * 功能描述:  同步订单审核状态接口(bill_status状态改为1, status_desc改为待结算)
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26
	 * @param  orderStatus 订单相关状态
	 * @return ResBodyData
	 */
	@PostMapping(value="/syncverifystatus")
	public ResBodyData syncVerifyStatus(@Validated EcmMzfOrderStatus orderStatus) {
		log.info("syncVerifyStatus() in the OrderController start--");
		
		int statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS;

		boolean isSuccess=true;
		String msg="同步订单审核状态数据到结算系统成功!";
		try {
			isSuccess = orderService.syncVerifyStatus(orderStatus);
		} catch (Exception e) {
			isSuccess=false;
			log.error("同步订单审核状态接口 出错，orderService.syncVerifyStatus() for orderSn:{} got error:{}",orderStatus.getOrderSn(),e.getMessage(),e);
		}
		
		if(!isSuccess){
			msg="同步订单审核状态数据到结算系统失败!";
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			log.info(msg);
		}
			
		return SettlementUtil.buildReponseData("", statusCode, msg);
		
	}

	
	/**
	 * 功能描述:  根据订单号列表查询订单分润接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * @param  orderSns 订单号
	 * @return ResBodyData
	 */
	@PostMapping("/queryshareprofit")
	public ResBodyData queryShareProfit(String[] orderSns) {
		List<EcmMzfShareProfit> shareProfits = new ArrayList<>();
		
		if (orderSns != null && orderSns.length > 0) {
			shareProfits = orderService.queryShareProfit(Arrays.asList(orderSns));
		}
		return SettlementUtil.success(shareProfits);
	}
	
	
	/**
	 * 功能描述:  根据区代/个代查询今日订单佣金和待结算金额接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26 
	 * @param  code 代理编号
	 * @param  accountRoleType 账号类型
	 * @return ResBodyData
	 */
	@PostMapping("/queryprofitbyrole")
	public ResBodyData queryProfitByRole(String code,Integer accountRoleType) {
		ShareProfitVO shareProfitVO = orderService.queryProfitByRole(code, accountRoleType);
		return SettlementUtil.success(shareProfitVO);
	}
	
	
	/**
	 * 功能描述:  根据代理或商家编号查询汇总分润数据接口
	 * Author: 许彦 雄
	 * Date:   2017年3月28日 下午14:41:02
	 * @param  codes 代理编号
	 * @param  billStartDate 账单开始时间
	 * @param  billEndDate 账单结束时间
	 * @return ResBodyData
	 */
	@PostMapping("/querytotalprofit")
	public ResBodyData queryTotalProfit(String[] codes,Integer billStartDate,Integer billEndDate) {
		
		List<ShareProfitVO> shareProfitVOs=new ArrayList<>();
		if(codes!=null && codes.length>0){
			shareProfitVOs = orderService.queryTotalProfit(Arrays.asList(codes), billStartDate, billEndDate);
		}
		return SettlementUtil.success(shareProfitVOs);
	}


}
