package com.windacc.wind.flow.config;

import com.windacc.wind.api.feign.IUserClient;
import com.windacc.wind.flow.service.impl.WindIdmIdentityServiceImpl;
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

    private final IUserClient userClient;

    public FlowableEngineConfiguration(IUserClient userClient) {
        this.userClient = userClient;
    }

    @Bean
    public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> ldapIdmEngineConfigurer() {
        return idmEngineConfiguration -> idmEngineConfiguration
            .setIdmIdentityService(new WindIdmIdentityServiceImpl(idmEngineConfiguration, userClient));
    }

}
