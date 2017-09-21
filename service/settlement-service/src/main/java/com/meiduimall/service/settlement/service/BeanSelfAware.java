package com.meiduimall.service.settlement.service;

@FunctionalInterface
public interface BeanSelfAware {  
    void setSelf(Object proxyBean);  
} 
