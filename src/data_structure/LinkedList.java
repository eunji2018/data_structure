/*
    @Author：eunji
 */
package data_structure;

//循环双向链表的实现
//在每个操作后都保持双向循环结构
//实现了添加，删除元素，判空，清空的操作
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
		}
	}
	
	public LinkedList() {
		this.head = new Node(null);//头节点不保存数据
		this.tail = head;
		this.head.previous = tail;
		this.tail.next = head;
		this.length = 0;		
	}
	
	//添加元素（尾部）
	public void insert(T t) {
		Node temp = new Node(t);
		tail.next = temp;
		temp.next = head;
		head.previous = temp;
		temp.previous = tail;
		tail = temp;
		length++;
		return;
	}
	
	//删除元素（尾部）
	public T remove() {
		if (length == 0) 
			return null;
		Node temp = tail;
		tail = tail.previous;
		tail.next = head;
		head.previous = tail;
		length--;
		return (T)temp.data;
	}
	
	//判空
	public boolean isEmpty() {
		return length == 0;
	}
	
	//清空
	public void clear() {
		tail = head;
		head.previous = tail;
		tail.next = head;
		length = 0;
		return;
	}
	
	public int length() {
		return length;
	}
	
	//打印
	public void print() {
		System.out.println();
		for(Node temp = head.next; temp != head; temp = temp.next) 
			System.out.print(temp.data + " ");
	}	
	
}

