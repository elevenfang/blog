package com.elevenfang.self.design.pattern.command;

public class ConcreteSelfCommand extends SelfCommand {
	private Receiver receiver;

	public ConcreteSelfCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.doSomething();
	}

}
