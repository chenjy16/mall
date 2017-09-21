package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录请求映射实体
 * @author chencong
 *
 */
public class RequestLogin  implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="用户名不能为空")
	@Length(max=100,message="用户名长度不正确")
	private String user_name;
	
	@NotEmpty(message="密码不能为空")
	@Length(min=32,max=32,message="密码长度不正确")
	private String password;
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RequestLogin [user_name=" + user_name + ", password=" + password + "]";
	}
	
}
