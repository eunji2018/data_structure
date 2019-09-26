package design_pattern.ActiveObject;

//调度线程
public class Scheduler extends Thread {
    
    private final Pool pool;
    
    public Scheduler(Pool pool) {
        this.pool = pool;
    }

    public void invoke(Request request) {
        pool.put(request);
        return;
    }
    
    @Override
    public void run() {
        super.run();
        while (true) {
            Request request = pool.take();//取出请求
            request.execute();//处理请求
        }
    }
}

//请求缓冲池
class Pool{
    
    private static final int MAX = 10;
    private final Request[] requests;
    private int head;//下一次取出请求的位置
    private int tail;//下一次放入请求的位置
    private int count;//当前的请求数量

    public Pool(){
        this.requests = new Request[MAX];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    //客户端线程放入请求
    public synchronized void put(Request request){
        while (count == requests.length){//缓冲池已满
            try {
                wait();
            }catch (Exception e){}
        }
        requests[tail] = request;
        tail = (tail + 1) % requests.length;
        count++;
        notifyAll();
        return;
    }

    //调度线程取出请求
    public synchronized Request take(){
        while (count == 0){//缓冲池已空
            try {
                wait();
            }catch (Exception e){}
        }
        Request request = requests[head];
        head = (head + 1) % requests.length;
        count--;
        notifyAll();
        return request;
    }
}