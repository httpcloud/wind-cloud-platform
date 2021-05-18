package com.windacc.wind.toolkit.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Slf4j
public class RequestUtil {

	public static HttpServletRequest getRequest() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return null;
		}
		return ((ServletRequestAttributes) attributes).getRequest();
	}

	public static HttpServletResponse getResponse() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return null;
		}
		return ((ServletRequestAttributes) attributes).getResponse();
	}

	public static HashMap<String, String> getHeaders() {
		HttpServletRequest request = getRequest();
		return getHeaders(request);
	}


	public static HashMap<String, String> getHeaders(HttpServletRequest request) {
		final HashMap<String, String> map = new LinkedHashMap<>();
		if (request != null) {
			final Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				final String name = headerNames.nextElement();
				final String value = request.getHeader(name);
				map.put(name, value);
			}
		}
		return map;
	}
	public static HashMap<String, Object> getAttributes(HttpServletRequest request) {
		final HashMap<String, Object> map = new LinkedHashMap<>();
		if (request != null) {
			final Enumeration<String> attributeNames = request.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				final String name = attributeNames.nextElement();
				final Object value = request.getAttribute(name);
				map.put(name, value);
			}
		}
		return map;
	}

	public static String getHeaders(String key) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			return request.getHeader(key);
		}
		return null;
	}



}

