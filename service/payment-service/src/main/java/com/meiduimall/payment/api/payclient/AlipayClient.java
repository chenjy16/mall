package com.meiduimall.payment.api.payclient;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.password.util.RsaEncrypt;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
import com.meiduimall.payment.api.model.alipay.AlipayRequestModel;

/**
 * 支付寶客戶端
 * 
 * @author Nico.Jiang
 * @since 2016.12.22
 */
@Component
public class AlipayClient {
	
	private static Logger log = LoggerFactory.getLogger(AlipayClient.class);
	
	@Value("${pay.alipay.appid}")
	private String APP_ID;
	@Value("${pay.alipay.key.private}")
	private String APP_PRIVATE_KEY;
	@Value("${pay.alipay.key.public}")
	private String ALIPAY_PUBLIC_KEY;
	@Value("${pay.alipay.gateway}")
	private String ALIPAY_URL;

	@Value("${pay.alipay.partner}")
	private String partner;

	private String CHARSET = "UTF-8";

	
	/**
	 * 
	 * @param model 支付宝支付对象
	 * @param service 客户端IP
	 */
	protected void buildBody(AlipayRequestModel model, String service){
		model.setPartner(partner);
		model.setSeller_id(APP_ID);
		model.setService(service);
		model.setPayment_type("1");
		model.set_input_charset(CHARSET);
	}

	/**
	 * 构建请求参数
	 * 
	 * @param param 请求参数
	 * @return 请求参数字符串
	 */
	protected String buildParam(Map<String, String> param) {
		Set<String> keys = param.keySet();
		StringBuffer result = new StringBuffer();

		for (String key : keys) {
			result.append(key + "=" + param.get(key) + "&");
		}

		return result.toString();
	}

	/**
	 * 
	 * @param obj 需签名对象
	 * @param output 存放签名map
	 * @return 签名字符串
	 */
	protected String buildSign(Object obj, Map<String, String> output){
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		Map<String, String> map = new LinkedHashMap<String, String>();

		for (Field f : fields) {
			f.setAccessible(true);
			try {
				if (f.get(obj) != null && f.get(obj) != "") {
					map.put(f.getName(), f.get(obj).toString());
					list.add(f.getName() + "=" + f.get(obj) + "&");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.info("error: /n{}",e);
				throw new ServiceException(ServicePaymentApiCode.CLASS_REFLECT_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.CLASS_REFLECT_ERROR));
			}
		}

		if (output != null) {
			output.putAll(map);
			log.info("Sign output: \n{}", output);
		}

		StringBuffer sb = new StringBuffer();

		for (String str : list) {
			sb.append(str);
		}

		String result = sb.toString().substring(0, sb.length() - 1);
		log.info("Sign result: \n{}", result);
		String sign = RsaEncrypt.encrypt(result, APP_PRIVATE_KEY, CHARSET);
		return sign;
	}

	/**
	 * 
	 * @param model 支付对象
	 * @return 签名字符串
	 */
	public String alipayTradeApp(AlipayRequestModel model){
		buildBody(model, "mobile.securitypay.pay");

		Map<String, String> param = new LinkedHashMap<String, String>();
		String sign = null;
		try {
			sign = buildSign(model, param);
		} catch (Exception e) {
			log.info("AlipayClient:\n{}",e);
			throw new DaoException(ServicePaymentApiCode.ALIPAY_SIGN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.ALIPAY_SIGN_ERROR));
		}
		StringBuffer result = new StringBuffer(buildParam(param));
		try {
			result.append("sign=" + URLEncoder.encode(sign, CHARSET));
		} catch (UnsupportedEncodingException e) {
			log.info("AlipayClient:\n{}",e);
			throw new DaoException(ServicePaymentApiCode.SIGN_ENCODE_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.SIGN_ENCODE_ERROR));
		}
		result.append("&sign_type=RSA");
		log.info("AlipayTradeWap: \n{}", result.toString());

		return result.toString();
	}

	/**
	 * 
	 * @param model 支付对象
	 * @return 签名字符串
	 */
	public String alipayTradeWap(AlipayRequestModel model){
		buildBody(model, "alipay.wap.create.direct.pay.by.user");

		Map<String, String> param = new HashMap<String, String>();
		String sign = buildSign(model, param);
		param.put("sign", sign);
		param.put("sign_type", "RSA");

		Set<String> keys = param.keySet();
		StringBuffer result = new StringBuffer();

		result.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_URL + "?_input_charset="
				+ CHARSET + "\" method=\"POST\">");

		for (String key : keys) {
			result.append("<input type=\"hidden\" name=\"" + key + "\" value=\"" + param.get(key) + "\"/>");
		}

		result.append("<input type=\"submit\" value=\"提交\" style=\"display:none;\"></form>");
		result.append("<script>document.forms['alipaysubmit'].submit();</script>");
		log.info("AlipayTradeWap: \n{}", result.toString());

		return result.toString();
	}

}
