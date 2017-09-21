package com.meiduimall.service.member.model.response;

import java.util.Date;

/**
 * 用户消费信息和积分冻结解冻混合DTO
 * @author chencong
 *
 */
public class MemberConsumeMessageDTO {
	
	/**memid*/
	private String memId;

	/** 订单ID **/
	private String order_id;
	
	/**积分冻结解冻编号**/
	private String mcpf_id;
	
	/**会员消费记录编号**/
	private String mcp_id;

	/** 消费金额 **/
	private String consumer_money;
	
	/** 商品名称 **/
	private String product_name;

	/** 消费来源 **/
	private String order_source;
	
	/** 商家赠送积分 **/
	private String back_integral;
	
	/** 支付类型 1：表示单独使用消费劵支付2：混合支付3: 其他第三方支付*/
	private String pay_type;
	
	/**订单状态1已支付，2表示已退单,3表示未支付*/
	private String mch_status;
	
	/**1冻结 2解冻*/
	private String freeType;
	
	/** 美兑积分 **/
	private String consume_points_count;
	
	/**积分余额*/
	private String mcpfConsumePointsBalance;
	
	
	/**  创建人  */
	private String mcpfCreatedBy;

	/**  创建时间  */
	private Date mcpfCreatedDate;
	
	/**  更新人  */
	private String mcpfUpdatedBy;
	
	/**  更新时间  */
	private Date mcpfUpdatedDate;
	
	/**  备注  */
	private String mcpfRemark;

	public String getMcpfConsumePointsBalance() {
		return mcpfConsumePointsBalance;
	}

	public void setMcpfConsumePointsBalance(String mcpfConsumePointsBalance) {
		this.mcpfConsumePointsBalance = mcpfConsumePointsBalance;
	}

	public String getMch_status() {
		return mch_status;
	}

	public void setMch_status(String mch_status) {
		this.mch_status = mch_status;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getBack_integral() {
		return back_integral;
	}

	public void setBack_integral(String back_integral) {
		this.back_integral = back_integral;
	}



	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getConsumer_money() {
		return consumer_money;
	}

	public void setConsumer_money(String consumer_money) {
		this.consumer_money = consumer_money;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getOrder_source() {
		return order_source;
	}

	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}


	public String getFreeType() {
		return freeType;
	}

	public void setFreeType(String freeType) {
		this.freeType = freeType;
	}


	public String getConsume_points_count() {
		return consume_points_count;
	}

	public void setConsume_points_count(String consume_points_count) {
		this.consume_points_count = consume_points_count;
	}
	
	public String getMemId() {
		return memId;
	}
	
	public String getMcpf_id() {
		return mcpf_id;
	}

	public void setMcpf_id(String mcpf_id) {
		this.mcpf_id = mcpf_id;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMcpfCreatedBy() {
		return mcpfCreatedBy;
	}

	public void setMcpfCreatedBy(String mcpfCreatedBy) {
		this.mcpfCreatedBy = mcpfCreatedBy;
	}

	public java.util.Date getMcpfCreatedDate() {
		return mcpfCreatedDate;
	}

	public void setMcpfCreatedDate(java.util.Date mcpfCreatedDate) {
		this.mcpfCreatedDate = mcpfCreatedDate;
	}

	public String getMcpfUpdatedBy() {
		return mcpfUpdatedBy;
	}

	public void setMcpfUpdatedBy(String mcpfUpdatedBy) {
		this.mcpfUpdatedBy = mcpfUpdatedBy;
	}

	public java.util.Date getMcpfUpdatedDate() {
		return mcpfUpdatedDate;
	}

	public void setMcpfUpdatedDate(java.util.Date mcpfUpdatedDate) {
		this.mcpfUpdatedDate = mcpfUpdatedDate;
	}

	public String getMcpfRemark() {
		return mcpfRemark;
	}

	public void setMcpfRemark(String mcpfRemark) {
		this.mcpfRemark = mcpfRemark;
	}
	
	public String getMcp_id() {
		return mcp_id;
	}

	public void setMcp_id(String mcp_id) {
		this.mcp_id = mcp_id;
	}
	
}
