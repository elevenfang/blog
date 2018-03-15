package com.elevenfang.self.design.pattern.iterator;

public interface SelfCollection {

	public void add(Object object);

	public void remove(Object object);

	public SelfIterator iterator();
}
