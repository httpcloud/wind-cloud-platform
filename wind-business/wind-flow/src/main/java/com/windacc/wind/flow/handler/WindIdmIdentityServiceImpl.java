package com.windacc.wind.flow.handler;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.GroupQuery;
import org.flowable.idm.api.User;
import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.IdmIdentityServiceImpl;

import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/21
 */
@Slf4j
public class WindIdmIdentityServiceImpl extends IdmIdentityServiceImpl {

    public WindIdmIdentityServiceImpl(IdmEngineConfiguration idmEngineConfiguration) {
        super(idmEngineConfiguration);
    }

    @Override
    public UserQuery createUserQuery() {
        log.info("用户查询--");
        return null;
    }

    @Override
    public GroupQuery createGroupQuery() {
        System.out.println("用户组查询--");
        return null;
    }

    @Override
    public List<Group> getGroupsWithPrivilege(String name) {
        System.out.println("查询--");
        throw new FlowableException("LDAP identity service doesn't support creating a new user");
    }

    @Override
    public List<User> getUsersWithPrivilege(String name) {
        System.out.println("查询--");
        throw new FlowableException("LDAP identity service doesn't support creating a new user");
    }


}
