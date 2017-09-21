package com.meiduimall.service.sms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.exception.SystemException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.constant.SysConstant;
import com.meiduimall.service.sms.entity.SendSmsHistory;
import com.meiduimall.service.sms.entity.TemplateInfo;
import com.meiduimall.service.sms.mapper.SmsSendHistoryMapper;
import com.meiduimall.service.sms.request.CheckCodeRequest;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;
import com.meiduimall.service.sms.result.VerificationCodeResult;
import com.meiduimall.service.sms.service.AliyunService;
import com.meiduimall.service.sms.service.SmsService;
import com.meiduimall.service.sms.service.TemplateInfoService;
import com.meiduimall.service.sms.service.ZucpService;
import com.meiduimall.service.sms.util.ToSecondsUtils;

@Service
public class SmsServiceImpl implements SmsService {

	private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private ZucpService zucpService;

	@Autowired
	private AliyunService aliyunService;

	@Autowired
	private TemplateInfoService templateInfoService;

	@Autowired
	private SmsSendHistoryMapper smsSendHistoryMapper;

	@Override
	public ResBodyData sendSmsMessage(SendMessageRequest model) {

		// redis存取数据时，使用的key
		String redisKey = model.getPhones() + model.getTemplateKey() + model.getParams();

		// 检查是否已在超时时间内，给该手机发送了短信
		String tempMsg = RedisUtils.get(redisKey);
		if (StringUtils.isNotEmpty(tempMsg)) {
			throw new ServiceException(SmsApiCode.REPEATING, BaseApiCode.getZhMsg(SmsApiCode.REPEATING));
		}

		// 获取消息模板--这里获取到的是所有的模板信息的json数据
		String templateListJsonStr = templateInfoService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if (StringUtils.isEmpty(templateListJsonStr)) {
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE_LIST,
					SmsApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE_LIST));
		}

		// 根据模板ID获取短信模板
		TemplateInfo ti = getTemplateByKey(model.getTemplateKey(), templateListJsonStr);
		if (ti == null || StringUtils.isEmpty(ti.getTemplateKey()) || StringUtils.isEmpty(ti.getTemplateContent())) {
			// 如果没有模板编号，或者模板内容，则抛异常
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE,
					SmsApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE));
		}

		// 替换模板内容参数
		String content = ti.getTemplateContent();
		content = replacesContent(model.getParams(), content);

		// 转换发送给阿里大于的替换模板参数格式，以json格式
		String params = "";
		if (StringUtils.isNotEmpty(model.getParams())) {
			params = aliDaYuParamsToJson(false, model.getParams());
		}

		/**
		 * 首先阿里云发送发送短信，如果发送失败则调用漫道发送。 全部失败则返回失败信息。
		 */
		boolean flag = aliyunService.send(model.getPhones(), ti.getExternalTemplateNo(), params);
		logger.info("阿里大于发送短信结果flag：" + flag);
		String res = "-1000";
		if (!flag) {
			try {
				res = zucpService.send(model.getPhones(), content);
			} catch (SystemException e) {
				logger.info("漫道发送短信结果异常：" + e);
				throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
			}
			logger.info("漫道发送短信结果res：" + res);
			try {
				if (Long.parseLong(res) < 0) {
					throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER,
							BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
				}
			} catch (NumberFormatException e) {
				logger.info("漫道发送短信结果res异常：" + e);
				throw new ServiceException(SmsApiCode.SMS_SEND_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
			}
		}

		try {
			// 发送成功，缓存到redis，设置缓存时间
			int expire = ToSecondsUtils
					.parseDuration(model.getTimeout() == null ? ti.getEffectiveTime() : model.getTimeout());
			RedisUtils.setex(redisKey, expire, content);

			// 发送成功,设置发送历史记录值,数据库保留历史记录
			SendSmsHistory ssh = setHistory(model);
			ssh.setRequestParams(model.getPhones() + " ; ali_send_external_template_no: " + ti.getExternalTemplateNo()
					+ " ; ali_send_param:" + params + " ; mandao_send_content: " + content);
			ssh.setResultMsg("ali result, flag: " + flag + " ; mandao result, res: " + res);
			smsSendHistoryMapper.insert(ssh);
		} catch (Exception e) {
			logger.info("短信发送成功，缓存到redis，保存到数据库历史记录异常：" + e);
		}

		ResBodyData result = new ResBodyData();
		result.setStatus(SmsApiCode.SUCCESS);
		result.setMsg(SmsApiCode.getZhMsg(SmsApiCode.SMS_SEND_SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	@Override
	public ResBodyData sendSmsVerificationCode(SendCodeRequest model) {

		String redisKey = model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateKey();

		// 检查是否已在超时时间内，给该手机发送了短信
		String tempMsg = RedisUtils.get(redisKey);
		if (StringUtils.isNotEmpty(tempMsg)) {
			throw new ServiceException(SmsApiCode.REPEATING, BaseApiCode.getZhMsg(SmsApiCode.REPEATING));
		}

		// 生成6位随机数
		String randomNumber = String.valueOf((Math.random() * 9 + 1) * 100000).substring(0, 6);
		logger.info("发送短信生成的验证码为：" + randomNumber);

		// 获取消息模板--这里获取到的是所有的模板信息的json数据
		String templateListJsonStr = templateInfoService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
		if (StringUtils.isEmpty(templateListJsonStr)) {
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE_LIST,
					BaseApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE_LIST));
		}

		// 根据模板ID获取短信模板
		TemplateInfo ti = getTemplateByKey(model.getTemplateKey(), templateListJsonStr);
		if (ti == null || StringUtils.isEmpty(ti.getTemplateKey()) || StringUtils.isEmpty(ti.getTemplateContent())) {
			// 如果没有模板编号，或者模板内容，则抛异常
			throw new ServiceException(SmsApiCode.NOT_FOUND_TEMPLATE,
					BaseApiCode.getZhMsg(SmsApiCode.NOT_FOUND_TEMPLATE));
		}

		// 替换模板内容参数
		String content = ti.getTemplateContent();
		content = content.replace("{VerificationCode}", randomNumber);
		if (StringUtils.isNotEmpty(model.getParams())) {
			content = replacesContent(model.getParams(), content);
		}

		// 转换发送给阿里大于的替换模板参数格式，以json格式
		String params = "";
		if (StringUtils.isNotEmpty(model.getParams())) {
			params = aliDaYuParamsToJson(true, randomNumber + "," + model.getParams());
		}

		/**
		 * 首先阿里云发送发送短信，如果发送失败则调用漫道发送 </br>
		 * 全部失败则返回失败信息
		 */
		String res = "-1000";
		boolean flag = aliyunService.send(model.getPhones(), ti.getExternalTemplateNo(), params);
		logger.info("阿里大于发送验证码短信结果(flag): " + flag);
		if (!flag) {
			try {
				res = zucpService.send(model.getPhones(), content);
			} catch (SystemException e) {
				logger.info("漫道发送验证码结果异常：" + e);
				throw new ServiceException(SmsApiCode.SEND_CODE_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SEND_CODE_FAILUER));
			}
			logger.info("漫道发送验证码短信结果（res）:" + res);
			try {
				if (Long.parseLong(res) < 0) {
					throw new ServiceException(SmsApiCode.SEND_CODE_FAILUER,
							BaseApiCode.getZhMsg(SmsApiCode.SEND_CODE_FAILUER));
				}
			} catch (NumberFormatException e) {
				logger.info("漫道发送验证码短信结果（res）异常：" + e);
				throw new ServiceException(SmsApiCode.SEND_CODE_FAILUER,
						BaseApiCode.getZhMsg(SmsApiCode.SEND_CODE_FAILUER));
			}
		}

		try {
			// 发送成功，缓存到redis，设置缓存时间
			int expire = ToSecondsUtils
					.parseDuration(model.getTimeout() == null ? ti.getEffectiveTime() : model.getTimeout());
			RedisUtils.setex(redisKey, expire, randomNumber);
			// 发送成功,设置发送历史记录值,数据库保留历史记录
			SendSmsHistory ssh = setHistory(model);
			ssh.setRequestParams(model.getPhones() + " ; ali_send_external_template_no: " + ti.getExternalTemplateNo()
					+ " ; ali_send_param:" + params + " ; mandao_send_content: " + content);
			ssh.setResultMsg("ali result, flag: " + flag + " ; mandao result, res: " + res);
			smsSendHistoryMapper.insert(ssh);
		} catch (Exception e) {
			logger.info("验证码发送成功，保存到数据库历史记录异常：" + e);
		}

		// 返回验证码
		VerificationCodeResult data = new VerificationCodeResult();
		data.setVerificationCode(randomNumber);
		ResBodyData result = new ResBodyData();
		result.setStatus(SmsApiCode.SUCCESS);
		result.setMsg(SmsApiCode.getZhMsg(SmsApiCode.SEND_CODE_SUCCESS));
		result.setData(data);
		return result;
	}

	@Override
	public ResBodyData checkSmsVerificationCode(CheckCodeRequest model) {

		String redisKey = model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateKey();
		String tempVerificationCode = RedisUtils.get(redisKey);

		if (StringUtils.isEmpty(tempVerificationCode)) {
			logger.info(model.getPhones() + "验证码已过期: " + model.getVerificationCode());
			throw new ServiceException(SmsApiCode.SMS_VALID_CODE_EXPIRED,
					BaseApiCode.getZhMsg(SmsApiCode.SMS_VALID_CODE_EXPIRED));
		}
		if (!StringUtils.equalsIgnoreCase(model.getVerificationCode().trim(), tempVerificationCode)) {
			logger.info(model.getPhones() + "验证码不匹配: " + model.getVerificationCode());
			throw new ServiceException(SmsApiCode.SMS_VALID_CODE_UNMATCHED,
					BaseApiCode.getZhMsg(SmsApiCode.SMS_VALID_CODE_UNMATCHED));
		}

		RedisUtils.del(redisKey);
		logger.info("结束校验短信验证码程序:" + model.getPhones());

		ResBodyData result = new ResBodyData();
		result.setStatus(SmsApiCode.SUCCESS);
		result.setMsg(SmsApiCode.getZhMsg(SmsApiCode.CHECK_CODE_SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	/**
	 * 根据模板ID获取短信模板
	 * 
	 * @param templateKey
	 *            模板ID
	 * 
	 * @param templateListJsonStr
	 *            所有的模板信息的json数据
	 * 
	 * @return
	 */
	private TemplateInfo getTemplateByKey(String templateKey, String templateListJsonStr) {
		List<TemplateInfo> list = JsonUtils.jsonToList(templateListJsonStr, TemplateInfo.class);
		if (list != null && !list.isEmpty()) {
			for (TemplateInfo info : list) {
				if (info.getTemplateKey().equals(templateKey)) {
					return info;
				}
			}
		}
		return null;
	}

	/**
	 * 替换模板中的参数
	 *
	 * @param params
	 * @param content
	 * @return
	 */
	private String replacesContent(String params, String content) {
		String result = content;
		if (StringUtils.isNotEmpty(params)) {
			String[] replaces = params.split(",");
			if (replaces != null && replaces.length > 0) {
				int count = StringUtils.countMatches(result, "{");
				if (replaces.length < count) {
					logger.info("替换短信模板内容异常," + "替换内容与替换参数不匹配, replaces=" + replaces + ",count=" + count);
					throw new ServiceException(SmsApiCode.PARAM_ERROR, SmsApiCode.getZhMsg(SmsApiCode.PARAM_ERROR));
				}
				for (int index = 0; index < replaces.length; index++) {
					// 需要检查参数 add by simon
					result = result.replace("{" + index + "}", replaces[index]);
				}
				return result;
			}
		}
		return result;
	}

	/**
	 * 转换发送给阿里大于的替换模板参数格式，以json格式
	 *
	 * @param params
	 * @return
	 */
	private String aliDaYuParamsToJson(boolean isCode, String params) {
		Map<String, String> map = new HashMap<>();
		String[] paramsStr = params.split(",");
		for (int i = 0; i < paramsStr.length; i++) {
			if (!isCode && !StringUtils.isEmpty(paramsStr[i])) {
				map.put("V" + i, paramsStr[i]);
			} else {
				if (i == 0) {
					map.put("VerificationCode", paramsStr[i]);
				} else {
					map.put("V" + i, paramsStr[i]);
				}
			}
		}
		return JsonUtils.beanToJson(map);
	}

	/**
	 * 设置发送短信的历史记录值
	 * 
	 * @param model
	 * @return
	 */
	private SendSmsHistory setHistory(SendMessageRequest model) {
		SendSmsHistory ssh = new SendSmsHistory();
		ssh.setId(UUID.randomUUID().toString());
		ssh.setTemplateKey(model.getTemplateKey());
		ssh.setCreateDate(new Date());
		ssh.setCreater(model.getPhones());
		ssh.setPhone(model.getPhones());
		ssh.setRemark(model.getParams());
		return ssh;
	}

	/**
	 * 设置发送短信的历史记录值
	 * 
	 * @param model
	 * @return
	 */
	private SendSmsHistory setHistory(SendCodeRequest model) {
		SendSmsHistory ssh = new SendSmsHistory();
		ssh.setId(UUID.randomUUID().toString());
		ssh.setTemplateKey(model.getTemplateKey());
		ssh.setCreateDate(new Date());
		ssh.setCreater(model.getPhones());
		ssh.setPhone(model.getPhones());
		ssh.setRemark(model.getParams());
		return ssh;
	}
}
