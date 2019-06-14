package design_pattern.Observer;

//具体观察者
//当主题状态发生变化时，接收状态更新
public class ConcreteObserver implements Observer{

    //表示观察者的状态
	private int state;
	
	@Override
	public void update(Subject subject) {
		state = ((ConcreteSubject)subject).getState();
		System.out.println(this + ": " + state);
	}
}
