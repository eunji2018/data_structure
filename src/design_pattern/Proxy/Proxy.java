package design_pattern.Proxy;

//代理角色
//普通行为由自己实现
//特有行为委托给真实角色
public class Proxy implements Role{

    //此代理关联的角色
	private Role role;
	
	public Proxy(Role role) {
		this.role = role;
	}

	//普通行为
	@Override
	public void general() {
		System.out.println("proxy role general");
	}

	//特有行为
	@Override
	public void special() {
		role.special();
	}

}
