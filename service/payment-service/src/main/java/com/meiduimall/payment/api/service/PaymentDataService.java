package com.meiduimall.payment.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.model.api.PaymentNotifyModel;
import com.meiduimall.payment.api.model.api.PaymentResultModel;

/**
 * Created by xj on 2017/2/20.
 */
@Service
public class PaymentDataService {

	 private static Logger log = LoggerFactory.getLogger(PaymentDataService.class);
	 
    @Autowired
    DaoTemplate daoTemplate;
   
    
    /**
     * 
     * @param resultModel 支付结果对象
     */
     public void updTrade(PaymentResultModel resultModel){
    	
    	
    		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		resultModel.setCreateTime(new Date());
    		// 写入日志表
    		log.info("开始写入日志表-------");
        	try {
				daoTemplate.insert("paymentLogs.addlog",resultModel);
			} catch (Exception e) {
				throw new ServiceException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
			}
        	log.info("成功写入日志表-------");
        	PaymentNotifyModel  paymentNotifyModel = new PaymentNotifyModel();
        	paymentNotifyModel.setOrderId(resultModel.getOrderNo());
        	paymentNotifyModel.setNotifyData(resultModel.getNotifyData());
        	paymentNotifyModel.setNotifyStatus(Integer.valueOf(resultModel.getNotifyStatus()));
        	try {
				paymentNotifyModel.setNotifyTime(sdf.parse(resultModel.getNotifyTime()));
			} catch (ParseException e) {
				throw new ServiceException(ServicePaymentApiCode.DATE_PARES_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.DATE_PARES_ERROR));
			}
        	//写入回调数据
        	log.info("开始写入回调数据-------");
        	try {
				daoTemplate.insert("paymentNotify.insert",paymentNotifyModel);
			} catch (Exception e) {
				throw new ServiceException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
			}
        	log.info("成功写入回调数据-------");
        	
    	
    	
    	
    }

}
