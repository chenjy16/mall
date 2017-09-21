package com.meiduimall.service.sms.entity;

import java.io.Serializable;
import java.util.Date;

public class TemplateInfo implements Serializable {

	private static final long serialVersionUID = -7636461159238284256L;

	private String id;

	private String channelId;

	private String templateKey;

	private String externalTemplateNo;

	private String templateName;

	private String templateContent;

	private String groupKey;

	private String remark;

	private String creater;

	private Date createDate;

	private String effectiveTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId == null ? null : channelId.trim();
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey == null ? null : templateKey.trim();
	}

	public String getExternalTemplateNo() {
		return externalTemplateNo;
	}

	public void setExternalTemplateNo(String externalTemplateNo) {
		this.externalTemplateNo = externalTemplateNo == null ? null : externalTemplateNo.trim();
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName == null ? null : templateName.trim();
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent == null ? null : templateContent.trim();
	}

	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey == null ? null : groupKey.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime == null ? null : effectiveTime.trim();
	}
}