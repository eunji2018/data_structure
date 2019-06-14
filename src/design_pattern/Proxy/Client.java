package design_pattern.Proxy;

//代理模式
//普通行为交由代理角色处理
//特有行为交由真实角色处理
public class Client {

	public static void main(String[] args) {
		Role real = new Real();
		Role proxy = new Proxy(real);
		
		real.general();
		real.special();
		System.out.println("-----");
		proxy.general();
		proxy.special();
		
		return;
	}
}
