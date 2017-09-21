package com.meiduimall.service.member.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统配置文件读取工具类
 * @author chencong
 *
 */
public class SystemConfig {
	
	private final static Logger logger=LoggerFactory.getLogger(SystemConfig.class);

	private static final SystemConfig systemConfig = new SystemConfig();
	public static Map<String, String> configMap;
	
	private SystemConfig() {
		if (configMap == null) {
			try {
				SystemConfig.configMap = loadProperty("config.properties");
			} catch (Exception e) {
				logger.error("exec SystemConfig() error:{}",e.toString());
			}
		}
	};
	public static SystemConfig getInstance() { 
		return systemConfig;
	}
	public static String get(String key) { 
			return SystemConfig.configMap.get(key);
	}
	
	/**
	 * 加载配置文件config.properties
	 * @param config 配置文件路径
	 * @return
	 */
	public static Map<String, String> loadProperty(String config) {
		InputStream is = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
			Properties pro = new Properties();
			pro.load(is);
			Iterator<Object> localIterator = pro.keySet().iterator();
			while (localIterator.hasNext()) {
				Object key = localIterator.next();
				map.put(key.toString(), pro.get(key).toString());
			}
		} catch (Exception ex) {
			System.out.println("配置文件:" + config + "加载出错!");
			ex.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				logger.error("exec loadProperty() error:{}",e.toString());
			}
		}
		return map;
	}
}
