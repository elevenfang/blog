package com.elevenfang.self.design.pattern.proxy.withstatic;

public class ConcreteService implements IService {

	@Override
	public void sayHello() {
		System.out.println("actually do something");
	}

}
