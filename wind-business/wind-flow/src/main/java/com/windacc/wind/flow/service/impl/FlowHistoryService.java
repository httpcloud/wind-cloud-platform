package com.windacc.wind.flow.service.impl;

import com.windacc.wind.flow.service.IFlowHistoryService;
import org.flowable.engine.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
@Service
public class FlowHistoryService implements IFlowHistoryService {

    @Resource
    private HistoryService historyService;

    @Override
    public boolean isFinished(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).count() > 0;
    }

}
