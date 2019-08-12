/**
 * @Author eunji
 */
package data_structure;

//平衡查找树的实现：具有平衡性和有序性
//在空节点里添加元素，自上向下生长
//添加元素时可能导致不平衡，有四种情形
//删除元素时可能导致不平衡，有六种情形
public class AVL {

	private TreeNode root;//根节点
	
	//树节点类
	private static class TreeNode {

		public Comparable comparable;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(Comparable comparable) {
			this.comparable = comparable;
			this.left = null;
			this.right = null;
		}
	}

	public AVL() {
		this.root = null;
	}
	
	//left-left旋转：将节点变为右子节点，左子节点变为父节点
	private static TreeNode leftLeft(TreeNode node) {
		TreeNode temp = node.left;
		node.left = temp.right;
		temp.right = node;
		return temp;
	}
	
	//left-right旋转
	private static TreeNode leftRight(TreeNode node) {
		node.left = rightRight(node.left);
		node = leftLeft(node);
		return node;
	}
	
	//right-left旋转
	private static TreeNode rightLeft(TreeNode node) {
		node.right = leftLeft(node.right);
		node = rightRight(node);
		return node;
	}
	
	//right-right旋转：将节点变为左子节点，右子节点变为父节点
	private static TreeNode rightRight(TreeNode node) {
		TreeNode temp = node.right;
		node.right = temp.left;
		temp.left = node;
		return temp;
	}
	
	//查找元素，返回节点
	private static TreeNode select(TreeNode node, Comparable comparable) {
		if(node == null)
			return null;
		if(node.comparable.compareTo(comparable) == 0)
			return node;
		return comparable.compareTo(node.comparable) < 0
		       ? select(node.left, comparable) : select(node.right, comparable);
	}
	
	/* 非递归式前序遍历
	 * 1.使当前节点指向根节点，当前节点入栈
	 * 2.若栈非空，则使当前节点指向出栈的栈顶节点，遍历节点，将右左子节点依次入栈
	 * 3.重复第2步，直到栈为空
	 */
	public void preOrder() {
		if (root != null) {
			TreeNode node = root;
			Stack<TreeNode> stack = new Stack<>();
			stack.push(node);
			System.out.println();
			while(!stack.isEmpty()) {//栈非空
				node = stack.pop();
				System.out.print(node.comparable + " ");//遍历节点
				if (node.right != null) 
					stack.push(node.right);
				if (node.left != null) 
					stack.push(node.left);
			}
		}
		return;
	}
	
	/* 非递归式中序遍历
	 * 1.使当前节点指向根节点
	 * 2.若当前节点非空，则入栈，使当前节点指向其左子节点
	 * 3.重复第2步，直到当前节点为空，若栈非空，则使当前节点指向出栈的栈顶节点，遍历节点，使当前节点指向其右子节点
	 * 4.重复第3步，直到栈为空
	 */
	public void inOrder() {
		if (root != null) {
			TreeNode node = root;
			Stack<TreeNode> stack = new Stack<>();
			System.out.println();
			while(!stack.isEmpty() || node != null) {
				if (node != null) {//当前节点非空
					stack.push(node);
					node = node.left;
				} else {//栈非空
					node = stack.pop();
					System.out.print(node.comparable + " ");//遍历节点
					node = node.right;
				}
			}
		}
		return;
	}
	
	/* 非递归式后序遍历（两个栈）
	 * 1.使当前节点指向根节点，当前节点入栈one
	 * 2.若栈one非空，则使当前节点指向出栈的栈顶节点，当前节点入栈other，将左右子节点依次入栈one
	 * 3.重复第2步，直到栈one为空
	 * 4.若栈other非空，则使当前节点指向出栈的栈顶节点，遍历节点
	 * 5.重复第4步，直到栈other为空
	 */
	public void postOrder() {
		if (root != null) {
			TreeNode node = root;
			Stack<TreeNode> one = new Stack<>();
			Stack<TreeNode> other = new Stack<>();
			one.push(node);
			while(!one.isEmpty()) {//栈one非空
				node = one.pop();
				other.push(node);
				if (node.left != null) 
					one.push(node.left);
				if (node.right != null) 
					one.push(node.right);
			}
			System.out.println();
			while(!other.isEmpty()) {//栈other非空
				System.out.print(other.pop().comparable + " ");//遍历节点
			}
		}
		return;
	}
	
