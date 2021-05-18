package com.windacc.wind.auth.service.impl;

import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.api.feign.IUserClient;
import com.windacc.wind.auth.service.IUserDetailsService;
import com.windacc.wind.security.entity.LoginUserDetails;
import com.windacc.wind.toolkit.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailServiceImpl implements IUserDetailsService {

    @Autowired
    private IUserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUser result = userClient.findByUsername(username);
        return checkUser(result);
    }

    private LoginUserDetails checkUser(LoginUser loginUser) {
        if (loginUser == null) {
            throw new BusinessException("服务繁忙请稍后重试");
        }
        LoginUserDetails loginUserDetails = new LoginUserDetails();

        BeanUtils.copyProperties(loginUser, loginUserDetails);
        if (!loginUserDetails.isAccountNonExpired()) {
            throw new UsernameNotFoundException("用户已失效");
        } else if (!loginUserDetails.isAccountNonLocked()) {
            throw new UsernameNotFoundException("用户被锁定");
        } else if (!loginUserDetails.isEnabled()) {
            throw new DisabledException("用户已作废");
        }
        return loginUserDetails;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return null;
    }
}
