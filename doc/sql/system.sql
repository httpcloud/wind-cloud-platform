/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : wind-center

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 22/05/2021 23:21:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client`
(
    `id`                      int UNSIGNED                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `client_name`             varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '应用名称',
    `client_id`               varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '应用标识',
    `resource_ids`            varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '资源限定串(逗号分割)',
    `client_secret`           varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
    `client_secret_str`       varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '应用密钥(明文)',
    `scope`                   varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '范围',
    `authorized_grant_types`  varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
    `web_server_redirect_uri` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '回调地址 ',
    `authorities`             varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '权限',
    `access_token_validity`   int UNSIGNED                                                  NULL     DEFAULT NULL COMMENT 'access_token有效期',
    `refresh_token_validity`  int UNSIGNED                                                  NULL     DEFAULT NULL COMMENT 'refresh_token有效期',
    `additional_information`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '{}' COMMENT '{}',
    `autoapprove`             char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL DEFAULT 'true' COMMENT '是否自动授权 是-true',
    `create_time`             datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP,
    `update_time`             datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `sys_client_client_id` (`client_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '客户端信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_client
-- ----------------------------
INSERT INTO `sys_client`
VALUES (1, '通用客户端', 'app', NULL, '$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO', 'app', 'app',
        'authorization_code,password,refresh_token,mobile_code,email_pwd,open_id,mobile_pwd,password_code,client_credentials',
        'http://www.baidu.com', NULL, 18000000, NULL, '{}', 'true', '2021-05-20 13:14:20', '2021-05-20 13:14:20');
