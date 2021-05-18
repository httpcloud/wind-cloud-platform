package com.windacc.wind.gateway.filter;

import cn.hutool.core.collection.ListUtil;
import com.windacc.wind.gateway.util.WebfluxResponseUtil;
import com.windacc.wind.toolkit.constants.FilterOrderConstant;
import com.windacc.wind.toolkit.constants.HeadersConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/17
 */
@Slf4j
public class ClearDangerousHeaderFilter implements WebFilter, Ordered {

    private static final List<String> DANGER_HEADERS = ListUtil.toList(HeadersConstant.CLEAR_HEADERS);

    @Override
    public int getOrder() {
        //200
        int order = FilterOrderConstant.TRACING_FILTER_ORDER + 5;
        return order;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("执行敏感head清除过滤器ClearWebFilter");
        final ServerWebExchange serverWebExchange = WebfluxResponseUtil.removeHeaderByMap(exchange, DANGER_HEADERS);
        return chain.filter(serverWebExchange);
    }

}
