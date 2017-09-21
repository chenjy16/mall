package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 商家送积分记录表
 * @author guidl
 *
 */
public class EcmMzfStoreRecord implements Serializable {

	private static final long serialVersionUID = 7413757322450416978L;

	//主键id
	private int id;
	
	//创建时间
	private Timestamp createdDate;
	
	//修改时间
	private Timestamp updatedDate;
	
	//商家编号
	private String storeNo;
	
	//手机号码
	private String phone;
	
	//积分
	private int score;
	
	//状态
	private int scoreStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
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

}
