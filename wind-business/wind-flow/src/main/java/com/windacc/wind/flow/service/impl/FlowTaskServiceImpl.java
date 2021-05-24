package com.windacc.wind.flow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.windacc.wind.flow.constants.FlowConstant;
import com.windacc.wind.flow.entity.FlowInstance;
import com.windacc.wind.flow.entity.FlowTask;
import com.windacc.wind.flow.exception.ProcessException;
import com.windacc.wind.flow.service.IFlowTaskService;
import com.windacc.wind.mybatis.entity.PageData;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.impl.de.odysseus.el.ExpressionFactoryImpl;
import org.flowable.common.engine.impl.de.odysseus.el.util.SimpleContext;
import org.flowable.common.engine.impl.javax.el.ExpressionFactory;
import org.flowable.common.engine.impl.javax.el.ValueExpression;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    @Resource
    private IdentityService identityService;
    @Resource
    private RepositoryService repositoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FlowInstance startProcessInstance(String processDefinitionId, String businessKey) {

        long count = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).count();
        if (count > 0) {
            throw new ProcessException("该流程已经发起，请不要重复启动");
        }

        //LoginUser loginUser = LoginUserContextHolder.getEntity();
        //if (loginUser == null) {
        //    throw new ProcessException("无法识别用户，请重新登录1");
        //}
        //String username = loginUser.getUsername();
        String username = "admin";
        identityService.setAuthenticatedUserId(username);
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey);

        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processDefinitionId).list();
        List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceId(processDefinitionId).list();

        log.info("{}", processInstance.getProcessInstanceId());
        return FlowInstance.of(processInstance);
    }

    @Override
    public PageData<FlowTask> taskTodoList() {

        //LoginUser loginUser = LoginUserContextHolder.getEntity();
        //if (loginUser == null) {
        //    throw new ProcessException("无法识别用户，请重新登录2");
        //}
        //String username = loginUser.getUsername();
        String username = "admin";
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(username).list();
        List<FlowTask> flowTasks = buildTaskList(taskList, FlowConstant.STATUS_TODO);

        PageData<FlowTask> flowTaskPageData = PageData.of(flowTasks, flowTasks.size(), 1, flowTasks.size());
        return flowTaskPageData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComment(String taskId, String processInstanceId, String comment, MultipartFile attach)
        throws IOException {

        if (!StrUtil.isAllNotBlank(taskId, processInstanceId)) {
            throw new ProcessException("参数错误，请输入任务id和流程实例id");
        }

        if (!attach.isEmpty()) {
            try (InputStream inputStream = attach.getInputStream()) {
                String attachmentName = attach.getOriginalFilename();
                taskService.createAttachment(FlowConstant.ATTACH_COMMENT, taskId, processInstanceId, attachmentName, "",
                    inputStream);
            }
        }

        if (StrUtil.isNotBlank(comment)) {
            taskService.addComment(taskId, processInstanceId, comment);
        }

        //throw new ProcessException("无法识别用户，请重新登录2");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nextActivity(String taskId) {

        // TODO 获取当前任务的下个节点列表 和对应的 可选用户列表，   待用户中心的调用完善后处理。

        User user1 = identityService.newUser("user1");
        user1.setEmail("user1@111.com");
        User user2 = identityService.newUser("user2");
        user2.setEmail("user2@111.com");
        identityService.saveUser(user1);
        identityService.saveUser(user2);
        Group group1 = identityService.newGroup("management");
        identityService.saveGroup(group1);
        identityService.createMembership("user1","management");
        identityService.createMembership("user2","management");

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        String currActivity = execution.getActivityId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        FlowElement flowElement = bpmnModel.getFlowElement(currActivity);

        FlowNode flowNode = (FlowNode) flowElement;
        List<SequenceFlow> outFlows = flowNode.getOutgoingFlows();
        for (SequenceFlow sequenceFlow : outFlows) {
            FlowElement targetFlow = sequenceFlow.getTargetFlowElement();
            if (targetFlow instanceof UserTask) {
                UserTask userTask = (UserTask) targetFlow;
                List<String> groups = userTask.getCandidateGroups();
                List<String> user3 = userTask.getCandidateUsers();
                String group33 = userTask.getBusinessCalendarName();
                Map<String, List<ExtensionElement>> ext = userTask.getExtensionElements();

                List<User> user = identityService.createUserQuery()
                    .memberOfGroups(groups)
                    .userDisplayName("aa")
                    .userFirstName("first")
                    .userId("userid")
                    .list();

                System.out.println("下一节点: id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
            }
            // 如果下个审批节点为结束节点
            if (targetFlow instanceof EndEvent) {
                System.out.println("下一节点为结束节点：id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
            }
        }
        //List<Process> processList = bpmnModel.getProcesses();
        //for (Process process : processList) {
        //    process.getFlowElements().forEach(flowElement -> {
        //        log.info("{}, {}", flowElement.getId(), flowElement.getName());
        //        if (flowElement instanceof SequenceFlow) {
        //            log.info("SequenceFlow");
        //        }
        //        if (flowElement instanceof UserTask) {
        //            log.info("UserTask");
        //        }
        //        if (flowElement instanceof StartEvent) {
        //            log.info("StartEvent");
        //        }
        //        if (flowElement instanceof EndEvent) {
        //            log.info("EndEvent");
        //        }
        //
        //
        //    });
        //}

        //    List<Pool> pools = bpmnModel.getPools();
        //List<Process> pro = bpmnModel.getProcesses();

        //throw new ProcessException("无法识别用户，请重新登录2");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean completeTask(String flowTask) {
        taskService.complete("1");
        //return false;

        throw new ProcessException("无法识别用户，请重新登录2");
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
        System.out
            .println("Number of tasks for fozzie: " + taskService.createTaskQuery().taskAssignee("fozzie").count());

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
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
            .processInstanceId("15834e9d-b96e-11eb-8dba-0250f2000002").singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
        log.info("查询任务.");

    }

    private List<FlowTask> buildTaskList(List<Task> taskList, String status) {



        List<FlowTask> flowTasks = new ArrayList<>();
        taskList.forEach(task -> {
            FlowTask flowTask = FlowTask.of(task);
            flowTask.setStatus(status);
            flowTasks.add(flowTask);
        });
        return flowTasks;
    }

    public Object getValue(List<HistoricVariableInstance> hvis, String exp, Class<?> clazz) {
        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();
        for (HistoricVariableInstance entry : hvis) {
            context.setVariable(entry.getVariableName(), factory.createValueExpression(entry.getValue(), Object.class));
        }
        ValueExpression e = factory.createValueExpression(context, exp, clazz);
        return e.getValue(context);
    }

    public String getValue(List<HistoricVariableInstance> hvis, String exp) {
        return (String) getValue(hvis, exp, String.class);
    }

}
