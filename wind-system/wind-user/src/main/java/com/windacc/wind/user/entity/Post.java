package com.windacc.wind.user.entity;

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
 * 岗位信息表
 * </p>
 *
 * @author codeGen
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_post")
@ApiModel(value="Post对象", description="岗位信息表")
public class Post extends Model<Post> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @ApiModelProperty(value = "岗位类型")
    @TableField("post_type")
    private String postType;

    @ApiModelProperty(value = "岗位编码")
    @TableField("post_code")
    private String postCode;

    @ApiModelProperty(value = "岗位名称")
    @TableField("post_name")
    private String postName;

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
