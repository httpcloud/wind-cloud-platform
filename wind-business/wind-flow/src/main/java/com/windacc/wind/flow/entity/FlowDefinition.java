package com.windacc.wind.flow.entity;

import lombok.Data;
import org.flowable.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
@Data
public class FlowDefinition implements Serializable {

    private static final long serialVersionUID = -6930186595659037923L;
    private String definitionId;
    protected String definitionName;
    protected String key;
    protected int version;
    protected String category;
    protected String categoryName;
    protected String deploymentId;
    protected String resourceName;
    protected String tenantId;
    protected int suspensionState;

    protected String parentDeploymentId;
    protected Date deploymentTime;
    protected String deploymentName;


    public FlowDefinition(ProcessDefinitionEntityImpl definition) {
        this.definitionId = definition.getId();
        this.definitionName = definition.getName();
        this.key = definition.getKey();
        this.version = definition.getVersion();
        this.category = definition.getCategory();
        this.deploymentId = definition.getDeploymentId();
        this.resourceName = definition.getResourceName();
        this.tenantId = definition.getTenantId();
        this.suspensionState = definition.getSuspensionState();
    }

    public FlowDefinition(ProcessDefinitionEntityImpl definition, DeploymentEntityImpl deploy) {
        this.definitionId = definition.getId();
        this.definitionName = definition.getName();
        this.key = definition.getKey();
        this.version = definition.getVersion();
        this.deploymentId = definition.getDeploymentId();
        this.resourceName = definition.getResourceName();
        this.tenantId = definition.getTenantId();
        this.suspensionState = definition.getSuspensionState();

        this.category = deploy.getCategory();
        this.parentDeploymentId = deploy.getParentDeploymentId();
        this.deploymentTime = deploy.getDeploymentTime();
        this.deploymentName = deploy.getName();
    }

    public static FlowDefinition of(ProcessDefinition processDefinition, Deployment deployment) {
        return new FlowDefinition((ProcessDefinitionEntityImpl) processDefinition, (DeploymentEntityImpl) deployment);
    }

}
