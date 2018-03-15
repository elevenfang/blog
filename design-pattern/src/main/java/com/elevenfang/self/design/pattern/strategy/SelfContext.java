package com.elevenfang.self.design.pattern.strategy;

public class SelfContext {
	private SelfStrategy strategy;

	public SelfContext(SelfStrategy strategy) {
		this.strategy = strategy;
	}

	public void execute() {
		strategy.doThing();
	}
}
