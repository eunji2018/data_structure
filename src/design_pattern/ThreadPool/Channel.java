package design_pattern.ThreadPool;

//
public class Channel {

	private final int SIZE = 10;
	private final Request[] requests;
	private int head;//下一个取
	private int tail;//下一个放
	private int count;//当前个数
	private final Worker[] workers;
	
	public Channel(int size) {
		this.requests = new Request[SIZE];
		this.head = 0;
		this.tail = 0;
		this.count = 0;
		this.workers = new Worker[size];
		for(int i = 0; i < workers.length; i++) {
			workers[i] = new Worker("worker " + i, this);
		}
	}
	
	//启动工作者线程
	public void start() {
		for(int i = 0; i < workers.length; i++) {
			workers[i].start();
		}
	}
	
	//客户端线程放入请求
	public synchronized void put(Request request) {
		while(count == requests.length) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		requests[tail] = request;
		tail = (tail + 1) % requests.length;
		count++;
		notifyAll();
		return;
	}
	
	//工作者线程取出请求
	public synchronized Request take() {
		while(count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		Request request = requests[head];
		head = (head + 1) % requests.length;
		count--;
		notifyAll();
		return request;
	}
	
}
