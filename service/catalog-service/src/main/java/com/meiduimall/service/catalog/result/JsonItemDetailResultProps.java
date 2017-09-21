package com.meiduimall.service.catalog.result;

import java.util.List;

/**
 * 商品规格
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResultProps {
	
	private String propId;//规格ID。比如：4,5
	private String propName;//规格名称。比如：颜色、服装尺码
	private List<JsonItemDetailResultPropValues> propList;

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public List<JsonItemDetailResultPropValues> getPropList() {
		return propList;
	}

	public void setPropList(List<JsonItemDetailResultPropValues> propList) {
		this.propList = propList;
	}
}
