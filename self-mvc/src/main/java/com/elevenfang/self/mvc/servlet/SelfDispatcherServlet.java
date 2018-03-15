package com.elevenfang.self.mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.elevenfang.self.mvc.annotation.SelfController;
import com.elevenfang.self.mvc.annotation.SelfRequestMapping;

public class SelfDispatcherServlet extends HttpServlet {

	private Properties properties = new Properties();
	private List<String> scanClassNames = new ArrayList<>();
	private Map<String, Object> ioc = new HashMap<>();
	private Map<String, Method> handlerMapping = new HashMap<>();
	private Map<String, Object> controllerMap = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		loadConfig(config.getInitParameter("contextConfigLocation"));
		scanPackage(properties.getProperty("scanPackage"));
		newInstanceAndLoadInIOC();
		initHandlerMapping();
	}

	private void initHandlerMapping() {
		if (ioc.isEmpty()) {
			return;
		}
		try {
			for (Entry<String, Object> entry : ioc.entrySet()) {
				Class<?> clazz = entry.getValue().getClass();
				if (!clazz.isAnnotationPresent(SelfController.class)) {
					continue;
				}
				String baseUrl = "";
				if (clazz.isAnnotationPresent(SelfRequestMapping.class)) {
					SelfRequestMapping classRequestMappingAnnotation = clazz.getAnnotation(SelfRequestMapping.class);
					baseUrl = classRequestMappingAnnotation.value();
				}
				Method[] methods = clazz.getMethods();
				for (Method method : methods) {
					if (!method.isAnnotationPresent(SelfRequestMapping.class)) {
						continue;
					}
					SelfRequestMapping methodRequestMappingAnnotation = method.getAnnotation(SelfRequestMapping.class);
					String url = methodRequestMappingAnnotation.value();
					url = StringUtils.replaceAll(baseUrl + "/" + url, "/+", "/");
					handlerMapping.put(url, method);
					controllerMap.put(url, clazz.newInstance());
					System.out.println(url + "," + method);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void newInstanceAndLoadInIOC() {
		if (scanClassNames.isEmpty()) {
			return;
		}
		for (String className : scanClassNames) {
			Class<?> clazz;
			try {
				clazz = Class.forName(className);
				if (clazz.isAnnotationPresent(SelfController.class)) {
					ioc.put(toLowerForFirstWord(clazz.getSimpleName()), clazz.newInstance());
				} else {
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}

	}

	/**
	 * ASCII difference between upper and lower character is 32
	 * 
	 * @param name
	 * @return
	 */
	private String toLowerForFirstWord(String name) {
		char[] chars = name.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}

	private void scanPackage(String packageName) {
		URL url = getClass().getClassLoader().getResource("/" + StringUtils.replaceAll(packageName, "\\.", "/"));
		File dir = new File(url.getFile());
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				scanPackage(packageName + "." + file.getName());
			} else {
				String qualifiedName = packageName + "." + file.getName().replace(".class", "");
				scanClassNames.add(qualifiedName);
			}
		}

	}

	private void loadConfig(String packageName) {
		InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(packageName);
		try {
			properties.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != resourceStream) {
				try {
					resourceStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatcher(req, resp);
	}

	private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (handlerMapping.isEmpty()) {
			return;
		}
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		url = url.replace(contextPath, "").replaceAll("/+", "/");
		if (!this.handlerMapping.containsKey(url)) {
			resp.getWriter().write("404!");
			return;
		}
		Method method = this.handlerMapping.get(url);
		Class<?>[] parameterTypes = method.getParameterTypes();
		Map<String, String[]> parameterMap = req.getParameterMap();
		Object[] paramValues = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			String requestParam = parameterTypes[i].getSimpleName();
			if (requestParam.equals("HttpServletRequest")) {
				paramValues[i] = req;
				continue;
			}
			if (requestParam.equals("HttpServletResponse")) {
				paramValues[i] = resp;
				continue;
			}
			if (requestParam.equals("String")) {
				for (Entry<String, String[]> param : parameterMap.entrySet()) {
					String temp = StringUtils.replaceChars(Arrays.toString(param.getValue()), "[", "");
					temp = StringUtils.replaceChars(temp, "]", "");
					paramValues[i] = temp;
				}
			}
		}
		try {
			method.invoke(this.controllerMap.get(url), paramValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
