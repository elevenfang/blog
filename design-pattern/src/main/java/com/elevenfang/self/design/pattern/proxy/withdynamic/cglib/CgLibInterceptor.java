package com.elevenfang.self.design.pattern.proxy.withdynamic.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CgLibInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("before");
		Object result = proxy.invokeSuper(object, args);
		System.out.println("after");
		return result;
	}

	public static <T> T getProxy(Class<?> cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallback(new CgLibInterceptor());
		return (T) enhancer.create();
	}

}
