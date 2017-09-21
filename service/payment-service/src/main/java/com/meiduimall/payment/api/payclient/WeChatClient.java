package com.meiduimall.payment.api.payclient;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.password.SecurityBaseApiCode;
import com.meiduimall.password.exception.Md5Exception;
import com.meiduimall.password.util.MD5;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
import com.meiduimall.payment.api.model.wechat.WeChatAppModel;
import com.meiduimall.payment.api.model.wechat.WeChatRequestModel;
import com.meiduimall.payment.api.model.wechat.WeChatResponeModel;
import com.meiduimall.payment.api.service.PaymentLogsService;
import com.meiduimall.payment.api.util.XmlSupport;
/**
 * 微信支付
 *
 * @author Nico.Jiang
 * @since 2016.12.28
 */
@Component
public class WeChatClient {
	
	 private static Logger log = LoggerFactory.getLogger(WeChatClient.class);
	 
    @Value("${pay.wechat.gateway}")
    private String WECHAT_URL_PAY; 
    
  

    @Value("${pay.wechat.appid.app}")
    private String WECHAT_APPID_APP;
    @Value("${pay.wechat.mchid.app}")
    private String WECHAT_MCHID_APP;
    
    @Value("${pay.wechat.appid.1gw.app}")
    private String WECHAT_APPID_1GW_APP;
    @Value("${pay.wechat.mchid.1gw.app}")
    private String WECHAT_MCHID_1GW_APP;

    @Value("${pay.wechat.appid.web}")
    private String WECHAT_APPID_WEB;
    @Value("${pay.wechat.mchid.web}")
    private String WECHAT_MCHID_WEB;

    @Value("${pay.wechat.key}")
    private String WECHAT_KEY;
    
    @Value("${pay.wechat.1gw.key}")
    private String WECHAT_1GW_KEY;

    @Autowired
    PaymentLogsService logsService;

   /**
    * 
    * @param obj 签名对象
    * @param key 签名key
    * @return  签名字符串
    */
    protected String getSign(Object obj, String key){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> list = new ArrayList<String>();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
				if (f.get(obj) != null && f.get(obj) != "") {
					if("pkg".equalsIgnoreCase(f.getName())){
						 list.add("package" + "=" + f.get(obj) + "&");
						 continue;
					}
				    list.add(f.getName() + "=" + f.get(obj) + "&");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.info("WeChatClient:\n{}", e);
				throw new DaoException(ServicePaymentApiCode.CLASS_REFLECT_ERROR, ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.CLASS_REFLECT_ERROR));
			}
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = (sb.toString() + "key=" + key);
        log.info("Sign result: \n{}", result);
        String md5 = null;
        try {
        	md5= MD5.getMD5EncodeUTF8(result);
		} catch (Md5Exception e) {
			log.info("WeChatClient:\n{}", e);
			throw new DaoException(SecurityBaseApiCode.EXCEPTION_MD5,SecurityBaseApiCode.getZhMsg(SecurityBaseApiCode.EXCEPTION_MD5));
		}
        
