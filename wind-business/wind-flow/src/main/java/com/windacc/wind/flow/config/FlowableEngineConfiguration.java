package com.windacc.wind.flow.config;

import com.windacc.wind.flow.handler.WindIdmIdentityServiceImpl;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>注入自定义身份认证接口   </p>
 *
 * @author windacc
 * @date 2021/5/21
 */
@Configuration
public class FlowableEngineConfiguration {

    @Bean
    public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> ldapIdmEngineConfigurer() {
        return idmEngineConfiguration -> idmEngineConfiguration
            .setIdmIdentityService(new WindIdmIdentityServiceImpl(idmEngineConfiguration));
    }

}
