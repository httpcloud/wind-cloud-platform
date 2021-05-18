package com.windacc.wind.auth;

import com.windacc.wind.toolkit.annotation.WindCloudApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:30
 */
@Slf4j
@WindCloudApplication
public class WindAuthApp {

    public static void main(String[] args) {
        SpringApplication.run(WindAuthApp.class, args);
        log.info("==============WindAuthApp start====================");
    }

}
