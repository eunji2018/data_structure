package design_pattern.Strategy;

//上下文环境
//调用抽象策略提供的接口
public class Context {

    //此上下文关联的策略
    private Strategy strategy;
    
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void action() {
        strategy.operate();
    }
}
