package com.elevenfang.self.design.pattern.proxy.withstatic;

public class ProxyService implements IService {
	private IService iService;

	public ProxyService(IService iService) {
		this.iService = iService;
	}

	@Override
	public void sayHello() {
		System.out.println("before");
		iService.sayHello();
		System.out.println("after");

	}

}
