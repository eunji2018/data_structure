package design_pattern.ThreadPool;

import java.util.Random;

//客户端请求
public class Request {

	private String name;
	private static final Random random = new Random();
	
	public Request(String name) {
		this.name = name;
	}
	
	public void execute() {
		System.out.println(Thread.currentThread().getName() + " execute " + name);
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (Exception e) {}
	}
	
}
