package com.windacc.wind.flow.service;

import org.flowable.engine.runtime.ProcessInstance;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
public interface IFlowTaskService {

    ProcessInstance startProcessInstance(String processDefinitionId, String businessKey);

    void listTask();

}
