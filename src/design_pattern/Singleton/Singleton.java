package design_pattern.Singleton;

public class Singleton{}

//饿汉式
//类加载时完成实例化，避免线程同步的问题
//缺点：立即实例化对象，造成内存消耗
class Singleton1 {

    private static final Singleton1 instance = new Singleton1();
    
    private Singleton1() {};
    
    public static Singleton1 getInstance() {
        return instance;
    }
}

//懒汉式
//延迟加载，线程不安全
//优化：可以使用synchronized修饰getInstance()方法实现安全访问
//缺点：效率低，每次获取单例对象时都需要进行同步，开销大
class Singleton2{
    
    private static Singleton2 instance;
    
    private Singleton2() {};
    
    public static /* synchronized */ Singleton2 getInstance() {
        if (instance == null) 
            instance = new Singleton2();
        return instance;
    }
}

//双重检查锁
//线程不安全，存在指令重排序
//优化：可以使用volatile修饰instance属性禁止重排序
class Singleton3{
    
    private static /* volatile */ Singleton3 instance;
    
    private Singleton3() {};
    
    public static Singleton3 getInstance() {
        if (instance == null) {//1.第一个线程在此判断为空，进入下面的代码块
                               //4.第一个线程在初始化单例对象时，第二个线程在此判断instance非空，直接返回
                               //但是此时还未初始化完成，所以第二个线程可能会访问到单例对象未初始化的属性
            synchronized (Singleton3.class) {
                if (instance == null)//2.第一个线程在此再次判断为空，进入下面的代码块
                    instance = new Singleton3();//3.第一个线程在此实例化单例对象，此语句分为三步
                                                //申请新的内存，初始化单例对象，使引用指向新的内存
                                                //第一个线程在此可能重排序，使引用在初始化完成之前指向了新的内存，即此时instance非空
            }
        }
        return instance;
    }
}

//静态内部类
//依靠JVM类加载机制保证线程安全性
//优点：延迟加载，线程安全，效率高，结合了饿汉式与懒汉式的优点
class Singleton4{
    
    private Singleton4() {};
    
    private static class Inner{
        private static final Singleton4 INSTANCE = new Singleton4();
    }
    
    public static Singleton4 getInstance() {
        return Inner.INSTANCE;
    }
}

//枚举式
//避免多线程同步的问题，可以防止反序列化重新创建新的对象
enum Singleton5{
    INSTANCE;
}