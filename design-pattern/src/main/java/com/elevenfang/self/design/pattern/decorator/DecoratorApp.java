package com.elevenfang.self.design.pattern.decorator;

/**
 * only use the origin interface for add new duty.
 * @author elevenfang
 *
 */


public class DecoratorApp {
	public static void main(String[] args) {
		SelfBeDecorator beDecorator = new SelfBeDecorator();
		SelfDecorator decorator = new SelfDecorator();
		decorator.setBeDecorator(beDecorator);
		decorator.eat();
	}
}
