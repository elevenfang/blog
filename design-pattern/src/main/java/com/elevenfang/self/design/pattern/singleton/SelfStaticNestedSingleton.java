package com.elevenfang.self.design.pattern.singleton;

/**
 * 首先只有使用的时候才实例化，其次静态内部类已经实现了线程安全
 * @author elevenfang
 *
 */


public class SelfStaticNestedSingleton {
	private SelfStaticNestedSingleton() {
	}

	public static class SingletonHolder {
		private static final SelfStaticNestedSingleton SINGLETON = new SelfStaticNestedSingleton();
	}

	public static SelfStaticNestedSingleton getInstance() {
		return SingletonHolder.SINGLETON;
	}
}
