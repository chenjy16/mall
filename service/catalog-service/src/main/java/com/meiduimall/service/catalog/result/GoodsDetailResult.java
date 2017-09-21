package com.meiduimall.service.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询结果--商品详情对象
 * @author yangchang
 *
 */
public class GoodsDetailResult {
	
	@JsonProperty("item_id")
	private String itemId;
	
	private String title;
	
	@JsonProperty("sub_title")
	private String subTitle;
	
	private String price;
	
	private String point;
	
	@JsonProperty("image_default_id")
	private String imageDefaultId;
	
	private String url;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getImageDefaultId() {
		return imageDefaultId;
	}

	public void setImageDefaultId(String imageDefaultId) {
		this.imageDefaultId = imageDefaultId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
