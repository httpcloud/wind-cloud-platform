package com.windacc.wind.toolkit.entity;

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
 * 用户信息表
 * </p>
 *
 * @author codeGen
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="User对象", description="用户信息表")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @ApiModelProperty(value = "账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "邮箱地址")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "邮箱是否认证：0正常 1 未认证")
    @TableField("email_status")
    private String emailStatus;

    @ApiModelProperty(value = "性别 0 未知  1 男 2 女")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "openId 用于第三方登录")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "状态：0正常 1 禁用/关闭 2 冻结 3 注销 ")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "用户类型  1 app    2 web")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "0 未实名  1 已实名")
    @TableField("real_flag")
    private Boolean realFlag;

    @ApiModelProperty(value = "上一次登陆时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "部门id")
    @TableField("dept_id")
    private Integer deptId;

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
