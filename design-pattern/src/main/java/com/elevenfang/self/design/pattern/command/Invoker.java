package com.elevenfang.self.design.pattern.command;

public class Invoker {
	private SelfCommand command;

	public Invoker(SelfCommand command) {
		this.command = command;
	}

	public void invoke() {
		command.execute();
	}
}
