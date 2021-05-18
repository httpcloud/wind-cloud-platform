package com.windacc.wind.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统菜单表，包括模块、菜单、接口、外链
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="系统菜单表，包括模块、菜单、接口、外链")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单父级id")
    @TableField("menu_pid")
    private Integer menuPid;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "请求方法，多个方法用逗号隔开")
    @TableField("menu_method")
    private String menuMethod;

    @ApiModelProperty(value = "菜单路径/接口路径")
    @TableField("menu_path")
    private String menuPath;

    @ApiModelProperty(value = "菜单类型：(1:模块；2:菜单；3:外链 ; 4.组件)")
    @TableField("menu_type")
    private String menuType;

    @ApiModelProperty(value = "排序字段")
    @TableField("menu_order")
    private Integer menuOrder;

    @ApiModelProperty(value = "图标 url")
    @TableField("menu_icon")
    private String menuIcon;

    @ApiModelProperty(value = "关键字")
    @TableField("keyword")
    private String keyword;

    @ApiModelProperty(value = "逻辑删除：0否，1是")
    @TableField("del_flag")
    private Boolean delFlag;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
