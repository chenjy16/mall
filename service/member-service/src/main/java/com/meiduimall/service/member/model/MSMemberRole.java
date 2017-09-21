package com.meiduimall.service.member.model;

import java.io.Serializable;


/**
 * 会员角色表
 * @author chencong
 *
 */
public class MSMemberRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 会员ID **/
	private String memId;

	/** 角色ID **/
	private String roleId;

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return this.roleId;
	}
}
