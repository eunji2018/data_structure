package design_pattern.TemplateMethod;

//具体模板
//实现父类的抽象方法，表示具体的处理，将会在父类的模板方法中被调用
public class ConcreteTemplateB extends Template{

	@Override
	public void second() {
		System.out.println("template B step two");
	}
	
	@Override
	public void third() {
		System.out.println("template B step three");
	}
}
