package com.windacc.wind.flow.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.task.api.Task;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/22
 */
@Data
@NoArgsConstructor
public class FlowTask implements Serializable {

    private static final long serialVersionUID = 1034941559128802995L;

    protected String taskId;
    protected String assignee;
    protected String taskName;
    protected Date createTime;
    protected boolean suspensionState;
    protected String category;
    protected String executionId;
    protected String processInstanceId;
    protected String processDefinitionId;
    protected String taskDefinitionId;
    protected String taskDefinitionKey;
    protected Date claimTime;
    protected String tenantId;
    protected String comment;
    protected String status;
    protected Map<String, Object> variables;

    public FlowTask(Task task) {
        this.taskId = task.getId();
        this.assignee = task.getAssignee();
        this.taskName = task.getName();
        this.createTime = task.getCreateTime();
        this.suspensionState = task.isSuspended();
        this.executionId = task.getExecutionId();
        this.processInstanceId = task.getProcessInstanceId();
        this.processDefinitionId = task.getProcessDefinitionId();
        this.taskDefinitionId = task.getTaskDefinitionId();
        this.taskDefinitionKey = task.getTaskDefinitionKey();
        this.claimTime = task.getClaimTime();
        this.tenantId = task.getTenantId();
    }

    public static FlowTask of(Task task) {
        return new FlowTask(task);
    }
}
