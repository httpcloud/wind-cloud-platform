package com.windacc.wind.ribbon.config;

import com.windacc.wind.ribbon.strategy.RequestConcurrencyStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@Slf4j
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class RequestServletConfiguration {

    @Bean
    public RequestConcurrencyStrategy requestConcurrencyStrategy() {
        return new RequestConcurrencyStrategy();
    }
}
