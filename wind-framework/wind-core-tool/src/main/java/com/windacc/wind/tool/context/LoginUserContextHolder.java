package com.windacc.wind.tool.context;

import cn.hutool.core.collection.CollectionUtil;
import com.windacc.wind.tool.constants.HeadersConstant;

import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:47
 */
public class LoginUserContextHolder {

    public static void setUserId(String userId) {
        TransmitContextHolder.addKey(HeadersConstant.USER_ID_HEADER, userId);
    }

    private static String getContextUserId() {
        Map<String, String> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return context.get(HeadersConstant.USER_ID_HEADER);
    }

    private static void clearContextUserId() {
        TransmitContextHolder.removeKey(HeadersConstant.USER_ID_HEADER);
    }

    public static String getUserId() {
        return getContextUserId();
    }

    public static void clear() {
        clearContextUserId();
    }

}
