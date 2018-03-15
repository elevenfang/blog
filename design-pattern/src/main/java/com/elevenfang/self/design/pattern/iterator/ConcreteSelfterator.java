package com.elevenfang.self.design.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSelfterator implements SelfIterator{

	private List list = new ArrayList();
	private int cursor = 0;
	
	public ConcreteSelfterator(List list) {
		this.list = list;
	}
	public boolean hasNext() {
		if (cursor == list.size()) {
			return false;
		}
		return true;
	}

	public Object next() {
		Object object = null;
		if (this.hasNext()) {
			object = this.list.get(cursor++);
		}
		return object;
	}

}
