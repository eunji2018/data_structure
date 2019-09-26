/**
 * @Author eunji
 */
package data_structure;

//双向链表的实现
public class LinkedList<T> {
	
    private Node head;//头节点
	
    private Node tail;//尾节点
	
    private int length;//链表长度
	
    //节点类
    private static class Node {
		
        public Object data;
        public Node previous;
        public Node next;
		
        public Node(Object data) {
            this.data = data;
            this.previous = null;
            this.next = null;
        }
    }
	
    public LinkedList() {
        this.head = new Node(null);//头节点不保存数据
        this.tail = new Node(null);//尾节点不保存数据
        this.head.next = tail;
        this.tail.previous = head;
        this.length = 0;
    }

    //添加元素（头部）
    public void insertHead(T t) {
        Node node = new Node(t);
        node.next = head.next;
        head.next.previous = node;
        head.next = node;
        node.previous = head;
        length++;
        return;
    }

    //添加元素（尾部）
    public void insertTail(T t) {
        Node node = new Node(t);
        node.previous = tail.previous;
        tail.previous.next = node;
        tail.previous = node;
        node.next = tail;
        length++;
        return;
    }

    //添加元素（指定位置）
    public void insert(T t, int index) {
        Node node = new Node(t);
        Node temp = head.next;
        while (index > 1) {
            if (temp == tail)
                break;
            temp = temp.next;
            index--;
        }
        node.previous = temp.previous;
        temp.previous.next = node;
        temp.previous = node;
        node.next = temp;
        length++;
        return;
    }

    //删除元素（头部）
    public T removeHead() {
        if (length == 0)
            return null;
        Node node = head.next;
        head.next = node.next;
        node.next.previous = head;
        length--;
        return (T)node.data;
    }
	
    //删除元素（尾部）
    public T removeTail() {
        if (length == 0)
            return null;
        Node node = tail.previous;
        tail.previous = node.previous;
        node.previous.next = tail;
        length--;
        return (T)node.data;
    }

    //删除元素（指定位置）
    public T remove(int index) {
        if (length == 0)
            return null;
        Node node = head.next;
        while (index > 1) {
            if (node == tail)
                break;
            node = node.next;
            index--;
        }
        if (node == tail)
            node = node.previous;
        node.previous.next = node.next;
        node.next.previous = node.previous;
        length--;
        return (T)node.data;
    }

    //删除指定元素（第一个）
    public T removeFirst(T t) {
        Node node = head.next;
        while (node != tail) {
            if (node.data.equals(t))
                break;
            node = node.next;
        }
        if (node != tail) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            length--;
        }
        return (T)node.data;
    }

    //删除指定元素（最后一个）
    public T removeLast(T t) {
        Node node = tail.previous;
        while (node != head) {
            if (node.data.equals(t))
                break;
            node = node.previous;
        }
        if (node != head) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            length--;
        }
        return (T)node.data;
    }

    //判空
    public boolean isEmpty() {
        return length == 0;
    }
	
    //清空
    public void clear() {
        head.next = tail;
        tail.previous = head;
        length = 0;
        return;
    }
	
    public int length() {
        return length;
    }
	
    //打印
    public void print() {
        for (Node node = head.next; node != tail; node = node.next)
            System.out.print(node.data + " ");
        System.out.println();
        return;
    }
	
}
