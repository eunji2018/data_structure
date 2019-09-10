/**
 * @Author eunji
 */
package data_structure;

/* 红黑树的第一种实现
 * 等价于2-3查找树，2-节点表示为一个黑色节点，3-节点表示为一个黑色节点及其红色左子节点
 * 2-3查找树：由包含一个元素的节点（2-节点）和包含两个元素的节点（3-节点）组成
 * 在叶子节点里添加元素，自下向上生长
 * 红黑树性质：
 * （1）节点为黑色或者红色，根节点保持黑色
 * （2）红色节点都是左子节点且不能连续
 * （3）对于每个节点，从此节点到所有叶子节点的路径上的黑色节点数量相同
 * 添加元素的情形，新节点默认为红色：
 * 1.向2-节点添加元素：产生红色左子节点（无须调整）或红色右子节点（需要左旋）
 * 2.向3-节点添加元素，3-节点中已有一个红色左子节点：
 * （1）新节点的值最大，产生一个新的红色右子节点，需要对两个子节点和根节点变色（子节点都变为黑色，根节点变为红色）
 * （2）新节点的值最小，产生一个新的红色左子节点，需要先对根节点右旋，转换为上一种情形，再变色
 * （3）新节点的值居中，产生一个新的红色右子节点，需要先对红色左子节点左旋，转换为上一种情形，再对根节点右旋，变色
 * 删除元素的情形：
 * 
 * 
*/
public class RBT_1 {

	private TreeNode root;

	private int size;

	private int height;//红黑树的黑色高度

	//节点类
	private static class TreeNode {

		public Comparable comparable;
		public TreeNode left;
		public TreeNode right;
		public boolean color;//红色节点为true，黑色节点为false
		
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
		this.height = 0;
	}
	
	public int size() {
		return size;
	}

	public int height() {
		return height;
	}
	
	//判定节点颜色，空节点返回黑色
	private static boolean color(TreeNode node) {
		return node == null ? false : node.color;
	}
	
	//左旋：将节点与右子节点交换角色与颜色
	private static TreeNode rotateLeft(TreeNode node) {
		TreeNode temp = node.right;
		node.right = temp.left;
		temp.left = node;
		boolean flag = node.color;
		node.color = temp.color;
		temp.color = flag;
		return temp;
	}
	
	//右旋：将节点与左子节点交换角色与颜色
	private static TreeNode rotateRight(TreeNode node) {
		TreeNode temp = node.left;
		node.left = temp.right;
		temp.right = node;
		boolean flag = node.color;
		node.color = temp.color;
		temp.color = flag;
		return temp;
	}
	
	//变色：子节点都变为黑色，节点变为红色
	private static void convert(TreeNode node) {
		node.color = true;
		node.left.color = false;
		node.right.color = false;
		return;
	}

