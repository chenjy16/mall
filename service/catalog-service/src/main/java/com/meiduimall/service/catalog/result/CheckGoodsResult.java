package com.meiduimall.service.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * json数据构造对象--返回商品详情页访问地址
 * 
 * @author yangchang
 *
 */
public class CheckGoodsResult {

	// 商品的访问地址
	private String url;

	// 商品ID
	@JsonProperty("item_id")
	private String itemId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
