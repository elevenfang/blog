package com.elevenfang.self.design.pattern.proxy.withdynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

	/**
	 * add common proxy
	 * @param interfaceClazz
	 * @param realObject
	 * @return
	 */
	public static <T> T getProxy(Class<T> interfaceClazz, T realObject){
		return (T)Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class []{interfaceClazz}, new JdkProxyService(realObject));
	}

}
