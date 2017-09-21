package com.meiduimall.service.member.util;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.*;


public class StringUtil {
	interface StringValidate {
		/** 匹配邮箱 */
		static final String EMAIL = "^[_A-Za-z0-9]+@[a-z0-9]+\\.[a-z0-9]+$";
		/** 匹配电话号码 */
//		static final String IS_PHONE = "(1[356789][0-9][0-9]{8}|[0-9]{1,4}-[0-9]{7})";
		/**
		 * 中国大陆加港澳台手机正则:
		 * ^[1][3-8]\\d{9}$|^([6|9])\\d{7}$|^[0][9]\\d{8}$|^[6]([8|6])\\d{5}$
		 *  中国大陆：开头1 3-8号段，后边跟9位数字
		 *	台湾：09开头后面跟8位数字
		 *	香港：9或6开头后面跟7位数字
		 *	澳门：66或68开头后面跟5位数字
		 */
		//大陆和香港手机正则表达式
		static final String IS_PHONE = "^[1][3-8]\\d{9}$|^([6|9])\\d{7}$";
		/** 匹配正整数 */
		static final String IS_NUMBER = "^[0-9]\\d{0,10}$";
		/** 整数 */
		static final String PNUMBER = "^-?\\d+$";

		/** 特殊字符定义 不包含英文_ */
		static final String MEMBER_STRING = "[-——`~!@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？.]";
		/** 特殊字符定义 不包含英文_，。 */
		static final String MEMBER_STRINGES = "[-——`~!@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’、？.]";

		/** 特殊字符定义 */
		static final String TTRING = "[-_——`~!@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？.]";
		/** 特殊字符定义 */
		static final String TTRING_TOUXIANG = "[-——`~!@#$%^&*()+=|{}';',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

		/** 特殊字符定义 不包含中文 **/

		static final String TEXT_TTRING = "[-——`~!@#$%^&*()|{}':;',\\[\\]\"<>~@#￥%……&*（）——|{}；：./]";

		/** 特殊字符定义 不包含英文_@. */
		static final String MEMBER_STRING_REGIST = "[-_——`~!@@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？./]";
		
		static final String regExes ="[^a-zA-Z0-9]"; 
	}

	private static final Log log = LogFactory.getLog(StringUtil.class);

