package com.windacc.wind.flow.service.impl;

import com.windacc.wind.flow.constants.FlowConstant;
import com.windacc.wind.flow.entity.FlowDefinition;
import com.windacc.wind.flow.service.IFlowProcessService;
import com.windacc.wind.mybatis.entity.PageData;
import com.windacc.wind.toolkit.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
@Slf4j
@Service
public class FlowProcessServiceImpl implements IFlowProcessService {

    @Resource
    private RepositoryService repositoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FlowDefinition createDeployment(String filename, String category, String name) {

        String deployFilename = "processes/" + filename + FlowConstant.BPMN_SUFFIX;
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(deployFilename)
            .category(category).name(name).deploy();

        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment.getId());
        ProcessDefinition processDefinition = query.singleResult();
        repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
        log.info("部署成功,流程ID={}", processDefinition.getId());

        long count = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinition.getKey()).count();
        //if (count > 1) {
        //    throw new DeployException("已存在相同流程，请不要重复发布");
        //}

        return FlowDefinition.of(processDefinition, deployment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDeployment(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId);
        log.info("删除成功, ID={}", deploymentId);
    }

    @Override
    public PageData<FlowDefinition> listDeployment(Integer pageNum, Integer pageSize) {

        if (pageNum < 1 || pageSize < 1) {
            throw new BusinessException("分页参数错误");
        }
        List<Deployment> deploymentList =repositoryService.createDeploymentQuery().orderByDeploymentTime().desc()
            .listPage( (pageNum - 1) * pageSize, pageSize);
        long total = repositoryService.createDeploymentQuery().count();

        List<FlowDefinition> flowDefinitions = new ArrayList<>();
        deploymentList.forEach((deployment -> {
            String deploymentId = deployment.getId();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId).singleResult();
            FlowDefinition flowDefinition = FlowDefinition.of(processDefinition, deployment);
            flowDefinitions.add(flowDefinition);
        }));
        PageData<FlowDefinition> flowProcessPageData = PageData.of(flowDefinitions, (int) total, pageNum, pageSize);

        log.info("获取全部流程结束, 共{}条", flowDefinitions.size());
        return flowProcessPageData;
    }

}
