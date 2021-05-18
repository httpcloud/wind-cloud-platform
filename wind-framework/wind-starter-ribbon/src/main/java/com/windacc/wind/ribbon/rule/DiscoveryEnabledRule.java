package com.windacc.wind.ribbon.rule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.windacc.wind.toolkit.constants.CommonConstant;
import com.windacc.wind.toolkit.constants.HeadersConstant;
import com.windacc.wind.toolkit.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>根据HttpHeader进行负载   </p>
 *
 * @author windacc
 * @date 2021/5/17
 */
@Slf4j
public class DiscoveryEnabledRule extends RoundRobinRule {

    @Override
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        HashMap<String, String> headers = RequestUtil.getHeaders();
        final List<Server> versionList = new ArrayList<>();
        final List<Server> permitList = new ArrayList<>();
        final String headVersion = getVersion(key);
        final List<Server> allServers = lb.getReachableServers();
        allServers.forEach(server -> {
            NacosServer nacosServer = (NacosServer) server;
            String metaVersion = nacosServer.getMetadata().get(CommonConstant.METADATA_VERSION);
            if (StrUtil.isNotBlank(headVersion)) {
                if (headVersion.equals(metaVersion)) {
                    versionList.add(server);
                } else if (StrUtil.isBlank(metaVersion)) {
                    permitList.add(server);
                }
            } else {
                if (StrUtil.isBlank(metaVersion)) {
                    versionList.add(server);
                }
            }
        });
        log.info("版本负载规则处理结束,headVersion={}, allServers={}, versionList={}, permitList={}", headVersion,
            allServers.size(), versionList.size(), permitList.size());
        if (CollectionUtil.isNotEmpty(versionList)) {
            return getServer(versionList);
        }
        if (CollectionUtil.isNotEmpty(permitList)) {
            return getServer(permitList);
        }
        return super.choose(lb, key);
    }

    private Server getServer(List<Server> upList) {
        int nextInt = RandomUtil.randomInt(upList.size());
        return upList.get(nextInt);
    }

    private String getVersion(Object key) {
        if (key instanceof HttpHeaders) {
            HttpHeaders headers = (HttpHeaders) key;
            return headers.getFirst(HeadersConstant.VERSION_HEADER);
        }
        HashMap<String, String> headers = RequestUtil.getHeaders();
        return headers.get(HeadersConstant.VERSION_HEADER);
    }

}
