package design_pattern.Decorator;

//具体装饰者
public class ConcreteDecorator extends Decorator{

	public ConcreteDecorator(Component component) {
		this.component = component;
	}
	
	@Override
	public void describe() {
		component.describe();
		System.out.println(this.description);
	}
}
