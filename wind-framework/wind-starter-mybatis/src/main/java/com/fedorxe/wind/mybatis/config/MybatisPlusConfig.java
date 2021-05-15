package com.fedorxe.wind.mybatis.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.fedorxe.wind.mybatis.injetor.WindSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * <p>mybatis-plus 配置类   </p>
 *
 * @author fedorxe
 * @date 2021/5/14 22:27
 */
@Import(DateMetaObjectHandler.class)
public class MybatisPlusConfig {

    @Bean
    public ISqlInjector iSqlInjector() {
        return new WindSqlInjector();
    }

    /**
     * 分页插件，自动识别数据库类型
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
