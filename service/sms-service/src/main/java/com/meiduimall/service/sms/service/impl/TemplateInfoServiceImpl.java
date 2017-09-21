package com.meiduimall.service.sms.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.Constants;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.entity.TemplateInfo;
import com.meiduimall.service.sms.mapper.SmsTemplateInfoMapper;
import com.meiduimall.service.sms.service.TemplateInfoService;

@Service
public class TemplateInfoServiceImpl implements TemplateInfoService {

	private static Logger logger = LoggerFactory.getLogger(TemplateInfoServiceImpl.class);

	@Autowired
	private SmsTemplateInfoMapper smsTemplateInfoMapper;

	@Override
	public String getTemplateList(String key) {

		// 先从redis缓存中取出所有的短信模板信息，取不到再从数据库取
		String templateListJsonStr = RedisUtils.get(key);
		if (StringUtils.isEmpty(templateListJsonStr)) {
			try {
				// 查询数据库--所有的短信模板信息
				List<TemplateInfo> list = smsTemplateInfoMapper.getTemplateInfoList();
				if (null != list && !list.isEmpty()) {
					// 将数据库查询到的数据转为json，并存到redis
					templateListJsonStr = JsonUtils.beanToJsonAndFmDate(list);
					RedisUtils.setex(key, Constants.REDIS_TENMINUTE, templateListJsonStr);
				}
			} catch (Exception e) {
				logger.error("获取所有的短信模板信息列表异常：" + e);
				throw new ServiceException(SmsApiCode.EXCEPTION_ACCESS_TEMPLATE,
						SmsApiCode.getZhMsg(SmsApiCode.EXCEPTION_ACCESS_TEMPLATE));
			}
		}
		return templateListJsonStr;
	}
}
