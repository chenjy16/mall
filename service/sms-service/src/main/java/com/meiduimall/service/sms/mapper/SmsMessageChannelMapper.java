package com.meiduimall.service.sms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meiduimall.service.sms.entity.MessageChannel;


@Mapper
public interface SmsMessageChannelMapper {

	int insert(MessageChannel record);

	int insertSelective(MessageChannel record);

	List<MessageChannel> getChannelList();

}