package com.meiduimall.core.util;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumberUtils {
	
	private NumberUtils(){}

	private static Logger logger = LoggerFactory.getLogger(NumberUtils.class);

	/**
	 * 保留两位小数点，自动四舍五入
	 *
	 * @param money
	 * @return
	 */
	public static String formatMoney(double money) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			return decimalFormat.format(money);
		} catch (Exception e) {
			logger.error("数字格式化异常： " + e);
			return String.valueOf(money);
		}
	}

	/**
	 * 保留三位小数点，自动四舍五入
	 *
	 * @param money
	 * @return
	 */
	public static String formatMoney3(String money) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("#.###");
			float value = Float.parseFloat(money);
			return decimalFormat.format(value);
		} catch (Exception e) {
			logger.error("数字格式化异常： " + e);
			return String.valueOf(money);
		}
	}

	/**
	 * 四舍五入，保留double数值的指定位小数
	 *
	 * @param orignal
	 *            原始double值
	 * @param count
	 *            需要保留的小数位数
	 * @return
	 */
	public static double formatDouble(double orignal, int count) {
		if (count <= 0) {
			return (int) (orignal + 0.5);
		}
		double pow = Math.pow(10, count);
		int temp = (int) ((orignal * pow) + 0.5);
		return temp / pow;
	}

	/**
	 * 四舍五入，保留float数值的指定位小数
	 *
	 * @param orignal
	 *            原始float值
	 * @param count
	 *            需要保留的小数位数
	 * @return
	 */
	public static float formatFloat(float orignal, int count) {
		if (count <= 0) {
			return (int) (orignal + 0.5);
		}
		int pow = (int) Math.pow(10, count);
		int temp = (int) ((orignal * pow) + 0.5f);
		return temp / (pow * 1.0f);
	}

	/**
	 * 保留指定的小数点位数，自动四舍五入
	 *
	 * @param orignal
	 *            原始的小数
	 * @param count
	 *            需要保留的小数位数
	 * @return
	 */
	public static String formatString(double orignal, int count) {

		StringBuilder sb = new StringBuilder();
		if (count <= 0) {
			sb.append("#");
		} else {
			sb.append("#.");
		}

		for (int i = 0; i < count; i++) {
			sb.append("#");
		}

		try {
			DecimalFormat decimalFormat = new DecimalFormat(sb.toString());
			return decimalFormat.format(orignal);
		} catch (Exception e) {
			logger.error("数字格式化异常： " + e);
			return String.valueOf(orignal);
		}
	}
}
