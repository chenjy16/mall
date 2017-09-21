package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


public class EcmOrder implements Serializable{
	
	private static final long serialVersionUID = 8957133515876839069L;

	@NotBlank(message="订单号不能为空")
	private String orderSn;
	
	@NotBlank(message="商家编号不能为空")
	private String sellerName;
	@NotBlank(message="商家手机号码不能为空")
	private String sellerPhone;
	
	@NotBlank(message="消费者手机号码不能为空")
	private String buyerName; //消费用户手机号
	
	@Range(min=0,max=Integer.MAX_VALUE,message="订单状态不能为空,应该为数值。")
	private int status;
	
	private String outTradeSn; //交易号
	
	@Range(min=0,max=Integer.MAX_VALUE,message="付款时间不能为空,应该为10位数时间戳.")
	private int payTime;
	
	@Range(min=0,max=Integer.MAX_VALUE,message="订单创建时间不能为空,应该为10位数时间戳.")
	private int addTime;  //订单时间
	
	@NotNull(message="实际支付金额不能为空")
	private BigDecimal payAmount;
	@NotNull(message="订单金额不能为空")
	private BigDecimal goodsAmount;
	@NotNull(message="折扣不能为空")
	private BigDecimal discount;

	@NotNull(message="交易金额不能为空")
	private BigDecimal totalFee;
	@NotNull(message="店铺折扣不能为空")
	private BigDecimal storeDiscount;
	@NotNull(message="不参与优惠金额不能为空")
	private BigDecimal norebate;
	private BigDecimal coupons;
	private BigDecimal brokerage;
	private String discountInfo;
	@NotNull(message="区代编号不能为空")
	private String agentNoRegion; //区代编码
	private String agentNoPersonal; //个代编码
	private String agentNoRegionS; //跨区区代编码
	
	@NotNull(message="区代名称不能为空")
	private String agentNameRegion;  //区代名称
	private String agentNameRegionS;  //跨区代名称
	
	private int isTwoHundredArea;    //是否是前两百名区代
	
	@Range(min=0,max=Integer.MAX_VALUE,message="服务费率必须为数值且不能小于0.")
	private int serviceFee;
	
	@Range(min=0,max=Integer.MAX_VALUE,message="服务费计算方式必须为数值.")
	private int serviceFeeCalc;

	private String discountMsg;

	private String paymentCode; //交易方式
	//private Date gmtPayment; //订单交易时间
	
	/*
	 * goods_amount: 订单金额
	 * norebate:     不参与优惠金额
	 * discount_info: 店铺折扣 (列如9折)
	 * 优惠金额 = (订单金额 - 不参与优惠金额) * (1-店铺折扣/10)
	 * order_amount  支付金额  = 订单金额 - 优惠金额
	 */
	@NotNull(message="支付金额不能为空")
	private BigDecimal orderAmount;
	
	/*
	 *  service_fee   服务费率 （列如80%）
	 *	service_fee_calc 服务费计算方式(0/1)  0:全额返利；1:部分返利(让利)
	 *	1、两种服务费计算方式
	 *	  0:参与返利的金额=支付金额
	 *    1:参与返利的金额=支付金额-不参与优惠金额		
	 *    服务费=参与返利的金额*服务费率
	 *    参与返利的金额是PHP通过计算后保存的一个值
	 */
	@NotNull(message="返利金额不能为空")
	private BigDecimal rebateAmount;
	
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getOutTradeSn() {
		return outTradeSn;
	}
	public void setOutTradeSn(String outTradeSn) {
		this.outTradeSn = outTradeSn;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	
	public String getAgentNoRegion() {
		return agentNoRegion;
	}
	public void setAgentNoRegion(String agentNoRegion) {
		this.agentNoRegion = agentNoRegion;
	}
	public String getAgentNoPersonal() {
		return agentNoPersonal;
	}
	public void setAgentNoPersonal(String agentNoPersonal) {
		this.agentNoPersonal = agentNoPersonal;
	}
	public String getAgentNoRegionS() {
		return agentNoRegionS;
	}
	public void setAgentNoRegionS(String agentNoRegionS) {
		this.agentNoRegionS = agentNoRegionS;
	}
	
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}
	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}


	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPayTime() {
		return payTime;
	}
	public void setPayTime(int payTime) {
		this.payTime = payTime;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getStoreDiscount() {
		return storeDiscount;
	}
	public void setStoreDiscount(BigDecimal storeDiscount) {
		this.storeDiscount = storeDiscount;
	}
	public BigDecimal getNorebate() {
		return norebate;
	}
	public void setNorebate(BigDecimal norebate) {
		this.norebate = norebate;
	}
	public BigDecimal getCoupons() {
		return coupons;
	}
	public void setCoupons(BigDecimal coupons) {
		this.coupons = coupons;
	}
	public BigDecimal getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(BigDecimal brokerage) {
		this.brokerage = brokerage;
	}
	public String getDiscountInfo() {
		return discountInfo;
	}
	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}
	public int getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(int serviceFee) {
		this.serviceFee = serviceFee;
	}
	public int getServiceFeeCalc() {
		return serviceFeeCalc;
	}
	public void setServiceFeeCalc(int serviceFeeCalc) {
		this.serviceFeeCalc = serviceFeeCalc;
	}
	public String getDiscountMsg() {
		return discountMsg;
	}
	public void setDiscountMsg(String discountMsg) {
		this.discountMsg = discountMsg;
	}
	
	public String getAgentNameRegion() {
		return agentNameRegion;
	}
	public void setAgentNameRegion(String agentNameRegion) {
		this.agentNameRegion = agentNameRegion;
	}
	public String getAgentNameRegionS() {
		return agentNameRegionS;
	}
	public void setAgentNameRegionS(String agentNameRegionS) {
		this.agentNameRegionS = agentNameRegionS;
	}
	public int getIsTwoHundredArea() {
		return isTwoHundredArea;
	}
	public void setIsTwoHundredArea(int isTwoHundredArea) {
		this.isTwoHundredArea = isTwoHundredArea;
	}
	public int getAddTime() {
		return addTime;
	}
	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

	
}