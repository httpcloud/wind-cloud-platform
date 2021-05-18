package com.windacc.wind.user.service;

import com.windacc.wind.mybatis.service.ISuperService;
import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.toolkit.entity.User;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
public interface IUserService extends ISuperService<User> {

    LoginUser findByUsername(String username);

}
