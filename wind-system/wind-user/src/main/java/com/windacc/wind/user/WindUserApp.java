package com.windacc.wind.user;

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
public class WindUserApp {

    public static void main(String[] args) {
        SpringApplication.run(WindUserApp.class, args);
        //SpringApplication app = new SpringApplication(WindUserApp.class);
        //app.setApplicationStartup(new BufferingApplicationStartup(1000));

        log.info("==============WindUserApp start====================");
    }

}
