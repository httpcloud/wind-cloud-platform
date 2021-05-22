package com.windacc.wind.engine;

import com.windacc.wind.toolkit.annotation.WindCloudApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
@Slf4j
@WindCloudApplication
public class WindEngineApp {

    public static void main(String[] args) {
        SpringApplication.run(WindEngineApp.class, args);
        log.info("WindEngineApp 启动");
    }

}
