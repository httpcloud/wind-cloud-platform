package com.windacc.wind.user.mapper;

import com.windacc.wind.mybatis.mapper.SuperMapper;
import com.windacc.wind.toolkit.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Mapper
public interface UserMapper extends SuperMapper<User> {

}
