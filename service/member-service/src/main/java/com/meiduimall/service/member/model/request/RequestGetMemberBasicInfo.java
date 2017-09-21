package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 设置支付密码请求映射实体
 * @author chencong
 *
 */
public class RequestGetMemberBasicInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 会员系统ID **/
	@NotEmpty(message="memId不能为空")
	@Length(min=36,max=36,message="memId参数长度不正确")
	private String memId;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
}
