package design_pattern.ActiveObject;

//请求对象
public abstract class Request<T> {

	protected final Server server;	
	protected final Future<T> future;
	
	public Request(Server server, Future<T> future) {
		this.server = server;
		this.future = future;
	}
	
	public abstract void execute();
	
}

//生成字符串请求
class MakeRequest extends Request<String>{
	
	private final int count;	
	private final char c;
	
	public MakeRequest(Server server, Future<String> future, int count, char c) {
		super(server, future);
		this.count = count;
		this.c = c;
	}
	
	@Override
	public void execute() {
		Result<String> result = server.make(count, c);
		future.setResult(result);//关联Future角色与真实结果
		return;
	}
}

//显示字符串请求
class DisplayRequest extends Request<String>{
	
	private final String string;
	
	public DisplayRequest(Server server, String string) {
		super(server, null);
		this.string = string;
	}
	
	@Override
	public void execute() {
		server.display(string);
		return;
	}
}

