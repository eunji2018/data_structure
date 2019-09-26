package design_pattern.Future;

//Future角色
public class Future implements Result {

    private Real real = null;
    private boolean ready = false;//true表示已经与真实结果角色关联

    public synchronized void setReal(Real real) {
        if (ready) //已关联真实结果角色
            return;
        this.real = real;
        this.ready = true;
        notifyAll();//通知调用者线程
    }
    
    public synchronized String getContent() {
        while (!ready) {//未关联真实结果角色
            try {
                wait();
            } catch (Exception e) {}
        }
        return real.getContent();
    }
    
}
