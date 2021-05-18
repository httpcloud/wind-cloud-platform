package com.windacc.wind.toolkit.context;

import cn.hutool.core.collection.CollectionUtil;
import com.windacc.wind.toolkit.constants.HeadersConstant;
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

    public static void setClient(String tenant) {
        TransmitContextHolder.addKey(HeadersConstant.CLIENT_ID_HEADER, tenant);
    }

    private static String getClientTenant() {
        Map<String, Object> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return (String) context.get(HeadersConstant.CLIENT_ID_HEADER);
    }

    private static void clearContextTenant() {
        TransmitContextHolder.removeKey(HeadersConstant.CLIENT_ID_HEADER);
    }

    public static String getClient() {
        return getClientTenant();
    }

    public static void clear() {
        clearContextTenant();
    }
}