	//查找元素
	private TreeNode select(Comparable comparable) {
		TreeNode node = root;
		while (node != null) {
			if (comparable.compareTo(node.comparable) == 0)
				break;
			else if (comparable.compareTo(node.comparable) < 0)
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}
	
	//添加元素
	public void insert(Comparable comparable) {
		root = insert(root, comparable);
		if (root.color) {
			root.color = false;
			height++;
		}
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
		//自底向上调整红黑树
		if(!color(node.left) && color(node.right))//左子节点为黑色，右子节点为红色，对父节点左旋
			node = rotateLeft(node);
		if(color(node.left) && color(node.left.left))//左子节点及其左子节点都为红色，对父节点右旋
			node = rotateRight(node);
		if(color(node.left) && color(node.right))//左右子节点都为红色，对父子节点变色
			convert(node);
		return node;
	}

	private static TreeNode getMin(TreeNode node) {
	    while (node.left != null)
	        node = node.left;
	    return node;
	}

	private static TreeNode getMax(TreeNode node) {
	    while (node.right != null)
	        node = node.right;
	    return node;
	}
	
	
	//删除
	
	//删除元素




    //最近公共父节点
    public Comparable common(Comparable comparable1, Comparable comparable2) {
        TreeNode node1 = select(comparable1);
        TreeNode node2 = select(comparable2);
        if(node1 == null || node2 == null)
            return null;
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

	/* 非递归式前序遍历
	 * 1.使当前节点指向根节点，当前节点入栈
	 * 2.若栈非空，则使当前节点指向出栈的栈顶节点，遍历节点，将右左子节点依次入栈
	 * 3.重复第2步，直到栈为空
	 * 总结：根节点入栈，执行循环：若栈非空，则出栈并遍历该节点，将右、左子节点依次入栈
	 */
	public void preOrder() {
		if (root != null) {
			TreeNode node = root;
			Stack<TreeNode> stack = new Stack<>();
			stack.push(node);
			while(!stack.isEmpty()) {//栈非空
				node = stack.pop();
				System.out.print(node.comparable + " ");//遍历节点
				if (node.right != null)
					stack.push(node.right);
				if (node.left != null)
					stack.push(node.left);
			}
            System.out.println();
        }
		return;
	}

	/* 非递归式中序遍历
	 * 1.使当前节点指向根节点
	 * 2.若当前节点非空，则入栈，使当前节点指向其左子节点
	 * 3.重复第2步，直到当前节点为空，若栈非空，则使当前节点指向出栈的栈顶节点，遍历节点，使当前节点指向其右子节点
	 * 4.重复第3步，直到栈为空
	 * 总结：根节点入栈，执行循环：1.每个节点入栈后，左子节点立即入栈 2.出栈并遍历该节点后，右子节点立即入栈
	 */
	public void inOrder() {
		if (root != null) {
			TreeNode node = root;
			Stack<TreeNode> stack = new Stack<>();
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
            System.out.println();
        }
		return;
	}

	/* 非递归式后序遍历（两个栈）
	 * 1.使当前节点指向根节点，当前节点入栈one
	 * 2.若栈one非空，则使当前节点指向出栈的栈顶节点，当前节点入栈other，将左右子节点依次入栈one
	 * 3.重复第2步，直到栈one为空
	 * 4.若栈other非空，则使当前节点指向出栈的栈顶节点，遍历节点
	 * 5.重复第4步，直到栈other为空
	 * 总结：
	 */
	public void postOrder_two() {
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
			while(!other.isEmpty()) {//栈other非空
				node = other.pop();
				System.out.print(node.comparable + " ");//遍历节点
			}
            System.out.println();
        }
		return;
	}

	/* 非递归式后序遍历（一个栈）
	 * 1.使当前节点指向根节点，最近遍历的节点指向根节点，当前节点入栈
	 * 2.若栈非空
	 *  （1）栈顶节点存在子节点，并且最近遍历的节点不是其子节点，将右左子节点依次入栈
	 *  （2）栈顶节点不存在子节点，或者存在子节点，最近遍历的节点是其子节点，将栈顶节点出栈，遍历节点，更新最近遍历的节点
	 * 3.重复第2步，直到栈为空
	 * 总结：
	 */
	public void postOrder_one() {
		if (root != null) {
			TreeNode node = root;
			TreeNode last = root;//最近遍历的节点
			Stack<TreeNode> stack = new Stack<>();
			stack.push(node);
			while (!stack.isEmpty()) {
				node = stack.peek();
				if ((node.left != null || node.right != null) && last != node.left && last != node.right) {
					//栈顶节点有子节点且最近出栈的节点不是其子节点，则右左子节点依次入栈
					if (node.right != null)
						stack.push(node.right);
					if (node.left != null)
						stack.push(node.left);
				}else {//栈顶节点没有子节点或者栈顶节点有子节点，最近遍历的节点是其子节点，则出栈
					last = stack.pop();
					System.out.print(last.comparable + " ");//遍历节点
				}
			}
            System.out.println();
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
		queue.enter(new TreeNode(null, false));//虚拟节点
		while(!queue.isEmpty()) {//队非空
			node = queue.depart();
			if (node.comparable == null) {//虚拟节点
				if (queue.isEmpty())//队为空
					break;
				queue.enter(node);
				System.out.println();
			}else {
				System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
				if(node.left != null)
					queue.enter(node.left);
				if(node.right != null)
					queue.enter(node.right);
			}
		}
        System.out.println();
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
		while(!left.isEmpty() || !right.isEmpty()) {
			if(!left.isEmpty()) {//从左到右遍历
				while(!left.isEmpty()) {
					node = left.pop();
					System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
					if(node.left != null)
						right.push(node.left);
					if(node.right != null)
						right.push(node.right);
				}
				System.out.println();
			}else {//从右到左遍历
				while(!right.isEmpty()) {
					node = right.pop();
					System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
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
		while (left != 0 || right != 0) {
			if (left != 0) {//从左到右遍历，元素从队首出队
				while (left != 0) {
					node = queue.departHead();
					System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
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
					System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
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

	//Morris遍历
    public void morrisTraverse() {
        TreeNode node = root;
        TreeNode temp;
        while (node != null) {
            System.out.print(node.comparable + " ");
            temp = node.left;
            if (temp == null) {
                node = node.right;
            } else {
                while (temp.right != null && temp.right != node)
                    temp = temp.right;
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                }else {
                    temp.right = null;
                    node = node.right;
                }
            }
        }
        System.out.println();
        return;
    }
    
    //基于Morris遍历的前序遍历
    public void morrisPreTraverse() {
	    TreeNode node = root;
	    TreeNode temp;
        while (node != null) {
            temp = node.left;
            if (temp == null) {
                System.out.print(node.comparable + " ");
                node = node.right;
            }else {
                while (temp.right != null && temp.right != node)
                    temp = temp.right;
                if (temp.right == null) {
                    System.out.print(node.comparable + " ");
                    temp.right = node;
                    node = node.left;
                }else {
                    temp.right = null;
                    node = node.right;
                }
            }
        }
        System.out.println();
        return;
    }
    
    //基于Morris遍历的中序遍历
    public void morrisInTraverse() {
        TreeNode node = root;
        TreeNode temp;
        while (node != null) {
            temp = node.left;
            if (temp != null) {
                while (temp.right != null && temp.right != node)
                    temp = temp.right;
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                    continue;
                }else
                    temp.right = null;
            }
            System.out.print(node.comparable + " ");
            node = node.right;
        }
        System.out.println();
        return;
    }
    
    //基于Morris遍历的后序遍历
    public void morrisPostTraverse() {
	    
    }

}
