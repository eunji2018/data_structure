package design_pattern.ThreadPool;

//管道角色
public class Channel {

	private final int SIZE = 10;
	private final Request[] requests;
	private int head;//下一次取出请求的位置
	private int tail;//下一次放入请求的位置
	private int count;//当前的请求个数
	private final Worker[] workers;
	
	public Channel(int size) {
		this.requests = new Request[SIZE];
		this.head = 0;
		this.tail = 0;
		this.count = 0;
		this.workers = new Worker[size];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Worker("worker " + i, this);
		}
	}
	
	//启动工作线程
	public void start() {
		for (int i = 0; i < workers.length; i++) {
			workers[i].start();
		}
	}
	
	//客户端线程放入请求
	public synchronized void put(Request request) {
		while (count == requests.length) {//请求已满
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
	
	//工作线程取出请求
	public synchronized Request take() {
		while (count == 0) {//请求已空
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
