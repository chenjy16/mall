package com.meiduimall.service.catalog.constant;

import com.meiduimall.core.BaseApiCode;

public class ServiceCatalogApiCode extends BaseApiCode {

	/** 请求成功 */
	public static final Integer REQUEST_SUCCESS = 7001;
	/** 请求失败 */
	public static final Integer REQUEST_FAIL = 7002;

	/** 操作成功 */
	public static final Integer OPERAT_SUCCESS = 7003;
	/** 操作失败 */
	public static final Integer OPERAT_FAIL = 7004;

	/** 请求参数错误 */
	public static final Integer REQUEST_PARAMS_ERROR = 7005;
	/** 暂无数据 */
	public static final Integer NONE_DATA = 7006;

	/** 请先登录 */
	public static final Integer NO_LOGIN = 7007;

	/** memId验证异常 */
	public static final Integer MEMID_VALIDATE_ERROR = 7011;

	/** 店铺数据异常 */
	public static final Integer SHOP_DATA_EXCEPTION = 7021;
	/** 该店铺已被收藏，再次收藏失败 */
	public static final Integer ALREADY_COLLECT = 7022;

	/** 收藏成功 */
	public static final Integer COLLECT_SUCCESS = 7023;
	/** 收藏失败 */
	public static final Integer COLLECT_FAIL = 7024;

	/** 取消收藏成功 */
	public static final Integer CANCEL_COLLECT_SUCCESS = 7025;
	/** 取消收藏失败 */
	public static final Integer CANCEL_COLLECT_FAIL = 7026;

	/** 商品规格数据异常 */
	public static final Integer SPEC_DESC_DATA_EXCEPTION = 7027;
	/** 商品SKU数据异常 */
	public static final Integer SKU_DATA_EXCEPTION = 7028;

	/** 没有这个店铺 */
	public static final Integer NO_THIS_SHOP = 7029;
	/** 没有这个商品 */
	public static final Integer NO_THIS_PRODUCT = 7030;

	/** 反序列化数据异常 */
	public static final Integer SERIALIZER_EXCEPTION = 7501;

	/** 输出异常 */
	public static final Integer OUT_PUT_EXCEPTION = 7502;

	/** 数据库错误 */
	public static final Integer DB_EXCEPTION = 7777;

	static {
		zhMsgMap.put(REQUEST_SUCCESS, "请求成功");
		zhMsgMap.put(REQUEST_FAIL, "请求失败");
		zhMsgMap.put(OPERAT_SUCCESS, "操作成功");
		zhMsgMap.put(OPERAT_FAIL, "操作失败");
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "请求参数错误");
		zhMsgMap.put(NONE_DATA, "暂无数据");
		zhMsgMap.put(NO_LOGIN, "请先登录");
		zhMsgMap.put(MEMID_VALIDATE_ERROR, "memId验证异常");

		zhMsgMap.put(SHOP_DATA_EXCEPTION, "店铺数据异常");
		zhMsgMap.put(ALREADY_COLLECT, "该店铺已被收藏，再次收藏失败");
		zhMsgMap.put(COLLECT_SUCCESS, "收藏成功");
		zhMsgMap.put(COLLECT_FAIL, "收藏失败");
		zhMsgMap.put(CANCEL_COLLECT_SUCCESS, "取消收藏成功");
		zhMsgMap.put(CANCEL_COLLECT_FAIL, "取消收藏失败");
		zhMsgMap.put(SPEC_DESC_DATA_EXCEPTION, "商品规格数据异常");
		zhMsgMap.put(SKU_DATA_EXCEPTION, "商品SKU数据异常");
		zhMsgMap.put(NO_THIS_SHOP, "没有这个店铺");
		zhMsgMap.put(NO_THIS_PRODUCT, "没有这个商品");
		zhMsgMap.put(SERIALIZER_EXCEPTION, "反序列化数据异常");
		zhMsgMap.put(OUT_PUT_EXCEPTION, "输出异常");
		zhMsgMap.put(DB_EXCEPTION, "数据库错误");
	}

	private ServiceCatalogApiCode() {
	}
}
