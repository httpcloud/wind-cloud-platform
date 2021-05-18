package com.windacc.wind.web.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.windacc.wind.toolkit.constants.FilterOrderConstant;
import com.windacc.wind.toolkit.constants.HeadersConstant;
import com.windacc.wind.toolkit.context.ClientContextHolder;
import com.windacc.wind.toolkit.context.LoginUserContextHolder;
import com.windacc.wind.toolkit.context.TransmitContextHolder;
import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.toolkit.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
@Slf4j
public class RequestHeaderServletFilter extends OncePerRequestFilter implements Ordered {
    /**
     * 需要向下游传递的头
     */
    private static final List<String> NEED_TRANSMIT_HEADERS = HeadersConstant.TRANSMIT_HEADERS;

    private void beforeHandler(HttpServletRequest request) {
        log.debug("进入请求头收集过滤器，收集可能需要的传递给下游的头");
        Map<String, Object> context1 = TransmitContextHolder.getContext();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, Object> map = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerKey = headerNames.nextElement();
            //如果是需要的头 或者 是染色格式的头
            if (NEED_TRANSMIT_HEADERS.contains(headerKey)) {
                String headerValue = request.getHeader(headerKey);
                if (StrUtil.isNotBlank(headerValue)) {
                    map.put(headerKey, headerValue);
                }
            }
        }
        log.info("收集到的请求头信息 {}", map);
        if (CollectionUtil.isNotEmpty(map)) {
            //放入上下文中
            TransmitContextHolder.setContext(map);
        }

        String user = LoginUserContextHolder.getUser();
        String username = LoginUserContextHolder.getUsername();
        String clientId = ClientContextHolder.getClient();

        LoginUser loginUser = JsonUtil.toEntity(user, LoginUser.class);
        log.info("user:");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            beforeHandler(request);
            filterChain.doFilter(request, response);
        } finally {
            TransmitContextHolder.remove();
        }
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.HIGHEST_PRECEDENCE;
    }
}
