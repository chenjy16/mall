package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分润实体
 * @author  许彦雄
 *
 */
public class EcmMzfShareProfit implements Serializable {

	private static final long serialVersionUID = -819772399023048586L;
	
	//订单号
	private String orderSn;
	
	//交易号（pos流水）
	private String outTradeSn;
	
	//商户编号
	private String sellerId;
	
	//商家电话
	private String sellerPhone;
	
	//商户所获积分
	private BigDecimal sellerPoint;
	
	//商户分润
	private BigDecimal sellerProfit;
	
	//个代编号
	private String personAgentId;
	
	//个代分润
	private BigDecimal personAgentProfit;
	
	//区代编号（若跨区，则保存本区）
	private String areaAgentId;
	
	//区代分润（若跨区，则保存本区）
	private BigDecimal areaAgentProfit;
	
	//用户手机号
	private String phone;
	
	//一级推荐人编号
	private String belongOnePhone;
	
	//二级推荐人编号
	private String belongTwoPhone;
	
	//跨区区代编码
	private String outareaAgentId;
	
	//跨区区代分润
	private BigDecimal outareaAgentProfit;
	
	//订单金额
	private BigDecimal orderFee;
	
	//订单来源，0=POS机 1=扫码支付 2=保证金
	private String source;
	
	//用户所获积分
	private BigDecimal point;
	
	//一级推荐人所获积分
	private BigDecimal belongOnePoint;
	
	//二级推荐人所获积分
	private BigDecimal belongTwoPoint;
	
	//是否跨区 0=否 1=是
	private String outArea;
	
	//支付时间
	private Integer payTime;
	
	//商家分润比例
	private BigDecimal sellerShareprofitRate;
	
	//商家获取积分比例
	private BigDecimal sellerPointRate;
	
	//个代分润比例
	private BigDecimal personShareprofitRate;
	
	//区代分润比例
	private BigDecimal areaShareprofitRate;
	
	//跨区区代分润比例
	private BigDecimal outareaShareprofitRate;
	
	//一级推荐人获取的现金
	private BigDecimal firstReferrerCash;
	
	//一级推荐人获取的现金比例
	private BigDecimal firstReferrerCashRate;
	
	private Integer orderDate;
	
	private Integer createdDate;
	
	private Integer status;  // order status 订单状态0.交易取消10.已提交11.待付款20已付款30已发货40交易成功50.退款中60.已退款80接收退款90.退款申诉
	
	private Integer shareStatus;
	private Integer billStatus;
	private Integer scoreStatus;
	private Integer cashStatus;
	private String statusDesc;
	
	//流水详情的代理分润金额
	private BigDecimal profit;
	
	// 账单时间
	private Date billDate;
	
	// 账单生成时间
    private Date billAddDate;
    
    private String billDateStr;
    private String billAddDateStr;
    
    //林总说的新需求:没有汇总商家收益，并按服务费率返回补贴，该需求还没正式提。
	private Integer serviceFee;
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

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	public BigDecimal getSellerPoint() {
		return sellerPoint;
	}

	public void setSellerPoint(BigDecimal sellerPoint) {
		this.sellerPoint = sellerPoint;
	}

	public BigDecimal getSellerProfit() {
		return sellerProfit;
	}

	public void setSellerProfit(BigDecimal sellerProfit) {
		this.sellerProfit = sellerProfit;
	}

	public String getPersonAgentId() {
		return personAgentId;
	}

	public void setPersonAgentId(String personAgentId) {
		this.personAgentId = personAgentId;
	}

	public BigDecimal getPersonAgentProfit() {
		return personAgentProfit;
	}

	public void setPersonAgentProfit(BigDecimal personAgentProfit) {
		this.personAgentProfit = personAgentProfit;
	}

	public String getAreaAgentId() {
		return areaAgentId;
	}

	public void setAreaAgentId(String areaAgentId) {
		this.areaAgentId = areaAgentId;
	}

	public BigDecimal getAreaAgentProfit() {
		return areaAgentProfit;
	}

