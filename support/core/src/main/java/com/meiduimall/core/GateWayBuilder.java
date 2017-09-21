package com.meiduimall.core;


public final class GateWayBuilder {
	
	private GateWayComponent component;

    private GateWayBuilder(){}
    
    /**
     * 功能描述:  构造微服务访问
     * Author: 陈建宇
     * Date:   2017年4月25日 上午10:26:08 
     * param   @param req
     * return  GateWayBuilder
     */
    public static GateWayBuilder newBuilder(GateWayRequest req){
    	GateWayBuilder builder = new GateWayBuilder();
        builder.component = new GateWayComponent(req);
        return builder;
    }
    
    
    /**
     * 功能描述:  返回微服务访问组件
     * Author: 陈建宇
     * Date:   2017年4月25日 上午10:26:46 
     * return  GateWayComponent
     */
    public GateWayComponent build(){
        return component;
    }
}
