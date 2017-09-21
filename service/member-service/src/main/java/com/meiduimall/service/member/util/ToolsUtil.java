package com.meiduimall.service.member.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SysParamsConst;

/**
 * 常用工具类
 * @author chencong
 *
 */
public class ToolsUtil {
	
	private final static Logger logger=LoggerFactory.getLogger(ToolsUtil.class);
	
	/**
	 * 创建登录令牌
	 * @param userId
	 * @return
	 * @throws SystemException 
	 */
	public final static String createToken(String userId) throws SystemException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(userId);
		buffer.append(SysParamsConst.CONNECTION);
		buffer.append(System.currentTimeMillis());
		return MD5Util.MD5EncryptBy32(buffer.toString());
	}


	/**
	 * 生成不重复随机数，生成方式：毫秒+5位随机数
	 * @return
	 */
	public final static String getNotRepeatRandom() {
		// 当前秒数
		String timeMillis = String.valueOf(System.currentTimeMillis() / 1000L);
		String newString = null;
		// 得到0.0到1.0之间的数字,并扩大100000倍
		double doubleP = Math.random() * 100000;
		// 如果数据等于100000,则减少1
		if (doubleP >= 100000) {
			doubleP = 99999;
		}
		// 然后把这个数字转化为不包含小数点的整数
		int tempString = (int) Math.ceil(doubleP);
		// 转化为字符串
		newString = "" + tempString;
		// 把得到的数增加为固定长度,为5位
		while (newString.length() < 5) {
			newString = "0" + newString;
		}
		return (timeMillis + newString);
	}
	
	/**
	 * 计算现在距离第二天的时间差
	 * @return 秒数
	 * @throws SystemException
	 * @throws ParseException 
	 */
	public final static int getNowToTomorrowTimeSub() throws SystemException{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long from;
		long to;
		try {
			from = new Date().getTime();
			to = simpleFormat.parse(getTomorrowZeroClockTime()).getTime();
		} catch (ParseException e) {
			logger.error("执行getNowToTomorrowTimeSub()方法异常：{}",e.toString());
			throw new SystemException(ApiStatusConst.PARSE_DATE_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.PARSE_DATE_EXCEPTION));
		} 
		int minutes = (int) (to - from)/1000;
		return minutes;
	}
	
	/**
	 * 获取明天的凌晨0点0分的时间
	 * @return
	 * @throws ParseException 
	 */
	private final static String getTomorrowZeroClockTime() throws ParseException{
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,1);
		date=calendar.getTime();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return simpleFormat.format(date);
	}

}