package com.meiduimall.core;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import com.google.common.base.Strings;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.password.exception.Md5Exception;
import com.meiduimall.password.util.MD5;


public final class GateWayComponent {
	
	private GateWayRequest req;
	
	public GateWayComponent(GateWayRequest req){
        this.req = req;
    }
	
	
	/**
	 * 功能描述:  通过post方式访问微服务
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午9:42:30 
	 * param   @param url
	 * param   @param headers
	 * param   @param sendData
	 * param   @throws ClientProtocolException
	 * param   @throws IOException   
	 * return  ResBodyData
	 */
	public ResBodyData doPost(String url,Map<String,String> headers,String sendData) throws IOException{
    	String json=HttpUtils.post(url,sendData , headers);
    	return JsonUtils.jsonToBean(json, ResBodyData.class);
    	
    }
    
    
	/**
	 * 功能描述:  通过put方式访问微服务
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午9:44:12 
	 * param   @param url
	 * param   @param headers
	 * param   @param sendData
	 * param   @throws ClientProtocolException
	 * param   @throws IOException   
	 * return  ResBodyData
	 */
	public ResBodyData doPut(String url,Map<String,String> headers,String sendData) throws IOException{
    	String json=HttpUtils.put(url,sendData, headers);
    	return JsonUtils.jsonToBean(json, ResBodyData.class);
    }
    
    /**
     * 功能描述:  通过get方式访问微服务
     * Author: 陈建宇
     * Date:   2017年4月13日 下午6:33:13 
     * param   @param url
     * param   @throws ClientProtocolException
     * param   @throws IOException   
     * return  ResBodyData
     */
	public ResBodyData doGet(String url) throws IOException{
    	String json=HttpUtils.get(url);
    	return JsonUtils.jsonToBean(json, ResBodyData.class);
    	
    }
    
    /**
     * 功能描述:  通过delete方式访问微服务
     * Author: 陈建宇
     * Date:   2017年4月13日 下午6:34:40 
     * param   @param url
     * param   @throws ClientProtocolException
     * param   @throws IOException   
     * return  ResBodyData
     */
	public ResBodyData doDelete(String url) throws IOException{
    	String json=HttpUtils.delete(url);
    	return JsonUtils.jsonToBean(json, ResBodyData.class);
    }
    
    
	/**
	 * 功能描述:  通过form表单方式访问微服务
	 * Author: 陈建宇
	 * Date:   2017年4月14日 上午10:05:56 
	 * param   @param url
	 * param   @param sendData
	 * param   @throws ClientProtocolException
	 * param   @throws IOException   
	 * return  ResBodyData
	 */
	public ResBodyData form(String url,Map<String,String> sendData) throws IOException{
    	String json=HttpUtils.form(url, sendData);
    	return JsonUtils.jsonToBean(json, ResBodyData.class);
    }
	
	
    
    /**
     * 功能描述: 过滤签名参数(升序，排出空值，sign)
     * Author: 陈建宇
     * Date:   2017年4月13日 下午6:40:52 
     * param   待校验参数
     * return  TreeMap<String,String>
     */
    private  TreeMap<String, String> filterSignParams(Map<String, ?> params) {
    	TreeMap<String, String> validParams = new TreeMap<>();
        for (Map.Entry<String, ?> param : params.entrySet()){
            if ("sign".equals(param.getKey())
                    || param.getValue() == null
                    || "".equals(String.valueOf(param.getValue()))){
                continue;
            }
            validParams.put(param.getKey(), String.valueOf(param.getValue()));
        }
        return validParams;
    }
    
    
    
    
    /**
     * 功能描述: 支付请求前签名
     * Author: 陈建宇
     * Date:   2017年4月13日 下午6:41:24 
     * param   参数(已经升序, 排出非空值和sign)
     * return  String
     * @throws Md5Exception 
     */
    private String doSign(SortedMap<String, String> params) throws Md5Exception {
        StringBuilder signing = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!Strings.isNullOrEmpty(entry.getValue())){
                signing.append(entry.getKey()).append('=').append(entry.getValue()).append("&");
            }
        }
        // append key
        signing.append("key=").append(req.getSignKey());
        // md5
        return  MD5.encode(signing.toString()).toUpperCase();

    }
    
    /**
     * 功能描述: 构建clientID配置参数
     * Author: 陈建宇
     * Date:   2017年4月18日 下午5:53:14 
     * param   @param params
     * return  GateWayComponent
     */
    public GateWayComponent buildConfigParams(SortedMap<String, String> params){
        params.put("clientID", req.getClientID());
        return this;
    }
    
    /**
     * 功能描述:  构建签名参数
     * Author: 陈建宇
     * Date:   2017年4月18日 下午5:53:27 
     * param   @param params
     * return  GateWayComponent
     * @throws Md5Exception 
     */
    public GateWayComponent buildSignParams(SortedMap<String,String> params) throws Md5Exception {
    	//生成签名
    	TreeMap<String, String> signingMap=filterSignParams(params);
    	String expectSign=doSign(signingMap);
    	params.put("sign", expectSign);
    	return this;
    }

    
}
