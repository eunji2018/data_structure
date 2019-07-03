package design_pattern.ProducerConsumer;

import java.util.Random;

//消费者角色
public class Consumer extends Thread {

	private final Random random;
	private final Pool pool;
	
	public Consumer(String name, int seed, Pool pool) {
		super(name);
		this.random = new Random(seed);
		this.pool = pool;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			while(true) {
				pool.take();//从缓冲池取出数据
				Thread.sleep(random.nextInt(1000));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
