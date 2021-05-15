package com.fedorxe.wind.tool.constants;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:24
 */
public interface HeadersConstant {

    /**
     * 用户id信息头
     */
    String USER_ID_HEADER = "x-user_id-header";

    /**
     * 角色信息头
     */
    String ROLE_HEADER = "x-role-header";

    /**
     * 客户端id信息头
     */
    String CLIENT_ID_HEADER = "x-client_id-header";

    /**
     * token 请求头
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * 日志跟踪id名。
     */
    String TRACE_ID_HEADER = "X-B3-TraceId";

    /**
     * 订单id名。
     */
    String SPAN_ID_HEADER = "X-B3-SpanId";

    default String getTraceId() {
        String traceId = MDC.get(TRACE_ID_HEADER);
        //如果已存在链路追踪 则直接返回
        if (StrUtil.isNotBlank(traceId)) {
            return traceId;
        }
        //如果没有传递 则这里开始设置赋值，格式如下：
        return RandomUtil.randomString(16);
    }

    /**
     * 版本信息头
     */
    String VERSION_HEADER = "x-version-header";

    /**
     * 传递信息头
     */
    List<String> TRANSMIT_HEADERS = Arrays.asList(
        USER_ID_HEADER.toLowerCase(),
        ROLE_HEADER.toLowerCase(),
        CLIENT_ID_HEADER.toLowerCase(),
        TOKEN_HEADER.toLowerCase(),
        TRACE_ID_HEADER.toLowerCase(),
        SPAN_ID_HEADER.toLowerCase(),
        VERSION_HEADER.toLowerCase()
    );
}
