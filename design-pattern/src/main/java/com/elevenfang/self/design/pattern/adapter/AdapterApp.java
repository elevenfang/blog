package com.elevenfang.self.design.pattern.adapter;

/**
 * Convert source to target by adapter. 
 * Adpater implement target interface and
 * call source method in implementation.
 * 它是一种补偿模式，专用来在系统后期扩展、修改时所用。
 * 此处使用的继承，但是推荐使用组合方式。
 * @author elevenfang
 *
 */

public class AdapterApp {
	public static void main(String[] args) {
		SelfTarget target = new SelfAdapter();
		target.target();
	}
}
