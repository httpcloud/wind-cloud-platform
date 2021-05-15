package com.windacc.wind.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:30
 */
@Slf4j
@SpringBootApplication
public class WindGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(WindGatewayApp.class, args);
        log.info("==============WindGatewayApp start====================");
    }

}
