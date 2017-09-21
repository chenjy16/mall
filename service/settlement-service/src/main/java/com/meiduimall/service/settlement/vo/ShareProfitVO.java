package com.meiduimall.service.settlement.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShareProfitVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String type;
	private String code;
	private BigDecimal profitToday;
	private BigDecimal profit4Settlement;
	
	//for 结算5.24接口 --刘万峰的新需求
	private BigDecimal totalProfit;

	public ShareProfitVO() {
		super();
	}
	
	public ShareProfitVO(String type, String code, BigDecimal profitToday, BigDecimal profit4Settlement) {
		super();
		this.type = type;
		this.code = code;
		this.profitToday = profitToday;
		this.profit4Settlement = profit4Settlement;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getProfitToday() {
		return profitToday;
	}

	public void setProfitToday(BigDecimal profitToday) {
		this.profitToday = profitToday;
	}

	public BigDecimal getProfit4Settlement() {
		return profit4Settlement;
	}
	public void setProfit4Settlement(BigDecimal profit4Settlement) {
		this.profit4Settlement = profit4Settlement;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
}