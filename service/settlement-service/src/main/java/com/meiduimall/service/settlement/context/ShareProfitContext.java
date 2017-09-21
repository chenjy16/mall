package com.meiduimall.service.settlement.context;

import java.math.BigDecimal;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: ShareProfitContext.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 分润数据上下文
 */
public class ShareProfitContext {
	
	// 商家收益 = 订单总金额 * 商家收益比例 / 100%
	private BigDecimal merchantRevenue=BigDecimal.ZERO;
	// 平台分账 = 订单总金额 - 商家收益
	private BigDecimal platformRevenue=BigDecimal.ZERO;
	// 消费者返积分 = 平台分账 * 5
	private BigDecimal memberRevenue=BigDecimal.ZERO;
	// 商家所获积分 = 消费者返回积分 * 20%
	private BigDecimal sellerRevenue=BigDecimal.ZERO;
	// 推荐人获得积分 = 消费者返还积分 * 1%
	private BigDecimal belongRevenue=BigDecimal.ZERO;
	// 本区区代分账 = 消费者返积分 * 本区区代分账比例
	private BigDecimal areaAgentRevenue = BigDecimal.ZERO;
	// 跨区区代分账 = 消费者返积分 * 跨区区代分账比例
	private BigDecimal crossAreaAgentRevenue =BigDecimal.ZERO;
	// 个代分账 = 消费者返积分 * 个代分账比例
	private BigDecimal personAgentRevenue =BigDecimal.ZERO;
	// 是否是前200区代
	private boolean isTwoHundreAgentFlag;
	
	private BigDecimal discount =BigDecimal.ZERO;
	
	private Map<String, String> systemSetting = Maps.newHashMap();
	
	Map<String, String> belongMap = Maps.newHashMap();
	
	private String personalAgentType;
	
	//一级推荐人获得金额
	private BigDecimal firstReferrerCash =BigDecimal.ZERO;
	
	public BigDecimal getMerchantRevenue() {
		return merchantRevenue;
	}
	public void setMerchantRevenue(BigDecimal merchantRevenue) {
		this.merchantRevenue = merchantRevenue;
	}
	public BigDecimal getPlatformRevenue() {
		return platformRevenue;
	}
	public void setPlatformRevenue(BigDecimal platformRevenue) {
		this.platformRevenue = platformRevenue;
	}
	public BigDecimal getMemberRevenue() {
		return memberRevenue;
	}
	public void setMemberRevenue(BigDecimal memberRevenue) {
		this.memberRevenue = memberRevenue;
	}
	public BigDecimal getSellerRevenue() {
		return sellerRevenue;
	}
	public void setSellerRevenue(BigDecimal sellerRevenue) {
		this.sellerRevenue = sellerRevenue;
	}
	public BigDecimal getBelongRevenue() {
		return belongRevenue;
	}
	public void setBelongRevenue(BigDecimal belongRevenue) {
		this.belongRevenue = belongRevenue;
	}
	public BigDecimal getAreaAgentRevenue() {
		return areaAgentRevenue;
	}
	public void setAreaAgentRevenue(BigDecimal areaAgentRevenue) {
		this.areaAgentRevenue = areaAgentRevenue;
	}
	public BigDecimal getCrossAreaAgentRevenue() {
		return crossAreaAgentRevenue;
	}
	public void setCrossAreaAgentRevenue(BigDecimal crossAreaAgentRevenue) {
		this.crossAreaAgentRevenue = crossAreaAgentRevenue;
	}
	public BigDecimal getPersonAgentRevenue() {
		return personAgentRevenue;
	}
	public void setPersonAgentRevenue(BigDecimal personAgentRevenue) {
		this.personAgentRevenue = personAgentRevenue;
	}
	public boolean getIsTwoHundreAgentFlag() {
		return isTwoHundreAgentFlag;
	}
	public void setIsTwoHundreAgentFlag(boolean isTwoHundreAgentFlag) {
		this.isTwoHundreAgentFlag = isTwoHundreAgentFlag;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Map<String, String> getSystemSetting() {
		return systemSetting;
	}
	public void setSystemSetting(Map<String, String> systemSetting) {
		this.systemSetting = systemSetting;
	}
	public Map<String, String> getBelongMap() {
		return belongMap;
	}
	public void setBelongMap(Map<String, String> belongMap) {
		this.belongMap = belongMap;
	}
	public String getPersonalAgentType() {
		return personalAgentType;
	}
	public void setPersonalAgentType(String personalAgentType) {
		this.personalAgentType = personalAgentType;
	}
	
	public BigDecimal getFirstReferrerCash() {
		return firstReferrerCash;
	}
	public void setFirstReferrerCash(BigDecimal firstReferrerCash) {
		this.firstReferrerCash = firstReferrerCash;
	}
	
	

}
