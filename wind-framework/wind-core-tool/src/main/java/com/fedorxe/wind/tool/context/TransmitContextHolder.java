package com.fedorxe.wind.tool.context;

import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:26
 */
@Slf4j
public class TransmitContextHolder {

    /**
     * 支持父子线程之间的数据传递
     */
    private static final ThreadLocal<Map<String, String>> CONTEXT = new TransmittableThreadLocal<Map<String, String>>() {
        @Override
        protected Map<String, String> initialValue() {
            return new HashMap<>();
        }
    };

    private static Map<String, String> getContextMap() {
        return CONTEXT.get();
    }

    public static Map<String, String> getContext() {
        return Collections.unmodifiableMap(getContextMap());
    }

    public static void setContext(Map<String, String> tenant) {
        tenant.forEach(TransmitContextHolder::addKey);
    }

    public static void addKey(String key, String value) {
        final String oldStr = getContextMap().put(key, value);
        if (StrUtil.isNotBlank(oldStr)) {
            log.debug("全局上下文数据替换 key : {} old : {} new : {} ", key, oldStr, value);
        }
    }

    public static String getKey(String key) {
        return getContextMap().get(key);
    }

    public static void removeKey(String key) {
        final String remove = getContextMap().remove(key);
        log.debug("删除上下文key {}  oldValue {}", key, remove);
    }

    public static void remove() {
        CONTEXT.remove();
    }

}
