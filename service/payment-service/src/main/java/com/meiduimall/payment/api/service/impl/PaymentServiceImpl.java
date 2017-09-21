package com.meiduimall.payment.api.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.BizException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.payment.api.model.api.PaymentParamModel;
import com.meiduimall.payment.api.model.api.PaymentResultModel;
import com.meiduimall.payment.api.service.HandlerService;
import com.meiduimall.payment.api.service.PaymentDataService;
import com.meiduimall.payment.api.service.PaymentService;

/**
 * Created by nico on 2017/3/3.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	private static Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Value("${pay.wechat.isHuik}")
	private String isHuik;
	
    @Autowired
    HandlerService handlerService;

    @Autowired
    PaymentDataService dataService;


    @Override
    public ResBodyData payService(PaymentParamModel paramModel){
    	ResBodyData resultBody = null;
        	
        	String payType=paramModel.getPayType();
        	//微信支付
        	if("0".equals(payType)){
        		
        		
        		 if("1".equals(isHuik)){//汇卡启用标志  默认不启用
        			
        			 paramModel.setPayChannel("10003");
        			 resultBody = handlerService.handleHiCardWeChatAppPay(paramModel);//汇卡微信
        			 
        		 }else{
        			 paramModel.setPayChannel("10001");
                     resultBody = handlerService.handleWeChatAppSign(paramModel);//普通微信
        			 
        		 }
        		 
        		
        	}else if("1".equals(payType)){  //普通支付宝
        		
        			paramModel.setPayChannel("10002");
                    resultBody = handlerService.handleAlipayAppSign(paramModel);
        		
        		
        	}
           
            
           

       

        return resultBody;
    }


	@Override
	public ResBodyData payAfterService(PaymentResultModel resultModel){
		ResBodyData resultBody = new ResBodyData(0, "success");
		dataService.updTrade(resultModel);
		return resultBody;
	}

  

}
