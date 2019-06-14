/*
    @Author：eunji
 */
package data_structure;

//双端队列的双向链表实现
//实现了队首、队尾入队与出队，判空，清空的操作
public class DoubleQueue<T> {

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
		}
	}
	
	public DoubleQueue() {
		this.head = null;
		this.tail = null;
		this.length = 0;
	}
	
	//队首入队
	public void enterHead(T t) {
		Node node = new Node(t);
		if(length == 0) {
			tail = node;
		}else {
			head.previous = node;
			node.next = head;
		}
		head = node;
		length++;
		return;
	}
	
	//队尾入队
	public void enterTail(T t) {
		Node node = new Node(t);
		if(length == 0) {
			head = node;
		}else {
			tail.next = node;
			node.previous = tail;
		}
		tail = node;
		length++;
		return;
	}
	
	//队首出队
	public T departHead() {
		if(length == 0)
			return null;
		Node node = head;
		head = head.next;
		if(head == null)//只有一个元素
			tail = null;
		length--;
		return (T)node.data;
	}
	
	//队尾出队
	public T departTail() {
		if(length == 0)
			return null;
		Node node = tail;
		tail = tail.previous;
		if(tail == null)//只有一个元素
			head = null;
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
