package design_pattern.Observer;

import java.util.ArrayList;
import java.util.List;

//具体主题
//当状态发生变化后，通知所有已经注册的观察者
public class ConcreteSubject implements Subject{

    //表示主题的状态
    private int state;

    //保存关联的观察者
    private List<Observer> observers;

    public ConcreteSubject() {
        this.state = 0;
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObserver();
    }

}
