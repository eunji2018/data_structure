/**
 * @Author eunji
 */
package data_structure;

/* 红黑树的实现
 * 等价于2-3查找树，3-节点表示为由一条红色左连接相连的两个2-节点
 * 2-3查找树：由包含一个元素的节点（2-节点）和包含两个元素的节点（3-节点）组成
 * 在叶子节点里添加元素，自下向上生长
 * 红黑树性质：
 * 节点是黑色或者红色（指向此节点的连接的颜色），根节点保持黑色
 * 红色节点都是左子节点且不能连续，红连接都是左连接
 * 对于每个节点，从此节点到所有叶子节点的路径上的黑色节点数量相同
 * 添加元素的情形，新节点默认为红色：
 * 向2-节点添加元素：产生红色左连接（无须调整）或红色右连接（需要左旋）
 * 向3-节点添加元素，3-节点中已有一条红色左连接：
 * 新节点的值最大，产生一条新的红色右连接，需要对两个子节点和根节点变色（子节点变为黑色，根节点变为红色）
 * 新节点的值最小，产生一条新的红色左连接，需要先对根节点右旋，转换为上一种情形，再变色
 * 新节点的值居中，产生一条新的红色右连接，需要先对左子节点左旋，转换为上一种情形，再对根节点右旋，变色
 * 删除元素的情形：
 * 
 * 
*/
public class RBT_1 {

	private TreeNode root;

	private int size;

	//节点类
	private static class TreeNode {

		public Comparable comparable;
		public TreeNode left;
		public TreeNode right;
		public boolean color;//红节点为true，黑节点为false
		
		public TreeNode(Comparable comparable, boolean color) {
			this.comparable = comparable;
			this.color = color;
			this.left = null;
			this.right = null;
		}
	}

	public RBT_1() {
		this.root = null;
		this.size = 0;
	}
	
	public int size() {
		return size;
	}
	
	//判定节点颜色
	private static boolean color(TreeNode node) {
		return node == null ? false : node.color;
	}
	
	//左旋：将根节点与右子节点交换角色，实现将一个红色右连接转换为红色左连接
	private static TreeNode rotateLeft(TreeNode node) {
		TreeNode temp = node.right;
		node.right = temp.left;
		temp.left = node;
		temp.color = node.color;
		node.color = true;
		return temp;
	}
	
	//右旋：将根节点与左子节点交换角色，实现将一个红色左连接转换为红色右连接
	private static TreeNode rotateRight(TreeNode node) {
		TreeNode temp = node.left;
		node.left = temp.right;
		temp.right = node;
		temp.color = node.color;
		node.color = true;
		return temp;
	}
	
	//变色：子节点变为黑色，根节点变为红色
	private static void convert(TreeNode node) {
		node.color = true;
		node.left.color = false;
		node.right.color = false;
		return;
	}
	
	//添加元素
	public void insert(Comparable comparable) {
		root = insert(root, comparable);
		root.color = false;//根节点保持黑色
		size++;
		return;
	}
	
	private static TreeNode insert(TreeNode node, Comparable comparable) {
		if(node == null)//找到添加的位置
			return new TreeNode(comparable, true);//新节点都为红色
		if(comparable.compareTo(node.comparable) < 0) {
			node.left = insert(node.left, comparable);
		}else {
			node.right = insert(node.right, comparable);
		}
		//调整节点
		if(color(node.right) && !color(node.left))//左子节点为黑，右子节点为红，对父节点左旋 
			node = rotateLeft(node);
		if(color(node.left) && color(node.left.left))//左子节点及其左子节点都为红，对父节点右旋
			node = rotateRight(node);
		if(color(node.left) && color(node.right))//左右子节点都为红，对父子节点变色
			convert(node);
		return node;
	}

//	private static TreeNode getMin(TreeNode node) {
//		if(node == null)
//			return null;
//		if(node.left == null)
//			return node;
//		return getMin(node.left);
//	}
//
//	private static TreeNode getMax(TreeNode node) {
//		if(node == null)
//			return null;
//		if(node.right == null)
//			return node;
//		return getMax(node.right);
//	}
	
	
	//删除
	
	//删除元素

	//递归遍历
	public void preTraverse() {
		System.out.println();
		preTraverse(root);
		return;
	}
	
	public void inTraverse() {
		System.out.println();
		inTraverse(root);
		return;
	}
	
	public void postTraverse() {
		System.out.println();
		postTraverse(root);
		return;
	}
	
	//前序遍历
	private static void preTraverse(TreeNode node) {
		if(node == null)
			return;
		System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));
		preTraverse(node.left);
		preTraverse(node.right);
	}
	
	//中序遍历
	private static void inTraverse(TreeNode node) {
		if(node == null)
			return;
		inTraverse(node.left);
		System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));
		inTraverse(node.right);
	}
	
	//后序遍历
	private static void postTraverse(TreeNode node) {
		if(node == null)
			return;
		postTraverse(node.left);
		postTraverse(node.right);
		System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));
	}
}
