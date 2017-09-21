package com.meiduimall.service.catalog.result;

import java.util.List;

/**
 * APP商品详情页，请求接口返回json数据，对应的实体类
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResult {

	private JsonItemDetailResultShopData shopData;//店铺相关
	private JsonItemDetailResultItemData itemData;//商品相关
	private List<JsonItemDetailResultSku> skuList;//SKU相关
	private List<JsonItemDetailResultProps> itemPropsList;//规格相关
	
	public JsonItemDetailResultShopData getShopData() {
		return shopData;
	}
	public void setShopData(JsonItemDetailResultShopData shopData) {
		this.shopData = shopData;
	}
	public JsonItemDetailResultItemData getItemData() {
		return itemData;
	}
	public void setItemData(JsonItemDetailResultItemData itemData) {
		this.itemData = itemData;
	}
	public List<JsonItemDetailResultSku> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<JsonItemDetailResultSku> skuList) {
		this.skuList = skuList;
	}
	public List<JsonItemDetailResultProps> getItemPropsList() {
		return itemPropsList;
	}
	public void setItemPropsList(List<JsonItemDetailResultProps> itemPropsList) {
		this.itemPropsList = itemPropsList;
	}
}

