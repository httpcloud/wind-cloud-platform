package com.windacc.wind.flow.service.impl;

import com.windacc.wind.flow.service.IFlowTaskService;
import com.windacc.wind.toolkit.context.LoginUserContextHolder;
import com.windacc.wind.toolkit.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
@Slf4j
@Service
public class FlowTaskServiceImpl implements IFlowTaskService {

    @Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private HistoryService historyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessInstance startProcessInstance(String processDefinitionKey, String businessKey) {

        LoginUser loginUser = LoginUserContextHolder.getEntity();
        Map<String, Object> variables = new HashMap<>();
        variables.put("loginUser", loginUser);

        ProcessInstance processInstance = runtimeService
            .startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        log.info("{}", processInstance.getProcessInstanceId());
        return processInstance;
    }

    @Override
    public void listTask() {
        List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery().list();

    }


    public void listTask2222() {

        List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery().list();

        List<Task> taskList = taskService.createTaskQuery().list();

        List<Task> tasks1 = taskService.createTaskQuery().taskCandidateUser("kermit").list();

        List<Task> tasks2 = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();

        for (Task task : tasks2) {
            System.out.println("Following task is available for accountancy group: " + task.getName());
            taskService.claim(task.getId(), "fozzie");
        }

        tasks2 = taskService.createTaskQuery().taskAssignee("fozzie").list();
        for (Task task : tasks2) {
            System.out.println("Task for fozzie: " + task.getName());

            // 完成任务
            taskService.complete(task.getId());
        }
        System.out.println("Number of tasks for fozzie: "
            + taskService.createTaskQuery().taskAssignee("fozzie").count());

        // 获取并申领第二个任务
        tasks2 = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks2) {
            System.out.println("Following task is available for management group: " + task.getName());
            taskService.claim(task.getId(), "kermit");
        }

        // 完成第二个任务并结束流程
        for (Task task : tasks2) {
            taskService.complete(task.getId());
        }

        // 验证流程已经结束
        HistoricProcessInstance historicProcessInstance =
            historyService.createHistoricProcessInstanceQuery().processInstanceId("15834e9d-b96e-11eb-8dba-0250f2000002").singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
        log.info("查询任务.");

    }

}
