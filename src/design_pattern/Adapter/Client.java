package design_pattern.Adapter;

//适配器模式
//两种实现方式：使用继承（类适配器），使用组合（对象适配器）
public class Client {

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.handle();
    }
    
}
