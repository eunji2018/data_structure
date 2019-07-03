/**
 * @Author eunji
 */
package demo;

//死锁演示
//cmd查看：jps获得虚拟机进程id，jstack pid打印堆栈信息
//样例：
//"Thread-1" #12 prio=5 os_prio=0 tid=0x000000001c23e800 nid=0x2ec0 waiting for monitor entry [0x000000001d0df000]
//		   java.lang.Thread.State: BLOCKED (on object monitor)
//		        at eunji.DeadLock$2.run(DeadLock.java:50)
//		        - waiting to lock <0x00000000d5fbee58> (a java.lang.Object)
//		        - locked <0x00000000d5fbee68> (a java.lang.Object)
//
//"Thread-0" #11 prio=5 os_prio=0 tid=0x000000001c22f000 nid=0x3704 waiting for monitor entry [0x000000001cfde000]
//		   java.lang.Thread.State: BLOCKED (on object monitor)
//		        at eunji.DeadLock$1.run(DeadLock.java:34)
//		        - waiting to lock <0x00000000d5fbee68> (a java.lang.Object)
//		        - locked <0x00000000d5fbee58> (a java.lang.Object)

public class DeadLock {

	private static final Object one = new Object();
	private static final Object two = new Object();
	
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				super.run();
				synchronized (one) {//先获取对象one的锁
					System.out.println(Thread.currentThread().getName() + " get one");
					try {
						Thread.sleep(100);//休眠100ms
					} catch (Exception e) {}
					synchronized (two) {//再获取对象two的锁
						System.out.println(Thread.currentThread().getName() + " get two");
					}
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				super.run();
				synchronized (two) {//先获取对象two的锁
					System.out.println(Thread.currentThread().getName() + " get two");
					try {
						Thread.sleep(100);//休眠100ms
					} catch (Exception e) {}
					synchronized (one) {//再获取对象one的锁
						System.out.println(Thread.currentThread().getName() + " get one");
					}
				}
			}
		}.start();

	}
}
