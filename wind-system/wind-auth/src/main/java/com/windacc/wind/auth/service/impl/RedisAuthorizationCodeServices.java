package com.windacc.wind.auth.service.impl;

import com.windacc.wind.redis.config.RedisService;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

	private final RedisService redisService;
	private final RedisSerializer<Object> valueSerializer;

	public RedisAuthorizationCodeServices(RedisService redisService) {
		this.redisService = redisService;
		this.valueSerializer = RedisSerializer.java();
	}

	/**
	 * 替换JdbcAuthorizationCodeServices的存储策略
	 * 将存储code到redis，并设置过期时间，10分钟<br>
	 */
	@Override
	protected void store(String code, OAuth2Authentication authentication) {

		redisService.setExpire(redisKey(code), authentication, 100, TimeUnit.MINUTES, valueSerializer);
	}

	@Override
	protected OAuth2Authentication remove(final String code) {

		String codeKey = redisKey(code);
		OAuth2Authentication token = (OAuth2Authentication) redisService.get(codeKey, valueSerializer);
		//redisService.del(codeKey);
		return token;
	}

	/**
	 * redis中 code key的前缀
	 * 
	 * @param code
	 * @return
	 */
	private String redisKey(String code) {
		return "oauth:code:" + code;
	}
}
