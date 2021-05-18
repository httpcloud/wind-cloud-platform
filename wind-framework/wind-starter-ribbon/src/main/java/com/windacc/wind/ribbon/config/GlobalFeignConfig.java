package com.windacc.wind.ribbon.config;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 17:02
 */
public class GlobalFeignConfig {

    @Bean
    public Level level() {
        return Level.FULL;
    }
}
