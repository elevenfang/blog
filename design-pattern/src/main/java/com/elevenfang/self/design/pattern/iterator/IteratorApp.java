package com.elevenfang.self.design.pattern.iterator;


/**
 * 提供一种方法访问一个容器对象中各个元素，而又不暴露该对象的内部细节。
 * 应用场景不是很多，因为大多数语言都实现了迭代器模式，容器和迭代器关系比较密切。
 * @author elevenfang
 *
 */
public class IteratorApp {
	public static void main(String[] args) {
		SelfCollection collection = new ConcreteSelfCollection();
		collection.add("a");
		collection.add("b");
		collection.add("c");
		SelfIterator iterator = collection.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
