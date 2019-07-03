package design_pattern.Future;

//Future模式
//实现异步方法调用，并且可以获取返回结果
//调用者角色
public class Client {

	public static void main(String[] args) {
		Host host = new Host();
		System.out.println("client start");
		//异步请求调用
		Result result1 = host.request(10, 'a');
		Result result2 = host.request(20, 'b');
		Result result3 = host.request(30, 'c');
		
		System.out.println("	other start");
		//调用者线程休眠
		try {
			Thread.sleep(2000);
		} catch (Exception e) {}
		System.out.println("	other end");

		//获取返回结果
		System.out.println("	result1= " + result1.getContent());
		System.out.println("	result2= " + result2.getContent());
		System.out.println("	result3= " + result3.getContent());
		System.out.println("client end");
	}
}
