package com.windacc.wind.toolkit.context;

import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.util.ObjectUtil;
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
        log.info("");
    }

    public static void addKey(String key, Object value) {
        final Map<String, Object> map = getContextMap();
        final Object oldStr = map.put(key, value);

        if (ObjectUtil.isNotEmpty(oldStr)) {
            log.info("全局上下文数据替换 key : {} old : {} new : {} ", key, oldStr, value);
        }
    }

    public static Object getKey(String key) {
        final Map<String, Object> map = getContextMap();
        return map.get(key);
    }

    public static void removeKey(String key) {
        final Object remove = getContextMap().remove(key);
        log.debug("删除上下文key {}  oldValue {}", key, remove);
    }

    public static void remove() {
        CONTEXT.remove();
    }

}
