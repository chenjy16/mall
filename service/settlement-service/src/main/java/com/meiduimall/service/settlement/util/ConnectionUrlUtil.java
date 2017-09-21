package com.meiduimall.service.settlement.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ConnectionUrlUtil.java
 * Author:   不详
 * Date:     2016年12月26日 下午6:15:47
 * Description: HttpURLConnection工具类
 */
public class ConnectionUrlUtil {

	private static Logger log = LoggerFactory.getLogger(ConnectionUrlUtil.class);
	
	private ConnectionUrlUtil(){}
	
	/**
	 * Http请求
	 * @param requestUrl 请求url
	 * @param requestMethod 请求方式POST或GET
	 * @param outputStr 输出数据
	 * @return String
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		HttpURLConnection httpConn = null;
		InputStream input = null;
		BufferedReader bufferedReader = null;
		InputStreamReader reader = null;
		StringBuilder buffer = new StringBuilder();
		try {
			log.info("请求地址:{}", requestUrl);
			log.info("请求方法:{}", requestMethod);
			
			URL url = new URL(requestUrl);
			//打开Http链接
			httpConn = (HttpURLConnection)url.openConnection();
			
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false); //不使用缓存
			httpConn.setRequestMethod(requestMethod);//设置请求方式
			
			if (null != outputStr) {
				//获取输出工作流
				OutputStream output = httpConn.getOutputStream();
				//写入输出数据
				output.write(outputStr.getBytes("UTF-8"));
				//释放
				output.close();
			}
			
			//从输入流读取返回的内容
			input = httpConn.getInputStream();
			reader = new InputStreamReader(input, "UTF-8");
			bufferedReader = new BufferedReader(reader);
			buffer = new StringBuilder();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			log.error("http请求异常", e);
		} finally {
			//释放
			try {
				if(bufferedReader!=null){
					bufferedReader.close();
				}
				if(reader!=null){
					reader.close();
				}
				if(input!=null){
					input.close();
				}
				if(httpConn != null){
					httpConn.disconnect(); //关闭Http连接
				}
			} catch (IOException e) {
				log.error("关闭流异常", e);
			}
		}
		return buffer.toString();
	}
	
	
}
