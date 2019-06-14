package design_pattern.Decorator;

//抽象装饰者
public abstract class Decorator extends Component{

    //此装饰者关联的组件
    protected Component component;

	public abstract void describe();
}
