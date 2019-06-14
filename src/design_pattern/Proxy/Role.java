package design_pattern.Proxy;

//抽象角色
//定义普通行为，特有行为的接口
//使代理角色与真实角色具有一致性
public interface Role {

	//普通行为
	void general();
	
	//特有行为
	void special();
}
