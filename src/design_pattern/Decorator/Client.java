package design_pattern.Decorator;

//装饰者模式
//装饰者与被装饰者具有一致性，即相同的接口
//在不修改被装饰者的前提下，通过添加装饰者来增加对象的功能
//缺点：导致程序中增加许多很小的类
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.setDescription("i am a boy");
        
        Decorator decorator1 = new ConcreteDecorator(component);
        decorator1.setDescription("---and a girl");
        
        Decorator decorator2 = new ConcreteDecorator(decorator1);
        decorator2.setDescription("---and a dog");
                
        component.describe();
        decorator1.describe();
        decorator2.describe();

        return;    
    }
}
