package com.windacc.wind.flow.service.impl;

import com.windacc.wind.api.feign.IUserClient;
import com.windacc.wind.toolkit.entity.LoginUser;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.UserQueryImpl;

import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/22
 */
public class WindUserQueryImpl extends UserQueryImpl {

    private static final long serialVersionUID = -2360898114227587012L;
    private IUserClient userClient;

    public WindUserQueryImpl(IUserClient userClient) {
        this.userClient = userClient;
    }


    @Override
    public List<User> executeList(CommandContext commandContext) {

        // TODO 通过feign调用 获取用户组织表的用户到 flowable中。先直接返回null
        LoginUser loginUser = userClient.findByUsername("admin");

        return null;
    }


    @Override
    public long executeCount(CommandContext commandContext) {

        // TODO 通过feign调用 获取用户组织表的用户到 flowable中。先直接返回null
        return 0;
    }

}
