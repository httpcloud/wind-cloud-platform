package com.windacc.wind.toolkit.context;

import cn.hutool.core.collection.CollectionUtil;
import com.windacc.wind.toolkit.constants.HeadersConstant;
import com.windacc.wind.toolkit.entity.LoginClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:25
 */
@Slf4j
public class ClientContextHolder {

    public static void setClientId(String clientId) {
        TransmitContextHolder.addKey(HeadersConstant.CLIENT_ID_HEADER, clientId);
    }
    public static void setClientJson(String clientJson) {
        TransmitContextHolder.addKey(HeadersConstant.CLIENT_JSON_HEADER, clientJson);
    }
    public static void setEntity(LoginClient loginClient) {
        TransmitContextHolder.addKey(HeadersConstant.CLIENT_ENTITY_HEADER, loginClient);
    }

    private static String getContextClientId() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (String) context.get(HeadersConstant.CLIENT_ID_HEADER);
    }
    private static String getContextClientJson() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (String) context.get(HeadersConstant.CLIENT_JSON_HEADER);
    }
    private static LoginClient getContextEntity() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (LoginClient) context.get(HeadersConstant.CLIENT_ENTITY_HEADER);
    }

    private static void clearContextClientId() {
        TransmitContextHolder.removeKey(HeadersConstant.CLIENT_ID_HEADER);
    }
    private static void clearContextClientJson() {
        TransmitContextHolder.removeKey(HeadersConstant.CLIENT_JSON_HEADER);
    }
    private static void clearContextEntity() {
        TransmitContextHolder.removeKey(HeadersConstant.CLIENT_ENTITY_HEADER);
    }

    public static String getClientId() {
        return getContextClientId();
    }
    public static String getClientJson() {
        return getContextClientJson();
    }
    public static LoginClient getEntity() {
        return getContextEntity();
    }

    public static void clear() {
        clearContextClientJson();
    }
    public static void clearAll() {
        clearContextClientJson();
        clearContextClientId();
        clearContextEntity();
    }

}
