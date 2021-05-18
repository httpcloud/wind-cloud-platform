package com.windacc.wind.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.windacc.wind.auth.entity.Client;
import com.windacc.wind.auth.mapper.ClientMapper;
import com.windacc.wind.auth.service.IClientService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import com.windacc.wind.toolkit.constants.CacheConstant;
import com.windacc.wind.toolkit.utils.JsonUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 客户端信息表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-15
 */
@Service
public class ClientServiceImpl extends SuperServiceImpl<ClientMapper, Client> implements IClientService {

    @Override
    @Cacheable(value = CacheConstant.CLIENT_KEY, key = "#clientId", unless="#result == null")
    public Client getEntityByClientId(String clientId) {
        LambdaQueryWrapper<Client> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Client::getClientId, clientId);
        return super.getOne(queryWrapper);
    }

    @Override
    @Cacheable(value = CacheConstant.CLIENT_DETAILS, key = "#client.clientId", unless="#result == null")
    public ClientDetails getClientDetails(Client client) {
        if (client == null) {
            return null;
        }
        BaseClientDetails details = new BaseClientDetails(client.getClientId(), client.getResourceIds(), client.getScope(),
            client.getAuthorizedGrantTypes(), client.getAuthorities(), client.getWebServerRedirectUri());
        details.setClientSecret(client.getClientSecret());
        details.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        details.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        if (client.getAdditionalInformation() != null) {
            Map<String, Object> additionalInformation = JsonUtil.toMap(client.getAdditionalInformation());
            details.setAdditionalInformation(additionalInformation);
        }
        details.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(client.getAutoapprove()));
        return details;
    }
}
