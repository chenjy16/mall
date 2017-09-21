package com.meiduimall.service.catalog.result;

import java.util.List;

public class ParentShopCat {

	private Integer catId; //店铺自定义分类ID
	
	private String catName; //店铺自定义分类名字
	
	private List<ChildShopCat> childShopCat; //子分类

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public List<ChildShopCat> getChildShopCat() {
		return childShopCat;
	}

	public void setChildShopCat(List<ChildShopCat> childShopCat) {
		this.childShopCat = childShopCat;
	}
}
