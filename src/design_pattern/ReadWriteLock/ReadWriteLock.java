package design_pattern.ReadWriteLock;

public class ReadWriteLock {

	private int workingReaders = 0;//工作的读线程
	private int waitingReaders = 0;//等待的读线程
	
	private int workingWriters = 0;//工作的写线程
	private int waitingWriters = 0;//等待的写线程
	
	private boolean prefer = false;//写线程优先
	
	//申请读锁
	public synchronized void readLock() throws Exception {
		waitingReaders++;
		try {
			while(workingWriters > 0 || (prefer && waitingWriters > 0)) {
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
		prefer = true;
		notifyAll();
		return;
	}
	
	//申请写锁
	public synchronized void writeLock() throws Exception {
		waitingWriters++;
		try {
			while(workingReaders > 0 || workingWriters > 0 || (!prefer && waitingReaders > 0)) {
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
		prefer = false;
		notifyAll();
		return;
	}
}
