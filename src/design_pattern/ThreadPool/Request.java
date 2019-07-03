package design_pattern.ThreadPool;

import java.util.Random;

//请求角色
public class Request {

	private String name;
	private static final Random random = new Random();
	
	public Request(String name) {
		this.name = name;
	}

	//请求的处理逻辑
	public void execute() {
		System.out.println(Thread.currentThread().getName() + " execute " + name);
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (Exception e) {}
	}
	
}
