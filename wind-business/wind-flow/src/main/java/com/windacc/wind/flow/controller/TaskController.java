package com.windacc.wind.flow.controller;

import com.windacc.wind.flow.entity.FlowInstance;
import com.windacc.wind.flow.entity.FlowTask;
import com.windacc.wind.flow.service.IFlowHistoryService;
import com.windacc.wind.flow.service.IFlowTaskService;
import com.windacc.wind.mybatis.entity.PageData;
import com.windacc.wind.toolkit.entity.Result;
import com.windacc.wind.toolkit.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
@Slf4j
@RequestMapping("task")
@RestController
public class TaskController {

    @Resource
    private IFlowTaskService flowTaskService;
    @Resource
    private IFlowHistoryService flowHistoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private HistoryService historyService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private TaskService taskService;

    @PostMapping("/start")
    public Result<?> startProcessInstance(@RequestParam String processDefinitionId, String businessKey) {

        FlowInstance flowInstance = flowTaskService.startProcessInstance(processDefinitionId, businessKey);
        return Result.of(flowInstance);
    }

    @GetMapping("/todoList")
    public Result<?> taskTodoList() {

        PageData<FlowTask> flowTaskPageData = flowTaskService.taskTodoList();
        return Result.of(flowTaskPageData);
    }

    /**
     * TODO 获取流程实例详情
     */
    @GetMapping("/getInstanceDetail")
    public Result<?> taskDetail(@RequestParam String processInstanceId) {

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
            .processInstanceId(processInstanceId).singleResult();
        List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        FlowInstance flowInstance = FlowInstance.of(processInstance);
        FlowTask flowTask = FlowTask.of(task);


        return Result.of(flowTask);
    }

    @PostMapping("/addComment")
    public Result<?> addComment(@RequestParam String taskId, @RequestParam String processInstanceId,
        String comment, MultipartFile attach) throws IOException {

        flowTaskService.addComment(taskId, processInstanceId, comment, attach);
        return Result.of("ok");
    }

    /**
     * 获取下一个任务节点列表
     */
    @GetMapping("/nextActivity")
    public Result<?> nextActivity(@RequestParam String taskId) {

        flowTaskService.nextActivity(taskId);

        return Result.of("ok");
    }

    /**
     * TODO 完成任务
     */
    @PostMapping("/complete")
    public Result<?> completeTask(String taskId) throws IOException {

        flowTaskService.completeTask(taskId);
        return Result.of("ok");
    }



    @GetMapping("/view")
    public void diagramView(String processInstanceId, HttpServletResponse response) throws IOException {

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(InstanceId).list();
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator
            .generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(),
                engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, true);

        try (OutputStream out = response.getOutputStream()) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
        } catch (IOException e) {
            log.error("操作异常", e);

            response.setContentType("application/json;charset=utf-8");
            HashMap<String, String> map = new HashMap<>();
            map.put("msg", "生成流程图错误");
            map.put("code", "2001");
            response.getWriter().print(JsonUtil.toJson(map));
        }

    }

}
