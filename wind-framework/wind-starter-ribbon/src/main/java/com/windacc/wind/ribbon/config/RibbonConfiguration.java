package com.windacc.wind.ribbon.config;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.IRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonApplicationContextInitializer;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * <p>负载均衡自定义规则   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@Slf4j
@ConditionalOnClass({ NacosServer.class, IRule.class})
@RibbonClients(defaultConfiguration = { RibbonHeaderPredicate.class})
public class RibbonConfiguration {

    private final DiscoveryClient discoveryClient;

    public RibbonConfiguration(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Bean
    @ConditionalOnMissingBean({ RibbonApplicationContextInitializer.class})
    public RibbonApplicationContextInitializer ribbonApplicationContextInitializer(
        SpringClientFactory springClientFactory) {
        List<String> services = discoveryClient.getServices();
        log.info("从注册中心拉取所有微服务的信息{}", services);
        return new RibbonApplicationContextInitializer(springClientFactory, services);
    }

}
