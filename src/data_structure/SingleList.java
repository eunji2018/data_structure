/**
 * @Author eunji
 */
package data_structure;

//单向链表的实现
public class SingleList<T> {
    
    private Node head;//头节点
    
    private int length;//链表长度

    private static class Node {
        
        public Object data;
        public Node next;
        
        public Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public SingleList() {
        this.head = new Node(null);//头节点不保存数据
        this.length = 0;
    }
    
    //添加元素（尾部）
    public void insert(T t) {
        Node node = new Node(t);
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = node;
        length++;
        return;
    }
    
    //删除元素（尾部）
    public T remove() {
        if (length == 0)
            return null;
        Node temp = head;
        Node node = temp.next;
        while (node.next != null) {
            temp = node;
            node = node.next;
        }
        temp.next = null;
        length--;
        return (T)node.data;
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
    
    public void print() {
        for (Node temp = head.next; temp != null; temp = temp.next)
            System.out.print(temp.data + " ");
        System.out.println();
        return;
    }
    
    //逆置链表（递归式）
    public void inverse_recursive() {
        if (length == 0)
            return;
        Node temp = inverse(head.next);
        head.next.next = null;//原来的首节点成为新的尾节点
        head.next = temp;//原来的尾节点成为新的首节点
        return;
    }
    
    private static Node inverse(Node node) {
        if (node.next == null)//返回原来的尾节点
            return node;
        Node temp = inverse(node.next);
        node.next.next = node;//逆置此节点后面的节点
        return temp;
    }
    
    //逆置链表（循环式）
    public void inverse() {
        if (length == 0)
            return;
        Node left = null, right = head.next, temp = null;
        while (right != null) {
            temp = right.next;
            right.next = left;//逆置指针
            left = right;
            right = temp;
        }
        head.next = left;//原来的尾节点成为新的首节点
        return;
    }
    
    //倒数第k个元素
    public T reciprocal(int k) {
        if (k < 1 || k > length)
            return null;
        int count = 1;
        Node left = head.next;
        Node right = head.next;
        while (count < k) {
            right = right.next;
            count++;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        return (T)left.data;
    }
    
    //中间元素：链表长度为偶数时，返回左半部最后一个元素
    public T middle() {
        if (length == 0)
            return null;
        Node left = head.next;//慢指针，每次向后走一步
        Node right = head.next;//快指针，每次向后走两步
        while (right.next != null && right.next.next != null) {
            right = right.next.next;
            left = left.next;
        }
        return (T)left.data;
    }

}
