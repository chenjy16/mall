package com.meiduimall.service.catalog.result;

/**
 * 店铺信息
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResultShopData {
	
	private String shopId;
	private String shopName;// 店铺名称
	private String shopDescript;// 店铺描述
	
	// 店铺状态：self 运营商自营店铺， brand 品牌专卖店， cat 类目专营店， flag 品牌旗舰店
	private String shopType;
	
	private String shopLogo;// 店铺默认图片
	private String tallyDsr;// 店铺评分--描述相符
	private String attitudeDsr;// 店铺评分--描述相符
	private String deliverySpeedDsr;// 店铺评分--描述相符
	private String shopArea;//店铺所在地
	private String openTime;//开店时间
	private String isCollect;//是否收藏了该店铺：0没有收藏，1收藏了
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDescript() {
		return shopDescript;
	}
	public void setShopDescript(String shopDescript) {
		this.shopDescript = shopDescript;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public String getTallyDsr() {
		return tallyDsr;
	}
	public void setTallyDsr(String tallyDsr) {
		this.tallyDsr = tallyDsr;
	}
	public String getAttitudeDsr() {
		return attitudeDsr;
	}
	public void setAttitudeDsr(String attitudeDsr) {
		this.attitudeDsr = attitudeDsr;
	}
	public String getDeliverySpeedDsr() {
		return deliverySpeedDsr;
	}
	public void setDeliverySpeedDsr(String deliverySpeedDsr) {
		this.deliverySpeedDsr = deliverySpeedDsr;
	}
	public String getShopArea() {
		return shopArea;
	}
	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}
}
