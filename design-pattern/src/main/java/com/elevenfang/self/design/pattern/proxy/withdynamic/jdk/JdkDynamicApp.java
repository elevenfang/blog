package com.elevenfang.self.design.pattern.proxy.withdynamic.jdk;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;

import org.apache.commons.io.FileUtils;

import sun.misc.ProxyGenerator;

public class JdkDynamicApp {
	public static void main(String[] args) throws IOException {
		JdkDynamicService service = new ConcreteJdkDynamicService();
		JdkDynamicService proxyService = (JdkDynamicService) Proxy.newProxyInstance(
				JdkDynamicService.class.getClassLoader(), new Class<?>[] { JdkDynamicService.class },
				new JdkProxyService(service));
		proxyService.sayHello();
		createProxyClassFile();
		
		//use common proxy
		JdkDynamicService dynamicService = (JdkDynamicService) JdkProxyService.getProxy(JdkDynamicService.class,service);
		dynamicService.sayHello();
	}

	private static void createProxyClassFile() throws IOException {
		byte[] data = ProxyGenerator.generateProxyClass("ProxyJdk", new Class[] { JdkDynamicService.class });
		FileUtils.writeByteArrayToFile(
				new File("src/main/java/com/elevenfang/self/design/pattern/proxy/withdynamic/jdk/ProxyJdk.class"), data);
	}
}
