package com.windacc.wind.flow.entity;

import lombok.Data;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.runtime.ProcessInstance;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/22
 */
@Data
public class FlowInstance implements Serializable {

    private static final long serialVersionUID = -8741556447622016846L;

    protected String processInstanceId;
    protected String InstanceName;
    protected String startUserId;
    protected Date startTime;
    //protected String category;
    //protected String categoryName;
    protected String businessKey;
    protected String processDefinitionId;
    protected String processDefinitionKey;
    protected int suspensionState;
    protected String executionId;

    public FlowInstance(ExecutionEntityImpl execution) {
        this.InstanceName = execution.getName();
        this.startUserId = execution.getStartUserId();
        this.startTime = execution.getStartTime();

        this.businessKey = execution.getBusinessKey();
        this.processInstanceId = execution.getProcessInstanceId();
        this.processDefinitionId = execution.getProcessDefinitionId();
        this.processDefinitionKey = execution.getProcessDefinitionKey();
        this.suspensionState = execution.getSuspensionState();
    }

    public static FlowInstance of(ProcessInstance processInstance) {
        return new FlowInstance((ExecutionEntityImpl) processInstance);
    }

}
