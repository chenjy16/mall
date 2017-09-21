package com.meiduimall.service.member.model;

import java.io.Serializable;
import java.util.Date;

public class MSMembersPaypwdRecord implements Serializable{

	private static final long serialVersionUID = -3552072481661780293L;
	//会员ID
	private String memId;
	
	private String updatePwd1;
	//MD5支付密码
	private String updatePwd2;
	private String updatePwd3;
	private String updatePwd4;
	private String updatePwd5;
	//是否启用支付密码
	private int updateIndex;
	//更新时间
	private Date updateDate;
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getUpdatePwd1() {
		return updatePwd1;
	}
	public void setUpdatePwd1(String updatePwd1) {
		this.updatePwd1 = updatePwd1;
	}
	public String getUpdatePwd2() {
		return updatePwd2;
	}
	public void setUpdatePwd2(String updatePwd2) {
		this.updatePwd2 = updatePwd2;
	}
	public String getUpdatePwd3() {
		return updatePwd3;
	}
	public void setUpdatePwd3(String updatePwd3) {
		this.updatePwd3 = updatePwd3;
	}
	public String getUpdatePwd4() {
		return updatePwd4;
	}
	public void setUpdatePwd4(String updatePwd4) {
		this.updatePwd4 = updatePwd4;
	}
	public String getUpdatePwd5() {
		return updatePwd5;
	}
	public void setUpdatePwd5(String updatePwd5) {
		this.updatePwd5 = updatePwd5;
	}
	public int getUpdateIndex() {
		return updateIndex;
	}
	public void setUpdateIndex(int updateIndex) {
		this.updateIndex = updateIndex;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}