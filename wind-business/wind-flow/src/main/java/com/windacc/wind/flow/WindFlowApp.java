package com.windacc.wind.flow;

import com.windacc.wind.toolkit.annotation.WindCloudApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/19
 */
@Slf4j
@WindCloudApplication
public class WindFlowApp {

    public static void main(String[] args) {
        SpringApplication.run(WindFlowApp.class, args);
        log.info("WindFlowApplication 启动");
    }

    //@Bean
    //public CommandLineRunner init(final RepositoryService repositoryService, final RuntimeService runtimeService,
    //    final TaskService taskService) {
    //
    //    return new CommandLineRunner() {
    //        @Override
    //        public void run(String... strings) throws Exception {
    //            System.out.println(
    //                "Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
    //            System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
    //            runtimeService.startProcessInstanceByKey("oneTaskProcess");
    //            System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
    //        }
    //    };
    //}

}
