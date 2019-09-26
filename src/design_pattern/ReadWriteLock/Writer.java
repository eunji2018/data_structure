package design_pattern.ReadWriteLock;

import java.util.Random;

//写线程
public class Writer extends Thread {

    private static final Random random = new Random();
    private final Data data;
    private final String filler;
    private int index = 0;
    
    public Writer(Data data, String filler) {
        this.data = data;
        this.filler = filler;
    }
    
    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                data.write(nextchar());//执行写入操作
                Thread.sleep(random.nextInt(500));
            }
        } catch (Exception e) {}
    }
    
    private char nextchar() {
        char c = filler.charAt(index);
        index++;
        if (index == filler.length())
            index = 0;
        return c;
    }
    
}
