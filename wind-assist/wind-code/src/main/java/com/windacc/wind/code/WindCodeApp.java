package com.windacc.wind.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:30
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class WindCodeApp {

    public static void main(String[] args) {
        SpringApplication.run(WindCodeApp.class, args);
        log.info("==============WindCodeApp start====================");
    }

}
