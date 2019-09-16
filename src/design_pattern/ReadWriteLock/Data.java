package design_pattern.ReadWriteLock;

public class Data {

	private final char[] buffer;
	private final ReadWriteLock lock = new ReadWriteLock();
	
	public Data(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < size; i++) {
			buffer[i] = '#';
		}
	}

	//读线程读取数据
	public char[] read() throws Exception{
		lock.readLock();//获取读锁
		try {
			return doRead();
		} finally {
			lock.readUnlock();//释放读锁
		}
	}

	//写线程写入数据
	public void write(char c) throws Exception{
		lock.writeLock();//获取写锁
		try {
			doWrite(c);
		} finally {
			lock.writeUnlock();//释放写锁
		}
	}
	
	private char[] doRead() {
		char[] temp = new char[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			temp[i] = buffer[i];
		}
		slowly();
		return temp;
	}
	
	private void doWrite(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			slowly();
		}
	}

	//模拟耗时操作
	private void slowly() {
		try {
			Thread.sleep(50);
		} catch (Exception e) {}
	}
	
}