	/** 检查集合是否为空 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmptyByCollection(Collection c) {
		return ((c == null) || (c.size() == 0));
	}

	/**
	 * 检查日期格式
	 * 
	 * @param date
	 * @return true 正确
	 */
	public static boolean checkDate(String date) {
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean b = m.matches();
		return b;
	}
	/**
	 * 检查整数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数
	 * @return
	 */
	public static boolean checkNumber(String num, String type) {
		String eL = "";
		if (type.equals("0+"))
			eL = "^\\d+$";// 非负整数
		else if (type.equals("+"))
			eL = "^\\d*[1-9]\\d*$";// 正整数
		else if (type.equals("-0"))
			eL = "^((-\\d+)|(0+))$";// 非正整数
		else if (type.equals("-"))
			eL = "^-\\d*[1-9]\\d*$";// 负整数
		else
			eL = "^-?\\d+$";// 整数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 检查浮点数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数
	 * @return
	 */
	public static boolean checkFloat(String num, String type) {
		String eL = "";
		if (type.equals("0+"))
			eL = "^\\d+(\\.\\d+)?$";// 非负浮点数
		else if (type.equals("+"))
			eL = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";// 正浮点数
		else if (type.equals("-0"))
			eL = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";// 非正浮点数
		else if (type.equals("-"))
			eL = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";// 负浮点数
		else
			eL = "^(-?\\d+)(\\.\\d+)?$";// 浮点数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}
	/**
	 * 过滤特殊字符用(不包含_)
	 * 
	 * @param st
	 *            过滤前的字符
	 * @return 过滤之后的字符
	 */
	public static String stringByFilter(String st) {
		// 判断是否为空
		if ("".equals(st) || null == st) {
			return "";
		} else {
			// 特殊字符
			String regEx = StringValidate.MEMBER_STRING;
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(st);
			return m.replaceAll("").trim();
		}
	}
	/**
	 * 过滤特殊字符用
	 * 
	 * @param st
	 *            过滤前的字符
	 * @return 过滤之后的字符
	 */
	public static String stringByFilter_(String st) {
		// 判断是否为空
		if ("".equals(st) || null == st) {
			return "";
		} else {
			// 特殊字符
			String regEx = StringValidate.MEMBER_STRING_REGIST;
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(st);
			return m.replaceAll("").trim();
		}
	}
	public static String stringByFilter_str(String st) {
		// 判断是否为空
		if ("".equals(st) || null == st) {
			return "";
		} else {
			// 特殊字符
			String regEx = StringValidate.regExes;
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(st);
			return m.replaceAll("").trim();
		}
	}

	/**
	 * 过滤特殊字符用(不包含_.)
	 * 
	 * @param st
	 *            过滤前的字符
	 * @return 过滤之后的字符
	 */
	public static String stringByFilterTouxiang(String st) {
		// 判断是否为空
		if ("".equals(st) || null == st) {
			return "";
		} else {
			// 特殊字符
			String regEx = StringValidate.TTRING_TOUXIANG;
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(st);
			return m.replaceAll("").trim();
		}
	}

	/**
	 * 过滤特殊字符用(不包含，。_)
	 * 
	 * @param st
	 *            过滤前的字符
	 * @return 过滤之后的字符
	 */
	public static String stringByFilteres(String st) {
		// 判断是否为空
		if ("".equals(st) || null == st) {
			return "";
		} else {
			// 特殊字符
			String regEx = StringValidate.MEMBER_STRINGES;
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(st);
			return m.replaceAll("").trim();
		}
	}

	/**
	 * 所有特殊字符
	 * 
	 * @param st
	 *            过滤前的字符
	 * @return 过滤之后的字符
	 */
	public static String stringFilters(String str) {
		// 判断是否为空
		if ("".equals(str) || null == str) {
			return "";
		} else {
			// 特殊字符
			String regEx = StringValidate.TTRING;
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			return m.replaceAll("").trim();
		}
	}

	/**
	 * 判断是否含有特殊字符
	 * 
	 * @param st
	 *            过滤前的字符
	 * @return 过滤之后的字符
	 */
	public static boolean isExistingSpecial(String str) {
		// 特殊字符
		String regEx = StringValidate.MEMBER_STRING;
		if (str.length() > 0) {
			for (int i = 0; i < regEx.length(); i++) {
				if (str.indexOf(regEx.charAt(i)) >= 0) {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 公共的方法
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean checkObj(Object obj) {
		boolean bool = true;
		if (obj == null || "".equals(obj.toString().trim()))
			bool = false;
		return bool;
	}

	public static boolean checkStr(String str) {
		boolean bool = true;
		if (str == null || "".equals(str.trim()))
			bool = false;
		return bool;
	}

	public static String toString(Object obj) {
		return obj != null ? obj.toString() : "";
	}

	public static Long toLong(Object obj) {
		String str = toString(obj);
		return str != "" ? Long.parseLong(str) : 0L;
	}

	/***
	 * 基础json数据
	 * 
	 * @param dList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getBasetJsonData(List dList) {
		if (dList != null) {
			return JSONArray.toJSONString(dList);
		}
		return null;
	}

	/***
	 * 基础json数据
	 * 
	 * @param dMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getBasetJsonData(Map dMap) {
		if (dMap != null) {
			return JSONObject.toJSONString(dMap);
		}
		return null;
	}

	public static String formatDateTime(String dTime) {
		String dateTime = "";
		if (dTime != null && !"".equals(dTime)
				&& !dTime.startsWith("1900-01-01")) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}

	/**
	 * UTF-8编码 转换为对应的 汉字
	 * 
	 * @param s
	 * @return
	 */
	public static String convertUTF8ToString(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		try {
			s = s.toUpperCase();
			int total = s.length() / 2;
			int pos = 0;
			byte[] buffer = new byte[total];
			for (int i = 0; i < total; i++) {
				int start = i * 2;
				buffer[i] = (byte) Integer.parseInt(
						s.substring(start, start + 2), 16);
				pos++;
			}
			return new String(buffer, 0, pos, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("convertUTF8ToString异常:", e);
		}
		return s;
	}

	/**
	 * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
	 */
	public static String convertStringToUTF8(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			char c;
			for (int i = 0; i < s.length(); i++) {
				c = s.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;

					b = Character.toString(c).getBytes("utf-8");

					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append(Integer.toHexString(k).toUpperCase());
					}
				}
			}
		} catch (Exception e) {
			log.error("convertStringToUTF8异常:", e);
		}
		return sb.toString();
	}

	/**
	 * 正则表达式验证
	 */
	/**
	 * 判断是否为邮箱格式正则表达式验证
	 * 
	 * @param value要验证的字符
	 * @return true表示正确
	 */
	public static boolean isEmailToRegex(String value) {
		return getRegex(StringValidate.EMAIL, value);
	}

	/**
	 * 验证
	 * 
	 * @param regex正则表达式字符串
	 * @param value要验证的字符串
	 * @return 为真表示符合，为假表示不符合
	 */
	public static boolean getRegex(String regex, String value) {
		return Pattern.compile(regex).matcher(value).matches();
	}

	/**
	 * 判断手机号码
	 * 
	 * @param value要判断的字符
	 * @return true正确
	 */
	public static boolean isPhoneToRegex(String value) {
		if (isEmptyByString(value)) {
			return false;
		}
		return getRegex(StringValidate.IS_PHONE, value);
	}

	/**
	 * 检查字符串是否为空
	 * 
	 * @param value
	 * @return true 为空
	 */
	public static boolean isEmptyByString(String value) {
		return ((value == null)  || "null".equals(value) || "".equals(value) || (value.trim().length() == 0));
	}

	/**
	 * 判断是否为正整数
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isPNumber(String value) {
		return getRegex(StringValidate.IS_NUMBER, value);
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param value
	 * @return true表示是
	 */
	public static boolean isNumber(String value) {
		return getRegex(StringValidate.PNUMBER, value);
	}
	
	/**
	 * 判断字符串是否是纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		 String pattern = "^[1-9][0-9]*(\\.[0-9]+)?$"; 
		   return Pattern.compile(pattern).matcher(str).matches();
	}

	/**
	 * 判断是否是一个IP
	 * 
	 * @param IP
	 * @return
	 */
	public static boolean isIp(String IP) {
		boolean b = false;
		IP = IP.trim();
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		return b;
	}

	/**
	 * 自动生成自定义的字符长度（字母数字）
	 * 
	 * @param length
	 * @return
	 */
	public static String randomString(int length) {// 传入的字符串的长度
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			int r = (int) (Math.random() * 3);
			int rn1 = (int) (48 + Math.random() * 10);
			int rn2 = (int) (65 + Math.random() * 26);
			int rn3 = (int) (97 + Math.random() * 26);

			switch (r) {
			case 0:
				builder.append((char) rn1);
				break;
			case 1:
				builder.append((char) rn2);
				break;
			case 2:
				builder.append((char) rn3);
				break;
			}
		}
		return builder.toString();
	}
	
	/**
	 * @Description: 在一个字符串中查找一个子串出现的次数。
	 * @return int  
	 * @param str
	 * @param key
	 * @return
	 * @author 
	 * @date 2016-11-24
	 */
	public static int findSubstringCount(String str,String key) {
		int count = 0;
		int index = 0;
		if(str == null || key == null) {
			throw new RuntimeException("参数字符串不能为null！");
		}
		while((index = str.indexOf(key,index)) != -1) {
			index = index + key.length();
			count++;
		}
		return count;
	}
}