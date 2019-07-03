package design_pattern.ActiveObject;

//主动对象接口
public interface Active {

	//生成字符串
	Result<String> make(int count, char c);

	//显示字符串
	void display(String string);
}

//主动对象工厂
class ActiveFactory {
	
	public static Active create() {
		Pool pool = new Pool();
		Scheduler scheduler = new Scheduler(pool);
		Server server = new Server();
		Proxy proxy = new Proxy(server, scheduler);
		scheduler.start();//启动调度线程
		return proxy;
	}
}

//请求被调用
class Proxy implements Active{
	
	private final Server server;	
	private final Scheduler scheduler;
	
	public Proxy(Server server, Scheduler scheduler) {
		this.server = server;
		this.scheduler = scheduler;
	}
	
	@Override
	public Result<String> make(int count, char c) {
		Future<String> future = new Future<>();
		scheduler.invoke(new MakeRequest(server, future, count, c));//创建生成字符串的请求
		return future;
	}
	
	@Override
	public void display(String string) {
		scheduler.invoke(new DisplayRequest(server, string));//创建显示字符串的请求
		return;
	}
}

//请求被执行
class Server implements Active{

	//生成字符串的逻辑
	@Override
	public Result<String> make(int count, char c) {
		char [] buffer = new char[count];
		for(int i = 0; i < count; i++) {
			buffer[i] = c;
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		return new Real<>(new String(buffer));//返回真实结果
	}

	//显示字符串的逻辑
	@Override
	public void display(String string) {
		try {
			System.out.println("		display " + string);
			Thread.sleep(200);
		} catch (Exception e) {}
		return;
	}
}