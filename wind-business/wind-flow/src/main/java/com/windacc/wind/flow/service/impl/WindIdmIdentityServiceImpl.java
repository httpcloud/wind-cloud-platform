package com.windacc.wind.flow.service.impl;

import com.windacc.wind.api.feign.IUserClient;
import lombok.extern.slf4j.Slf4j;
import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.IdmIdentityServiceImpl;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/21
 */
@Slf4j
public class WindIdmIdentityServiceImpl extends IdmIdentityServiceImpl {

    private IUserClient userClient;

    public WindIdmIdentityServiceImpl(IdmEngineConfiguration idmEngineConfiguration, IUserClient userClient) {
        super(idmEngineConfiguration);
        this.userClient = userClient;
    }

    @Override
    public UserQuery createUserQuery() {
        log.info("用户查询--");
        return new WindUserQueryImpl(userClient);
    }

    //@Override
    //public NativeUserQuery createNativeUserQuery() {
    //    log.info("Native 用户查询--");
    //    return null;
    //}
    //
    //@Override
    //public GroupQuery createGroupQuery() {
    //    log.info("用户组查询--");
    //    return null;
    //}
    //
    //@Override
    //public NativeGroupQuery createNativeGroupQuery() {
    //    log.info("Native 用户组查询--");
    //    return null;
    //}
    //
    //@Override
    //public String getUserInfo(String userId, String key) {
    //    log.info("getUserInfo 用户具体字段查询--");
    //    return null;
    //}
    //
    //@Override
    //public List<String> getUserInfoKeys(String userId) {
    //    log.info("getUserInfoKeys 用户字段列表查询--");
    //    return null;
    //}


}
