package com.windacc.wind.env.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.windacc.wind.env.constant.ProfileConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:27
 */
@Slf4j
public class ProfilePostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final List<String> PROFILES = ProfileConstant.LOADING_PROFILE;
    private static final String FILE_EXTENSION = ".properties";
    private static final String YML_EXTENSION = ".yml";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        MutablePropertySources propertySources = environment.getPropertySources();
        String profileActive = getActiveProfile(environment);

        List<Resource> loadedProperties = new LinkedList<>();
        PROFILES.forEach(file -> {
            loadedProperties.addAll(getResource(file, profileActive));
        });
        PROFILES.forEach(file -> {
            loadedProperties.addAll(getResource(file, null));
        });

        loadedProperties.forEach(resource -> {
            try {
                String filename = resource.getFilename();
                if (Objects.requireNonNull(filename).contains(FILE_EXTENSION)) {
                    propertySources.addLast(loadProfiles(resource));
                } else if (Objects.requireNonNull(filename).contains(YML_EXTENSION)) {
                    propertySources.addLast(loadYmlProfiles(resource));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    public String getActiveProfile(ConfigurableEnvironment environment) {
        String[] activeProfiles = environment.getActiveProfiles();
        if (ObjectUtil.isEmpty(activeProfiles)) {
            return ProfileConstant.ACTIVE_DEV;
        } else {
            return activeProfiles[0];
        }
    }

    private PropertySource<?> loadProfiles(Resource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        return new PropertiesPropertySource(Objects.requireNonNull(resource.getFilename()), properties);
    }

    private PropertySource<?> loadYmlProfiles(Resource resource) throws IOException {
        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySources = sourceLoader.load(resource.getFilename(), resource);
        return propertySources.get(0);
    }

    private List<Resource> getResource(String filename, String profileActive) {
        ResourceLoader loader = new DefaultResourceLoader();
        String classpath = "classpath:/";
        String active = "";
        if (StrUtil.isNotBlank(profileActive)) {
            active = "-" + profileActive;
        } else {
            active = "";
        }
        List<Resource> devResource = new LinkedList<>();
        Resource resource1 = loader.getResource(classpath + filename + active + FILE_EXTENSION);
        if (resource1.exists()) {
            devResource.add(resource1);
        }
        Resource resource2 = loader.getResource(classpath + filename + active + YML_EXTENSION);
        if (resource2.exists()) {
            devResource.add(resource2);
        }
        return devResource;
    }

}
