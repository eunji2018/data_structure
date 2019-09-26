/**
 * @Author eunji
 */
package data_structure;

/* 使用两个队列one，other模拟栈
 * 入栈时添加元素到one队列中
 * 出栈时将one队列中除最后一个外的所有元素转移到other队列
 * 将one队列中最后的元素出队返回，交换one，other变量
 * 若one队列为空，则返回null
 * 实现getMin（getMax）功能
 * 另外维护一个min栈，栈顶元素始终是当前数据栈的最小元素
 * 入栈时，若min栈为空，则元素同时压入min栈
 * 否则min栈不为空时，若元素小于或等于min栈顶元素，则元素同时压入min栈
 * 出栈时，若元素等于min栈顶元素，则min出栈
 */
//栈的单向链表实现
public class Stack<T> {
    
    private Node head;//头节点
    
    private int length;//栈的长度
    
    //节点类
    private static class Node {
        
        public Object data;
        public Node next;
        
        public Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public Stack() {
        this.head = new Node(null);//头节点不保存元素
        this.length = 0;
    }
    
    //入栈
    public void push(T t) {
        Node node = new Node(t);
        node.next = head.next;
        head.next = node;
        length++;
        return;
    }
    
    //出栈
    public T pop() {
        if (length == 0)
            return null;
        Node node = head.next;
        head.next = node.next;
        length--;
        return (T)node.data;
    }
    
    //取栈顶
    public T peek() {
        if (length == 0)
            return null;
        return (T)head.next.data;
    }
    
    //判空
    public boolean isEmpty() {
        return length == 0;
    }

    //清空
    public void clear() {
        head.next = null;
        length = 0;
        return;
    }
    
    public int length() {
        return length;
    }

    //打印
    public void print() {
        Node node = head.next;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
        return;
    }
}
