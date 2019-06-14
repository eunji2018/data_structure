package design_pattern.ThreadPool;

//工作者线程
public class Worker extends Thread {

	private final Channel channel;
	
	public Worker(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			Request request = channel.take();
			request.execute();
		}
	}
}
