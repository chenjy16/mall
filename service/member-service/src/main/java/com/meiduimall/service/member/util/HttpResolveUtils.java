package com.meiduimall.service.member.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

public class HttpResolveUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpResolveUtils.class);
	
	/**
	 * 将HTTP GET String转换为JsonObject
	 * @param request HttpServletRequest对象
	 * @return 解析后的JSONObject对象
	 */
	public static JSONObject readGetStringToJsonObject(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String,String[]> parameterMap = request.getParameterMap();
			Iterator<String> paIter = parameterMap.keySet().iterator();
			while (paIter.hasNext()) {
				String key = paIter.next().toString();
				String[] values = (String[]) parameterMap.get(key);
				jsonObject.put(key, values[0]);
			}
		} catch (Exception e) {
			logger.error("exec readGetStringToJsonObject() error:{}",e.getMessage());
			throw e;
		}
		return jsonObject;
	}
	
	/**
	 * 将HTTP POST FORM形式请求转成jsonobject
	 * @param request HttpServletRequest对象
	 * @return 解析后的JSONObject对象
	 * @throws Exception 
	 */
	public static JSONObject readPostFormToJsonObject(HttpServletRequest request) throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			Enumeration<String> parameterMap = request.getParameterNames();
			while (parameterMap.hasMoreElements()) {
				String key =parameterMap.nextElement();
				String value= (String) request.getParameter(key);
				jsonObject.put(key,value);
			} 
		} catch (Exception e) {
			logger.error("exec readPostFormToJsonObject() error:{}",e.getMessage());
			throw e;
		}
		return jsonObject;
	}

	/**
	 * 将HTTP POST JSON转换为JsonObject
	 * @param request HttpServletRequest对象
	 * @return 解析后的JSONObject对象
	 * @throws IOException 
	 */
	public static JSONObject readStreamToJsonObject(HttpServletRequest request) throws IOException {
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(request.getInputStream(), "UTF-8");
			StringBuffer sb = new StringBuffer();
			int size = 0;
			char[] buf = new char[1024];
			while ((size = isr.read(buf)) != -1) {
				sb.append(buf, 0, size);
			}
			JSONObject jsonObject=JSONObject.parseObject(sb.toString());
			isr.close();
			return jsonObject;
		} catch (Exception e) {
			logger.error("exec readStreamToJsonObject() error:{}",e.getMessage());
			throw e;
		} 
		finally {
			isr.close();
		}
	}
}
