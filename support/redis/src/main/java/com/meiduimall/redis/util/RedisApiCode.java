package com.meiduimall.redis.util;
import com.meiduimall.core.BaseApiCode;

public class RedisApiCode extends BaseApiCode{

	public static final Integer EXCEPTION_CORE_OPER=7001;
	public static final Integer EXCEPTION_PIPE_OPER=7002;
	static {
		zhMsgMap.put(EXCEPTION_CORE_OPER, "jedis操作核心方法异常！");
		zhMsgMap.put(EXCEPTION_PIPE_OPER, "jedis管道操作核心方法异常！");
	}
}
