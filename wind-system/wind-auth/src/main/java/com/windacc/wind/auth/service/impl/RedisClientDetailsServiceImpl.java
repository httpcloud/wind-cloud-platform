package com.windacc.wind.auth.service.impl;

import com.windacc.wind.auth.entity.Client;
import com.windacc.wind.auth.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 17:05
 */
@Slf4j
@Service
public class RedisClientDetailsServiceImpl implements ClientDetailsService {

    private final IClientService clientService;

    public RedisClientDetailsServiceImpl(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        Client client = clientService.getEntityByClientId(clientId);
        if (client == null) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
        return clientService.getClientDetails(client);
    }

}
