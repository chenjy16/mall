package com.meiduimall.service.catalog.result;

/**
 * 每一个商品规格分别对应的值
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResultPropValues {
	
	private String propValueId;//规格属性ID
	private String propValue;//规格属性名称，比如：颜色规格--红色、蓝色，服装尺码--XXL、XXXL
	
	public String getPropValueId() {
		return propValueId;
	}
	public void setPropValueId(String propValueId) {
		this.propValueId = propValueId;
	}
	public String getPropValue() {
		return propValue;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
}
