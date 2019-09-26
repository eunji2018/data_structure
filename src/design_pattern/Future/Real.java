package design_pattern.Future;

//真实结果角色
public class Real implements Result {

    private String content;
    
    public Real(int count, char c) {
        System.out.println("        create real start " + c);
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(100);//表示构造对象需要一定的时间
            } catch (Exception e) {}
        }
        this.content = new String(buffer);
        System.out.println("        create real end " + c);
    }
    
    @Override
    public String getContent() {
        return content;
    }
}
