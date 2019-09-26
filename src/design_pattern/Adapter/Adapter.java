package design_pattern.Adapter;

//适配器
//使用组合，对象适配器
public class Adapter implements Target{

    //此适配器关联的被适配者
    private Adaptee adaptee;
    
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    //使用被适配者的功能来实现外部类的需求
    @Override
    public void handle() {
        System.out.println("i am a adapter");
        System.out.println("request a adaptee ...");
        adaptee.request();
    }

}
