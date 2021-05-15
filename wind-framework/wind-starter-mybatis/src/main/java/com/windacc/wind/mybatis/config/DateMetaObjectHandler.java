package com.windacc.wind.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * <p>自动填充字段   </p>
 *
 * @author windacc
 * @date 2021/5/14 22:27
 */
public class DateMetaObjectHandler implements MetaObjectHandler {
    private final static String UPDATE_TIME = "updateTime";
    private final static String CREATE_TIME = "createTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(CREATE_TIME, metaObject);
        Object updateTime = getFieldValByName(UPDATE_TIME, metaObject);
        if (createTime == null || updateTime == null) {
            LocalDateTime now = LocalDateTime.now();
            if (createTime == null) {
                setFieldValByName(CREATE_TIME, now, metaObject);
            }
            if (updateTime == null) {
                setFieldValByName(UPDATE_TIME, now, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
}