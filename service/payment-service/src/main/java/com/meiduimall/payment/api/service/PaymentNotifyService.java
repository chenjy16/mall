package com.meiduimall.payment.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.model.api.PaymentNotifyModel;

/**
 * Created by nico on 2017/2/20.
 */
public class PaymentNotifyService {

    @Autowired
    DaoTemplate daoTemplate;

    /**
     * 添加回调数据
     *
     * @param orderId 订单号
     * @param notifyData 回调报文
     * @param notifyStatus 支付回调状态
     * @throws Exception  异常
     */
    public void insertNotify(String orderId, String notifyData, Integer notifyStatus) throws Exception {

        PaymentNotifyModel model = new PaymentNotifyModel();
        model.setOrderId(orderId);
        model.setNotifyData(notifyData);
        model.setNotifyStatus(notifyStatus);

        daoTemplate.insert("paymentNotify.insert", model);
    }

    /**
     * 更新回调数据的状态
     *
     * @param model 通知对象
     * @throws Exception 异常
     */
    public void updateStatus(PaymentNotifyModel model) throws Exception {
        daoTemplate.update("paymentNotify.updateStatus", model);
    }

}
