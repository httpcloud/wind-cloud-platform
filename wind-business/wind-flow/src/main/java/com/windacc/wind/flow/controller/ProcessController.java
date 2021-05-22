package com.windacc.wind.flow.controller;

import com.windacc.wind.flow.entity.FlowDefinition;
import com.windacc.wind.flow.service.IFlowProcessService;
import com.windacc.wind.flow.service.MyService;
import com.windacc.wind.mybatis.entity.PageData;
import com.windacc.wind.toolkit.entity.Result;
import org.flowable.engine.IdentityService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/19
 */
@RequestMapping("deploy")
@RestController
public class ProcessController {

    @Autowired
    private MyService myService;
    @Autowired
    private IFlowProcessService processService;
    @Autowired
    private IdentityService identityService;

    /**
     * 生产应该是上传文件并发布， 自行优化。
     */
    @PostMapping("/new")
    public Result<?> deployProcess(@RequestParam String filename, @RequestParam String category,
        @RequestParam String name) {
        FlowDefinition flowDefinition = processService.createDeployment(filename, category, name);
        return Result.of(flowDefinition);
    }

    @GetMapping("list")
    public Result<?> listProcess(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize) {

        PageData<FlowDefinition> flowProcesses = processService.listDeployment(pageNum, pageSize);

        identityService.createUserQuery();

        return Result.of(flowProcesses);
    }





    @PostMapping("/delete")
    public Result<?> depleteProcess(@RequestParam String deploymentId) {
        processService.deleteDeployment(deploymentId);
        return Result.of(deploymentId);
    }

    @RequestMapping(value="/process", method= RequestMethod.POST)
    public String startProcessInstance(@RequestParam String processId, @RequestParam String assignee) {
        ProcessInstance processInstance = myService.startProcess(processId);
        return processInstance.getProcessInstanceId();
    }

    @RequestMapping(value="/tasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }

}
