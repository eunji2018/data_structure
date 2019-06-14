package design_pattern.Observer;

//抽象观察者
//定义接收主题状态变化更新的接口
public interface Observer {

	void update(Subject subject);
}

