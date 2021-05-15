package com.fedorxe.wind.redis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:30
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "wind.cache-manager")
public class CacheManagerProperties {
    private List<CacheConfig> configs;

    @Setter
    @Getter
    public static class CacheConfig {
        /**
         * cache key
         */
        private String key;
        /**
         * 过期时间，ttl
         */
        private Duration ttl = Duration.ofHours(1L);
    }
}
