package com.elevenfang.self.design.pattern.decorator;

public class SelfDecorator extends SelfBeDecorator {
	protected SelfBeDecorator beDecorator;

	public void setBeDecorator(SelfBeDecorator selfBeDecorator) {
		this.beDecorator = selfBeDecorator;
	}

	public void eat() {
		System.out.println("this is decorator");
		beDecorator.eat();
	}
}
