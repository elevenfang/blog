package com.elevenfang.self.design.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSelfCollection implements SelfCollection {
	private List list = new ArrayList();

	public void add(Object object) {
		list.add(object);
	}

	public void remove(Object object) {
		list.remove(object);
	}

	public SelfIterator iterator() {
		return new ConcreteSelfterator(list);
	}

}
