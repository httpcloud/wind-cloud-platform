package com.windacc.wind.user.service.impl;

import com.windacc.wind.user.entity.GroupMbr;
import com.windacc.wind.user.mapper.GroupMbrMapper;
import com.windacc.wind.user.service.IGroupMbrService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组成员表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-23
 */
@Service
public class GroupMbrServiceImpl extends SuperServiceImpl<GroupMbrMapper, GroupMbr> implements IGroupMbrService {

}
