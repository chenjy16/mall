package com.meiduimall.payment.api.model.hicardpay;

import com.meiduimall.payment.api.model.wechat.WeChatResponeModel;

import java.io.Serializable;

/**
 * @author nico
 * @since 2017/2/22.
 */
public class HiCardResponeModel implements Serializable {

    private static final long serialVersionUID = -895234807004845641L;

    private String version;
    private String organNo;
    private String hicardMerchNo;
    private String payType;
    private String merchOrderNo;
    private String hicardOrderNo;
    private String isHtml;
    private String html;
    private String qrURL;
    private String showPage;
    private String amount;
    private String createTime;
    private HiCardPayInfo payInfo;
    private String respCode;
    private String respMsg;
    private String sign;
    private String channelOrderNo;

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

    public String getMerchOrderNo() {
        return merchOrderNo;
    }

    public void setMerchOrderNo(String merchOrderNo) {
        this.merchOrderNo = merchOrderNo;
    }

    public String getHicardOrderNo() {
        return hicardOrderNo;
    }

    public void setHicardOrderNo(String hicardOrderNo) {
        this.hicardOrderNo = hicardOrderNo;
    }

    public String getIsHtml() {
        return isHtml;
    }

    public void setIsHtml(String isHtml) {
        this.isHtml = isHtml;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getQrURL() {
        return qrURL;
    }

    public void setQrURL(String qrURL) {
        this.qrURL = qrURL;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

  

    public HiCardPayInfo getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(HiCardPayInfo payInfo) {
		this.payInfo = payInfo;
	}

	public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

	public String getChannelOrderNo() {
		return channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}
    
    
}
