/**
 * @Author eunji
 */
package data_structure;

//双端队列的双向链表实现
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
			this.previous = null;
			this.next = null;
		}
	}
	
	public DoubleQueue() {
		this.head = new Node(null);//队首节点不保存元素
		this.tail = new Node(null);//队尾节点不保存元素
		this.head.next = tail;
		this.tail.previous = head;
		this.length = 0;
	}
	
	//队首入队
	public void enterHead(T t) {
		Node node = new Node(t);
		node.next = head.next;
		head.next.previous = node;
		head.next = node;
		node.previous = head;
		length++;
		return;
	}
	
	//队尾入队
	public void enterTail(T t) {
		Node node = new Node(t);
		node.previous = tail.previous;
		tail.previous.next = node;
		tail.previous = node;
		node.next = tail;
		length++;
		return;
	}
	
	//队首出队
	public T departHead() {
		if(length == 0)
			return null;
		Node node = head.next;
		head.next = node.next;
		node.next.previous = head;
		length--;
		return (T)node.data;
	}
	
	//队尾出队
	public T departTail() {
		if(length == 0)
			return null;
		Node node = tail.previous;
		tail.previous = node.previous;
		node.previous.next = tail;
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
