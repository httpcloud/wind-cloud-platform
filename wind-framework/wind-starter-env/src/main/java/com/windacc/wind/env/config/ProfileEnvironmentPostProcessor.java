package com.windacc.wind.env.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:27
 */
@Slf4j
public class ProfileEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    //private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/";
    //private static final String DEFAULT_NAMES = "custom";
    //private static final String DEFAULT_FILE_EXTENSION = ".properties";

    private String[] profiles = {
        "cloud-dev.properties",
        "cloud.properties",
        "wind-dev.properties",
        "wind.properties"
    };

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        //MutablePropertySources env = environment.getPropertySources();
        //
        //List<String> list = Arrays
        //    .asList(StringUtils.trimArrayElements(StringUtils
        //        .commaDelimitedListToStringArray(DEFAULT_SEARCH_LOCATIONS)));
        //Collections.reverse(list);
        //Set<String> reversedLocationSet = new LinkedHashSet<>(list);
        //
        //ResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        //
        //List<Properties> loadedProperties = new ArrayList<>();
        //reversedLocationSet.forEach(location -> {
        //    Resource resource = defaultResourceLoader.getResource(location
        //        + DEFAULT_NAMES + DEFAULT_FILE_EXTENSION);
        //    log.info(location + DEFAULT_NAMES + DEFAULT_FILE_EXTENSION);
        //    if (!resource.exists()) {
        //        log.info("不存在资源");
        //        return;
        //    }
        //    Properties p = new Properties();
        //    try {
        //        InputStream inputStream = resource.getInputStream();
        //        p.load(inputStream);
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //    loadedProperties.add(p);
        //});
        //System.err.println(loadedProperties);


        for (String profile : profiles) {
            Resource resource = new ClassPathResource(profile);
            String aaa =resource.getFilename();
            boolean exit = resource.exists();
            environment.getPropertySources().addLast(loadProfiles(resource));
        }

        log.info("sus");
        //environment.getProperty()
        //PropertySource<?> source = loadProfiles(resource);
        //environment.getPropertySources().addFirst(source);
    }

    private PropertySource<?> loadProfiles(Resource resource) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("file" + resource + "not exist");
        }
        try {
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            return new PropertiesPropertySource(resource.getFilename(), properties);
        } catch (IOException ex) {
            throw new IllegalStateException("load resource exception" + resource, ex);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    //private PropertySource<?> loadProfiles(Resource resource) throws IOException {
    //    YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
    //    List<PropertySource<?>> propertySources = sourceLoader.load(resource.getFilename(), resource);
    //    return propertySources.get(0);
    //}

}
