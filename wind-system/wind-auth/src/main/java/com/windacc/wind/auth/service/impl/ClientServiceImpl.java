package com.windacc.wind.auth.service.impl;

import com.windacc.wind.auth.entity.Client;
import com.windacc.wind.auth.mapper.ClientMapper;
import com.windacc.wind.auth.service.IClientService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

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

}
