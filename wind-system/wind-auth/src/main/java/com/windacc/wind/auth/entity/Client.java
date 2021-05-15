package com.windacc.wind.auth.entity;

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
 * 客户端信息表
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_client")
@ApiModel(value="Client对象", description="客户端信息表")
public class Client extends Model<Client> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "应用名称")
    @TableField("client_name")
    private String clientName;

    @ApiModelProperty(value = "应用标识")
    @TableField("client_id")
    private String clientId;

    @ApiModelProperty(value = "资源限定串(逗号分割)")
    @TableField("resource_ids")
    private String resourceIds;

    @ApiModelProperty(value = "应用密钥(bcyt) 加密")
    @TableField("client_secret")
    private String clientSecret;

    @ApiModelProperty(value = "应用密钥(明文)")
    @TableField("client_secret_str")
    private String clientSecretStr;

    @ApiModelProperty(value = "范围")
    @TableField("scope")
    private String scope;

    @ApiModelProperty(value = "5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)")
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "回调地址 ")
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "权限")
    @TableField("authorities")
    private String authorities;

    @ApiModelProperty(value = "access_token有效期")
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "refresh_token有效期")
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "{}")
    @TableField("additional_information")
    private String additionalInformation;

    @ApiModelProperty(value = "是否自动授权 是-true")
    @TableField("autoapprove")
    private String autoapprove;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
