package design_pattern.ReadWriteLock;

public class Client {

	public static void main(String[] args) {
		Data data = new Data(10);
		new Reader(data).start();
		new Reader(data).start();
		new Reader(data).start();
		new Reader(data).start();
		new Reader(data).start();
		new Writer(data, "abcdefg").start();
		new Writer(data, "ABCDEFG").start();
	}
}
