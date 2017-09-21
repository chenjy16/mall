package com.meiduimall.payment.api.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.payment.api.model.api.PaymentParamModel;
import com.meiduimall.payment.api.model.api.PaymentResultModel;

/**
 * 
 * @author lftan
 *
 */
public interface PaymentService {

    /**
     * 支付
     * @param paramModel 参数对象
     * @return 结果对象
     * @throws ServiceException 业务异常
     */
	ResBodyData payService(PaymentParamModel paramModel) throws ServiceException;

    /**
     * 描述：支付后处理
     * @param paramModel 参数对象
     * @return 结果对象
     * @throws ServiceException 业务异常
     */
	ResBodyData payAfterService(PaymentResultModel paramModel) throws ServiceException;

}
