package design_pattern.ActiveObject;

//执行结果对象
public abstract class Result<T> {

    public abstract T getValue();
}

class Future<T> extends Result<T> {
    
    private Result<T> result;
    private boolean ready = false;//true表示已关联真实结果

    //调度线程关联执行结果
    public synchronized void setResult(Result<T> result) {
        if (ready)
            return;
        this.result = result;
        this.ready = true;
        notifyAll();//通知客户端线程
    }

    //客户端线程获取执行结果
    @Override
    public synchronized T getValue() {
        while (!ready) {
            try {
                wait();
            } catch (Exception e) {}
        }
        return result.getValue();
    }
}

//真实执行结果
class Real<T> extends Result<T> {

    private final T value;
    
    public Real(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
}
