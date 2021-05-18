package com.windacc.wind.security.token;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
public class NewTokenStore extends RedisTokenStore {

    public NewTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}
