package com.meiduimall.service.catalog.result;

import java.util.List;

public class ShopProductList {

	private int pageSize;
	private int pageNo;
	private int totalPage;
	
	private List<ShopGoodsDetailResult> productList;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<ShopGoodsDetailResult> getProductList() {
		return productList;
	}

	public void setProductList(List<ShopGoodsDetailResult> productList) {
		this.productList = productList;
	}
}
