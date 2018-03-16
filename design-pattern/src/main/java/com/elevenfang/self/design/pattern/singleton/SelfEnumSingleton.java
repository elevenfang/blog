package com.elevenfang.self.design.pattern.singleton;

/**
 * 枚举类接口实现单例模式
 * 
 * @author elevenfang
 *
 */

interface MySingleton {
	void doSomething();
}

public enum SelfEnumSingleton implements MySingleton {
	INSTANCE {
		public void doSomething() {
			// TODO
		}
	};
	public static MySingleton getInstance() {
		return SelfEnumSingleton.INSTANCE;
	}

}