        return md5;
    }

    /**
     * 随机32位字符串
     *
     * @return 32位随机数
     */
    protected String getNonceStr() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

   
    
    WeChatRequestModel buildBody(String body, String tradeNo, int totalFee, String spbillCreateIp, String tradeType,
                                 String productId, String openId, String notifyUrl,String accountType) {
        WeChatRequestModel model = new WeChatRequestModel();
        if ("NATIVE".equals(tradeType) || "JSAPI".equals(tradeType)) {
            model.setAppid(WECHAT_APPID_WEB);
            model.setMch_id(WECHAT_MCHID_WEB);
        } else if ("APP".equals(tradeType)) {
        	//使用壹购物账号
        	if(accountType != null && "1".equals(accountType)){
        		model.setAppid(WECHAT_APPID_1GW_APP);
                model.setMch_id(WECHAT_MCHID_1GW_APP);
        	}else{//使用美兑账号
        		model.setAppid(WECHAT_APPID_APP);
                model.setMch_id(WECHAT_MCHID_APP);
        	}
            
        }
        model.setNonce_str(this.getNonceStr());
        model.setBody(body);
        model.setOut_trade_no(tradeNo);
        model.setTotal_fee(totalFee);
        model.setSpbill_create_ip(spbillCreateIp);
        model.setNotify_url(notifyUrl);
        model.setTrade_type(tradeType);
        if ("JSAPI".equals(tradeType)) {
            model.setOpenid(openId);
        } else if ("NATIVE".equals(tradeType)) {
            model.setProduct_id(productId);
        }
        return model;
    }
    
    
    public WeChatAppModel  buildSignBody(WeChatAppModel weChatAppModel,String accountType){
    	
    	
    		String sign;
    		weChatAppModel.setNoncestr(this.getNonceStr());
    		//壹购物账号
    		if(accountType != null && "1".equals(accountType)){
    			weChatAppModel.setAppid(WECHAT_APPID_1GW_APP);
            	weChatAppModel.setPartnerid(WECHAT_MCHID_1GW_APP);
            	sign =this.getSign(weChatAppModel, WECHAT_1GW_KEY);
    		}else{//美兑账号
    			weChatAppModel.setAppid(WECHAT_APPID_APP);
            	weChatAppModel.setPartnerid(WECHAT_MCHID_APP);
            	sign =this.getSign(weChatAppModel, WECHAT_KEY);
    		}
        	
        	weChatAppModel.setSign(sign);
        	
        	return weChatAppModel;
    	
    	
    }

    /**
     * 公共请求處理
     *
     * @param body 请求体
     * @param tradeNo 业务单号
     * @param totalFee 支付金额
     * @param spbillCreateIp 客户端
     * @param tradeType 交易类型
     * @param productId 产品ID
     * @param openId 用户openid
     * @return 签名对象
     */
    public WeChatResponeModel handler(String body, String tradeNo, int totalFee, String spbillCreateIp,
                                      String tradeType, String productId, String openId, String notifyUrl,String accountType) {
        WeChatRequestModel model = buildBody(body, tradeNo, totalFee, spbillCreateIp, tradeType, productId, openId,notifyUrl,accountType);

       
        	String signStr;
        	//壹购物账号
        	if(accountType !=null && "1".equals(accountType)){
        		  signStr = this.getSign(model, WECHAT_1GW_KEY);
        	}else{
        		  signStr = this.getSign(model, WECHAT_KEY);
        	}
           
            model.setSign(signStr);
            String xmlData = null;
			try {
				xmlData = XmlSupport.outputXml(model, WeChatRequestModel.class);
			} catch (JAXBException e) {
				log.info("xml转换异常: \n{}",e);
				throw new DaoException(ServicePaymentApiCode.XML_OBJECT_TRANS_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.XML_OBJECT_TRANS_ERROR));
			}
            log.info("getWeChatPayQrCode::request data: \n{}", xmlData);
            
            Map<String,String>  map = new HashMap<String,String>();
            map.put("Content-Type", "application/json");
            String result = null;
			try {
				result = HttpUtils.post(WECHAT_URL_PAY, xmlData, map);
			} catch (IOException e) {
				log.info("接口调用异常: \n{}",e);
				throw new DaoException(ServicePaymentApiCode.WEBCHAT_API_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.WEBCHAT_API_ERROR));
			}
            log.info("getWeChatPayQrCode::result data: \n{}", result);

            
            WeChatResponeModel resultModel = null;
			try {
				resultModel = XmlSupport.outputBean(result, WeChatResponeModel.class);
			} catch (JAXBException e) {
				log.info("xml转换异常: \n{}",e);
				throw new DaoException(ServicePaymentApiCode.XML_OBJECT_TRANS_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.XML_OBJECT_TRANS_ERROR));
			}
           
           

            return resultModel;
    }



    /**
     * 获取APP预支付单签名
     *
     * @param model 签名参数对象
     * @return  获取签名对象
     * @throws ApiException 接口异常
     */
    public WeChatResponeModel getAppSign(WeChatRequestModel model,String accountType) throws ApiException {

        WeChatResponeModel result = handler(model.getBody(),
                model.getOut_trade_no(),
                model.getTotal_fee(),
                model.getSpbill_create_ip(),
                "APP",
                "",
                "",
                model.getNotify_url(),
                accountType
        		);

        return result;
    }


}
