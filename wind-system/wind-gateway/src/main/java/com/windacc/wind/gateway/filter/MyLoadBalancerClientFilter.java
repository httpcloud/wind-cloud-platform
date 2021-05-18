package com.windacc.wind.gateway.filter;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@SuppressWarnings("deprecation")
public class MyLoadBalancerClientFilter extends LoadBalancerClientFilter {

    private final RibbonLoadBalancerClient loadBalancerClient;

    public MyLoadBalancerClientFilter(RibbonLoadBalancerClient loadBalancer, LoadBalancerProperties properties) {
        super(loadBalancer, properties);
        this.loadBalancerClient = loadBalancer;
    }

    @Override
    protected ServiceInstance choose(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        return loadBalancerClient.choose(
            ((URI) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)).getHost(), headers);
    }

}
