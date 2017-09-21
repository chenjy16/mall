package com.meiduimall.service.catalog.request;

import javax.validation.constraints.NotNull;

public class ShopProductRequest {

	// 店铺ID
	@NotNull
	private Integer shopId;

	// 店铺自定义分类ID，可以为空
	private Integer shopCatId;

	// 排序字段：store 按销量，updateTime 按修改时间，price 按价格，point 按积分；默认 store 按销量
	private String sortBy;

	// 排序规则：desc 降序，asc 升序；默认 desc 降序
	private String column;

	// 页数，从1开始，默认为1
	private Integer pageNo;
	
	// 每页显示数量，默认20
	private Integer pageSize;
	
	// 开始位置
	private Integer limitBegin;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShopCatId() {
		return shopCatId;
	}

	public void setShopCatId(Integer shopCatId) {
		this.shopCatId = shopCatId;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getLimitBegin() {
		return limitBegin;
	}

	public void setLimitBegin(Integer limitBegin) {
		this.limitBegin = limitBegin;
	}
}
