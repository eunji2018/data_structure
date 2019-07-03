package design_pattern.ReadWriteLock;

//读写锁模式
//读取数据时可以有多个读线程同时读取，写入数据时只能有一个写线程写入
//实现了读写分离，提高性能效率
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
