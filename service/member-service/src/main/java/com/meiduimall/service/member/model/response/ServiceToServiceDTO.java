package com.meiduimall.service.member.model.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名:  ServiceToServiceDTO<br>
 * 描述:  service到service数据传递对像<br>
 * 创建时间: 2016-12-2
 */
public class ServiceToServiceDTO extends RequestDTO{

	private static final long serialVersionUID = 4523354427763027082L;
	
	/** 执行成功  */
	public static final String EXEC_SUCCESS = "1"; 
	/** 执行失败 */
	public static final String EXEC_FAILED = "0";
	/** 执行成功没有数据返回  */
	public static final String EXEC_SUCCESS_NO_DATA = "2";
	/** 执行成功有数据返回  */
	public static final String EXEC_SUCCESS_RESULT_DATA = "3";
	
	/**
	 * 执行标志
	 */
	private String execFlag = EXEC_SUCCESS;

	/**
	 * 请求字符串
	 */
	private String reqStr;
	
	/**
	 * 业务主键，可存放mem_id、order_id
	 */
	private String bsId;
	
	/**
	 * 返回值，一般返回字符串时使用
	 */
	private String resultStr;
	
	/**
	 * 返回值，一般返回数值时使用
	 */
	private Integer resultInt;
	
	/**
	 * 请求集合类 list
	 */
	private List<Object> reqList = new ArrayList<Object>();
	
	/**
	 * 请求集合类 map
	 */
	private Map<String,String> reqMap = new HashMap<String,String>();
	
	/**
	 * 请求JSONObject
	 */
	private JSONObject reqJsonObj;
	
	/**
	 * 返回值，一般返回数据集合时使用
	 */
	private List<Object> resultList = new ArrayList<Object>();
	
	/**
	 * 返回值，一般返回数据集合时使用
	 */
	private Map<String,String> resultMap = new HashMap<String,String>();

	/**
	 * 返回值，一般返回JSONObject数据时使用
	 */
	private JSONObject resultJsonObj;

	public String getExecFlag() {
		return execFlag;
	}

	public void setExecFlag(String execFlag) {
		this.execFlag = execFlag;
	}

	public String getReqStr() {
		return reqStr;
	}

	public void setReqStr(String reqStr) {
		this.reqStr = reqStr;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getBsId() {
		return bsId;
	}

	public void setBsId(String bsId) {
		this.bsId = bsId;
	}

	public List<Object> getReqList() {
		return reqList;
	}

	public void setReqList(List<Object> reqList) {
		this.reqList = reqList;
	}

	public Map<String, String> getReqMap() {
		return reqMap;
	}

	public void setReqMap(Map<String, String> reqMap) {
		this.reqMap = reqMap;
	}

	public JSONObject getReqJsonObj() {
		return reqJsonObj;
	}

	public void setReqJsonObj(JSONObject reqJsonObj) {
		this.reqJsonObj = reqJsonObj;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public JSONObject getResultJsonObj() {
		return resultJsonObj;
	}

	public void setResultJsonObj(JSONObject resultJsonObj) {
		this.resultJsonObj = resultJsonObj;
	}

	public Integer getResultInt() {
		return resultInt;
	}

	public void setResultInt(Integer resultInt) {
		this.resultInt = resultInt;
	}
	
	/**
	 * 方法名: reqJsonObjectToReqMap<br>
	 * 描述:  Json转换成Map<br>
	 * 创建时间: 2016-12-5
	 */
	public void reqJsonObjectToReqMap() {
		if (reqJsonObj != null && reqMap != null) {
			
			Iterator<String> iterator = reqJsonObj.keySet().iterator();
			while(iterator.hasNext())
			{
				 String key = iterator.next();
				 String value = reqJsonObj.getString(key);
				 
				 reqMap.put(key, value);
			}
		}
	}
	
}
