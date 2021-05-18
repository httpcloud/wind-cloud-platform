package com.windacc.wind.user.service.impl;

import com.windacc.wind.user.entity.UserRole;
import com.windacc.wind.user.mapper.UserRoleMapper;
import com.windacc.wind.user.service.IUserRoleService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色关系表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Service
public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
