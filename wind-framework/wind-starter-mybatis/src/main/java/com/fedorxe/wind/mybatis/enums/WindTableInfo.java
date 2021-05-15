package com.fedorxe.wind.mybatis.enums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/14 22:27
 */
@Data
@Setter(AccessLevel.PACKAGE)
@Accessors(chain = true)
public class WindTableInfo implements Constants {

    private static final String VALUE = "values";

    private TableInfo tableInfo;

    private List<TableFieldInfo> fieldList;

    private String keyColumn;

    private String keyProperty;

    private String tableName;

    private IdType idType;

    private KeySequence keySequence;

    public WindTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
        this.fieldList = tableInfo.getFieldList();
        this.keyColumn = tableInfo.getKeyColumn();
        this.keyProperty = tableInfo.getKeyProperty();
        this.idType = tableInfo.getIdType();
        this.keySequence = tableInfo.getKeySequence();
    }

    public String getAllInsertSqlDuplicate() {
        String keyScript = getDuplicateUpdate(keyColumn);

        String fieldValue = fieldList.stream().filter(s -> !"tenant_id".equals(s.getColumn()))
            .map(i -> getDuplicateUpdate(i.getColumn())).collect(joining(NEWLINE));
        return keyScript + NEWLINE + fieldValue;
    }

    private String getDuplicateUpdate(String column) {
        return column + SPACE + EQUALS + SPACE + VALUE + LEFT_BRACKET + column + RIGHT_BRACKET + COMMA;
    }

}
