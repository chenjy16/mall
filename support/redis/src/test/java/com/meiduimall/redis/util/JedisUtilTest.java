package com.meiduimall.redis.util;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;
import com.meiduimall.redis.util.RedisTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-common.xml"})
@FixMethodOrder(MethodSorters.DEFAULT)  
public class JedisUtilTest {
	
	
	
   @Before
   public void setUp(){

   }
	
	/**
	 * 功能描述:  测试得到从缓冲中自增num的值
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:19:34 
	 * return  void
	 */
	@Test
	public void testExecIncrByToCache(){
		Long value=RedisTemplate.getJedisInstance().execIncrByToCache("testExecIncrByToCache", 1);
		Assert.assertNotNull(value);
	}
	
	
	/**
	 * 功能描述:  测试得到从缓冲中自增1的值
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:18:41 
	 * return  void
	 */
	@Test
	public void testExecIncrToCache(){
		Long value=RedisTemplate.getJedisInstance().execIncrToCache("testExecIncrToCache");
		Assert.assertNotNull(value);
	}
	
	
	/**
	 * 功能描述:  测试得到从缓冲中自减1的值
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:18:20 
	 * return  void
	 */
	@Test
	public void testExecDecrToCache(){
		Long value=RedisTemplate.getJedisInstance().execDecrToCache("testExecIncrToCache");
		Assert.assertNotNull(value);
	}
	
	/**
	 * 功能描述:  测试从缓冲中自减num的值
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:14:45 
	 * return  void
	 */
	@Test
	public void testExecDecrByToCache(){
		Long value=RedisTemplate.getJedisInstance().execDecrByToCache("testExecDecrByToCache", 1);
		Assert.assertNotNull(value);
	}
	
	
	
	
	/**
	 * 功能描述:  测试key value
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:12:30 
	 * return  void
	 */
	@Test
	public void testExecSetToCache(){
		Long value=RedisTemplate.getJedisInstance().execSetnxToCache("testExecSetToCache", "testExecSetToCache");
		Assert.assertNotNull(value);
	}
	
	
	/**
	 * 功能描述:  测试删除缓存 
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:11:59 
	 * return  void
	 */
	@Test
	public void testExecDelToCache(){
		boolean res=RedisTemplate.getJedisInstance().execDelToCache("testExecSetToCache");
		Assert.assertTrue(res);
	}
	
	
	/**
	 * 功能描述:  测试设置key value  seconds
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:10:50 
	 * return  void
	 */
	@Test
	public void testExecSetexToCache(){
		RedisTemplate.getJedisInstance().execSetexToCache("testExecSetexToCache", 60, "testExecSetexToCache");
	}
	
	
	/**
	 * 功能描述:  测试通过key获取value
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:09:44 
	 * return  void
	 */
	@Test
	public void testExecGetFromCache(){
		String value=RedisTemplate.getJedisInstance().execGetFromCache("testExecGetFromCache");
		Assert.assertNull(value);
	}
	
	
	/**
	 * 功能描述:  测试是否已经缓存
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:09:09 
	 * return  void
	 */
	@Test
	public void testExecExistsFromCache(){
		Boolean res=RedisTemplate.getJedisInstance().execExistsFromCache("testExecSetexToCache");
		Assert.assertNotNull(res);
	}
	
	
	/**
	 * 功能描述:  测试设置过期时间
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午2:07:59 
	 * return  void
	 */
	@Test
	public void testExecExpireToCache(){
		Long res=RedisTemplate.getJedisInstance().execExpireToCache("testExecSetexToCache",5);
		Assert.assertNotNull(res);
	}

	
	
	/**
	 * 功能描述:  测试设置keyvalue
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午12:30:24 
	 * return  void
	 */
	@Test
	public void testExecSetnxToCache(){
		Long value=RedisTemplate.getJedisInstance().execSetnxToCache("testExecSetnxToCache", "testExecSetnxToCache");
		Assert.assertNotNull(value);
	}
	
	
	
	
	/**
	 * 功能描述:  测试获取keys
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午12:22:26 
	 * return  void
	 */
	@Test
	public void testExecKeysFromCache(){
		Set<String> set=RedisTemplate.getJedisInstance().execKeysFromCache("testExecKeysFromCache");
		assertEquals(0,set.size());
	}
	
	
	/**
	 * 功能描述:  测试获取key的过期时间
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午12:21:58 
	 * return  void
	 */
	@Test
	public void testExecTtlFormCache(){
		Long value=RedisTemplate.getJedisInstance().execTtlFormCache("testExecTtlFormCache");
		assertEquals(-1,value.intValue());
	}
	
	
	
	/**
	 * 功能描述:  测试设置set
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午12:18:58 
	 * return  void
	 */
	@Test
	public void testExecHmsetToCache(){
		Map<String,String> param=Maps.newHashMap();
		param.put("testExecHmsetToCache","testExecHmsetToCache");
		String value=RedisTemplate.getJedisInstance().execHmsetToCache("testExecHmsetToCache",param);
		assertEquals("OK",value);
	}
	
	
	/**
	 * 功能描述:  测试set读取
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午12:14:08 
	 * return  void
	 */
	@Test
	public void testExecHmgetToCache(){
		List<String> list=RedisTemplate.getJedisInstance().execHmgetToCache("testExecHmgetToCache", "testExecHmgetToCache");
		Assert.assertNull(list.get(0));
	}
	
	
	/**
	 * 功能描述:  测试设置set
	 * Author: 陈建宇
	 * Date:   2017年3月31日 下午12:09:49 
	 * return  void
	 */
	@Test
	public void testExecHsetToCache(){
		Long value=RedisTemplate.getJedisInstance().execHsetToCache("testExecHsetToCache","testExecHsetToCache","testExecHsetToCache");
		Assert.assertNotNull(value);
	}
	
	/**
	 * 功能描述:  测试查询set
	 * Author: 陈建宇
	 * Date:   2017年3月31日 上午11:57:08 
	 * return  void
	 */
	@Test
	public void testExecHgetAllToCache(){
		Map<String, String> map=RedisTemplate.getJedisInstance().execHgetAllToCache("testExecHsetToCache");
		Assert.assertNotNull(map);
	}
	
	
	/**
	 * 功能描述:  测试设置过期时间
	 * Author: 陈建宇
	 * Date:   2017年3月31日 上午11:46:30 
	 * return  void
	 */
	@Test
	public void testExecExpireAtTimeToCache(){
		Long second=(System.currentTimeMillis()/1000) +20;
		Long value=RedisTemplate.getJedisInstance().execExpireAtTimeToCache("testExecExpireAtTimeToCache",second);
		assertEquals(0,value.intValue());
		
	}
	
	
	/**
	 * 功能描述:  测试增加有序集 
	 * Author: 陈建宇
	 * Date:   2017年3月31日 上午11:44:35 
	 * return  void
	 */
	@Test
	public void testExecZaddToCache(){
		Long value=RedisTemplate.getJedisInstance().execZaddToCache("testExecZaddToCache", 10d, "testExecZaddToCache");
		Assert.assertNotNull(value);
	}
	
	
	/**
	 * 功能描述:  测试移除有序集
	 * Author: 陈建宇
	 * Date:   2017年3月31日 上午11:43:40 
	 * return  void
	 */
	@Test
	public void testExecZremToCache() {
		Long value=RedisTemplate.getJedisInstance().execZremToCache("testExecZremToCache", "testExecZremToCache");
		assertEquals(0,value.intValue());
		
	}
	
	

	
}
