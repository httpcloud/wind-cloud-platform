package com.windacc.wind.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import com.windacc.wind.api.entity.Role;
import com.windacc.wind.user.entity.UserRole;
import com.windacc.wind.user.mapper.RoleMapper;
import com.windacc.wind.user.mapper.UserRoleMapper;
import com.windacc.wind.user.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Service
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Set<String> findRolesByUserId(Integer userId) {

        LambdaQueryWrapper<UserRole> userRoleWrapper = Wrappers.lambdaQuery();
        userRoleWrapper.eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleWrapper);

        if (!CollectionUtil.isEmpty(userRoles)) {
            Set<Integer> roleIds = userRoles.parallelStream().map(UserRole::getRoleId).collect(Collectors.toSet());
            LambdaQueryWrapper<Role> roleWrapper = Wrappers.lambdaQuery();
            roleWrapper.in(Role::getId, roleIds);
            List<Role> roles = baseMapper.selectList(roleWrapper);
            if (!CollectionUtil.isEmpty(userRoles)) {
                return roles.parallelStream().map(Role::getRoleCode).collect(Collectors.toSet());
            }
        }
        return null;
    }

}
