package com.fedorxe.wind.tool.context;

import cn.hutool.core.collection.CollectionUtil;
import com.fedorxe.wind.tool.constants.HeadersConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:25
 */
@Slf4j
public class ClientContextHolder {

    public static void setClient(String tenant) {
        TransmitContextHolder.addKey(HeadersConstant.CLIENT_ID_HEADER, tenant);
    }

    private static String getClientTenant() {
        Map<String, String> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return context.get(HeadersConstant.CLIENT_ID_HEADER);
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
