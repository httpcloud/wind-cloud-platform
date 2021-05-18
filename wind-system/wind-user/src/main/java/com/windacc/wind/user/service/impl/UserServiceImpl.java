package com.windacc.wind.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.toolkit.entity.User;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import com.windacc.wind.user.mapper.UserMapper;
import com.windacc.wind.user.service.IRoleService;
import com.windacc.wind.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IRoleService roleService;

    @Override
    @Cacheable(value = "abc", keyGenerator = "keyGenerator", unless="#result == null")
    public LoginUser findByUsername(String username) {
        LambdaQueryWrapper<User> userWrapper = Wrappers.lambdaQuery();
        userWrapper.eq(User::getUsername, username);
        User user = baseMapper.selectOne(userWrapper);

        if (user != null) {
            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(user, loginUser);
            Set<String> codes = roleService.findRolesByUserId(user.getId());
            loginUser.setRoleCodes(codes);
            return loginUser;
        }
        return null;
    }
}
