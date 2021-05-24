package com.windacc.wind.user.mapper;

import com.windacc.wind.user.entity.UserRole;
import com.windacc.wind.mybatis.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户-角色关系表 Mapper 接口
 * </p>
 *
 * @author codeGen
 * @since 2021-05-23
 */
@Mapper
public interface UserRoleMapper extends SuperMapper<UserRole> {

}
