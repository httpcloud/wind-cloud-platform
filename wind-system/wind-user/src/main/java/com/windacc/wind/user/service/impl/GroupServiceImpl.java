package com.windacc.wind.user.service.impl;

import com.windacc.wind.user.entity.Group;
import com.windacc.wind.user.mapper.GroupMapper;
import com.windacc.wind.user.service.IGroupService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组信息表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-23
 */
@Service
public class GroupServiceImpl extends SuperServiceImpl<GroupMapper, Group> implements IGroupService {

}
