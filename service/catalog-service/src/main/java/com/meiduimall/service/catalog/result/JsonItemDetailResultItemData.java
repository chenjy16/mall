package com.meiduimall.service.catalog.result;

/**
 * 商品详情
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResultItemData {

	private String itmeId;
	private String price;// 商品价格
	private String point;// 可用美兑积分
	private String title;// 商品标题、商品名称
	private String subTitle;// 子标题
	private String listImage;// 商品图片集合
	private String imageDefaultId;// 商品默认图片
	private String isCollect;// 用户是否收藏了该商品--根据token判断:1表示收藏，0表示没有收藏
	private String salesVolume;// 商品销量
	private String rateCount;// 被评论数量
	private String htmlDetailUrl;// HTML商品详情页
	private String bn;// 商品编号
	private String weight;// 商品重量(毛重)
	private String approveStatus;// 商品状态：onsale 出售中，instock 库中
	private String listTime;// 商品上架时间
	private String itemStore;// 商品库存
	private String isShowWeight;//是否显示重量

	public String getItmeId() {
		return itmeId;
	}

	public void setItmeId(String itmeId) {
		this.itmeId = itmeId;
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

	public String getListImage() {
		return listImage;
	}

	public void setListImage(String listImage) {
		this.listImage = listImage;
	}

	public String getImageDefaultId() {
		return imageDefaultId;
	}

	public void setImageDefaultId(String imageDefaultId) {
		this.imageDefaultId = imageDefaultId;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(String salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getRateCount() {
		return rateCount;
	}

	public void setRateCount(String rateCount) {
		this.rateCount = rateCount;
	}

	public String getHtmlDetailUrl() {
		return htmlDetailUrl;
	}

	public void setHtmlDetailUrl(String htmlDetailUrl) {
		this.htmlDetailUrl = htmlDetailUrl;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getListTime() {
		return listTime;
	}

	public void setListTime(String listTime) {
		this.listTime = listTime;
	}

	public String getItemStore() {
		return itemStore;
	}

	public void setItemStore(String itemStore) {
		this.itemStore = itemStore;
	}

	public String getIsShowWeight() {
		return isShowWeight;
	}

	public void setIsShowWeight(String isShowWeight) {
		this.isShowWeight = isShowWeight;
	}
}