INSERT INTO `sys_client`
VALUES (2, '手机客户端', 'mobile', NULL, '$2a$10$ULxRssv/4NWOc388lZFbyus3IFfsbcpG/BZOq4TRxDhsx5HHIR7Jm', 'mobile', 'mobile',
        'mobile_code,mobile_pwd', NULL, NULL, 180000, NULL, '{}', 'true', '2021-05-20 13:14:20', '2021-05-20 13:14:20');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          int UNSIGNED                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '租户id',
    `menu_pid`    int UNSIGNED                                                  NULL     DEFAULT NULL COMMENT '菜单父级id',
    `menu_name`   varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '菜单名称',
    `menu_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '请求方法，多个方法用逗号隔开',
    `menu_path`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '菜单路径/接口路径',
    `menu_type`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT NULL COMMENT '菜单类型：(1:模块；2:菜单；3:外链 ; 4.组件)',
    `menu_order`  int                                                           NULL     DEFAULT 0 COMMENT '排序字段',
    `menu_icon`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '图标 url',
    `keyword`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '关键字',
    `create_time` datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`    datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`    datetime                                                      NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1020202
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表，包括模块、菜单、接口、外链'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1020201, '0', 10202, '账号管理-新增', NULL, '/wind-auth/get', '1', 2, NULL, '账号管理-新增',
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);
INSERT INTO `sys_menu`
VALUES (1020202, '0', 10202, '账号管理-详情', NULL, '/wind-auth/new', '1', 2, NULL, '账号管理-详情',
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          int UNSIGNED                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '租户id',
    `role_code`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色id',
    `role_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '角色名称',
    `role_desc`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '角色描述',
    `create_time` datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`    datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`    datetime                                                      NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `sys_role_role_code` (`role_code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11111
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '0', 'admin', '系统管理员', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);
INSERT INTO `sys_role`
VALUES (3, '0', 'user', '普通用户', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          int UNSIGNED                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '租户id',
    `role_id`     int UNSIGNED                                                 NOT NULL COMMENT '角色id',
    `menu_id`     int UNSIGNED                                                 NOT NULL COMMENT '菜单id',
    `create_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`    datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`    datetime                                                     NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11111
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色-资源关系表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (4270, '0', 1, 1020202, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4271, '0', 1, 1020201, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4272, '0', 3, 1020202, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4273, '0', 3, 1020201, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`              int UNSIGNED                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`       varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '租户id',
    `username`        varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '账号',
    `password`        varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '密码',
    `nick_name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
    `mobile`          varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '手机号',
    `email`           varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '邮箱地址',
    `email_status`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT '1' COMMENT '邮箱是否认证：0正常 1 未认证',
    `sex`             char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT '0' COMMENT '性别 0 未知  1 男 2 女',
    `open_id`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT 'openId 用于第三方登录',
    `status`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT '0' COMMENT '状态：0正常 1 禁用/关闭 2 冻结 3 注销 ',
    `type`            char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT '0' COMMENT '用户类型  1 app    2 web',
    `real_flag`       tinyint(1)                                                    NULL     DEFAULT 0 COMMENT '0 未实名  1 已实名',
    `last_login_time` datetime                                                      NULL     DEFAULT NULL COMMENT '上一次登陆时间',
    `dept_id`         int UNSIGNED                                                  NOT NULL COMMENT '部门id',
    `create_time`     datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`        datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`        datetime                                                      NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `sys_user_username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11111
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (32, '0', 'admin', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '管理员', '13088888888',
        'windacc@163.com', '0', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 1001,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (33, '0', 'market1', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '市场部1', '13188888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100101,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (34, '0', 'market2', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '市场部2', '13288888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100101,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (35, '0', 'market3', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '市场部3', '13388888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100101,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (36, '0', 'market4', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '市场部4', '13488888888',
        '', '1', '0', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100101,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (37, '0', 'market5', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '市场部5', '13588888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100101,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (38, '0', 'manager1', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '市场部经理', '13688888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100101,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (39, '0', 'tech1', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '科技部1', '13788888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100102,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (40, '0', 'tech2', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '科技部2', '13888888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100102,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (41, '0', 'manager2', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '科技部经理', '13988888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100102,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (42, '0', 'hr1', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '人资1', '18088888888',
        '', '1', '0', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100103,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (43, '0', 'hr2', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '人资2', '18188888888',
        '', '1', '0', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100103,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (44, '0', 'hr3', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '人资3', '18288888888',
        '', '1', '1', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100103,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (45, '0', 'manager3', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '人资部经理', '18388888888',
        '', '1', '0', '8817276-2917263-22211', '0', '1', 1, '2020-11-17 16:56:59', 100103,
        '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          int UNSIGNED                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '租户id',
    `user_id`     int UNSIGNED                                                 NOT NULL COMMENT '用户Id',
    `role_id`     int UNSIGNED                                                 NOT NULL COMMENT '角色id',
    `create_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`    datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`    datetime                                                     NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4286
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关系表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (4278, '0', 32, 1, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4279, '0', 33, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4280, '0', 34, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4281, '0', 35, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4282, '0', 36, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4283, '0', 37, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4284, '0', 38, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4285, '0', 39, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4286, '0', 40, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4297, '0', 41, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4299, '0', 42, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4300, '0', 43, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4301, '0', 44, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4302, '0', 45, 3, '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);


-- ---------------------------------
-- Table structure for sys_dict
-- ---------------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          int UNSIGNED                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '租户id',
    `table_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '表名',
    `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '字段名',
    `dict_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '字典值',
    `dict_value`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '字典名称',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '字典备注',
    `is_sealed`   int(2)                                                        NULL     DEFAULT 0 COMMENT '是否已封存',
    `is_deleted`  int(2)                                                        NULL     DEFAULT 0 COMMENT '是否已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4286
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字典表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`
(
    `id`            int UNSIGNED                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`     varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '租户id',
    `parent_id`     int UNSIGNED                                                  NOT NULL COMMENT '上级部门id',
    `dept_category` int(2)                                                        NULL     DEFAULT NULL COMMENT '部门类型',
    `dept_name`     varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '部门名称',
    `full_name`     varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '部门全称',
    `remark`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `create_time`   datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`      datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`      datetime                                                      NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100300
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '部门信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department`
VALUES (1001, '0', 0, 0, '大风集团', '大风集团公司', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        null),
       (100101, '0', 1001, 1, '市场部', '大风集团公司-市场部', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null),
       (100102, '0', 1001, 1, '科技部', '大风集团公司-科技部', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null),
       (100103, '0', 1001, 1, '人力资源部', '大风集团公司-人力资源部', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null),
       (100104, '0', 1001, 1, '财务部', '大风集团公司-财务部', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null),
       (100105, '0', 1001, 1, '飓风分公司', '大风集团公司-飓风分公司', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null),
       (100204, '0', 100105, 2, '企业拓展部', '飓风分公司-企业拓展部', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null),
       (100205, '0', 100105, 2, '政要拓展部', '飓风分公司-正要拓展部', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null),
       (100206, '0', 100105, 2, '校园拓展部', '飓风分公司-校园拓展部', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20',
        '2021-05-20 13:14:20', null);

-- -------------------------------
-- Table structure for sys_group
-- -------------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`
(
    `id`             int UNSIGNED                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`      varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '租户id',
    `group_category` int(2)                                                        NULL     DEFAULT NULL COMMENT '用户组类型',
    `group_name`     varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '用户组名称',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `create_time`    datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`       datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`       datetime                                                      NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2000
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户组信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group`
VALUES (1001, '0', 11, '督察组', '', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);


-- -------------------------------
-- Table structure for sys_group_mbr
-- -------------------------------
DROP TABLE IF EXISTS `sys_group_mbr`;
CREATE TABLE `sys_group_mbr`
(
    `id`          int UNSIGNED                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '租户id',
    `group_id`    int UNSIGNED                                                 NOT NULL COMMENT '用户组Id',
    `user_id`     int UNSIGNED                                                 NOT NULL COMMENT '用户Id',
    `create_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`    datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`    datetime                                                     NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2000
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户组成员表'
  ROW_FORMAT = DYNAMIC;

-- -------------------------------
-- Table structure for sys_post
-- -------------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`
(
    `id`          int UNSIGNED                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '租户id',
    `post_type`   varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位类型',
    `post_code`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '岗位名称',
    `create_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`    datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`    datetime                                                     NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2000
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post`
VALUES (1001, '0', '14', 'manager', '总经理', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (1002, '0', '14', 'vice', '副总经理', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (1003, '0', '14', 'clerk', '文员', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (1004, '0', '14', 'staff', '员工', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`
(
    `id`          int UNSIGNED                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '租户id',
    `user_id`     int UNSIGNED                                                 NOT NULL COMMENT '用户Id',
    `dept_id`     int UNSIGNED                                                 NOT NULL COMMENT '部门Id',
    `post_id`     int UNSIGNED                                                 NOT NULL COMMENT '岗位id',
    `type`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NULL     DEFAULT '0' COMMENT '岗位类型1主岗，2兼职',
    `create_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `eff_time`    datetime                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `exp_time`    datetime                                                     NULL     DEFAULT NULL COMMENT '失效时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4286
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户-岗位表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post`
VALUES (4278, '0', 32, 1001, 1001, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4279, '0', 33, 100101, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4280, '0', 34, 100101, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4281, '0', 35, 100101, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4282, '0', 36, 100101, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4283, '0', 37, 100101, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4284, '0', 38, 100101, 1001, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4285, '0', 39, 100102, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4286, '0', 40, 100102, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4297, '0', 41, 100102, 1001, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4299, '0', 42, 100103, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4300, '0', 43, 100103, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4301, '0', 44, 100103, 1004, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null),
       (4302, '0', 45, 100103, 1001, '1', '2021-05-20 13:14:20', '2021-05-20 13:14:20', '2021-05-20 13:14:20', null);

SET FOREIGN_KEY_CHECKS = 1;
