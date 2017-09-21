package com.meiduimall.service.catalog.result;

import java.util.List;

/**
 * json数据构造对象--返回商品详情信息
 * @author yangchang
 *
 */
public class ListGoodsDetailResult {

	private List<GoodsDetailResult> results;

	public List<GoodsDetailResult> getResults() {
		return results;
	}

	public void setResults(List<GoodsDetailResult> results) {
		this.results = results;
	}
}
