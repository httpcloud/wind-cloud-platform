package com.windacc.wind.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(name = "wind.gateway.another-http.port")
public class AnotherHttpPortConfig {
    @Resource
    private HttpHandler httpHandler;

    private WebServer webServer;

    @Value("${wind.gateway.another-http.port:80}")
    private Integer httpPort;

    @PostConstruct
    public void start() {
        NettyReactiveWebServerFactory factory = new NettyReactiveWebServerFactory(httpPort);
        webServer = factory.getWebServer(httpHandler);
        webServer.start();
    }

    @PreDestroy
    public void stop() {
        webServer.stop();
    }
}
