/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.platform.route.config;

import com.meiduimall.platform.route.filter.AccessFilter;
import com.meiduimall.platform.route.hanler.impl.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ImportResource("classpath:applicationContext-common.xml")
@ConfigurationProperties(prefix = "config")
public class ApplicationConfig {

  @Scope("prototype")
  @Bean("chain")
  public HandlerChain handlerChain() {
    return new HandlerChain();
  }


  /**
   * 功能描述:  黑名单验证
   * Author: 陈建宇
   * Date:   2017年2月22日 上午10:06:50
   * return  BlackListValidateHandler
   */
  @Bean
  public BlackListValidateHandler blackListValidateHandler() {
    return new BlackListValidateHandler();
  }

  /**
   * 功能描述:  表单kv数据解析处理器
   * Author: 陈建宇
   * Date:   2017年2月22日 上午10:06:50
   * return  FormParseHandler
   */
  @Bean
  public FormParseHandler formParseHandler() {
    return new FormParseHandler();
  }


  /**
   * 功能描述: 请求kv参数解析处理器
   * Author: 陈建宇
   * Date:   2016年12月19日 下午3:18:22
   * return ParamPraseHandler
   */
  @Bean
  public ParamPraseHandler paramPraseHandler() {
    return new ParamPraseHandler();
  }

  /**
   * 功能描述: 必填参数验证处理器
   * Author: 陈建宇
   * Date:   2016年12月19日 下午3:18:42
   * return RequiredValidateHandler
   */
  @Bean
  public RequiredValidateHandler requiredValidateHandler() {
    return new RequiredValidateHandler();
  }


  /**
   * 功能描述: 签名验证处理器
   * Author: 陈建宇
   * Date:   2016年12月19日 下午3:19:32
   * return SignValidateHandler
   */
  @Bean
  public SignValidateHandler signValidateHandler() {
    return new SignValidateHandler();
  }

  /**
   * 功能描述: 时间戳验证处理器
   * Author: 陈建宇
   * Date:   2016年12月19日 下午3:19:50
   * return TimeValidateHandler
   */
  @Bean
  public TimeValidateHandler timeValidateHandler() {
    return new TimeValidateHandler();
  }


  /**
   * 功能描述:  Json解析处理器
   * Author: 陈建宇
   * Date:   2016年12月20日 上午11:02:22
   * return  PraseJsonHandler
   */
  @Bean
  public PraseJsonHandler praseJsonHandler() {
    return new PraseJsonHandler();
  }


  /**
   * 功能描述:  zuul过滤器
   * Author: 陈建宇
   * Date:   2016年12月20日 上午11:02:56
   * return  AccessFilter
   */
  @Bean
  public AccessFilter accessFilter() {
    return new AccessFilter();
  }


  @Bean(name = "shardedJedisPool")
  public ShardedJedisPool shardedJedisPool(@Qualifier("jedis.config") JedisPoolConfig config,
                                           @Qualifier("jedisShardInfo") JedisShardInfo jedisShardInfo) {
    List<JedisShardInfo> list = new ArrayList<>();
    list.add(jedisShardInfo);
    return new ShardedJedisPool(config, list);
  }


  @Bean(name = "jedisShardInfo")
  public JedisShardInfo jedisShardInfo(@Value("${config.jedis.jedisShardInfo.host}") String host,
                                       @Value("${config.jedis.jedisShardInfo.port}") int port,
                                       @Value("${config.jedis.jedisShardInfo.password}") String password) {
    JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);
    jedisShardInfo.setPassword(password);
    return jedisShardInfo;
  }


  @Bean(name = "jedis.config")
  public JedisPoolConfig jedisPoolConfig(@Value("${config.jedis.maxTotal}") int maxTotal,
                                         @Value("${config.jedis.maxIdle}") int maxIdle,
                                         @Value("${config.jedis.maxWaitMillis}") int maxWaitMillis,
                                         @Value("${config.jedis.testOnBorrow}") boolean testOnBorrow,
                                         @Value("${config.jedis.testOnReturn}") boolean testOnReturn) {

    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(maxTotal);
    config.setMaxIdle(maxIdle);
    config.setMaxWaitMillis(maxWaitMillis);
    return config;
  }



}
