package com.windacc.wind.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.windacc.wind.redis.config.RedisService;
import com.windacc.wind.toolkit.entity.User;
import com.windacc.wind.toolkit.utils.JsonUtil;
import com.windacc.wind.user.entity.UserRole;
import com.windacc.wind.user.mapper.UserRoleMapper;
import com.windacc.wind.user.service.IUserRoleService;
import com.windacc.wind.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Callable;

//import com.windacc.wind.user.config.PrifleSuper;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Resource
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void setUserRoleServiceTest() {
        UserRole userRole = new UserRole();
        userRole.setUserId(111);
        userRole.setRoleId(111);
        boolean saveResult = userRoleService.save(userRole);
        Assert.assertTrue(saveResult);
        log.info("result:{}", userRole);

        boolean removeResult = userRoleService.removeById(userRole.getId());
        Assert.assertTrue(removeResult);

        LambdaQueryWrapper<UserRole> userWrapper = Wrappers.lambdaQuery();
        userWrapper.eq(UserRole::getRoleId, userRole.getRoleId());
        List<UserRole> users = userRoleMapper.selectList(userWrapper);
        Assert.assertEquals(0, users.size());
    }

    @Test
    @Transactional
    void findByUsername() {
        String username = "张三";
        User user = new User();
        user.setUsername(username);
        user.setNickName("张三");
        user.setPassword("111");
        user.setDeptId(11);
        boolean saveResult = userService.save(user);
        Assert.assertTrue(saveResult);

        LambdaQueryWrapper<User> userWrapper = Wrappers.lambdaQuery();
        userWrapper.eq(User::getUsername, username);
        User entity = userService.getOne(userWrapper);
        Assert.assertEquals(username, entity.getUsername());

        //LoginUser loginUser = userService.findByUsername("admin");
        //log.info("user1:{}", loginUser);
        //redisService.set("test", loginUser);
        //Object object = redisService.get("test");
        //log.info("user2:{}", object);
    }

    @Test
    void redisUserTest() {
        String username = "market1";
        LambdaQueryWrapper<User> userWrapper = Wrappers.lambdaQuery();
        userWrapper.eq(User::getUsername, username);
        User entity = userService.getOne(userWrapper);

        String key = "username:".concat(entity.getUsername());
        redisService.set(key, entity);

        Object user = redisService.get(key);

        log.info("user2:{}", user);
    }

    @Test
    void cacheUserTest() {
        String username = "market1";
        LambdaQueryWrapper<User> userWrapper = Wrappers.lambdaQuery();
        userWrapper.eq(User::getUsername, username);
        User entity = userService.getOne(userWrapper);

        String cacheName = "user";
        String key = "username:".concat(entity.getUsername());

        //Cache.ValueWrapper valueWrapper = getCache(cacheName).get(key);
        //
        //Cache cache = getCache(cacheName);
        //cache.put(key, entity);
        //
        //Cache.ValueWrapper valueWrapper2 = getCache(cacheName).get(key);
        //
        //User user2 = getCache(cacheName).get(key, User.class);

        log.info("user2:{}", JsonUtil.toJson(entity));

        Callable<User> callable = () -> getUser(username);

        User user3 = getCache(cacheName).get(key, callable);


        log.info("user2:{}", JsonUtil.toJson(entity));

    }

    private User getUser(String username) {
        User user = new User();
        user.setUsername("aaa");
        return user;
    }


    private Cache getCache(String cacheName) {

        return cacheManager.getCache(cacheName);
    }





}