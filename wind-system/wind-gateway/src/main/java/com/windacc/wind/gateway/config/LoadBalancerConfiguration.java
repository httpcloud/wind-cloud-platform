package com.windacc.wind.gateway.config;

import com.windacc.wind.gateway.filter.MyLoadBalancerClientFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerClientAutoConfiguration;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.DispatcherHandler;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ LoadBalancerClient.class, RibbonAutoConfiguration.class,
                        DispatcherHandler.class })
@AutoConfigureBefore(GatewayLoadBalancerClientAutoConfiguration.class)
@AutoConfigureAfter(RibbonAutoConfiguration.class)
@EnableConfigurationProperties(LoadBalancerProperties.class)
public class LoadBalancerConfiguration {

    private final SpringClientFactory springClientFactory;

    public LoadBalancerConfiguration(SpringClientFactory springClientFactory) {
        this.springClientFactory = springClientFactory;
    }

    @Primary
    @Bean
    public LoadBalancerClientFilter loadBalancerClientFilter(LoadBalancerProperties properties) {

        RibbonLoadBalancerClient loadBalancerClient = new RibbonLoadBalancerClient(springClientFactory);
        log.info("================开启自定义负载过滤器================");
        MyLoadBalancerClientFilter loadBalancerClientFilter = new MyLoadBalancerClientFilter(loadBalancerClient, properties);
        return loadBalancerClientFilter;
    }

}
