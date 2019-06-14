package design_pattern.Future;

public class Host {

	public Result request(int count, char c) {
		System.out.println("request start " + c);
		final Future future = new Future();
		new Thread() {
			public void run() {
				Real real = new Real(count, c);
				future.setReal(real);
			}; 
		}.start();
		System.out.println("request end " + c);
		return future;
	}
}
