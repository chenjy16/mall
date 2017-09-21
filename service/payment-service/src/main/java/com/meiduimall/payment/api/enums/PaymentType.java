package com.meiduimall.payment.api.enums;

/**
 * 支付接口枚举类
 *
 * @author nico
 * @since 2017.1.16
 */
public enum PaymentType {

    TYPE_ALIPAY("alipay"), TYPE_WECHAT("wechat"), TYPE_HK("huika");

    private String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String Value() {
        return this.value;
    }

}
