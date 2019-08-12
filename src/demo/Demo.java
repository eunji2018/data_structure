package demo;

public class Demo {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
//        test7();
    }

    public static void test1() {
        short s1 = 1;
//        s1 = s1 + 1;//错误：右边short类型提升为int，导致类型转换错误
        s1 += 1;//隐式类型转换，可以正确编译
    }


    //整型数据
    public static void test2() {
        //常量池范围：-128 ~ 127
        int i1 = 127;
        Integer i2 = 127;
        Integer i3 = 127;
        Integer i4 = new Integer(127);

        int i5 = 128;
        Integer i6 = 128;
        Integer i7 = 128;
        Integer i8 = new Integer(128);

        System.out.println(i1 == i2);//true：与基本类型比较的是值
        System.out.println(i2 == i3);//true：两个都指向常量池中的同一个常量
        System.out.println(i2 == i4);//false：一个指向常量池，一个指向堆
        System.out.println(i1 == i4);//true：与基本类型比较的是值

        System.out.println(i5 == i6);//true：与基本类型比较的是值
        System.out.println(i6 == i7);//false：两个指向堆中不同的对象
        System.out.println(i6 == i8);//false：两个指向堆中不同的对象
        System.out.println(i5 == i8);//true：与基本类型比较的是值
    }

    public static void test3() {
        String s1 = "ab";
        String s2 = "a" + "b";
        String s3 = "a";
        String s4 = "b";
        String s5 = s3 + s4;
        String s6 = s1.intern();

        System.out.println(s1 == s2);//true
        System.out.println(s2 == s5);//false：一个指向常量池，一个指向堆
        System.out.println(s1 == s6);//true
    }

    public static void test4() {
        String s1 = "aa";
        String s2 = s1.intern();

        String s3 = "b";
        String s4 = "b";
        String s5 = s3 + s4;
        String s6 = s5.intern();

        System.out.println(s1 == s2);//true
        System.out.println(s5 == s6);//false
    }

    //start()与run()的区别
    public static void test5() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());//当前执行线程对象
            }
        };
        Thread thread = new Thread(runnable);
        thread.run();//主线程直接调用
        thread.run();//可以重复调用

        thread.start();//新线程启动并调用
        try {
            Thread.sleep(1000);
        }catch (Exception e){};
        thread.start();//不能重复调用，会抛出非法状态异常
    }


    //synchronized与volatile的区别
    //synchronized具有原子性，volatile不具有原子性
    private static int result = 0;

    private volatile static int count = 0;

    public static void test6() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    count++;
            }
        };
        Thread [] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        try {
            Thread.sleep(5000);
        }catch (Exception e){};
        System.out.println(count);
    }

    public static void test7() {
        Object object = new Object();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    for (int i = 0; i < 100; i++)
                        result++;
                }
            }
        };
        Thread [] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        try {
            Thread.sleep(5000);
        }catch (Exception e){};
        System.out.println(result);
    }


}
