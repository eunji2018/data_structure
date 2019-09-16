package design_pattern.ThreadPool;

import java.util.Random;

//客户端线程
public class Client extends Thread {

	private static final Random random = new Random();
	private final Channel channel;
	
	public Client(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; true; i++) {
				Request request = new Request(getName() + " " + i);//准备请求
				channel.put(request);//放入请求
				Thread.sleep(random.nextInt(1000));
			}
		} catch (Exception e) {}
	}
}
