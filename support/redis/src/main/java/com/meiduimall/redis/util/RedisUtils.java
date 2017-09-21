package com.meiduimall.redis.util;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisUtils {
	
	/**
	 * 功能描述:  删除缓存 
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:32:54 
	 * param   cacheKey
	 * return  boolean
	 */
	public static boolean del(final String cacheKey){
		return RedisTemplate.getJedisInstance().execDelToCache(cacheKey);
	}
	
	
	/**
	 * 功能描述:  存入缓存值 LRU
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:31:51 
	 * param   cacheKey
	 * param   value   
	 * return  void
	 */
	public static void set(final String cacheKey, final String value){
		RedisTemplate.getJedisInstance().execSetToCache(cacheKey,value);
	}
	
	
	/**
	 * 功能描述: 设置key value  seconds
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:31:35 
	 * param   cacheKey
	 * param   seconds
	 * param   value   
	 * return  void
	 */
	public static void setex(final String cacheKey, final int seconds, final String value){
		RedisTemplate.getJedisInstance().execSetexToCache(cacheKey,seconds,value);
	}
	
	
	/**
	 * 功能描述: 通过key获取value
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:31:22 
	 * param   cacheKey
	 * return  String
	 */
	public static String get(final String cacheKey){
		return RedisTemplate.getJedisInstance().execGetFromCache(cacheKey);
	}
	
	/**
	 * 功能描述: 是否已经缓存
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:31:07 
	 * param   cacheKey
	 * return  Boolean
	 */
	public static Boolean exists(final String cacheKey){
		return RedisTemplate.getJedisInstance().execExistsFromCache(cacheKey);
	}
	
	/**
	 * 功能描述:  设置过期时间
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:30:54 
	 * param   cacheKey
	 * param   seconds
	 * return  Long
	 */
	public static Long expire(final String cacheKey, final int seconds){
		return RedisTemplate.getJedisInstance().execExpireToCache(cacheKey,seconds);
	}
	
	
	/**
	 * 功能描述:  SET if Not eXists
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:30:13 
	 * param   cacheKey
	 * param   value
	 * return  Long
	 */
	public static Long setnx(final String cacheKey, final String value){
		return RedisTemplate.getJedisInstance().execSetnxToCache(cacheKey,value);
	}
	
	
	/**
	 * 功能描述:  获取keys
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:29:57 
	 * param   cacheKey
	 * return  Set
	 */
	public static Set<String> keys(final String cacheKey){
		return RedisTemplate.getJedisInstance().execKeysFromCache(cacheKey);
	}
	
	
	/**
	 * 功能描述: 获取key的过期时间
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:29:44 
	 * param   cacheKey
	 * return  Long
	 */
	public static Long ttl(final String cacheKey){
		return RedisTemplate.getJedisInstance().execTtlFormCache(cacheKey);
	}
	
	
	/**
	 * 功能描述:  hash操作 存放
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:34:43 
	 * param   cacheKey
	 * param   hash
	 * return  String
	 */
	public static String  hmset(final String cacheKey, final Map<String, String> hash){
		return RedisTemplate.getJedisInstance().execHmsetToCache(cacheKey,hash);
	}
	
	/**
	 * 功能描述:  返回哈希表 key 中给定域 field 的值
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:35:59 
	 * param   cacheKey
	 * param   hashKey
	 * return  List
	 */
	public static List<String>  hmget(final String cacheKey, final String hashKey){
		return RedisTemplate.getJedisInstance().execHmgetToCache(cacheKey,hashKey);
	}
	
	/**
	 * 功能描述:  hash操作 读取
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:39:12 
	 * param   cacheKey
	 * param   field
	 * return  String
	 */
	public static String hget(final String cacheKey, final String field){
		return RedisTemplate.getJedisInstance().execHgetToCache(cacheKey,field);
	}
	
	/**
	 * 功能描述:  将哈希表 key中的域 field的值设为value
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:41:33 
	 * param   cacheKey
	 * param   field
	 * param   value
	 * return  Long
	 */
	public static Long hset(final String cacheKey, final String field, final String value) {
		return RedisTemplate.getJedisInstance().execHsetToCache(cacheKey,field,value);
	}
	
	
	/**
	 * 功能描述:  返回哈希表 key中,所有的域和值。
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:43:05 
	 * param   cacheKey
	 * return  Map
	 */
	public static Map<String, String> hgetAll(final String cacheKey) {
		return RedisTemplate.getJedisInstance().execHgetAllToCache(cacheKey);
	}
	
	
	/**
	 * 功能描述:  设置到什么时候过期
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:45:25 
	 * param   cacheKey
	 * param   unixTime
	 * return  Long
	 */
	public static Long expireAt(final String cacheKey, final Long unixTime){
		return RedisTemplate.getJedisInstance().execExpireAtTimeToCache(cacheKey, unixTime);
	}
	
	/**
	 * 功能描述:  将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:47:12 
	 * param   cacheKey
	 * param   score
	 * param   member
	 * return  Long
	 */
	public static Long zadd(final String cacheKey, final Double score, final String member){
		return RedisTemplate.getJedisInstance().execZaddToCache(cacheKey, score, member);
	}
	
	/**
	 * 功能描述:  移除有序集 key 中的一个或多个成员，不存在的成员将被忽略
	 * Author: 陈建宇
	 * Date:   2017年4月12日 上午10:48:07 
	 * param   cacheKey
	 * param   members
	 * return  Long
	 */
	public Long zrem(final String cacheKey, final String... members){
		return RedisTemplate.getJedisInstance().execZremToCache(cacheKey, members);
	}
	
	
}
