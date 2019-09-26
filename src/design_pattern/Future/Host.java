package design_pattern.Future;

//被调用者角色
public class Host {

    public Result request(int count, char c) {
        System.out.println("    request start " + c);
        final Future future = new Future();
        //启动新线程，异步处理请求
        new Thread() {
            public void run() {
                Real real = new Real(count, c);//构造真实结果角色
                future.setReal(real);//关联真实结果角色与Future角色
            }; 
        }.start();
        System.out.println("    request end " + c);
        return future;
    }
}
