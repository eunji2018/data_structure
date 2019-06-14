package design_pattern.ActiveObject;

//主动对象接口
public interface Active {

	Result<String> make(int count, char c);
	
	void display(String string);
}

class ActiveFactory {
	
	public static Active create() {
		Pool pool = new Pool();
		Scheduler scheduler = new Scheduler(pool);
		Server server = new Server();
		Proxy proxy = new Proxy(server, scheduler);
		scheduler.start();
		return proxy;
	}
}

//请求被调用的地方
class Proxy implements Active{
	
	private final Server server;	
	private final Scheduler scheduler;
	
	public Proxy(Server server, Scheduler scheduler) {
		this.server = server;
		this.scheduler = scheduler;
	}
	
	@Override
	public Result<String> make(int count, char c) {
		Future<String> future = new Future<String>();
		scheduler.invoke(new MakeRequest(server, future, count, c));
		return future;
	}
	
	@Override
	public void display(String string) {
		scheduler.invoke(new DisplayRequest(server, string));
		return;
	}
}

//请求真正被执行的地方
class Server implements Active{
	
	@Override
	public Result<String> make(int count, char c) {
		char [] buffer = new char[count];
		for(int i = 0; i < count; i++) {
			buffer[i] = c;
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		return new Real<String>(new String(buffer));
	}
	
	@Override
	public void display(String string) {
		try {
			System.out.println("display " + string);
			Thread.sleep(200);
		} catch (Exception e) {}
	}
}