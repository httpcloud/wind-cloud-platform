package com.windacc.wind.flow.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/21
 */
@Configuration
public class FlowableConfiguration implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration engineConfiguration) {
        engineConfiguration.setActivityFontName("思源宋体");
        engineConfiguration.setLabelFontName("思源宋体");
        engineConfiguration.setAnnotationFontName("思源宋体");
    }

}
