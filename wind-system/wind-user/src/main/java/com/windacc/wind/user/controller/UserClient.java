package com.windacc.wind.user.controller;

import com.windacc.wind.api.feign.IUserClient;
import com.windacc.wind.toolkit.context.ClientContextHolder;
import com.windacc.wind.toolkit.context.LoginUserContextHolder;
import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 17:19
 */
@RestController
@Api(tags = "用户模块")
public class UserClient implements IUserClient {

    @Autowired
    private IUserService userService;

    @Override
    @ApiOperation(value = "根据用户名查询用户")
    @GetMapping("/feign/users-anon/findByUsername")
    public LoginUser findByUsername(@RequestParam("username") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String client = ClientContextHolder.getClient();
        String loginUser = LoginUserContextHolder.getUser();
        String aabbb = LoginUserContextHolder.getUsername();

        //Enumeration<String> attributes = request.getAttributeNames();
        //Enumeration<String> headerNames = request.getHeaderNames();

        return userService.findByUsername(username);
    }
}
