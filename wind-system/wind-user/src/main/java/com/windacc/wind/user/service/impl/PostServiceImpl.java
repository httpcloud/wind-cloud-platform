package com.windacc.wind.user.service.impl;

import com.windacc.wind.user.entity.Post;
import com.windacc.wind.user.mapper.PostMapper;
import com.windacc.wind.user.service.IPostService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-23
 */
@Service
public class PostServiceImpl extends SuperServiceImpl<PostMapper, Post> implements IPostService {

}
