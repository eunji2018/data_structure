package design_pattern.Observer;

//观察者模式（发布-订阅模式）
//当被观察对象的状态发生变化时，会通知给观察者
public class Client {

	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver();
        Observer observer2 = new ConcreteObserver();
        Observer observer3 = new ConcreteObserver();

        subject.register(observer1);
        subject.register(observer2);
        subject.register(observer3);

        subject.setState(100);
        System.out.println("---------------");
        
        subject.remove(observer2);
        subject.setState(200);
        System.out.println("---------------");
        
        subject.remove(observer3);
        subject.setState(300);

        return;
	}
}
