package design_pattern.ReadWriteLock;

public class Data {

	private final char[] buffer;
	private final ReadWriteLock lock = new ReadWriteLock();
	
	public Data(int size) {
		this.buffer = new char[size];
		for(int i = 0; i < size; i++) {
			buffer[i] = '#';
		}
	}
	
	public char[] read() throws Exception{
		lock.readLock();
		try {
			return doRead();
		} finally {
			lock.readUnlock();
		}
	}
	
	public void write(char c) throws Exception{
		lock.writeLock();
		try {
			doWrite(c);
		} finally {
			lock.writeUnlock();
		}
	}
	
	private char[] doRead() {
		char[] temp = new char[buffer.length];
		for(int i = 0; i < buffer.length; i++) {
			temp[i] = buffer[i];
		}
		slowly();
		return temp;
	}
	
	private void doWrite(char c) {
		for(int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			slowly();
		}
	}
	
	private void slowly() {
		try {
			Thread.sleep(50);
		} catch (Exception e) {}
	}
	
}
