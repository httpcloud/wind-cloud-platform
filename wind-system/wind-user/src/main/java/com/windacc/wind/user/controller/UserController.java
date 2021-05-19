package com.windacc.wind.user.controller;

import com.windacc.wind.toolkit.context.ClientContextHolder;
import com.windacc.wind.toolkit.context.LoginUserContextHolder;
import com.windacc.wind.toolkit.entity.LoginClient;
import com.windacc.wind.toolkit.entity.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:30
 */
@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("current")
    public LoginUser hello(HttpServletRequest request) {
        LoginUser loginUser = LoginUserContextHolder.getEntity();
        LoginClient loginClient = ClientContextHolder.getEntity();

        return loginUser;
    }

}
