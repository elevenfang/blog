package com.elevenfang.self.design.pattern.adapter;

public class SelfAdapter extends SelfSource implements SelfTarget {
	public void target() {
		super.source();
	}

}
