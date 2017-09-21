package com.meiduimall.core.util;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;


public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static JsonUtils jacksonUtil = new JsonUtils();

    private ObjectMapper mapper;

    private JsonUtils() {
    	mapper = new ObjectMapper();
    }


    public static ObjectMapper getInstance() {
        return jacksonUtil.mapper;
    }


    /**
     * 功能描述:  json转化为java bean
     * Author: 陈建宇
     * Date:   2017年3月15日 下午6:15:32
     * return  T
     */
    public static <T> T jsonToBean(String json,  Class<T> valueType){
    	if(!Strings.isNullOrEmpty(json)){
    		try {
    			return getInstance().readValue(json, valueType);
    		} catch (JsonParseException e) {
    			logger.error(e.getMessage(),e);
    		} catch (JsonMappingException e) {
    			logger.error(e.getMessage(),e);
    		} catch (IOException e) {
    			logger.error(e.getMessage(),e);
    		}
    	}
    	return null;
    }


   /**
    *
    * 功能描述:  java bean转化为json
    * Author: 陈建宇
    * Date:   2017年3月15日 下午6:15:16
    * return  String
    */
    public static String beanToJson(Object bean){
		try {
			return getInstance().writeValueAsString(bean);
		} catch (JsonGenerationException e) {
			 logger.error(e.getMessage(),e);
		} catch (JsonMappingException e) {
			 logger.error(e.getMessage(),e);
		} catch (IOException e) {
			 logger.error(e.getMessage(),e);
		}
    	return null;
    }



    /**
     * 功能描述:  json转list
     * Author: 陈建宇
     * Date:   2017年3月15日 下午6:10:54
     * return  List
     */
    public static <T> List<T> jsonToList(String json,Class<T> clazz) {
    	List<T> list =null;
    	if(!Strings.isNullOrEmpty(json)){
    		T[] t =  (T[]) Array.newInstance(clazz, 1024);
            try {
                t =  (T[]) getInstance().readValue(json, t.getClass());
                list= Arrays.asList(t);
            } catch (JsonGenerationException e) {
                logger.error(e.getMessage(),e);
            } catch (JsonMappingException e) {
                logger.error(e.getMessage(),e);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
    	}
        return list;
    }



  public static String beanToJsonAndFmDate(Object channelList) {
    return beanToJson(channelList);
  }
}
