package design_pattern.TemplateMethod;

//抽象模板
//实现的模板方法，表示处理的流程，调用抽象方法
//声明的抽象方法，表示具体的处理，由子类实现
public abstract class Template {
	
	//模板方法，不能被重写
	public final void process() {
		first();
		second();
		third();
	}

	//具体模板通用的处理
	public void first() {
		System.out.println("common step one");
	}
	
	//抽象方法，需要被实现
	public abstract void second();

	//抽象方法，需要被实现
	public abstract void third();
}
