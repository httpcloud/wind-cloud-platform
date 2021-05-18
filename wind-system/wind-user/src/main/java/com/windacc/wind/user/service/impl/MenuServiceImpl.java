package com.windacc.wind.user.service.impl;

import com.windacc.wind.api.entity.Menu;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import com.windacc.wind.user.mapper.MenuMapper;
import com.windacc.wind.user.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单表，包括模块、菜单、接口、外链 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Service
public class MenuServiceImpl extends SuperServiceImpl<MenuMapper, Menu> implements IMenuService {

}
