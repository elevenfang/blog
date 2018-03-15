package com.elevenfang.self.design.pattern.strategy;

/**
 * 一般针对不同的算法供客户端使用，算法种类太多会带来额外开销，
 * 且客户端必须了解这些策略的区别，这无疑是暴露了策略
 * @author elevenfang
 *
 */

public class StrategyApp {
	public static void main(String[] args) {
		SelfContext context = new SelfContext(new ConcreteSelfStrategy());
		context.execute();
	}
}
