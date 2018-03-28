package com.elevenfang.self.design.pattern.proxy.withdynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxyService implements InvocationHandler {

	private Object realObject;

	public JdkProxyService(Object object) {
		this.realObject = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before");
		Object result = method.invoke(realObject, args);
		System.out.println("after");
		return result;
	}

}
