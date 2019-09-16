package design_pattern.ReadWriteLock;

//读写锁角色
public class ReadWriteLock {

	private int workingReaders = 0;//正在工作的读线程个数
	private int waitingReaders = 0;//正在等待的读线程个数
	
	private int workingWriters = 0;//正在工作的写线程个数
	private int waitingWriters = 0;//正在等待的写线程个数
	
	private boolean prefer = false;//true表示写入操作优先，false表示读取操作优先
	
	//获取读锁
	public synchronized void readLock() throws Exception {
		waitingReaders++;
		try {
			//有正在工作的写线程、或者写入操作优先的时候有正在等待的写线程
			while (workingWriters > 0 || (prefer && waitingWriters > 0)) {
				wait();
			}	
		} finally {
			waitingReaders--;
		}
		workingReaders++;
		return;
	}
	
	//释放读锁
	public synchronized void readUnlock() {
		workingReaders--;
		prefer = true;//读取操作完成后使写入操作优先
		notifyAll();
		return;
	}
	
	//获取写锁
	public synchronized void writeLock() throws Exception {
		waitingWriters++;
		try {
			//有正在工作的读线程、或者有正在工作的写线程、或者读取操作优先的时候有正在等待的读线程
			while (workingReaders > 0 || workingWriters > 0 || (!prefer && waitingReaders > 0)) {
				wait();
			}	
		} finally {
			waitingWriters--;
		}
		workingWriters++;
		return;
	}
	
	//释放写锁
	public synchronized void writeUnlock() {
		workingWriters--;
		prefer = false;//写入操作完成后使读取操作优先
		notifyAll();
		return;
	}
}
