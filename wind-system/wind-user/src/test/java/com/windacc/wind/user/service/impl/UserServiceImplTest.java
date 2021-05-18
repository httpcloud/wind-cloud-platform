package com.windacc.wind.user.service.impl;

import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.redis.config.RedisService;
import com.windacc.wind.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
@Slf4j
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisService redisService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUsername() {
        //LambdaQueryWrapper<User> userWrapper = Wrappers.lambdaQuery();
        //userWrapper.eq(User::getUsername, "admin");
        //User user = userService.getOne(userWrapper);
        LoginUser loginUser = userService.findByUsername("admin");
        log.info("user1:{}", loginUser);

        redisService.set("test", loginUser);

        Object object = redisService.get("test");
        log.info("user2:{}", object);

    }




}