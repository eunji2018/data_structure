package design_pattern.ActiveObject;

//ActiveObject模式
//可以从外部接收和处理异步消息，并且返回处理结果
//结合了生产者消费者模式，Future模式，线程池模式
public class Main {

	public static void main(String[] args) {
		Active active = ActiveFactory.create();
        new MakerClient("abby", active).start();
        new MakerClient("bob", active).start();
        new DisplayClient("cindy", active).start();
	}
}

//客户端线程
//请求生成字符串
class MakerClient extends Thread{
	
    private final Active active;
    private final char c;

    public MakerClient(String name, Active active){
        super(name);
        this.active = active;
        this.c = name.charAt(0);
    }

    @Override
    public void run() {
        super.run();
        try {
            for(int i = 1; true; i++){
                Result<String> result = active.make(i, c);//发送生成字符串的异步消息
                Thread.sleep(10);
                String string = result.getValue();//获取返回结果
                System.out.println(Thread.currentThread().getName() + " value= " + string);
            }
        }catch (Exception e){}
    }
}

//客户端线程
//请求显示字符串
class DisplayClient extends Thread{
	
    private final Active active;

    public DisplayClient(String name, Active active){
        super(name);
        this.active = active;
    }

    @Override
    public void run() {
        super.run();
        try {
            for(int i = 0; true; i++){
                String string = Thread.currentThread().getName() + " " + i;
                active.display(string);//发送显示字符串的异步消息
                Thread.sleep(500);
            }
        }catch (Exception e){}
    }
}