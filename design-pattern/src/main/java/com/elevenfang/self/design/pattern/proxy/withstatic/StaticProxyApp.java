package com.elevenfang.self.design.pattern.proxy.withstatic;

public class StaticProxyApp {
	public static void main(String[] args) {
		IService service = new ConcreteService();
		IService proxyService = new ProxyService(service);
		proxyService.sayHello();
	}
}
