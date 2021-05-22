package com.windacc.wind.flow.service;

import com.windacc.wind.flow.entity.FlowInstance;
import com.windacc.wind.flow.entity.FlowTask;
import com.windacc.wind.mybatis.entity.PageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
public interface IFlowTaskService {

    /**
     * 通过流程定义id开启流程
     *
     * @param processDefinitionId 流程id
     * @param businessKey       业务key
     * @return 流程实例
     */
    FlowInstance startProcessInstance(String processDefinitionId, String businessKey);

    /**
     * 查询待办任务
     * @return  待办任务列表
     */
    PageData<FlowTask> taskTodoList();

    /**
     * 在流程实例中评论意见或者添加附件
     * @param taskId  人物奖id
     * @param processInstanceId 流程实例ID
     * @param comment   意见
     * @param attach    附件
     * @throws IOException 异常
     */
    void addComment(String taskId, String processInstanceId, String comment, MultipartFile attach) throws IOException;

    void nextActivity(String taskId);

    boolean completeTask(String taskId);
}
