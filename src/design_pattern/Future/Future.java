package design_pattern.Future;

public class Future implements Result {

	private Real real = null;
	private boolean ready = false;
	
	public synchronized void setReal(Real real) {
		if (ready) 
			return;
		this.real = real;
		this.ready = true;
		notifyAll();
	}
	
	public synchronized String getContent() {
		while (!ready) {
			try {
				wait();
			} catch (Exception e) {}
		}
		return real.getContent();
	}
	
}
