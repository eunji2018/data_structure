package design_pattern.Strategy;

//策略模式
//可以整体地替换算法的实现部分
public class Client {

	public static void main(String[] args) {
		Strategy strategyA = new ConcreteStrategyA();
        Strategy strategyB = new ConcreteStrategyB();

        Context context = new Context(strategyA);
        context.action();
        context.setStrategy(strategyB);
        context.action();
        
        return;
	}
}
