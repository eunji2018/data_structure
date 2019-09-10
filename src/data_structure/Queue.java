/**
 * @Author eunji
 */
package data_structure;

/* 使用两个栈in，out模拟队列
 * 入队时添加元素到in栈中
 * 出队时从out栈中取出元素返回
 * 若out栈为空，则将in栈中所有元素转移到out栈中，出栈返回
 * 若in栈为空，则返回null
 */
//队列的双向链表实现
public class Queue<T> {
	
	private Node head;//队首节点
	
	private Node tail;//队尾节点
	
	private int length;//队列长度
	
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
	
	public Queue() {
		this.head = new Node(null);//队首节点不保存元素
		this.tail = new Node(null);//队尾节点不保存元素
		this.head.next = tail;
		this.tail.previous = head;
		this.length = 0;
	}
	
	//入队
	public void enter(T t) {
		Node node = new Node(t);
		node.previous = tail.previous;
		tail.previous.next = node;
		tail.previous = node;
		node.next = tail;
		length++;
		return;
	}
	
	//出队
	public T depart() {
		if(length == 0)
			return null;
		Node node = head.next;
		head.next = node.next;
		node.next.previous = head;
		length--;
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
		Node node = head.next;
		while(node != tail) {
			System.out.print(node.data + " ");
			node = node.next;
		}
        System.out.println();
        return;
	}

}
