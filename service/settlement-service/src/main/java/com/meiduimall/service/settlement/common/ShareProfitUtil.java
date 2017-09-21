package com.meiduimall.service.settlement.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.StringUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.meiduimall.core.Constants;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.util.DateUtil;
import com.meiduimall.service.settlement.vo.EcmMzfBillWaterVO;
import com.meiduimall.service.settlement.vo.ShareProfitVO;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ShareProfitUtil.java
 * Author:   许彦雄
 * Date:     2017年3月15日 下午6:15:47
 * Description: 分润工具类
 */
public class ShareProfitUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ShareProfitUtil.class);
	
	//大区个代提成比例
	public static final String PERSONAL_SCALE_FOR_BIG_REGION = "person_shareprofit_rate_for_big_region";
	
	//个代提成比例
	public static final String PERSONAL_SCALE = "person_shareprofit_rate";
	
	//区代提成比例
	public static final String AREA_SCALE = "area_shareprofit_rate";
	
	//跨区个代提成比例
	public static final String CROSS_PERSONAL_SCALE = "outperson_shareprofit_rate";
	
	//跨区区代提成比例
	public static final String CROSS_AREA_SCALE = "outarea_shareprofit_rate";
	
	//前200区代提成
	public static final String TWO_AREA_SCALE = "two_hundred_area_shareprofit_rate";
	
	//推荐人积分提成
	public static final String BELONG_SCALE = "belong_rate";
	
	//一级推荐人所获现金比例
	public static final String FIRST_REFERRER_CASH_RATE = "first_referrer_cash_rate";
	
	//商家所获积分提成
	public static final String SELLER_POINT_RATE = "seller_point_rate";
	
	//接收短信的手机号码
	public static final String SMS_PHONES = "sms.phones";
	public static final String CLIENT_ID = "clientID";

	public static final String TEMPLATE_ID_O2O_1009 = "O2O_1009";
	public static final String TEMPLATE_ID_O2O_1008 = "O2O_1008";
	
	//请求方式_get
	public static final String REQUEST_METHOD_GET = "GET";
	
	//请求方式_post
	public static final String REQUEST_METHOD_POST = "POST";
	
	//直营前缀编码
	public static final String CODE_DIRECT_SALE = "888888";
	
	//大区前缀编码
	public static final String CODE_BIG_REGION = "999999";
	
	//直营个代(888888开头)
	public static final String PERSONAL_AGENT_TYPE_DIRECT_SALE = "directSale";
	
	//大区个代(999999开头)
	public static final String PERSONAL_AGENT_TYPE_BIG_REGION = "bigRegion";
	
	//(普通个代类型)
	public static final String PERSONAL_AGENT_TYPE_NORMAL = "normal";
	
	/** 支付方式 */
	public static final String PAY_WECHAT = "pay_wechat";
	public static final String PAY_ALIPAY = "pay_alipay";
	public static final String PAY_CMB = "pay_cmb";
	public static final String PAY_POS = "pay_pos";
	
	public static final Map<Integer,String> O2O_SETTLEMENT_STATUS_CODE_MAP=ImmutableMap.of(ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_SCORE,"积分已送出",
			ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_BILL,"订单已结算",ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_CASH,"一级推荐人现金奖励已送出");
	
	private ShareProfitUtil(){}

	/**
	 * 查询分润数据配置
	 * @param systemSettings 系统设置相关信息
	 * @return shareProfit
	 */
	public static Map<String, String> queryShareProfit(List<EcmSystemSetting> systemSettings) {
		Map<String, String> shareProfit = Maps.newHashMap();
		try {
			for (EcmSystemSetting systemSetting : systemSettings) {
				shareProfit.put(systemSetting.getScode(), systemSetting.getValue());
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return shareProfit;
	}
	
	
	public static String getPersonalAgentType(String personalAgentNo){
		if(Strings.isNullOrEmpty(personalAgentNo)){
			return PERSONAL_AGENT_TYPE_NORMAL;
		}else{
			String prefix6=personalAgentNo.trim().substring(0, 6);
			if(CODE_DIRECT_SALE.equals(prefix6)){
				return PERSONAL_AGENT_TYPE_DIRECT_SALE;
			}else if(CODE_BIG_REGION.equals(prefix6)){
				return PERSONAL_AGENT_TYPE_BIG_REGION;
			}else{
				return PERSONAL_AGENT_TYPE_NORMAL;
			}
		}
		
	}


	public static EcmMzfOrderStatus buildOrderStatusObj(EcmMzfShareProfit ecmMzfShareProfit) {
		EcmMzfOrderStatus orderStatus = new EcmMzfOrderStatus();
		orderStatus.setOrderSn(ecmMzfShareProfit.getOrderSn());
		orderStatus.setStatus(ecmMzfShareProfit.getStatus());
		orderStatus.setAddTime(ecmMzfShareProfit.getOrderDate());
		orderStatus.setPayTime(ecmMzfShareProfit.getPayTime());
		orderStatus.setCreatedDate(DateUtil.getCurrentTimeSec());
		orderStatus.setShareStatus(1);  //1:已分润
		return orderStatus; 
	}

	public static BigDecimal getShareProfitByType(String roleType, List<ShareProfitVO> shareProfitVOs,String profitType) {
		BigDecimal value = null;
		if (shareProfitVOs != null && !shareProfitVOs.isEmpty() && !StringUtil.isEmpty(roleType)) {
			
			Collection<ShareProfitVO> col = Collections2.filter(shareProfitVOs, soVo -> roleType.equals(soVo.getType()));
			
			if (col != null) {
				final List<ShareProfitVO> list = new ArrayList<>(col);
				ShareProfitVO spVO = list.get(0);
				if ("Today".equals(profitType)) {
					return spVO.getProfitToday();
				}

				if ("Settlement".equals(profitType)) {
					return spVO.getProfit4Settlement();
				}
			}
		}
		return value;
	}


	public static void updateBillInfo(List<EcmMzfShareProfit> shareProfits, List<EcmMzfBillWaterVO> billWaterVOs) {
		if(shareProfits!=null && !shareProfits.isEmpty() && billWaterVOs!=null && !billWaterVOs.isEmpty()){
			Map<String, EcmMzfShareProfit> shareProfitMap=SettlementUtil.convert2Map(shareProfits, "orderSn");
			Map<String, EcmMzfBillWaterVO> billWaterMap=SettlementUtil.convert2Map(billWaterVOs, "orderSn");
			for(Map.Entry<String, EcmMzfShareProfit> entry:shareProfitMap.entrySet()){
				String orderSn=entry.getKey();
				if(billWaterMap.get(orderSn)!=null){
					EcmMzfBillWaterVO bwVO=billWaterMap.get(orderSn);
					entry.getValue().setBillDate(bwVO.getBillDate());
					entry.getValue().setBillAddDate(bwVO.getBillAddDate());
					entry.getValue().setBillDateStr(DateUtil.format(bwVO.getBillDate(), DateUtil.YYYY_MM_DD));
					entry.getValue().setBillAddDateStr(DateUtil.format(bwVO.getBillAddDate(), DateUtil.YYYY_MM_DD));
					
				}
			}
			
		}
	}

	
	/**
	 * 获取请求接口后的数据提取推荐人手机号
	 * @param list 推荐人信息
	 * @return retMap
	 */
	public static Map<String, String> getlvlAndPhone(List<Map<String, String>> list) {
		Map<String, String> retMap = new HashMap<>();
		if(list!=null && !list.isEmpty()){
			for(Map<String,String> referrerMap:list){
				retMap.put(referrerMap.get("level"), referrerMap.get("phone"));
			}
		}
		return retMap;
	}
		
		
	public static String mD5Encrypt(String values) {
		StringBuilder buf = new StringBuilder("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(values.toLowerCase().getBytes());
			byte[] b = md.digest();

			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
		}
		return buf.toString();
	}
		
	/**
	 * 生成不重复随机数，生成方式：毫秒+5位随机数
	 * @return String
	 */
	public static final String getRandomNum() {
		//当前秒数
		String timeMillis = String.valueOf(System.currentTimeMillis()/1000L);
		
		// 得到0.0到1.0之间的数字,并扩大100000倍
		double doubleP = Math.random() * 100000;
		// 如果数据等于100000,则减少1
		if (doubleP >= 100000) {
			doubleP = 99999;
		}
		// 然后把这个数字转化为不包含小数点的整数
		int tempString = (int) Math.ceil(doubleP);
		// 转化为字符串
		String newString = String.valueOf(tempString);
		// 把得到的数增加为固定长度,为5位
		while (newString.length() < 5) {
			StringBuilder sb = new StringBuilder();
			sb.append("0");
			sb.append(newString);
			newString = sb.toString();
		}
		StringBuilder sb1 = new StringBuilder();
		sb1.append(timeMillis);
		sb1.append(newString);
		return sb1.toString();
	}
		
	
	/**
	 * 加载配置文件
	 * @param config 配置文件名称
	 * @return map
	 */
	public static Map<String, String> loadProperty(String config) {
		Map<String, String> map = new HashMap<>();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (IOException e) {
			log.error("配置文件加载出错{},{}", config, e);
		}
		Iterator<Object> localIterator = pro.keySet().iterator();
		while (localIterator.hasNext()) {
			Object key = localIterator.next();
			map.put(key.toString(), pro.get(key).toString());
		}
		return map;
	}
		
	
	/**
	 * 字符转码
	 * @param str 字符串
	 * @param charset 转码类型
	 * @return String
	 */
	public static String encodeStr(String str,String charset){
		
		String encodestr = "";
		String defaultCharSet=Constants.ENCODE_UTF8;
		if(!Strings.isNullOrEmpty(charset)){
			defaultCharSet=charset;
		}
		
		try {
			if(!Strings.isNullOrEmpty(str)){
				encodestr = URLEncoder.encode(str, defaultCharSet);
			}
		} catch (UnsupportedEncodingException e) {
			log.error("Unsupported encoding type:{}", e);
		}
		return encodestr;
	}

	 
}