package com.windacc.wind.toolkit.constants;

import org.springframework.core.Ordered;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:24
 */
public interface FilterOrderConstant {

    /**
     * WebFilterChainProxy order
     */
    int WEB_FILTER_CHAIN_FILTER_ORDER = -100;

    /**
     * TraceWebFilter order
     */
    int TRACING_FILTER_ORDER = Ordered.HIGHEST_PRECEDENCE + 5;

    int HIGHEST_PRECEDENCE = Ordered.HIGHEST_PRECEDENCE;

}
