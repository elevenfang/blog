package com.elevenfang.self.design.pattern.proxy.withdynamic.cglib;

public class CglibApp {
	public static void main(String[] args) {
		RealService realService = CgLibInterceptor.getProxy(RealService.class);
		realService.sayHello();
	}
}
