package com.windacc.wind.user.service.impl;

import com.windacc.wind.user.entity.UserPost;
import com.windacc.wind.user.mapper.UserPostMapper;
import com.windacc.wind.user.service.IUserPostService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-岗位表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-23
 */
@Service
public class UserPostServiceImpl extends SuperServiceImpl<UserPostMapper, UserPost> implements IUserPostService {

}
