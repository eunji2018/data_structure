package design_pattern.Proxy;

//真实角色
public class Real implements Role{

    //普通行为
    @Override
    public void general() {
        System.out.println("real role general");
    }

    //特有行为
    @Override
    public void special() {
        System.out.println("real role special");
    }
}
