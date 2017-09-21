package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


/**
 * 设置支付密码请求映射实体
 * @author chencong
 *
 */
public class RequestSetPaypwdStatus extends RequestGetMemberBasicInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**是否启用支付密码*/
	@NotEmpty
	@Length(min=1,max=1,message="enable参数长度不正确")
	@NumberFormat(style=Style.NUMBER)
	private String enable;

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}	
	
}
