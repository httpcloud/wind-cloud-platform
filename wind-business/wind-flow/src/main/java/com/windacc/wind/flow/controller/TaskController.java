package com.windacc.wind.flow.controller;

import com.windacc.wind.flow.service.IFlowHistoryService;
import com.windacc.wind.flow.service.IFlowTaskService;
import com.windacc.wind.toolkit.entity.Result;
import com.windacc.wind.toolkit.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/start")
    public Result<?> startProcessInstance(@RequestParam String processDefinitionKey, String businessKey) {

        ProcessInstance processInstance = flowTaskService.startProcessInstance(processDefinitionKey, businessKey);
        return Result.of(processInstance.getId());
    }

    @GetMapping("/list")
    public Result<?> listTask() {

        flowTaskService.listTask();
        return Result.of("ok");
    }

    @GetMapping("/view")
    public void diagramView(String processInstanceId, HttpServletResponse response) throws IOException {

        String processDefinitionId;
        if (flowHistoryService.isFinished(processInstanceId)) {
            HistoricProcessInstance instance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
            processDefinitionId = instance.getProcessDefinitionId();
        } else {
            ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
                .singleResult();
            processDefinitionId = instance.getProcessDefinitionId();

        }
        List<String> highLightedActivities = new ArrayList<>();
        List<HistoricActivityInstance> highLightedActivityList = historyService.createHistoricActivityInstanceQuery()
            .processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();
        for (HistoricActivityInstance activity : highLightedActivityList) {
            String activityId = activity.getActivityId();
            highLightedActivities.add(activityId);
        }
        List<String> flows = new ArrayList<>();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        ProcessEngineConfiguration engConf = processEngine.getProcessEngineConfiguration();

        ProcessDiagramGenerator diagramGenerator = engConf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities, flows, engConf.getActivityFontName(),
            engConf.getLabelFontName(), engConf.getAnnotationFontName(), engConf.getClassLoader(), 1.0, true);

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
