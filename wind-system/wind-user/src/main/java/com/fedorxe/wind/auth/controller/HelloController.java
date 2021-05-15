package com.fedorxe.wind.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:30
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public Object hello() {
        return "hello,world.";
    }

}
