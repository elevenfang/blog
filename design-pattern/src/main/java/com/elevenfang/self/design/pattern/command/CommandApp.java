package com.elevenfang.self.design.pattern.command;

/**
 * Receiver receive command to do something, it is wrapped by command. The
 * action is executed by invoker but the concrete thing is from receiver. Action
 * trigger: Invoker -> Command -> Receiver
 * Used in file,like new file,cut file or delete file. The concrete command executer is FileSystem.
 * Used in Dispatcherservlet
 * @author elevenfang
 *
 */
public class CommandApp {
	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		SelfCommand command = new ConcreteSelfCommand(receiver);
		Invoker invoker = new Invoker(command);
		invoker.invoke();
	}
}
