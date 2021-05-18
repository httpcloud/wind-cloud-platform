package com.windacc.wind.user.service;

import com.windacc.wind.mybatis.service.ISuperService;
import com.windacc.wind.api.entity.Role;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
public interface IRoleService extends ISuperService<Role> {

    Set<String> findRolesByUserId(Integer userId);

}
