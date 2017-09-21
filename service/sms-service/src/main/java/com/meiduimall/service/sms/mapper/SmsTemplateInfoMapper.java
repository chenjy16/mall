package com.meiduimall.service.sms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meiduimall.service.sms.entity.TemplateInfo;

@Mapper
public interface SmsTemplateInfoMapper {

	int insert(TemplateInfo record);

	int insertSelective(TemplateInfo record);

	List<TemplateInfo> getTemplateInfoList();

}