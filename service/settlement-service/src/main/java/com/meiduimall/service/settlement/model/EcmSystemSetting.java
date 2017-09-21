package com.meiduimall.service.settlement.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Title : EcmSystemSetting 
 * Description : 系统设置实体类
 * Created By : Fkx 
 * Creation Time : 2016-10-19 上午10:38:24 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public class EcmSystemSetting{
	
	private Integer sid;
	
	@NotBlank(message="scode不能为空")
	private String scode;
	
	@NotBlank(message="value值不能为空")
	private String value;
	
	@NotNull(message="state状态不能为空")
	private Integer state;
	
	private String desc;
	
	 
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	 
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}