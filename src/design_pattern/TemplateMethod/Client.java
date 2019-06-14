package design_pattern.TemplateMethod;

//模板方法模式
//在父类中定义处理的流程，在子类中实现具体的处理
public class Client {

	public static void main(String[] args) {
		Template templateA = new ConcreteTemplateA();
        Template templateB = new ConcreteTemplateB();

        templateA.process();
        System.out.println("-------");
        templateB.process();
	}
}
