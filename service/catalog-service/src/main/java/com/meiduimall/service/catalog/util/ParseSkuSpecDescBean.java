package com.meiduimall.service.catalog.util;

public class ParseSkuSpecDescBean {

	private Integer propId;// 规格ID -- 对应syscategory_props表的prop_id字段

	private Integer propValueId;// 规格属性ID --
								// 对应syscategory_prop_values表的prop_value_id字段
	private String specValue; // 规格属性ID对应的值 --
								// 对应syscategory_prop_values表的prop_value字段

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public Integer getPropValueId() {
		return propValueId;
	}

	public void setPropValueId(Integer propValueId) {
		this.propValueId = propValueId;
	}

	public String getSpecValue() {
		return specValue;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}
}
