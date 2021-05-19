package com.windacc.wind.toolkit.context;

import cn.hutool.core.map.CaseInsensitiveMap;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:26
 */
@Slf4j
public class TransmitContextHolder {

    /**
     * 支持父子线程之间的数据传递
     */
    private static final ThreadLocal<Map<String, Object>> CONTEXT = new TransmittableThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new CaseInsensitiveMap<>();
        }
    };

    private static Map<String, Object> getContextMap() {
        return CONTEXT.get();
    }

    public static Map<String, Object> getContext() {
        return Collections.unmodifiableMap(getContextMap());
    }

    public static void setContext(Map<String, Object> tenant) {
        tenant.forEach(TransmitContextHolder::addKey);
    }

    public static void addKey(String key, Object value) {
        final Map<String, Object> map = getContextMap();
        map.put(key, value);
    }

    public static Object getKey(String key) {
        final Map<String, Object> map = getContextMap();
        return map.get(key);
    }

    public static void removeKey(String key) {
        final Object remove = getContextMap().remove(key);
    }

    public static void remove() {
        CONTEXT.remove();
    }

}