	//层次遍历（从左到右，队列）
	//1.使当前节点指向根节点，当前节点入队，虚拟节点入队
	//2.若队非空，则使当前节点指向出队的队首节点
	//（1）当前节点是虚拟节点，若队为空，则结束，若队非空，则当前节点入队
	//（2）当前节点不是虚拟节点，遍历节点，将左右子节点依次入队
	//3.重复第2步，直到队为空
	public void hierarchy() {
		if(root == null)
			return;
		TreeNode node = root;
		Queue<TreeNode> queue = new Queue<>();
		queue.enter(node);
		queue.enter(new TreeNode(null));//虚拟节点
		System.out.println();
		while(!queue.isEmpty()) {//队非空
			node = queue.depart();
			if (node.comparable == null) {//虚拟节点
				if (queue.isEmpty())//队为空
					break;
				queue.enter(node);
				System.out.println();
			}else {
				System.out.print(node.comparable + " ");//遍历节点
				if(node.left != null)
					queue.enter(node.left);
				if(node.right != null)
					queue.enter(node.right);
			}
		}
		return;
	}
	
	//层次遍历（左右交替，两个栈）
	public void hierarchy_inturn_stack() {
		if(root == null)
			return;
		TreeNode node = root;
		Stack<TreeNode> left = new Stack<>();//保存从左到右遍历的节点
		Stack<TreeNode> right = new Stack<>();//保存从右到左遍历的节点
		left.push(node);//先从左到右
		System.out.println();
		while(!left.isEmpty() || !right.isEmpty()) {
			if(!left.isEmpty()) {//从左到右遍历
				while(!left.isEmpty()) {
					node = left.pop();
					System.out.print(node.comparable + " ");//遍历节点
					if(node.left != null)
						right.push(node.left);
					if(node.right != null)
						right.push(node.right);
				}
				System.out.println();
			}else {//从右到左遍历
				while(!right.isEmpty()) {
					node = right.pop();
					System.out.print(node.comparable + " ");//遍历节点
					if(node.right != null)
						left.push(node.right);
					if(node.left != null)
						left.push(node.left);
				}
				System.out.println();
			}
		}
		return;
	}

	//层次遍历（左右交替，双端队列）
    public void hierarchy_inturn_queue() {
	    if (root == null)
	        return;
	    TreeNode node = root;
	    DoubleQueue<TreeNode> queue = new DoubleQueue<>();
	    queue.enterHead(node);//先从左到右
	    int left = 1, right = 0;//left表示从左到右遍历的节点的数目，right表示从右到左遍历的节点的数目
        System.out.println();
        while (left != 0 || right != 0) {
            if (left != 0) {//从左到右遍历，元素从队首出队
                while (left != 0) {
                    node = queue.departHead();
                    System.out.print(node.comparable + " ");//遍历节点
                    left--;
                    //左右子节点依次从队尾入队，更新right
                    if (node.left != null) {
                        queue.enterTail(node.left);
                        right++;
                    }
                    if (node.right != null) {
                        queue.enterTail(node.right);
                        right++;
                    }
                }
				System.out.println();
            }else {//从右到左遍历，元素从队尾出队
                while (right != 0) {
                    node = queue.departTail();
                    System.out.print(node.comparable + " ");//遍历节点
                    right--;
                    //右左子节点依次从队首入队，更新left
                    if (node.right != null) {
                        queue.enterHead(node.right);
                        left++;
                    }
                    if (node.left != null) {
                        queue.enterHead(node.left);
                        left++;
                    }
                }
				System.out.println();
            }
        }
        return;
    }
	
	//最近公共父节点
	public Comparable common(Comparable comparable1, Comparable comparable2) {
		TreeNode node1 = select(root, comparable1);
		TreeNode node2 = select(root, comparable2);
		if(node1 == null || node2 == null)
			return -1;
		return common(root, node1, node2).comparable;
	}
	
	//当以node为根的子树不包含两个节点时，返回null
	//只包含一个节点时，返回此节点；包含两个节点时，返回最近公共父节点
	private static TreeNode common(TreeNode node, TreeNode node1, TreeNode node2) {
		if(node == null || node == node1 || node == node2)
			return node;
		TreeNode left = common(node.left, node1, node2);
		TreeNode right = common(node.right, node1, node2);
		if(left != null && right != null)
			return node;
		return left == null ? right : left;
	}
	
}
