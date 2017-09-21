package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

/**
 * 代理商
 * 
 * @author guidl
 *
 */
public class EcmAgent implements Serializable {

	private static final long serialVersionUID = 2617681316901272701L;

	// 唯一标识
	@NotNull(message="id不能为空")
	private int id;

	// 代理商编号
	@NotNull(message="代理编号不能为空")
	private String agentNo;

	// 绑定手机
	@NotNull(message="手机号码不能为空")
	private String bindPhone;

	// 姓名
	@NotNull(message="姓名不能为空")
	private String userName;

	// 会员名
	@NotNull(message="会员名不能为空")
	private String misUserId;

	// 公司名称
	@NotNull(message="公司名称不能为空")
	private String companyName;

	// 个代 履约保证金(元)
	@NotNull(message="个代 履约保证金不能为空")
	private int cashDeposit;

	// 缴费余额
	private int depositLeftAmount;

	// 推荐人编号
	@NotNull(message="推荐人编号不能为空")
	private String recommenderCode;

	// 推荐人姓名
	@NotNull(message="推荐人姓名不能为空")
	private String recommenderName;

	// 推荐人联系电话
	@NotNull(message="推荐人联系电话不能为空")
	private String recommenderPhone;

	// 推荐单号
	@NotNull(message="推荐单号不能为空")
	private String recNo;

	// 推荐类型
	@NotNull(message="推荐类型不能为空")
	private int recType;

	// 创建人代理编号
	@NotNull(message="创建人代理编号不能为空")
	private String addAgentNo;

	// 创建人手机号码
	@NotNull(message="创建人手机号码不能为空")
	private String addBindPhone;

	// 创建人公司名称
	@NotNull(message="创建人公司名称不能为空")
	private String addCompanyName;

	// 创建人余款
	@NotNull(message="创建人余款不能为空")
	private int addDepositLeftAmount;
	
	private Timestamp opTime;

	public int getId() {
		return id;
	}

	public Timestamp getOpTime() {
		return opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(String bindPhone) {
		this.bindPhone = bindPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMisUserId() {
		return misUserId;
	}

	public void setMisUserId(String misUserId) {
		this.misUserId = misUserId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(int cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public int getDepositLeftAmount() {
		return depositLeftAmount;
	}

	public void setDepositLeftAmount(int depositLeftAmount) {
		this.depositLeftAmount = depositLeftAmount;
	}

	public String getRecommenderCode() {
		return recommenderCode;
	}

	public void setRecommenderCode(String recommenderCode) {
		this.recommenderCode = recommenderCode;
	}

	public String getRecommenderName() {
		return recommenderName;
	}

	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}

	public String getRecommenderPhone() {
		return recommenderPhone;
	}

	public void setRecommenderPhone(String recommenderPhone) {
		this.recommenderPhone = recommenderPhone;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public int getRecType() {
		return recType;
	}

	public void setRecType(int recType) {
		this.recType = recType;
	}

	public String getAddAgentNo() {
		return addAgentNo;
	}

	public void setAddAgentNo(String addAgentNo) {
		this.addAgentNo = addAgentNo;
	}

	public String getAddBindPhone() {
		return addBindPhone;
	}

	public void setAddBindPhone(String addBindPhone) {
		this.addBindPhone = addBindPhone;
	}

	public String getAddCompanyName() {
		return addCompanyName;
	}

	public void setAddCompanyName(String addCompanyName) {
		this.addCompanyName = addCompanyName;
	}

	public int getAddDepositLeftAmount() {
		return addDepositLeftAmount;
	}

	public void setAddDepositLeftAmount(int addDepositLeftAmount) {
		this.addDepositLeftAmount = addDepositLeftAmount;
	}

	@Override
	public String toString() {
		return "EcmAgent [id=" + id + ", agentNo=" + agentNo + ", bindPhone=" + bindPhone + ", userName=" + userName
				+ ", misUserId=" + misUserId + ", companyName=" + companyName + ", cashDeposit=" + cashDeposit
				+ ", depositLeftAmount=" + depositLeftAmount + ", recommenderCode=" + recommenderCode
				+ ", recommenderName=" + recommenderName + ", recommenderPhone=" + recommenderPhone + ", recNo=" + recNo
				+ ", recType=" + recType + ", addAgentNo=" + addAgentNo + ", addBindPhone=" + addBindPhone
				+ ", addCompanyName=" + addCompanyName + ", addDepositLeftAmount=" + addDepositLeftAmount + "]";
	}
	
	
}