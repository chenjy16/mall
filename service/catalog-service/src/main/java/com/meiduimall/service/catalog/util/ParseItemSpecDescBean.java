package com.meiduimall.service.catalog.util;

import java.util.List;

public class ParseItemSpecDescBean {

	private Integer propId;// 规格ID -- 对应syscategory_props表的prop_id字段
	private List<PropValueBean> propValueBeanList;

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public List<PropValueBean> getPropValueBeanList() {
		return propValueBeanList;
	}

	public void setPropValueBeanList(List<PropValueBean> propValueBeanList) {
		this.propValueBeanList = propValueBeanList;
	}

	public class PropValueBean {
		private Integer specValueId; //规格属性ID -- 对应syscategory_prop_values表的prop_value_id字段
		private String specValue; //规格属性ID对应的值 -- 对应syscategory_prop_values表的prop_value字段
		
		public Integer getSpecValueId() {
			return specValueId;
		}
		public void setSpecValueId(Integer specValueId) {
			this.specValueId = specValueId;
		}
		public String getSpecValue() {
			return specValue;
		}
		public void setSpecValue(String specValue) {
			this.specValue = specValue;
		}
	}
}
