package design_pattern.ProducerConsumer;

import java.util.Random;

//生产者角色
public class Producer extends Thread {

    private static int id;//所有生产者共有的产品编号
    private final Random random;
    private final Pool pool;
    
    public Producer(String name, int seed, Pool pool) {
        super(name);
        this.random = new Random(seed);
        this.pool = pool;
    }
    
    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String data = "data " + next();//生产数据
                pool.put(data);//向缓冲池放入数据
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //下一个产品编号
    public static synchronized int next() {
        id++;
        return id;
    }
}
