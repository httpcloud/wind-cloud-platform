package com.fedorxe.wind.mybatis.method;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.fedorxe.wind.mybatis.enums.WindMethod;
import com.fedorxe.wind.mybatis.enums.WindTableInfo;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/14 22:27
 */
public class UpsertMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {

        WindTableInfo windTableInfo = new WindTableInfo(tableInfo);

        KeyGenerator keyGenerator = new NoKeyGenerator();
        WindMethod sqlMethod = WindMethod.UPSERT_ONE;
        String columnScript = SqlScriptUtils
                .convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(), LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils
                .convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(null), LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String sqlDuplicate = windTableInfo.getAllInsertSqlDuplicate();
        String duplicateScript = SqlScriptUtils.convertTrim(sqlDuplicate, null, null, null, COMMA);
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotBlank(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                // 自增主键
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(sqlMethod.getMethod(), tableInfo, builderAssistant);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }

        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript, duplicateScript);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource, keyGenerator,
                keyProperty, keyColumn);
    }
}
