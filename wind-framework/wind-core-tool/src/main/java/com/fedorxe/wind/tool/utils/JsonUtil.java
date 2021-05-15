package com.fedorxe.wind.tool.utils;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedorxe.wind.tool.jackson.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:27
 */
@Slf4j
public class JsonUtil {

    /**
     * 对象转换为json字符串
     * @param entity 要转换的对象
     */
    public static <T> String toJson(T entity) {
        return toJson(entity, false);
    }

    /**
     * 对象转换为json字符串
     * @param entity 要转换的对象
     * @param format 是否格式化json
     */
    public static <T> String toJson(T entity, boolean format)  {
        try {
            if (entity == null) {
                return "";
            }
            if (entity instanceof Number) {
                return entity.toString();
            }
            if (entity instanceof String) {
                return (String) entity;
            }
            if (format) {
                return getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(entity);
            }
            return getInstance().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 字符串转换为指定对象
     * @param json json字符串
     * @param clazz 目标对象
     */
    public static <T> T toEntity(String json, Class<T> clazz) {
        if(StrUtil.isBlank(json) || clazz == null){
            return null;
        }
        try {
            return getInstance().readValue(json, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 字符串转换为指定对象，并增加泛型转义
     * 例如：List<Integer> test = toObject(jsonStr, List.class, Integer.class);
     * @param json json字符串
     * @param parametrized 目标对象
     * @param parameterClasses 泛型对象
     */
    public static <T> T toEntity(String json, Class<?> parametrized, Class<?>... parameterClasses) {
        if(StrUtil.isBlank(json) || parametrized == null){
            return null;
        }
        try {
            JavaType javaType = getInstance().getTypeFactory().constructParametricType(parametrized, parameterClasses);
            return getInstance().readValue(json, javaType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 字符串转换为指定对象
     * @param json json字符串
     * @param typeReference 目标对象类型
     */
    public static <T> T toEntity(String json, TypeReference<T> typeReference) {
        if(StrUtil.isBlank(json) || typeReference == null){
            return null;
        }
        try {
            return getInstance().readValue(json, typeReference);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * json字符串转换为list对象，并指定元素类型
     * @param json json字符串
     * @param cls list的元素类型
     */
    public static <T> List<T> toList(String json, Class<T> cls) {
        if (StrUtil.isBlank(json)) {
            return null;
        }
        try {
            JavaType javaType = getInstance().getTypeFactory().constructParametricType(List.class, cls);
            return getInstance().readValue(json, javaType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * jackson 的类型转换
     *
     * @param fromValue   来源对象
     * @param toValueType 转换的类型
     * @param <T>         泛型标记
     * @return 转换结果
     */
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        if (fromValue == null) {
            return null;
        }
        return getInstance().convertValue(fromValue, toValueType);
    }

    /**
     * jackson 的类型转换
     *
     * @param fromValue   来源对象
     * @param toValueType 转换的类型
     * @param <T>         泛型标记
     * @return 转换结果
     */
    public static <T> T convertValue(Object fromValue, JavaType toValueType) {
        return getInstance().convertValue(fromValue, toValueType);
    }

    public static HashMap<String, Object> toMap(String content) {
        return toEntity(content, HashMap.class, String.class, Object.class);
    }

    public static HashMap<String, Object> toMapString(String content) {
        return toEntity(content, HashMap.class, String.class, String.class);
    }

    public static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    private static class JacksonHolder {
        private static ObjectMapper INSTANCE = new JacksonObjectMapper();
    }

}
