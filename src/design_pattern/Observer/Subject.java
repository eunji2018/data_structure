package design_pattern.Observer;

//抽象主题
//定义注册，移除，通知观察者的接口
public interface Subject {

    void register(Observer observer);
    
    void remove(Observer observer);
    
    void notifyObserver();
}
