package com.windacc.wind.web.config;

import com.windacc.wind.web.filter.RequestHeaderServletFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
@Slf4j
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class RequestHeaderFilterConfiguration {

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> collectionHeaderFilter() {
        FilterRegistrationBean<OncePerRequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        RequestHeaderServletFilter headerFilter = new RequestHeaderServletFilter();
        filterRegistrationBean.setFilter(headerFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        filterRegistrationBean.setName(RequestHeaderServletFilter.class.getSimpleName());
        return filterRegistrationBean;
    }

}
