package com.meiduimall.redis.util;
import redis.clients.jedis.ShardedJedis;



public interface RedisCallback<T> {
	
	public T invoke(ShardedJedis jedis);

}
