package com.windacc.wind.auth.service;

import com.windacc.wind.auth.entity.Client;
import com.windacc.wind.mybatis.service.ISuperService;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * <p>
 * 客户端信息表 服务类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
public interface IClientService extends ISuperService<Client> {

    Client getEntityByClientId(String clientId);


    ClientDetails getClientDetails(Client client);

}
