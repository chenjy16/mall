package com.meiduimall.payment.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.model.api.PaymentLogsModel;

/**
 * 操作日志
 *
 * @author Nico.Jiang
 * @since 2017.1.9
 */
@Service
public class PaymentLogsService {

    @Autowired
    private DaoTemplate daoTemplate;

   
    /**
     * 描述：添加日志
     * @param model 日志参数对象
     */
    public void insertLog(PaymentLogsModel model){
        
        try {
			daoTemplate.insert("paymentLogs.insert", model);
		} catch (Exception e) {
			throw new ServiceException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
    }


}
