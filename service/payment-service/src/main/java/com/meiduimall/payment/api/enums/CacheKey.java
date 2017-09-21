package com.meiduimall.payment.api.enums;

/**
 * Created by nico on 2017/2/22.
 */
public enum CacheKey {

    PAY_CONFIG("PAYMENT:CONFIG:ID:");

    private String value;

    CacheKey(String value) {
        this.value = value;
    }

    public String Key() {
        return this.value;
    }
}
