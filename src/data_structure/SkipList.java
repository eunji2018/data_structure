/*
    @Author：eunji
 */
package data_structure;

import java.util.Random;

//跳跃表的实现
//元素有序且不重复
//实现了查找，添加，删除元素，判空，清空的操作
public class SkipList {

	private Node head;//顶层头节点
	
	private int rate;//相邻两层元素个数的比例
	
	private int level;//跳跃表层数
	
	private int length;//底层节点个数
	
	private int size;//所有层节点个数
	
	private Random random;//随机数
	
	private Stack<Node> stack;//保存查询时遍历的节点
	
	//节点类
	private static class Node {
		
		private int data;
		private Node right, down;//右边节点，下层节点
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	public SkipList(int rate, int level) {
		this.rate = rate;
		this.level = level;
		this.length = 0;
		this.size = 0;
		this.random = new Random();
		this.stack = new Stack<Node>();
		head = new Node(-1);//头节点的值默认为-1
		Node temp = head;
		for(int i = 1; i < level; i++) {
			temp.down = new Node(-1);
			temp = temp.down;
		}
	}
	
	//判空
	public boolean isEmpty() {
		return length == 0;
	}
	
	//清空
	public void clear() {
		Node temp = head;
		while(temp != null) {
			temp.right = null;
			temp = temp.down;
		}
		length = 0;
		size = 0;
		return;
	}
	
	public int length() {
		return length;
	}
	
	public int size() {
		return size;
	}
	
	//查询元素
    //自顶向下，返回底层【小于】给定值的最大的节点，包含头节点
	private Node search(int data) {
		stack.clear();
		Node temp = head;//从顶层开始
		while(true) {
			while(temp.right != null && temp.right.data < data)//查找当前层【小于】给定值的最大的节点 
				temp = temp.right;
			if(temp.down != null) {
				stack.push(temp);//stack保存遍历路径中每一层最右边的节点，除底层外
				temp = temp.down;//转到下一层
			}else {
				break;//找到底层的节点
			}
		}
		return temp;
	}
	
	//添加元素
    //若元素已存在，则返回，保证无重复元素
	public void insert(int data) {
		Node temp = search(data);
		if(temp.right != null && temp.right.data == data)//元素已存在
			return;
		Node node = new Node(data);
		Node other = null;
		//根据随机数，自底向上添加每层的新节点
		while(true) {
			node.right = temp.right;
			temp.right = node;//当前层添加完毕
			size++;
			if(random.nextInt(rate) != 0 || stack.isEmpty())
				break;
			//若随机数为0且还未到顶层，则向上层添加元素
			temp = stack.pop();
			other = node;
			node = new Node(data);
			node.down = other;
		}
		length++;
		return;
	}
	
	//删除元素
    //若元素不存在，则返回，否则删除所有层中包含的元素
	public void delete(int data) {
		Node temp = search(data);
		if(temp.right == null || temp.right.data != data)//元素不存在
			return;
		while(true) {
			if(temp.right == null || temp.right.data != data) //当前层的元素不存在
				break;
			//从底层开始，依次删除每层的元素
			temp.right = temp.right.right;
			size--;
			if(!stack.isEmpty()) {
				temp = stack.pop();//转到上一层
			}else {
				break;//到达顶层
			}
		}
		length--;
		return;
	}
	
	//打印：自顶向下
	public void print() {
		Node node = head;
		Node temp = node;
		int i = level;
		System.out.println();
		while(temp != null) {
			System.out.print("level " + i-- + " : ");
			while(temp.right != null) {
				temp = temp.right;
				System.out.print(temp.data + " ");
			}
			temp = node.down;
			node = temp;
			System.out.println();
		}
		return;
	}
	
	//遍历：自底向上，打印路径中每层最右节点
	public void trace(int data) {
		Node temp = search(data);
		System.out.println();
		System.out.print(temp.data + " ");
		while(!stack.isEmpty()) 
			System.out.print(stack.pop().data + " ");
		return;
	}
	
}
