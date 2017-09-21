package com.meiduimall.service.sms.mapper;

import com.meiduimall.service.sms.entity.SendSmsHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsSendHistoryMapper {

	int insert(SendSmsHistory record);

	int insertSelective(SendSmsHistory record);

}