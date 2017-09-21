package com.meiduimall.service.member.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.meiduimall.service.member.util.PageHelp;

public class MSAccountDetailGet extends PageHelp implements Serializable {
    private String id;

    private String accountId; //账户ID

    private String accountType; //账户类别（AT01：积分账户；AT02：现金账户；）

    private String memId; //会员ID

    private String tradeType; //交易类型

    private String tradeAmount; //交易金额

    private Date tradeDate; //发生时间

    private Integer inOrOut; //账户变更标识（1：收入；-1：支出）

    private String balance; //余额

    private String businessNo; //业务单号

    private String remark; //备注

    private Date createDate; //创建时间

    private Long currTime; //当前时间戳
    private List<Object> tradeTypeList;
    
    
    

    public List<Object> getTradeTypeList() {
		return tradeTypeList;
	}

	public void setTradeTypeList(List<Object> tradeTypeList) {
		this.tradeTypeList = tradeTypeList;
	}

	private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount == null ? null : tradeAmount.trim();
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Integer getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(Integer inOrOut) {
        this.inOrOut = inOrOut;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Long currTime) {
        this.currTime = currTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountId=").append(accountId);
        sb.append(", accountType=").append(accountType);
        sb.append(", memId=").append(memId);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", tradeAmount=").append(tradeAmount);
        sb.append(", tradeDate=").append(tradeDate);
        sb.append(", inOrOut=").append(inOrOut);
        sb.append(", balance=").append(balance);
        sb.append(", businessNo=").append(businessNo);
        sb.append(", remark=").append(remark);
        sb.append(", createDate=").append(createDate);
        sb.append(", currTime=").append(currTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}