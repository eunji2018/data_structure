package design_pattern.ProducerConsumer;

public class Client {

	public static void main(String[] args) {
		Pool pool = new Pool(3);
		new Producer("p1", 31415, pool).start();
		new Producer("p2", 31415, pool).start();
		new Producer("p3", 31415, pool).start();
		new Consumer("              c1", 31415, pool).start();
		new Consumer("              c2", 31415, pool).start();
		new Consumer("              c3", 31415, pool).start();
	}
}
