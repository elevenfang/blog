package com.elevenfang.self.design.pattern.singleton;
/**
 * 线程安全，但是在类加载的时候就会创建，如果有很多这样类型的实例会影响效率
 * @author elevenfang
 *
 */
public class SelfHungrySingleton {
	private static SelfHungrySingleton singleton = new SelfHungrySingleton();

	private SelfHungrySingleton() {
	}

	public static SelfHungrySingleton getInstance() {
		return singleton;
	}
}
