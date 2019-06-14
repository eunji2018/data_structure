package design_pattern.ActiveObject;

//执行结果
public abstract class Result<T> {

	public abstract T getValue();
}

class Future<T> extends Result<T> {
	
	private Result<T> result;
	private boolean ready;
	
	public synchronized void setResult(Result<T> result) {
		if(ready)
			return;
		this.result = result;
		this.ready = true;
		notifyAll();
	}
	
	@Override
	public synchronized T getValue() {
		while(!ready) {
			try {
				wait();
			} catch (Exception e) {}
		}
		return result.getValue();
	}
}

class Real<T> extends Result<T> {

	private final T value;
	
	public Real(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
}




