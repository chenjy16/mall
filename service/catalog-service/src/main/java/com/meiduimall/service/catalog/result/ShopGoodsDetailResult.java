package com.meiduimall.service.catalog.result;

/**
 * 查询结果--商品详情对象
 * 
 * @author yangchang
 *
 */
public class ShopGoodsDetailResult {

	private String itemId;//商品ID
	private String title;//标题
	private String price;//商品价格
	private String point;//可用美兑积分
	private String imageDefaultId;//商品默认图片
	private String shopCatId;//商家自定义分类id
	private String quantity;//商品销量=实际销量+虚拟销量
	private String listTime;//商品上架时间

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

	public String getShopCatId() {
		return shopCatId;
	}

	public void setShopCatId(String shopCatId) {
		this.shopCatId = shopCatId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getListTime() {
		return listTime;
	}

	public void setListTime(String listTime) {
		this.listTime = listTime;
	}
}
