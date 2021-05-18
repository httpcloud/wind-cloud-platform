package com.windacc.wind.user.controller;

import com.windacc.wind.toolkit.context.ClientContextHolder;
import com.windacc.wind.toolkit.context.LoginUserContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:30
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public Object hello(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String client = ClientContextHolder.getClient();
        String loginUser = LoginUserContextHolder.getUser();
        String username = LoginUserContextHolder.getUsername();

        Enumeration<String> attributes = request.getAttributeNames();
        Enumeration<String> headerNames = request.getHeaderNames();

        return "hello,world1.";
    }

}
