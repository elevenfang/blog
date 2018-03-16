package com.elevenfang.self.design.pattern.singleton;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * JDK 1.5 之后 
 * 这种模式中双重判断加同步的方式，比懒汉的效率大大提升，
 * 因为双重加锁模式下，只有第一次的时候才需要做同步，而懒汉式模式下，每次都需要同步。
 * 
 * 1. 另外运用单例模式时，一定注意不要使用反射产生新的单例对象。 如何防止反射，需要在构造方法中加料 
 * 2. 如果单例得到的对象进行序列化再反序列化回来，会出现两者的hashcode不同，说明单例被破坏 
 * 		a. 使用枚举类 b. 重写readResolve方法
 * 
 * @author elevenfang
 *
 */

public class SelfDoubleLockSingleton implements Serializable {
	private static SelfDoubleLockSingleton singleton = null;

	private static boolean flag = false;

	private SelfDoubleLockSingleton() {
		synchronized (SelfDoubleLockSingleton.class) {
			if (flag == false) {
				flag = !flag;
			} else {
				throw new RuntimeException("单例模式被破坏了！");
			}
		}
	}

	public static SelfDoubleLockSingleton getInstance() {
		if (null == singleton) {
			synchronized (SelfDoubleLockSingleton.class) {
				if (null == singleton) {
					singleton = new SelfDoubleLockSingleton();
				}
			}
		}
		return singleton;
	}

	protected Object readResolve() {
		System.out.println("调用了readResolve方法！");
		return singleton;
	}

	public static void main(String[] args) throws Exception {
		Class clazz = Class.forName(SelfDoubleLockSingleton.class.getName());
		Constructor constructor = clazz.getDeclaredConstructors()[0];
		SelfDoubleLockSingleton singleton = (SelfDoubleLockSingleton) constructor.newInstance();
		SelfDoubleLockSingleton singleton2 = (SelfDoubleLockSingleton) constructor.newInstance();
	}
}
