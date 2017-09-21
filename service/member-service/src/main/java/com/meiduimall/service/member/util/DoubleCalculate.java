package com.meiduimall.service.member.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 小数运算工具类
 * @author chencong
 *
 */
public class DoubleCalculate {

	/**
	 * 提供精确加法计算的add方法
	 * 
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确减法运算的sub方法
	 * 
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 转换成两位小数
	 * @param str 需要转换的小数
	 * @return
	 */
	public static String getFormalValueTwo(String str) {
		if (str == null || str == "") {
			return "0.00";
		} else {
			if (str.indexOf(",") != -1) {
				str = str.replace(",", "");
				Double d = new Double(str);
				DecimalFormat formater = new DecimalFormat("0.00");
				formater.setMaximumFractionDigits(2);
				formater.setGroupingSize(0);
				formater.setRoundingMode(RoundingMode.FLOOR);
				return formater.format(d).toString();
			} else {
				Double d = new Double(str);
				DecimalFormat formater = new DecimalFormat("0.00");
				formater.setMaximumFractionDigits(2);
				formater.setGroupingSize(0);
				formater.setRoundingMode(RoundingMode.FLOOR);
				return formater.format(d).toString();
			}
		}
	}
}