	public void setAreaAgentProfit(BigDecimal areaAgentProfit) {
		this.areaAgentProfit = areaAgentProfit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOutareaAgentId() {
		return outareaAgentId;
	}

	public void setOutareaAgentId(String outareaAgentId) {
		this.outareaAgentId = outareaAgentId;
	}

	public BigDecimal getOutareaAgentProfit() {
		return outareaAgentProfit;
	}

	public void setOutareaAgentProfit(BigDecimal outareaAgentProfit) {
		this.outareaAgentProfit = outareaAgentProfit;
	}

	public BigDecimal getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(BigDecimal orderFee) {
		this.orderFee = orderFee;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public String getOutArea() {
		return outArea;
	}

	public void setOutArea(String outArea) {
		this.outArea = outArea;
	}



	public BigDecimal getSellerShareprofitRate() {
		return sellerShareprofitRate;
	}

	public void setSellerShareprofitRate(BigDecimal sellerShareprofitRate) {
		this.sellerShareprofitRate = sellerShareprofitRate;
	}
	
	public BigDecimal getSellerPointRate() {
		return sellerPointRate;
	}

	public void setSellerPointRate(BigDecimal sellerPointRate) {
		this.sellerPointRate = sellerPointRate;
	}

	public BigDecimal getPersonShareprofitRate() {
		return personShareprofitRate;
	}

	public void setPersonShareprofitRate(BigDecimal personShareprofitRate) {
		this.personShareprofitRate = personShareprofitRate;
	}

	public BigDecimal getAreaShareprofitRate() {
		return areaShareprofitRate;
	}

	public void setAreaShareprofitRate(BigDecimal areaShareprofitRate) {
		this.areaShareprofitRate = areaShareprofitRate;
	}

	public BigDecimal getOutareaShareprofitRate() {
		return outareaShareprofitRate;
	}

	public void setOutareaShareprofitRate(BigDecimal outareaShareprofitRate) {
		this.outareaShareprofitRate = outareaShareprofitRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBelongOnePhone() {
		return belongOnePhone;
	}

	public void setBelongOnePhone(String belongOnePhone) {
		this.belongOnePhone = belongOnePhone;
	}

	public String getBelongTwoPhone() {
		return belongTwoPhone;
	}

	public void setBelongTwoPhone(String belongTwoPhone) {
		this.belongTwoPhone = belongTwoPhone;
	}

	public BigDecimal getBelongOnePoint() {
		return belongOnePoint;
	}

	public void setBelongOnePoint(BigDecimal belongOnePoint) {
		this.belongOnePoint = belongOnePoint;
	}

	public BigDecimal getBelongTwoPoint() {
		return belongTwoPoint;
	}

	public void setBelongTwoPoint(BigDecimal belongTwoPoint) {
		this.belongTwoPoint = belongTwoPoint;
	}

	public BigDecimal getFirstReferrerCash() {
		return firstReferrerCash;
	}

	public void setFirstReferrerCash(BigDecimal firstReferrerCash) {
		this.firstReferrerCash = firstReferrerCash;
	}

	public BigDecimal getFirstReferrerCashRate() {
		return firstReferrerCashRate;
	}

	public void setFirstReferrerCashRate(BigDecimal firstReferrerCashRate) {
		this.firstReferrerCashRate = firstReferrerCashRate;
	}

	public Integer getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Integer orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Integer createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPayTime() {
		return payTime;
	}

	public void setPayTime(Integer payTime) {
		this.payTime = payTime;
	}

	public Integer getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(Integer shareStatus) {
		this.shareStatus = shareStatus;
	}

	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}

	public Integer getScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(Integer scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	public Integer getCashStatus() {
		return cashStatus;
	}

	public void setCashStatus(Integer cashStatus) {
		this.cashStatus = cashStatus;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getBillAddDate() {
		return billAddDate;
	}

	public void setBillAddDate(Date billAddDate) {
		this.billAddDate = billAddDate;
	}

	public String getBillDateStr() {
		return billDateStr;
	}

	public void setBillDateStr(String billDateStr) {
		this.billDateStr = billDateStr;
	}

	public String getBillAddDateStr() {
		return billAddDateStr;
	}

	public void setBillAddDateStr(String billAddDateStr) {
		this.billAddDateStr = billAddDateStr;
	}

	public Integer getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Integer serviceFee) {
		this.serviceFee = serviceFee;
	}

	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

}