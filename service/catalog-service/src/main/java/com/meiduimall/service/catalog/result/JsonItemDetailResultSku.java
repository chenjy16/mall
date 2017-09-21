package com.meiduimall.service.catalog.result;

/**
 * 商品SKU
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResultSku {
	
	private String skuId;
	private String price;// 该规格商品价格
	private String weight;// 该规格商品重量
	//private String spec_info;// 该规格描述信息
	private String status;// sku状态: normal 代表正常， delete 代表删除
	private String point;// 该规格商品可用美兑积分
	
	// 由syscategory_prop_values表中的prop_value_id组成的字符串，格式如下： 51_4_76
	private String propValueIds;
	
	private String skuStore;//每一个SKU对应的库存

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPropValueIds() {
		return propValueIds;
	}

	public void setPropValueIds(String propValueIds) {
		this.propValueIds = propValueIds;
	}

	public String getSkuStore() {
		return skuStore;
	}

	public void setSkuStore(String skuStore) {
		this.skuStore = skuStore;
	}
}
