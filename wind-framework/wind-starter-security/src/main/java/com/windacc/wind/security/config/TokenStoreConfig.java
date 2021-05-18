package com.windacc.wind.security.config;

import com.windacc.wind.security.token.RedisTemplateTokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.Assert;

public class TokenStoreConfig {

	@Bean
	public RedisTemplateTokenStore redisTokenStore(RedisConnectionFactory connectionFactory ){
//		return new RedisTokenStore( redisTemplate.getConnectionFactory() ) ; //单台redis服务器
		Assert.state(connectionFactory != null, "connectionFactory must be provided");
		RedisTemplateTokenStore redisTemplateStore = new RedisTemplateTokenStore(connectionFactory)  ;
		return redisTemplateStore ;
	}

}
