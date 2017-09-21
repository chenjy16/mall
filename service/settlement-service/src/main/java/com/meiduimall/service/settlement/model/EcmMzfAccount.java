package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * 商家账户实体对象
 * @author guidl
 *
 */
public class EcmMzfAccount implements Serializable {

	private static final long serialVersionUID = -6002930254810324757L;
	
	//账户编号
	private int accountId;
	
	//代理和商家的编码
	@NotNull(message="代理编号不能为空")
	private String code;
	
	//角色类型，1区代 2个代 3商家
	@NotNull(message="角色类型不能为空")
	private String accountRoleType;
	
	//账户余额
	private BigDecimal balance;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccountRoleType() {
		return accountRoleType;
	}

	public void setAccountRoleType(String accountRoleType) {
		this.accountRoleType = accountRoleType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "EcmMzfAccount [accountId=" + accountId + ", code=" + code + ", accountRoleType=" + accountRoleType
				+ ", balance=" + balance + "]";
	}

	
}
