-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`              int(11) unsigned                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`       varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '租户id',
    `org_id`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '0' COMMENT '机构id',
    `username`        varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '账号',
    `password`        varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '密码',
    `nick_name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
    `mobile`          varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '手机号',
    `sex`             char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT '0' COMMENT '性别 0 未知  1 男 2 女',
    `open_id`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT 'openId 用于第三方登录',
    `status`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT '0' COMMENT '账户状态：0正常 1 禁用/关闭 2 冻结 3 注销 ',
    `type`            char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL     DEFAULT '0' COMMENT '用户类型  1 app    2 web',
    `real_flag`       tinyint(1)                                                    NULL     DEFAULT 0 COMMENT '0 未实名  1 已实名',
    `last_login_time` datetime(0)                                                   NULL     DEFAULT NULL COMMENT '上一次登陆时间',
    `create_time`     datetime(0)                                                   NULL     DEFAULT CURRENT_TIMESTAMP,
    `update_time`     datetime(0)                                                   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    `del_flag`        tinyint(1)                                                    NULL     DEFAULT 0 COMMENT '逻辑删除：0否，1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `sys_user_username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11111
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

INSERT INTO `sys_user`
VALUES (32, '0', '0', 'admin', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '管理员',
        '13106975707', 1, '8817276-2917263-22211', 0, '1', 1, '2020-11-17 16:56:59',
        '2018-09-15 03:12:44', '2018-09-15 03:12:44', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          int(11) unsigned                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '租户id',
    `role_code`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色id',
    `role_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '角色名称',
    `role_desc`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
    `status`      tinyint(1)                                                    NULL DEFAULT 0 COMMENT '角色状态   0正常，1关闭',
    `del_flag`    tinyint(1)                                                    NULL DEFAULT 0 COMMENT '逻辑删除：0否，1是',
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `sys_role_role_code` (`role_code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11111
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色信息表'
  ROW_FORMAT = Dynamic;

INSERT INTO `sys_role`
VALUES (1, '0', 'admin', '系统管理员', '', 0, 0, '2017-11-17 16:56:59', '2017-11-17 16:56:59'),
       (2, '0', 'seller', '卖家', '', 0, 0, '2017-11-17 16:56:59', '2017-11-17 16:56:59'),
       (3, '0', 'user', '普通用户', '', 0, 0, '2017-11-17 16:56:59', '2017-11-17 16:56:59');

-- ------------------------------------
-- Table structure for sys_user_role
-- ------------------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          int(11) unsigned                                             NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户id',
    `user_id`     int(11) unsigned                                             NOT NULL COMMENT '用户Id',
    `role_id`     int(11) unsigned                                             NOT NULL COMMENT '角色id',
    `del_flag`    tinyint(1)                                                   NULL DEFAULT 0 COMMENT '逻辑删除：0否，1是',
    `create_time` datetime(0)                                                  NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime(0)                                                  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4286
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关系表'
  ROW_FORMAT = Dynamic;

INSERT INTO `sys_user_role`
VALUES (4278, '0', 32, 1, 0, '2020-11-23 14:13:09', '2020-12-08 13:20:42');

-- ------------------------------------
-- Table structure for sys_menu
-- ------------------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          int(11) unsigned                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `menu_pid`    int(11) unsigned                                              NULL DEFAULT NULL COMMENT '菜单父级id',
    `menu_name`   varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '菜单名称',
    `menu_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '请求方法，多个方法用逗号隔开',
    `menu_path`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路径/接口路径',
    `menu_type`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL DEFAULT NULL COMMENT '菜单类型：(1:模块；2:菜单；3:外链 ; 4.组件)',
    `menu_order`  int(2)                                                        NULL DEFAULT 0 COMMENT '排序字段',
    `menu_icon`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '图标 url',
    `keyword`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '关键字',
    `del_flag`    tinyint(1)                                                    NULL DEFAULT 0 COMMENT '逻辑删除：0否，1是',
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1110
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表，包括模块、菜单、接口、外链'
  ROW_FORMAT = Dynamic;

INSERT INTO `sys_menu`
VALUES (1020201, 10202, '账号管理-新增', null, '/api-uaa/v1/feign/oauth/1.0/getUserInfo', '4', 2, null, '账号管理-新增', 0,
        '2020-04-21 13:25:47', '2020-12-09 16:45:07');
INSERT INTO `sys_menu`
VALUES (1020202, 10202, '账号管理-详情', null, '/api-user/com/ymdx/baas/account/view', '4', 2, null, '账号管理-详情', 0,
        '2020-04-21 13:25:47', '2020-12-08 13:16:01');

-- ------------------------------------
-- Table structure for sys_role_menu
-- ------------------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`     int(11) unsigned NOT NULL COMMENT '角色id',
    `menu_id`     int(11) unsigned NOT NULL COMMENT '菜单id',
    `del_flag`    tinyint(1)       NULL DEFAULT 0 COMMENT '逻辑删除：0否，1是',
    `create_time` datetime(0)      NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime(0)      NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4279
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色-资源关系表'
  ROW_FORMAT = Dynamic;

INSERT INTO `sys_role_menu`
VALUES (4278, 1, 1020201, 0, '2020-11-23 14:13:56', '2020-11-23 14:14:05'),
       (4270, 1, 1020202, 0, '2020-11-23 14:13:56', '2020-11-23 14:14:05');


-- ------------------------------------
-- Table structure for sys_client
-- ------------------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client`
(
    `id`                      int(11) unsigned                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `client_name`             varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '应用名称',
    `client_id`               varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '应用标识',
    `resource_ids`            varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '资源限定串(逗号分割)',
    `client_secret`           varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
    `client_secret_str`       varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '应用密钥(明文)',
    `scope`                   varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '范围',
    `authorized_grant_types`  varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
    `web_server_redirect_uri` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '回调地址 ',
    `authorities`             varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '权限',
    `access_token_validity`   int(11) unsigned                                              NULL     DEFAULT NULL COMMENT 'access_token有效期',
    `refresh_token_validity`  int(11) unsigned                                              NULL     DEFAULT NULL COMMENT 'refresh_token有效期',
    `additional_information`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '{}' COMMENT '{}',
    `autoapprove`             char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL DEFAULT 'true' COMMENT '是否自动授权 是-true',
    `create_time`             datetime(0)                                                   NULL     DEFAULT CURRENT_TIMESTAMP,
    `update_time`             datetime(0)                                                   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `sys_client_client_id` (`client_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '客户端信息表'
  ROW_FORMAT = Dynamic;

INSERT INTO `sys_client`
VALUES (1, '通用客户端', 'app', null, '$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO', 'app', 'app',
        'authorization_code,password,refresh_token,mobile_code,email_pwd,open_id,mobile_pwd,password_code,client_credentials',
        'http://www.baidu.com', null, 180000, null, '{}', 'true', current_time, current_time),
       (2, '手机客户端', 'mobile', null, '$2a$10$ULxRssv/4NWOc388lZFbyus3IFfsbcpG/BZOq4TRxDhsx5HHIR7Jm', 'mobile',
        'mobile',
        'mobile_code,mobile_pwd', null, null, 180000, NULL, '{}', 'true', current_time, current_time);

