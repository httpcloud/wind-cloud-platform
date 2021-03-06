package com.windacc.wind.toolkit.constants;

import java.time.format.DateTimeFormatter;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/14 23:31
 */
public interface CommonConstant {

    /**
     * 时区
     */
    String TIME_ZONE_GMT8 = "GMT+8";

    /**
     * 分布式锁的前缀
     */
    String LOCK_KEY_PREFIX = "LOCK_KEY:";

    /**
     * 时间格式
     */
    String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式
     */
    String PATTERN_DATE = "yyyy-MM-dd";
    /**
     * 时间格式
     */
    String PATTERN_TIME = "HH:mm:ss";

    /**
     * 时间格式
     */
    DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(PATTERN_DATETIME);
    /**
     * 时间格式
     */
    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(PATTERN_DATE);
    /**
     * 时间格式
     */
    DateTimeFormatter TIME_FORMAT =  DateTimeFormatter.ofPattern(PATTERN_TIME);

    /**
     * 注册中心元数据 版本号
     */
    String METADATA_VERSION = "version";

    /**
     * 路由规则
     */
    String METADATA_LB_RULE = "lbRule";

}
