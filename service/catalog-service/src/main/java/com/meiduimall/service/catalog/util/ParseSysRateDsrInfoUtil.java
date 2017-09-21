package com.meiduimall.service.catalog.util;

import java.util.HashMap;
import java.util.Map;

import org.phprpc.util.AssocArray;
import org.phprpc.util.PHPSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangchang
 *
 */
public class ParseSysRateDsrInfoUtil {

	private static Logger logger = LoggerFactory.getLogger(ParseSysRateDsrInfoUtil.class);

	private ParseSysRateDsrInfoUtil() {
	}

	/**
	 * 反序列化表sysrate_dsr中的序列化数据，并计算店铺评分 {1=0, 2=0, 3=0, 4=1, 5=3}
	 * 
	 * @param content
	 *            评分内容，格式：a:5:{i:1;i:0;i:2;i:0;i:3;i:0;i:4;i:11;i:5;i:118;}
	 * @return 分值
	 */
	public static float getValue(String content) {

		if (content == null || "null".equals(content) || content.trim().length() == 0) {
			return 5.0f;
		}

		// 开始反序列化
		PHPSerializer p = new PHPSerializer();
		AssocArray array = null;
		try {
			array = (AssocArray) p.unserialize(content.getBytes("utf-8"));
		} catch (Exception e) {
			logger.error("计算店铺评分异常： " + e);
			return 5.0f;
		}

		if (array == null || array.isEmpty()) {
			return 5.0f;
		}
		@SuppressWarnings("unchecked")
		HashMap<Integer, Integer> map = array.toHashMap();
		if (map == null || map.size() == 0) {
			return 5.0f;
		}

		// 开始计算评分
		int sum = 0;
		int count = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			int tempSum = key * value;
			count += value;
			sum += tempSum;
		}

		if (count == 0) {
			return 5.0f;
		} else {
			return (sum * 1.0f) / count;
		}
	}
}
