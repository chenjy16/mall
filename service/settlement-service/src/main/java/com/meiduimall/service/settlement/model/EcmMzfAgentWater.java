package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 保证金流水
 * 
 * @author guidl
 * 
 */
public class EcmMzfAgentWater implements Serializable {

	private static final long serialVersionUID = -3327132566895489828L;

	// id
	private int id;

	// 保证金流水编号
	private String agentWaterId;

	// 代理的编号
	private String code;

	// 流水金额
	private double money;

	// 流水时间
	private Timestamp agentWaterTime;
	
	//获得积分
	private int score;
	
	//是否已更新到会员中心 0：未更新，1：已更新
	private int scoreStatus;
	
	//手机号
	private String phone;
	
	//角色类型 1-区代、2-个代
	private int type;
	
	//代理费比例
	private int agentRate;
	
	//代理id
	private int agentId;
	
	//推荐单号
	private String recNo;

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getAgentRate() {
		return agentRate;
	}

	public void setAgentRate(int agentRate) {
		this.agentRate = agentRate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(int scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentWaterId() {
		return agentWaterId;
	}

	public void setAgentWaterId(String agentWaterId) {
		this.agentWaterId = agentWaterId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Timestamp getAgentWaterTime() {
		return agentWaterTime;
	}

	public void setAgentWaterTime(Timestamp agentWaterTime) {
		this.agentWaterTime = agentWaterTime;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EcmMzfAgentWater [id=" + id + ", agentWaterId=" + agentWaterId
				+ ", code=" + code + ", money=" + money + ", agentWaterTime="
				+ agentWaterTime + ", score=" + score + ", scoreStatus="
				+ scoreStatus + ", phone=" + phone + ", type=" + type
				+ ", agentRate=" + agentRate + "]";
	}


}
