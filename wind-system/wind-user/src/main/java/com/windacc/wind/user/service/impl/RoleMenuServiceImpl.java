package com.windacc.wind.user.service.impl;

import com.windacc.wind.user.entity.RoleMenu;
import com.windacc.wind.user.mapper.RoleMenuMapper;
import com.windacc.wind.user.service.IRoleMenuService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-资源关系表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Service
public class RoleMenuServiceImpl extends SuperServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
