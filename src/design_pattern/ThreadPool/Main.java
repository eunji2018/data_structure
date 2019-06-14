package design_pattern.ThreadPool;

public class Main {

	public static void main(String[] args) {
		Channel channel = new Channel(5);
		channel.start();
		new Client("a", channel).start();
		new Client("    b", channel).start();
		new Client("        c", channel).start();
	}
}
