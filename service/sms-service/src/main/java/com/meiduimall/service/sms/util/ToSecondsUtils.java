package com.meiduimall.service.sms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * 
 * @author yangchang
 *
 */
public class ToSecondsUtils {

	public static final Pattern p = Pattern.compile("(([0-9]+?)((d|h|mi|min|mn|s)))+?");
	public static final Integer MINUTE = 60;
	public static final Integer HOUR = 60 * MINUTE;
	public static final Integer DAY = 24 * HOUR;
	public static final String D = "d";
	public static final String H = "h";
	public static final String MI = "mi";
	public static final String MN = "mn";
	public static final String MIN = "min";

	private ToSecondsUtils() {
	}

	/**
	 * Parse a duration
	 * 
	 * @param duration
	 *            3h, 2mn, 7s or combination 2d4h10s, 1w2d3h10s
	 * @return The number of seconds
	 */
	public static int parseDuration(String duration) {
		if (duration == null) {
			return 30 * DAY;
		}
		final Matcher matcher = p.matcher(duration);
		int seconds = 0;
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid duration pattern : " + duration);
		}
		matcher.reset();
		while (matcher.find()) {
			if (matcher.group(3).equals(D)) {
				seconds += Integer.parseInt(matcher.group(2)) * DAY;
			} else if (matcher.group(3).equals(H)) {
				seconds += Integer.parseInt(matcher.group(2)) * HOUR;
			} else if (matcher.group(3).equals(MI) || matcher.group(3).equals(MIN) || matcher.group(3).equals(MN)) {
				seconds += Integer.parseInt(matcher.group(2)) * MINUTE;
			} else {
				seconds += Integer.parseInt(matcher.group(2));
			}
		}
		return seconds;
	}
}
