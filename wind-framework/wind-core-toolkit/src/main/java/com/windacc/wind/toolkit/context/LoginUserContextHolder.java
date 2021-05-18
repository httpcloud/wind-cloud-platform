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
    public static void setUser(String user) {
        TransmitContextHolder.addKey(HeadersConstant.USER_HEADER, user);
    }
    public static void setEntity(LoginUser loginUser) {
        TransmitContextHolder.addKey(HeadersConstant.USER_HEADER, loginUser);
    }

    private static String getContextUsername() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (String) context.get(HeadersConstant.USERNAME_HEADER);
    }
    private static String getContextUser() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (String) context.get(HeadersConstant.USER_HEADER);
    }

    private static void clearContextUserId() {
        TransmitContextHolder.removeKey(HeadersConstant.USER_ID_HEADER);
    }

    public static String getUsername() {
        return getContextUsername();
    }
    public static String getUser() {
        return getContextUser();
    }

    public static void clear() {
        clearContextUserId();
    }

}
