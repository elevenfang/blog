package com.elevenfang.self.design.pattern.singleton;
/**
 * 延时加载，同步效率低。
 * @author elevenfang
 *
 */
public class SelfLazySingleton {
	private static SelfLazySingleton singleton;

	private SelfLazySingleton() {
	}

	public static synchronized SelfLazySingleton getInstance() {
		if (null == singleton) {
			singleton = new SelfLazySingleton();
		}
		return singleton;
	}

}
