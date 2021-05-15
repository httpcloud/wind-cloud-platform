package com.fedorxe.wind.tool.context;

import cn.hutool.core.collection.CollectionUtil;
import com.fedorxe.wind.tool.constants.HeadersConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:26
 */
@Slf4j
public class TraceContextHolder {

    public static void setTraceId(String traceId) {
        MDC.put(HeadersConstant.TRACE_ID_HEADER, traceId);
        TransmitContextHolder.addKey(HeadersConstant.TRACE_ID_HEADER, traceId);
    }

    private static String getContextTraceId() {
        Map<String, String> context = TransmitContextHolder.getContext();
        if (CollectionUtil.isEmpty(context)) {
            return null;
        }
        return context.get(HeadersConstant.TRACE_ID_HEADER);
    }

    private static void clearContextTraceId() {
        TransmitContextHolder.removeKey(HeadersConstant.TRACE_ID_HEADER);
    }

    public static String getTraceId() {
        return getContextTraceId();
    }

    public static void clear() {
        clearContextTraceId();
    }
}
