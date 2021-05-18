package com.windacc.wind.ribbon.config;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.IRule;
import com.windacc.wind.ribbon.rule.DiscoveryEnabledRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@Slf4j
public class RibbonHeaderPredicate {

    @Bean
    @ConditionalOnClass(NacosServer.class)
    @ConditionalOnMissingBean(DiscoveryEnabledRule.class)
    public IRule discoveryEnabledRule() {
        log.info("=============注入负载引擎================");
        return new DiscoveryEnabledRule();
    }

}
