package com.windacc.wind.toolkit.context;

import cn.hutool.core.collection.CollectionUtil;
import com.windacc.wind.toolkit.constants.HeadersConstant;
import com.windacc.wind.toolkit.entity.LoginUser;

import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:47
 */
public class LoginUserContextHolder {

    public static void setUsername(String username) {
        TransmitContextHolder.addKey(HeadersConstant.USERNAME_HEADER, username);
    }

    public static void setUserJson(String userJson) {
        TransmitContextHolder.addKey(HeadersConstant.USER_JSON_HEADER, userJson);
    }

    public static void setEntity(LoginUser loginUser) {
        TransmitContextHolder.addKey(HeadersConstant.USER_ENTITY_HEADER, loginUser);
    }

    private static String getContextUsername() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (String) context.get(HeadersConstant.USERNAME_HEADER);
    }
    private static String getContextUserJson() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (String) context.get(HeadersConstant.USER_JSON_HEADER);
    }
    private static LoginUser getContextEntity() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (LoginUser) context.get(HeadersConstant.USER_ENTITY_HEADER);
    }

    private static void clearContextUserJson() {
        TransmitContextHolder.removeKey(HeadersConstant.USER_JSON_HEADER);
    }
    private static void clearContextUsername() {
        TransmitContextHolder.removeKey(HeadersConstant.USERNAME_HEADER);
    }
    private static void clearContextEntity() {
        TransmitContextHolder.removeKey(HeadersConstant.USER_ENTITY_HEADER);
    }

    public static String getUsername() {
        return getContextUsername();
    }
    public static String getUserJson() {
        return getContextUserJson();
    }
    public static LoginUser getEntity() {
        return getContextEntity();
    }

    public static void clear() {
        clearContextUserJson();
    }
    public static void clearAll() {
        clearContextUserJson();
        clearContextUsername();
        clearContextEntity();
    }

}
