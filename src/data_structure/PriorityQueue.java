/**
 * @Author eunji
 */
package data_structure;

//优先队列的有序单向链表实现
//元素有序且可以重复
//优化：跳跃表实现或堆实现
//comparable1.compareTo(comparable2) < 0 表示comparable1优先于comparable2
//Java八种数据类型都实现了Comparable接口，整型数据较小的优先，字符型数据字典序较前的优先
public class PriorityQueue {

	private Node head;//头节点
	
	private int length;//队列长度

	private final boolean order;//true表示正序，false表示逆序
	
	//节点类
	private static class Node {
		
		public Comparable comparable;
		public Node next;
		
		public Node(Comparable comparable) {
			this.comparable = comparable;
			this.next = null;
		}
	}
	
	public PriorityQueue(boolean order) {
		this.head = new Node(null);//头节点不保存元素
		this.length = 0;
		this.order = order;
	}
	
	//正序时查找优先级【高于给定元素且最低】的节点，包含头节点
	//逆序时查找优先级【低于给定元素且最高】的节点，包含头节点
	private Node search(Comparable comparable) {
		Node temp = head;
		while(temp.next != null) {
			if(order && temp.next.comparable.compareTo(comparable) >= 0)
				break;
			if (!order && temp.next.comparable.compareTo(comparable) <= 0)
				break;
			temp = temp.next;
		}
		return temp;
	}
	
	//入队
	public void enter(Comparable comparable) {
		Node temp = search(comparable);
		Node node = new Node(comparable);
		node.next = temp.next;
		temp.next = node;
		length++;
		return;
	}
	
	//出队
	public Comparable depart() {
		if(length == 0)
			return null;
		Node node = head.next;
		head.next = node.next;
		length--;
		return node.comparable;
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
		System.out.println();
		while(node != null) {
			System.out.print(node.comparable + " ");
			node = node.next;
		}
		return;
	}
}
