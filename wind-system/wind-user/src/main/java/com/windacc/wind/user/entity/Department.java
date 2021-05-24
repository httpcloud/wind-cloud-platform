package com.windacc.wind.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门信息表
 * </p>
 *
 * @author codeGen
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_department")
@ApiModel(value="Department对象", description="部门信息表")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @ApiModelProperty(value = "上级部门id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "部门类型")
    @TableField("dept_category")
    private Integer deptCategory;

    @ApiModelProperty(value = "部门名称")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "部门全称")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "生效时间")
    @TableField("eff_time")
    private LocalDateTime effTime;

    @ApiModelProperty(value = "失效时间")
    @TableField("exp_time")
    private LocalDateTime expTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
