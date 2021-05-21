package com.windacc.wind.ribbon.config;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.IRule;
import com.windacc.wind.ribbon.rule.RibbonHeaderPredicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * <p>负载均衡自定义规则   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@Slf4j
@ConditionalOnClass({ NacosServer.class, IRule.class})
@RibbonClients(defaultConfiguration = { RibbonHeaderPredicate.class})
@ConditionalOnProperty(name = "wind.ribbon.filter.enabled", havingValue="true")
public class RibbonConfiguration {

}
