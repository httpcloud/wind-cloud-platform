package com.windacc.wind.user.mapper;

import com.windacc.wind.api.entity.Menu;
import com.windacc.wind.mybatis.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统菜单表，包括模块、菜单、接口、外链 Mapper 接口
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Mapper
public interface MenuMapper extends SuperMapper<Menu> {

}
