package com.meiduimall.redis.util;

import redis.clients.jedis.ShardedJedisPipeline;

public interface PipelineCallback {
	
	
	/**
	 * 功能描述:  管道操作接口
	 * Author: 陈建宇
	 * Date:   2017年4月25日 下午4:29:51 
	 * param   @param pipeline   
	 * return  void
	 */
	public void invokePipeline(ShardedJedisPipeline pipeline);

}
