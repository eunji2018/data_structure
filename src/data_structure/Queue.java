/*
    @Author：eunji
 */
package data_structure;

/* 使用两个栈in，out模拟队列
 * 入队时添加元素到in栈中
 * 出队时从out栈中取出元素返回
 * 若out栈为空，则将in栈中所有元素转移到out栈中，出栈返回
 * 若in栈为空，则返回null
 */
//队列的单向链表实现
//实现了入队，出队，判空，清空的操作
public class Queue<T> {
	
	private Node head;//队首节点
	
	private Node tail;//队尾节点
	
	private int length;//队列长度
	
	//节点类
	private static class Node {
		
		public Object data;
		public Node next;
		
		public Node(Object data) {
			this.data = data;
		}
	}
	
	public Queue() {
		this.head = null;
		this.tail = null;
		this.length = 0;
	}
	
	//入队
	public void enter(T t) {
		Node node = new Node(t);
		if(length == 0) {
			head = node;
		}else {
			tail.next = node;
		}
		tail = node;
		length++;
		return;
	}
	
	//出队
	public T depart() {
		if(length == 0)
			return null;
		Node node = head;
		head = head.next;
		if(head == null)//只有一个元素
			tail = null;
		length--;
		return (T)node.data;
	}
	
	//判空
	public boolean isEmpty() {
		return length == 0;
	}
	
	//清空
	public void clear() {
		head = null;
		tail = null;
		length = 0;
		return;
	}
	
	public int length() {
		return length;
	}
	
	//打印
	public void print() {
		Node node = head;
		System.out.println();
		while(node != null) { 
			System.out.print(node.data + " ");
			node = node.next;
		}
		return;
	}

}
