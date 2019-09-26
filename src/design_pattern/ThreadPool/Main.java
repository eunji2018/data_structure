package design_pattern.ThreadPool;

//线程池模式
//将调用与执行分离，提高响应速度、吞吐量，方便请求调度
public class Main {

    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.start();
        new Client("a", channel).start();
        new Client("    b", channel).start();
        new Client("        c", channel).start();
    }
}
