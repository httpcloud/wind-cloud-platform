package com.windacc.wind.flow.service;

import com.windacc.wind.flow.entity.FlowProcess;
import com.windacc.wind.mybatis.entity.PageData;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
public interface IFlowProcessService {

    FlowProcess createDeployment(String filename, String category, String name);

    void deleteDeployment(String deploymentId);

    PageData<FlowProcess> listDeployment(Integer pageNum, Integer pageSize);
}
