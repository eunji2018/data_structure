package design_pattern.ActiveObject;

public class Main {

	public static void main(String[] args) {
		Active active = ActiveFactory.create();
        new MakerClient("abby", active).start();
        new MakerClient("bob", active).start();
        new DisplayClient("cindy", active).start();
	}
}

//客户端线程
//制作字符串
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
                Result<String> result = active.make(i, c);
                Thread.sleep(10);
                String string = result.getValue();
                System.out.println(Thread.currentThread().getName() + " value= " + string);
            }
        }catch (Exception e){}
    }
}

//展示字符串
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
                active.display(string);
                Thread.sleep(500);
            }
        }catch (Exception e){}
    }
}