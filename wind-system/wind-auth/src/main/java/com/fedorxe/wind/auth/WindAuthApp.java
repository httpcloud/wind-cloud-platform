package com.fedorxe.wind.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:30
 */
@Slf4j
@SpringBootApplication
public class WindAuthApp {

    public static void main(String[] args) {
        SpringApplication.run(WindAuthApp.class, args);
        log.info("==============WindAuthApp start====================");
    }

}
