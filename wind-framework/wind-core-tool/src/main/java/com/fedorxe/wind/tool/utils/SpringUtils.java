package com.fedorxe.wind.tool.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:27
 */
@Component
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		SpringUtils.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		if (clazz == null) {
			return null;
		}
		return applicationContext.getBean(clazz);
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		if (null == name || "".equals(name.trim())) {
			return null;
		}
		if (clazz == null) {
			return null;
		}
		return applicationContext.getBean(name, clazz);
	}

	public static String getProperty(String key) {
		return applicationContext.getBean(Environment.class).getProperty(key);
	}

	public static String getActiveProfile() {
		return applicationContext.getEnvironment().getActiveProfiles()[0];
	}

}
