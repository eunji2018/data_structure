package design_pattern.ProducerConsumer;

//缓冲池角色
//数据传递的通道，缓解生产者与消费者之间的速度差异
//保证线程间的互斥处理，实现线程的协调运行
public class Pool {

    private String[] buffer;
    private int head;//下一次取的位置
    private int tail;//下一次放的位置
    private int count;//当前数量
    
    public Pool(int size) {
        this.buffer = new String[size];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }
    
    //生产者放入
    public synchronized void put(String data) throws Exception {
        while (count == buffer.length) {//缓冲池满，生产者线程阻塞
            wait();
        }
        buffer[tail] = data;
        tail = (tail + 1) % buffer.length;
        count++;
        System.out.println(Thread.currentThread().getName() + " put " + data);
        notifyAll();//通知其他线程
        return;
    }
    
    //消费者取出
    public synchronized String take() throws Exception {
        while (count == 0) {//缓冲池空，消费者线程阻塞
            wait();
        }
        String data = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        System.out.println(Thread.currentThread().getName() + " take " + data);
        notifyAll();//通知其他线程
        return data;
    }
    
}
