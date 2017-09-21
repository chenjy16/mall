package com.meiduimall.payment.api.model.hicardpay;

import java.io.Serializable;

/**
 * @author nico
 * @since 2017/2/22.
 */
public class HiCardRequestModel {

    private String version;
    private String organNo;
    private String hicardMerchNo;
    private String payType;
    private String bizType;
    private String goodsName;
    private String merchOrderNo;
    private String showPage;
    private String amount;
    private String certsNo;
    private String frontEndUrl;
    private String backEndUrl;
    private String sign;
    private String openId;
    private String isT0;
    private String remark;
    private String reserved;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrganNo() {
        return organNo;
    }

    public void setOrganNo(String organNo) {
        this.organNo = organNo;
    }

    public String getHicardMerchNo() {
        return hicardMerchNo;
    }

    public void setHicardMerchNo(String hicardMerchNo) {
        this.hicardMerchNo = hicardMerchNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMerchOrderNo() {
        return merchOrderNo;
    }

    public void setMerchOrderNo(String merchOrderNo) {
        this.merchOrderNo = merchOrderNo;
    }

    public String getShowPage() {
        return showPage;
    }

    public void setShowPage(String showPage) {
        this.showPage = showPage;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCertsNo() {
        return certsNo;
    }

    public void setCertsNo(String certsNo) {
        this.certsNo = certsNo;
    }

    public String getFrontEndUrl() {
        return frontEndUrl;
    }

    public void setFrontEndUrl(String frontEndUrl) {
        this.frontEndUrl = frontEndUrl;
    }

    public String getBackEndUrl() {
        return backEndUrl;
    }

    public void setBackEndUrl(String backEndUrl) {
        this.backEndUrl = backEndUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIsT0() {
        return isT0;
    }

    public void setIsT0(String isT0) {
        this.isT0 = isT0;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
