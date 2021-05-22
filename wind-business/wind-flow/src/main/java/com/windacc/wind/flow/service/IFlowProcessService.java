package com.windacc.wind.flow.service;

import com.windacc.wind.flow.entity.FlowDefinition;
import com.windacc.wind.mybatis.entity.PageData;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
public interface IFlowProcessService {

    FlowDefinition createDeployment(String filename, String category, String name);

    void deleteDeployment(String deploymentId);

    PageData<FlowDefinition> listDeployment(Integer pageNum, Integer pageSize);
}
