package com.windacc.wind.flow.service;

import com.windacc.wind.api.feign.IUserClient;
import com.windacc.wind.toolkit.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/19
 */
@Slf4j
@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IUserClient userClient;

    public void createDeployment() {

        repositoryService.createDeployment().addClasspathResource("processes/one-task-process.bpmn20.xml");

    }

    @Transactional(rollbackFor = Exception.class)
    public ProcessDefinitionQuery createProcessDefinition() {
        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
        return definitionQuery;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProcessInstance startProcess(String processId) {

        LoginUser loginUser = userClient.findByUsername("admin");

        Map<String, Object> map=new HashMap<>();
        map.put("loginUser", loginUser);


        ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey(processId, map);
        log.info("{}", processInstance.toString());
        return processInstance;
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.list();
        return taskQuery.taskAssignee(assignee).list();
    }


}
