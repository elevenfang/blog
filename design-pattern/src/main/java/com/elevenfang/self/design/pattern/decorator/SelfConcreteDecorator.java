package com.elevenfang.self.design.pattern.decorator;

public class SelfConcreteDecorator extends SelfDecorator{
	
	@Override
	public void eat() {
		System.out.println("i am decoratoring.....");
		beDecorator.eat();
		
	}

}
