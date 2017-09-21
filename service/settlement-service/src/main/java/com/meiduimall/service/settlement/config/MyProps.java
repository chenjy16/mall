package com.meiduimall.service.settlement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myProps") // 接收application.yml中的myProps下面的属性
public class MyProps {

	private String oauthSignatureMethod;
	private String oauthVersion;
	private String oauthAccessorSecret;
	private String oauthConsumerKey;
	private String url;
	private String belong;
	private String apiUpdFirstReferrerCash;
	private String o2oUrl;
	private String o2oSaveOrderBillStatu;
	private String o2oAddProxyFee;
	private String o2oApiKey;
	private String smsUrl;
	private String smsSendMessage;
	private String smsPhones;
	
	public String getOauthSignatureMethod() {
		return oauthSignatureMethod;
	}
	public void setOauthSignatureMethod(String oauthSignatureMethod) {
		this.oauthSignatureMethod = oauthSignatureMethod;
	}
	public String getOauthVersion() {
		return oauthVersion;
	}
	public void setOauthVersion(String oauthVersion) {
		this.oauthVersion = oauthVersion;
	}
	public String getOauthAccessorSecret() {
		return oauthAccessorSecret;
	}
	public void setOauthAccessorSecret(String oauthAccessorSecret) {
		this.oauthAccessorSecret = oauthAccessorSecret;
	}
	public String getOauthConsumerKey() {
		return oauthConsumerKey;
	}
	public void setOauthConsumerKey(String oauthConsumerKey) {
		this.oauthConsumerKey = oauthConsumerKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getApiUpdFirstReferrerCash() {
		return apiUpdFirstReferrerCash;
	}
	public void setApiUpdFirstReferrerCash(String apiUpdFirstReferrerCash) {
		this.apiUpdFirstReferrerCash = apiUpdFirstReferrerCash;
	}
	public String getO2oUrl() {
		return o2oUrl;
	}
	public void setO2oUrl(String o2oUrl) {
		this.o2oUrl = o2oUrl;
	}
	public String getO2oSaveOrderBillStatu() {
		return o2oSaveOrderBillStatu;
	}
	public void setO2oSaveOrderBillStatu(String o2oSaveOrderBillStatu) {
		this.o2oSaveOrderBillStatu = o2oSaveOrderBillStatu;
	}
	public String getO2oAddProxyFee() {
		return o2oAddProxyFee;
	}
	public void setO2oAddProxyFee(String o2oAddProxyFee) {
		this.o2oAddProxyFee = o2oAddProxyFee;
	}
	public String getO2oApiKey() {
		return o2oApiKey;
	}
	public void setO2oApiKey(String o2oApiKey) {
		this.o2oApiKey = o2oApiKey;
	}
	public String getSmsUrl() {
		return smsUrl;
	}
	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}
	public String getSmsSendMessage() {
		return smsSendMessage;
	}
	public void setSmsSendMessage(String smsSendMessage) {
		this.smsSendMessage = smsSendMessage;
	}
	public String getSmsPhones() {
		return smsPhones;
	}
	public void setSmsPhones(String smsPhones) {
		this.smsPhones = smsPhones;
	}
	

}
