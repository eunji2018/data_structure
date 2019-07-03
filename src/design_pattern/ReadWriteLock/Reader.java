package design_pattern.ReadWriteLock;

//读线程
public class Reader extends Thread{

	private final Data data;
	
	public Reader(Data data) {
		this.data = data;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			while(true) {
				char[] temp = data.read();//执行读取操作
				System.out.println(Thread.currentThread().getName() + 
				" read " + String.valueOf(temp));
			}	
		} catch (Exception e) {}
	}
}
