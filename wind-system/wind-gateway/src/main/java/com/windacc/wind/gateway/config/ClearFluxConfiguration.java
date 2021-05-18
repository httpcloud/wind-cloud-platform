package com.windacc.wind.gateway.config;

import com.windacc.wind.gateway.filter.ClearDangerousHeaderFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ClearFluxConfiguration {

    @Bean
    public WebFilter webFilter() {
        log.info("================开启请求头收集过滤器================");
        return new ClearDangerousHeaderFilter();
    }

}
