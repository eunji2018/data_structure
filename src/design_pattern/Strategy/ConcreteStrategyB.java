package design_pattern.Strategy;

//具体策略
//实现具体的算法
public class ConcreteStrategyB extends Strategy{
	
	@Override
	public void operate() {
		System.out.println("Strategy B");
	}
}
