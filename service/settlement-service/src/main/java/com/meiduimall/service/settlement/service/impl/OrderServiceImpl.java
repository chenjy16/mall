package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.SettlementApiCode;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.config.MyProps;
import com.meiduimall.service.settlement.context.ShareProfitContext;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmOrder;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.OrderService;
import com.meiduimall.service.settlement.util.ConnectionUrlUtil;
import com.meiduimall.service.settlement.util.DateUtil;
import com.meiduimall.service.settlement.vo.EcmMzfBillWaterVO;
import com.meiduimall.service.settlement.vo.ShareProfitVO;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private MyProps myProps;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public void saveShareProfit(EcmMzfShareProfit shareProfit){
		try{
			baseMapper.insert(shareProfit, "ShareProfitMapper.shareProfit");
			log.info("OrderServiceImpl-->doShareProfit-->ShareProfitMapper.shareProfit-->分润数据插入成功!");
			
			baseMapper.insert(ShareProfitUtil.buildOrderStatusObj(shareProfit), "EcmMzfOrderStatusMapper.createOrderStatus");
			log.info("OrderServiceImpl-->doShareProfit-->EcmMzfOrderStatusMapper.createOrderStatus-->创建订单状态数据成功!");

		}catch(DaoException e){
			log.error("分润数据插入失败，创建订单状态数据失败{}", e);
			throw new ServiceException(SettlementApiCode.ORDER_SHAREDATA_INSERT_FAILURE, BaseApiCode.getZhMsg(SettlementApiCode.ORDER_SHAREDATA_INSERT_FAILURE));
		}
	}
	
	
	@Override
	public EcmMzfShareProfit buildShareProfit(EcmOrder ecmOrder) {

		log.info("buildShareProfit start:Current Date:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// 查询基本分润配置
		Map<String, String> systemSetting = agentService.quertSharefit();
		
		ShareProfitContext ctx = new ShareProfitContext();
		
		if(Strings.isNullOrEmpty(ecmOrder.getSellerName())){
			log.error("商家编号为空!略过该条数据！");
			throw new ServiceException(SettlementApiCode.SELLER_NAME_ISNULL, BaseApiCode.getZhMsg(SettlementApiCode.SELLER_NAME_ISNULL));
		}
		// 商家让利折扣
		BigDecimal serviceRate = new BigDecimal(ecmOrder.getServiceFee());
		if (serviceRate.compareTo(new BigDecimal(0)) <= 0) {
			log.error("商家收益比例为空!略过该条数据");
			throw new ServiceException(SettlementApiCode.SERVICE_RATE_ISNULL, BaseApiCode.getZhMsg(SettlementApiCode.SERVICE_RATE_ISNULL));
		}
		log.info("个代编号:{}", ecmOrder.getAgentNoPersonal());
		log.info("订单编号:{}", ecmOrder.getOrderSn());
		log.info("商家编号:{}", ecmOrder.getSellerName());
		log.info("平台服务费:{}", serviceRate);
		BigDecimal discount = new BigDecimal(100).subtract(serviceRate);
		if (null == ecmOrder.getOrderAmount() || ecmOrder.getOrderAmount().compareTo(new BigDecimal(0)) <= 0) {
			log.error("支付金额为空或为0!略过该条数据");
			throw new ServiceException(SettlementApiCode.ORDER_AMOUNT_ISNULL, BaseApiCode.getZhMsg(SettlementApiCode.ORDER_AMOUNT_ISNULL));
		}
		if (null == ecmOrder.getAgentNoRegion() || "".equals(ecmOrder.getAgentNoRegion())) {
			log.error("该订单区代为空!");
			throw new ServiceException(SettlementApiCode.AGENT_NO_REGION_ISNULL, BaseApiCode.getZhMsg(SettlementApiCode.AGENT_NO_REGION_ISNULL));
		}
		log.info("订单支付金额:{}", ecmOrder.getOrderAmount());
		
		//获取推荐人信息
		Map<String, String> belongMap = getRecommenderInfo(ecmOrder.getBuyerName());
		
		// 平台分账(即服务费) = 参与让利金额 * 店铺服务费率
		//因为PHP部门已经根据全额返利和部分返利这两种情况，经参与让利金额计算到ecm_order.rebate_amount字段，所以结算这边无需再判断是全额返利还是部分返利。
		BigDecimal platformRevenue =ecmOrder.getRebateAmount().multiply(serviceRate).divide(new BigDecimal(100));
		log.info("平台分账 = 参与让利金额("+ecmOrder.getRebateAmount()+") * 店铺服务费率("+serviceRate.divide(new BigDecimal(100))+"):"+platformRevenue);
		
		//商家收益 = 订单支付金额 -平台分账(即服务费) 
		BigDecimal merchantRevenue = ecmOrder.getOrderAmount().subtract(platformRevenue);
		log.info("商家收益 = 订单支付金额 -平台分账(即服务费):{}", merchantRevenue);
		
		//一级推荐人获得金额:参与让利金额 * 1%
		BigDecimal firstReferrerCash=ecmOrder.getRebateAmount().multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.FIRST_REFERRER_CASH_RATE)).divide(new BigDecimal(100)));
		
		// 消费者返积分 = 平台分账 * 5
		BigDecimal memberRevenue = platformRevenue.multiply(new BigDecimal(5));
		// 商家所获积分 = 消费者返回积分 * 20%
		BigDecimal sellerRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.SELLER_POINT_RATE)).divide(new BigDecimal(100)));
		// 推荐人获得积分 = 消费者返还积分 * 1%
		BigDecimal belongRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.BELONG_SCALE))).divide(new BigDecimal(100));
		// 本区区代分账 = 消费者返积分 * 本区区代分账比例
		BigDecimal areaAgentRevenue = null;
		// 跨区区代分账 = 消费者返积分 * 跨区区代分账比例
		BigDecimal crossAreaAgentRevenue = null;
		// 个代分账 = 消费者返积分 * 个代分账比例
		BigDecimal personAgentRevenue = null;
		// 是否是前200区代
		boolean isTwoHundreAgentFlag = false;
		
		String personalAgentNo=ecmOrder.getAgentNoPersonal();
		String personalAgentType= ShareProfitUtil.getPersonalAgentType(personalAgentNo);
		
		ctx.setFirstReferrerCash(firstReferrerCash);
		ctx.setMerchantRevenue(merchantRevenue);
		ctx.setPlatformRevenue(platformRevenue);
		ctx.setMemberRevenue(memberRevenue);
		ctx.setSellerRevenue(sellerRevenue);
		ctx.setBelongRevenue(belongRevenue);
		ctx.setDiscount(discount);
		ctx.setBelongMap(belongMap);
		ctx.setSystemSetting(systemSetting);
		ctx.setPersonalAgentType(personalAgentType);
		
		log.info("个代类型:{}", personalAgentType);
		log.info("商家收益:${}", merchantRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
		log.info("平台收益:${}", platformRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
		log.info("用户返还积分:{}", memberRevenue.setScale(0, BigDecimal.ROUND_DOWN));
		
		if(ShareProfitUtil.PERSONAL_AGENT_TYPE_DIRECT_SALE.equalsIgnoreCase(personalAgentType)){
			personAgentRevenue = BigDecimal.ZERO;
			areaAgentRevenue = BigDecimal.ZERO;
			crossAreaAgentRevenue = BigDecimal.ZERO;
			
		}else if(ShareProfitUtil.PERSONAL_AGENT_TYPE_BIG_REGION.equalsIgnoreCase(personalAgentType)){
			personAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.PERSONAL_SCALE_FOR_BIG_REGION)).divide(new BigDecimal(100)));
			areaAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.AREA_SCALE)).divide(new BigDecimal(100)));
			//大区个代：此个代无区域性，此个代推广的商家均为本区商家，无论商家的地址在哪儿
			crossAreaAgentRevenue = BigDecimal.ZERO;
			
			log.info("--大区个代信息--");
			log.info("大区个代分账比例:{},大区个代收益:${}", systemSetting.get(ShareProfitUtil.PERSONAL_SCALE_FOR_BIG_REGION), personAgentRevenue);
			log.info("大区个代商家区代分账比例:{},大区个代商家区代收益:${}", systemSetting.get(ShareProfitUtil.AREA_SCALE), areaAgentRevenue);
			
		}else{
			// 商家跨区
			if (null != ecmOrder.getAgentNoRegionS() && !"".equals(ecmOrder.getAgentNoRegionS())) {
				log.info("商家跨区,不用判断是否前两百名区代判断问题。");
				areaAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.AREA_SCALE)).divide(new BigDecimal(100)));
				personAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.CROSS_PERSONAL_SCALE)).divide(new BigDecimal(100)));
				crossAreaAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.CROSS_AREA_SCALE)).divide(new BigDecimal(100)));
			
				log.info("--普通个代信息--");
				log.info("跨区个代分账比例:{}%;个代收益:${}", systemSetting.get(ShareProfitUtil.CROSS_PERSONAL_SCALE), personAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
				log.info("区代分账比例是:{}%;区代收益是:${}", systemSetting.get(ShareProfitUtil.AREA_SCALE), areaAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
				if (crossAreaAgentRevenue!=null) {
					log.info("跨区区代分账比例:{}%;跨区区代收益:${}", systemSetting.get(ShareProfitUtil.CROSS_AREA_SCALE), crossAreaAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			
			} else {
				log.info("本区区代,将判断是否为前两百名区代");
				personAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.PERSONAL_SCALE)).divide(new BigDecimal(100)));
				
				Integer isTwoHundredAgent = ecmOrder.getIsTwoHundredArea();
				if (null == isTwoHundredAgent || "".equals(String.valueOf(isTwoHundredAgent))) {
					log.info("前二百区代标识为空!略过该条数据");
					throw new ServiceException(SettlementApiCode.IS_TWO_HUNDRED_AGENT_ISNULL, BaseApiCode.getZhMsg(SettlementApiCode.IS_TWO_HUNDRED_AGENT_ISNULL));
				}else{
					log.info("前二百区代:{}", isTwoHundredAgent.toString());
				}
				// 判断区代是否为前200区代
				if (isTwoHundredAgent.equals(Integer.valueOf(1))) {
					areaAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.TWO_AREA_SCALE)).divide(new BigDecimal(100)));
					isTwoHundreAgentFlag = true;
				} else {
					areaAgentRevenue = memberRevenue.multiply(new BigDecimal(systemSetting.get(ShareProfitUtil.AREA_SCALE)).divide(new BigDecimal(100)));
					isTwoHundreAgentFlag = false;
				}
				
				log.info("--普通个代信息--");
				log.info("个代分账比例:{}%;个代收益:${}", systemSetting.get(ShareProfitUtil.PERSONAL_SCALE), personAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
				log.info("isTwoHundreAgentFlag:{}",isTwoHundreAgentFlag);
				if(isTwoHundreAgentFlag){
					log.info("区代分账比例:{}%;区代收益:${}", systemSetting.get(ShareProfitUtil.TWO_AREA_SCALE), areaAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
				}else{
					log.info("区代分账比例:{}%;区代收益:${}", systemSetting.get(ShareProfitUtil.AREA_SCALE),areaAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
	
			}
		}
		ctx.setPersonAgentRevenue(personAgentRevenue);
		ctx.setAreaAgentRevenue(areaAgentRevenue);
		ctx.setCrossAreaAgentRevenue(crossAreaAgentRevenue);
		ctx.setIsTwoHundreAgentFlag(isTwoHundreAgentFlag);
		
		final List<String> meiduiCompanyAgentNos = new ArrayList<>();

		if(!StringUtil.isEmpty(ecmOrder.getAgentNameRegion()) && ShareProfitConstants.COMPANY_NAME.equals(ecmOrder.getAgentNameRegion())){
			meiduiCompanyAgentNos.add(ecmOrder.getAgentNoRegion());
		}
		
		if(!StringUtil.isEmpty(ecmOrder.getAgentNameRegionS()) && ShareProfitConstants.COMPANY_NAME.equals(ecmOrder.getAgentNameRegionS())){
			meiduiCompanyAgentNos.add(ecmOrder.getAgentNoRegionS());
		}
		
		return buildShareProfitModel(ecmOrder, ctx, meiduiCompanyAgentNos);
	}


	/**
	 * 获取推荐人信息
	 * @param buyerName 消费用户手机号
	 * @return Map
	 */
	private Map<String, String> getRecommenderInfo(String buyerName) {
		// 获取推荐人信息		
		String resultStr = ConnectionUrlUtil.httpRequest(getBelongInfoUrl(buyerName), ShareProfitUtil.REQUEST_METHOD_POST, null);
		ResBodyData resultJson= JsonUtils.jsonToBean(resultStr, ResBodyData.class);
		
		Map<String, String> belongMap = null;
		
		if (null == resultJson) { 
			log.info("会员系统连接失败!略过该条数据");
			throw new ServiceException(SettlementApiCode.GET_RECOMMENDER_INFO_FAILURE, BaseApiCode.getZhMsg(SettlementApiCode.GET_RECOMMENDER_INFO_FAILURE));
		}else{
			// 判断返回是否成功,如果不成功则不理会
			if (resultJson.getStatus()==0) {
//				List<Map<String, String>> map = resultJson.getRESULT();
//				belongMap = ShareProfitUtil.getlvlAndPhone(map);
				
				log.info("推荐人信息:{}", resultJson.getData());
			} else {
				log.error("errcode:{};errmsg:{}", resultJson.getStatus(), resultJson.getMsg());
			}
		}
		return belongMap;
	}
	

	@Override
	public List<EcmMzfOrderStatus> queryOrderStatus(List<String> orderSns) {
		return baseMapper.selectList(ImmutableMap.of("orderSns", orderSns), "EcmMzfOrderStatusMapper.queryorderstatus");
	}

	
	public EcmMzfShareProfit buildShareProfitModel(EcmOrder ecmOrder,ShareProfitContext ctx,List<String> meiduiCompanyAgentNos) {
		
		BigDecimal merchantRevenue = ctx.getMerchantRevenue();
		BigDecimal sellerRevenue = ctx.getSellerRevenue();
		BigDecimal personAgentRevenue = ctx.getPersonAgentRevenue();
		BigDecimal areaAgentRevenue = ctx.getAreaAgentRevenue();
		BigDecimal crossAreaAgentRevenue = ctx.getCrossAreaAgentRevenue();
		BigDecimal memberRevenue = ctx.getMemberRevenue();
		BigDecimal belongRevenue = ctx.getBelongRevenue();
		BigDecimal firstReferrerCash = ctx.getFirstReferrerCash();

		BigDecimal discount = ctx.getDiscount();
		boolean isTwoHundreAgentFlag = ctx.getIsTwoHundreAgentFlag();
		Map<String, String> systemSetting = ctx.getSystemSetting();
		Map<String, String> belongMap = ctx.getBelongMap();
		String personalAgentType = ctx.getPersonalAgentType();

		EcmMzfShareProfit ecmMzfShareProfit = new EcmMzfShareProfit();
		
		// 支付方式为POS
		if (ShareProfitUtil.PAY_POS.equals(ecmOrder.getPaymentCode())) {
			ecmMzfShareProfit.setSellerProfit(new BigDecimal(0));
			ecmMzfShareProfit.setSource("0");
		}else{
			ecmMzfShareProfit.setSellerProfit(merchantRevenue);
			ecmMzfShareProfit.setSource("1");
		}
		ecmMzfShareProfit.setOrderSn(ecmOrder.getOrderSn());
		ecmMzfShareProfit.setOutTradeSn(ecmOrder.getOutTradeSn());
		ecmMzfShareProfit.setSellerId(ecmOrder.getSellerName());
		ecmMzfShareProfit.setSellerPhone(ecmOrder.getSellerPhone());
		ecmMzfShareProfit.setSellerPoint(sellerRevenue.setScale(0, BigDecimal.ROUND_DOWN));
		//如果不存在个代,则个代的数据为null
		if (null != ecmOrder.getAgentNoPersonal() && !"".equals(ecmOrder.getAgentNoPersonal())) {
			ecmMzfShareProfit.setPersonAgentId(ecmOrder.getAgentNoPersonal());
			ecmMzfShareProfit.setPersonAgentProfit(personAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
			
		}
		//获取区代是否为美兑壹购物
		if(!Strings.isNullOrEmpty(ecmOrder.getAgentNoRegion())){
			ecmMzfShareProfit.setAreaAgentId(ecmOrder.getAgentNoRegion());
			ecmMzfShareProfit.setAreaAgentProfit(areaAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
			//判断区代是否为美兑壹购物
			if(meiduiCompanyAgentNos.contains(ecmOrder.getAgentNoRegion())){
				ecmMzfShareProfit.setAreaAgentProfit(new BigDecimal(0));
			}
		}
		ecmMzfShareProfit.setPhone(ecmOrder.getBuyerName());
		ecmMzfShareProfit.setOrderFee(ecmOrder.getTotalFee());
		ecmMzfShareProfit.setPoint(memberRevenue.setScale(0, BigDecimal.ROUND_DOWN));
		ecmMzfShareProfit.setSellerShareprofitRate(discount);
		ecmMzfShareProfit.setAreaShareprofitRate(isTwoHundreAgentFlag ? new BigDecimal(systemSetting.get(ShareProfitUtil.TWO_AREA_SCALE)) : new BigDecimal(systemSetting.get(ShareProfitUtil.AREA_SCALE)));
		ecmMzfShareProfit.setSellerPointRate(new BigDecimal(systemSetting.get(ShareProfitUtil.SELLER_POINT_RATE)));
		if (null != belongMap && !belongMap.isEmpty()) {
			if(!Strings.isNullOrEmpty(belongMap.get("1"))){  //Alex:fix the bug:belongMap={1=} @2016 Dec 13
				ecmMzfShareProfit.setBelongOnePhone(belongMap.get("1"));
				ecmMzfShareProfit.setBelongOnePoint(belongRevenue.setScale(0, BigDecimal.ROUND_DOWN));
				ecmMzfShareProfit.setFirstReferrerCash(firstReferrerCash.setScale(2, BigDecimal.ROUND_HALF_UP));
				ecmMzfShareProfit.setFirstReferrerCashRate(new BigDecimal(systemSetting.get(ShareProfitUtil.FIRST_REFERRER_CASH_RATE)));
			}
			if (!Strings.isNullOrEmpty(belongMap.get("2"))) {
				ecmMzfShareProfit.setBelongTwoPhone(belongMap.get("2"));
				ecmMzfShareProfit.setBelongTwoPoint(belongRevenue.setScale(0, BigDecimal.ROUND_DOWN));
			}
		}
		if (null != crossAreaAgentRevenue && crossAreaAgentRevenue.compareTo(new BigDecimal(0)) > 0) {
			
			ecmMzfShareProfit.setOutArea("1");
			ecmMzfShareProfit.setOutareaAgentId(ecmOrder.getAgentNoRegionS());
			ecmMzfShareProfit.setOutareaAgentProfit(crossAreaAgentRevenue.setScale(2, BigDecimal.ROUND_HALF_UP));
			//判断跨区区代是否为美兑壹购物
			if(meiduiCompanyAgentNos.contains(ecmOrder.getAgentNoRegionS())){
				ecmMzfShareProfit.setOutareaAgentProfit(new BigDecimal(0));
			}
			
			ecmMzfShareProfit.setOutareaShareprofitRate(new BigDecimal(systemSetting.get(ShareProfitUtil.CROSS_AREA_SCALE)));
			ecmMzfShareProfit.setPersonShareprofitRate(new BigDecimal(systemSetting.get(ShareProfitUtil.CROSS_PERSONAL_SCALE)));
		} else {
			ecmMzfShareProfit.setPersonShareprofitRate(new BigDecimal(systemSetting.get(ShareProfitUtil.PERSONAL_SCALE)));
			ecmMzfShareProfit.setOutArea("0");
			ecmMzfShareProfit.setOutareaShareprofitRate(new BigDecimal(systemSetting.get(ShareProfitUtil.CROSS_AREA_SCALE)));
		}
		
		//直营商家
		if(ShareProfitUtil.PERSONAL_AGENT_TYPE_DIRECT_SALE.equalsIgnoreCase(personalAgentType)){
			ecmMzfShareProfit.setPersonAgentId(ecmOrder.getAgentNoPersonal());//保留代理ID，不要设置为空，否则导致 出现bug:分润后创建账单后订单的结算状态有可能无法更新为已结算。
			ecmMzfShareProfit.setPersonAgentProfit(BigDecimal.ZERO);
			ecmMzfShareProfit.setPersonShareprofitRate(null);
			
			ecmMzfShareProfit.setAreaAgentId(ecmOrder.getAgentNoRegion());
			ecmMzfShareProfit.setAreaAgentProfit(BigDecimal.ZERO);
			ecmMzfShareProfit.setAreaShareprofitRate(null);
			
			ecmMzfShareProfit.setOutareaAgentId(ecmOrder.getAgentNoRegionS());
			ecmMzfShareProfit.setOutareaAgentProfit(BigDecimal.ZERO);
			ecmMzfShareProfit.setOutareaShareprofitRate(null);
		}else if(ShareProfitUtil.PERSONAL_AGENT_TYPE_BIG_REGION.equalsIgnoreCase(personalAgentType)){  //大区个代
			ecmMzfShareProfit.setOutareaAgentId(ecmOrder.getAgentNoRegionS());
			ecmMzfShareProfit.setOutareaAgentProfit(BigDecimal.ZERO);
			ecmMzfShareProfit.setOutareaShareprofitRate(null);
		}
		ecmMzfShareProfit.setCreatedDate(DateUtil.getCurrentTimeSec());
		//for build EcmMzfOrderStatus purpose
		ecmMzfShareProfit.setStatus(ecmOrder.getStatus());
		ecmMzfShareProfit.setOrderDate(ecmOrder.getAddTime());
		ecmMzfShareProfit.setPayTime(ecmOrder.getPayTime());
		ecmMzfShareProfit.setRebateAmount(ecmOrder.getRebateAmount());
		ecmMzfShareProfit.setServiceFee(ecmOrder.getServiceFee());
		
		return ecmMzfShareProfit;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean syncVerifyStatus(EcmMzfOrderStatus input)  {
		input.setBillStatus(1);
		input.setStatusDesc("待结算");
		Integer update = baseMapper.update(input, "EcmMzfOrderStatusMapper.syncverifystatus");
		log.info("OrderServiceImpl-->syncverifystatus-->EcmMzfOrderStatusMapper.syncverifystatus-->同步订单审核状态:{}条记录", update);
		return update>0?true:false;
	}

	
	@Override
	public List<EcmMzfShareProfit> queryShareProfit(Collection<String> orderSns)  {
		Map<String,Object> params = Maps.newHashMap();
		params.put("orderSns", orderSns);
		List<EcmMzfShareProfit> shareProfits= baseMapper.selectList(params, "ShareProfitMapper.getShareProfitByOrderSns");
		List<EcmMzfBillWaterVO> billWaterVOs= baseMapper.selectList(params, "ShareProfitMapper.getBillDateByOrderSns");

		ShareProfitUtil.updateBillInfo(shareProfits,billWaterVOs);
		return shareProfits;

	}
	

	@Override
	public ShareProfitVO queryProfitByRole(String code, Integer accountRoleType)  {
		
		ShareProfitVO spVO=null;
		Integer todayStart=DateUtil.getDayBeginBySecond(0).intValue();
		Integer todayEnd=DateUtil.getDayEndBySecond(0).intValue();
		
		final Map<String,Object> params = Maps.newHashMap();
		params.put("startDate",todayStart);
		params.put("endDate",todayEnd);
		
		if(accountRoleType==null){
			return spVO;
		}
		
		if(ShareProfitConstants.ROLE_TYPE_AREA_AGENT==accountRoleType){
			params.put("areaAgentNo", code);
			List<ShareProfitVO> spVOs4Today=baseMapper.selectList(params, "ShareProfitMapper.getAreaAgentShareProfit4Period");
			//一个区代，即可能是区代，也有可能是跨区代。
			BigDecimal areaAgentProfit4Today=ShareProfitUtil.getShareProfitByType("areaAgent",spVOs4Today,"Today");
			BigDecimal outareaAgentProfit4Today=ShareProfitUtil.getShareProfitByType("outAreaAgent",spVOs4Today,"Today");
			
			BigDecimal totalAreaAgentProfit4Today=(areaAgentProfit4Today==null?BigDecimal.ZERO:areaAgentProfit4Today).add(outareaAgentProfit4Today==null?BigDecimal.ZERO:outareaAgentProfit4Today);
			
			//合并汇总today arerAgent and outAreaAgent
			List<ShareProfitVO> spVOs4Settlement=baseMapper.selectList(params, "ShareProfitMapper.getAreaAgentShareProfit4Settlement");
			BigDecimal areaAgentProfit4Settlement=ShareProfitUtil.getShareProfitByType("areaAgent",spVOs4Settlement,"Settlement");
			BigDecimal outareaAgentProfit4Settlement=ShareProfitUtil.getShareProfitByType("outAreaAgent",spVOs4Settlement,"Settlement");
			
			BigDecimal totalAreaAgentProfit4Settlement=(areaAgentProfit4Settlement==null?BigDecimal.ZERO:areaAgentProfit4Settlement).add(outareaAgentProfit4Settlement==null?BigDecimal.ZERO:outareaAgentProfit4Settlement);
			
			//合并汇总settlemt arerAgent and outAreaAgent
			spVO=new ShareProfitVO("AreaAgent",code,totalAreaAgentProfit4Today,totalAreaAgentProfit4Settlement);
			
		}else if(ShareProfitConstants.ROLE_TYPE_PERSONAL_AGENT==accountRoleType){
			params.put("personalAgentNo", code);
			ShareProfitVO spVOs4Today=baseMapper.selectOne(params, "ShareProfitMapper.getPersonalAgentShareProfit4Period");
			ShareProfitVO spVOs4Settlement=baseMapper.selectOne(params, "ShareProfitMapper.getPersonalAgentShareProfit4Settlement");
			
			BigDecimal personalAgentProfit4Today=spVOs4Today.getProfitToday();
			BigDecimal personalAgentProfit4Settlement=spVOs4Settlement.getProfit4Settlement();
			spVO=new ShareProfitVO("PersonalAgent",code,personalAgentProfit4Today,personalAgentProfit4Settlement);
			
		}
		
		return spVO;

	}

	@Override
	public List<EcmMzfShareProfit> queryProfitByWaterByType(String waterId, Integer loginType, String code,Integer pageNumber,Integer pageSize)  {
		
		PageHelper.startPage(pageNumber, pageSize);
		
		List<EcmMzfShareProfit> shareProfitList = baseMapper.selectList(waterId, "EcmMzfWaterMapper.getShareProfitByWaterId");
		//loginType:1代理 2商家 3 其他
		
		if(CollectionUtils.isEmpty(shareProfitList)){
			return shareProfitList; 
		}
		
		for(EcmMzfShareProfit shareProfit:shareProfitList){
			
			if(loginType!=null && loginType==1){
				if(!StringUtil.isEmpty(code) && code.length()==6){  //个代
					shareProfit.setProfit(shareProfit.getPersonAgentProfit());
				}else{  //区代
					shareProfit.setProfit(shareProfit.getAreaAgentProfit().add(shareProfit.getOutareaAgentProfit()));
				}
			}
		}
		return shareProfitList;
	}

	@Override
	public int queryProfitCountByWaterId(String waterId)  {
		return baseMapper.selectOne(waterId, "EcmMzfWaterMapper.getShareProfitCountByWaterId");
	}

	@Override
	public boolean checkShareProfitExisted(String orderSn)  {
		
		int count= baseMapper.selectOne(orderSn, "ShareProfitMapper.checkShareProfitExisted");
		return count>0?true:false;
	}
	
	@Override
	public List<ShareProfitVO> queryTotalProfit(Collection<String> codes, Integer billStartDate, Integer billEndDate){
		final Map<String,Object> params=new HashMap<>();
		params.put("codes", codes);
		params.put("billStartDate", billStartDate);
		params.put("billEndDate", billEndDate);
		return baseMapper.selectList(params, "EcmBillMapper.querytotalprofit");
	}
	
	//接口参数url拼接
	public String getBelongInfoUrl(String phone) {
		String timeStamp = String.valueOf(System.currentTimeMillis() / 1000L);
		String nonce = ShareProfitUtil.getRandomNum();
		return myProps.getUrl()
				+ myProps.getBelong()
				+ "?oauth_signature_method=" + myProps.getOauthSignatureMethod()
				+ "&oauth_accessor_secret=" + myProps.getOauthAccessorSecret()
				+ "&oauth_consumer_key=" + myProps.getOauthConsumerKey()
				+ "&oauth_timestamp=" + timeStamp 
				+ "&oauth_nonce=" + nonce 
				+ "&oauth_version=" + myProps.getOauthVersion()
				+ "&oauth_signature=" + oauthSignature(phone, timeStamp, nonce) 
				+ "&share_man=" + phone;
	}
		
	//MD5加密串拼接
	public String oauthSignature(String phone, String timeStamp, String nonce) {
		String md5Str = "get&" + myProps.getUrl() 
				+ myProps.getBelong()
				+ "&oauth_signature_method=" + myProps.getOauthSignatureMethod() 
				+ "&oauth_accessor_secret=" + myProps.getOauthAccessorSecret()
				+ "&oauth_consumer_key=" + myProps.getOauthConsumerKey() 
				+ "&oauth_timestamp=" + timeStamp 
				+ "&oauth_nonce=" + nonce 
				+ "&oauth_version=" + myProps.getOauthVersion() 
				+ "&share_man=" + phone;
		return ShareProfitUtil.mD5Encrypt(ShareProfitUtil.encodeStr(md5Str, "UTF-8"));
	}

}
