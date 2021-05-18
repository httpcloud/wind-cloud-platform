package com.windacc.wind.toolkit.constants;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:24
 */
public interface HeadersConstant {

    /**
     * 用户id信息头
     */
    String USER_HEADER = "X-user-header";

    /**
     * 用户id信息头
     */
    String ENTITY_HEADER = "x-entity-header";

    /**
     * 用户id信息头
     */
    String USER_ID_HEADER = "x-user-id-header";

    /**
     * 用户id信息头
     */
    String USERNAME_HEADER = "x-user-name-header";

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

    /**
     * 版本信息头
     */
    String VERSION_HEADER = "x-version-header";

    /**
     * 传递信息头
     */
    List<String> TRANSMIT_HEADERS = Arrays.asList(
        USER_HEADER.toLowerCase(),
        ENTITY_HEADER.toLowerCase(),
        USER_ID_HEADER.toLowerCase(),
        USERNAME_HEADER.toLowerCase(),
        ROLE_HEADER.toLowerCase(),
        CLIENT_ID_HEADER.toLowerCase(),
        TOKEN_HEADER.toLowerCase(),
        TRACE_ID_HEADER.toLowerCase(),
        SPAN_ID_HEADER.toLowerCase(),
        VERSION_HEADER.toLowerCase()
    );

    /**
     * 清除攻击信息头
     */
    List<String> CLEAR_HEADERS = Arrays.asList(
        USER_HEADER.toLowerCase(),
        ENTITY_HEADER.toLowerCase(),
        USER_ID_HEADER.toLowerCase(),
        USERNAME_HEADER.toLowerCase(),
        ROLE_HEADER.toLowerCase(),
        CLIENT_ID_HEADER.toLowerCase()
    );

}
