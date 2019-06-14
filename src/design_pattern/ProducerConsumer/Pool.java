package design_pattern.ProducerConsumer;

//缓冲池
public class Pool {

	private String[] buffer;
	private int head;//下一次取
	private int tail;//下一次放
	private int count;//当前数量
	
	public Pool(int size) {
		// TODO Auto-generated constructor stub
		this.buffer = new String[size];
		this.head = 0;
		this.tail = 0;
		this.count = 0;
	}
	
	//生产者放
	public synchronized void put(String data) throws Exception {
		while(count == buffer.length) {
			wait();
		}
		buffer[tail] = data;
		tail = (tail + 1) % buffer.length;
		count++;
		System.out.println(Thread.currentThread().getName() + " put " + data);
		notifyAll();
		return;
	}
	
	//消费者取
	public synchronized String take() throws Exception {
		while(count == 0) {
			wait();
		}
		String data = buffer[head];
		head = (head + 1) % buffer.length;
		count--;
		System.out.println(Thread.currentThread().getName() + " take " + data);
		notifyAll();
		return data;
	}
	
}
