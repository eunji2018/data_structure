/*
    @Author：eunji
 */
package data_structure;

//堆的完全二叉树实现
//小根堆，元素较小则优先
//实现了入堆，出堆，取堆顶，判空，清空，向上查找第一个左子节点或右子节点，向下查找最左节点或最右节点的操作
public class Heap {

    private int size;//堆的大小

    private TreeNode head;//堆顶节点

    private TreeNode tail;//堆尾节点

    //节点类
    private static class TreeNode{

        public int data;
        private TreeNode left;//左子节点
        private TreeNode right;//右子节点
        private TreeNode parent;//父节点

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public Heap() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int size() {
        return size;
    }

    //判空
    public boolean isEmpty() {
        return size == 0;
    }

    //清空
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        return;
    }

    //取堆顶
    public int peek() {
        return size == 0 ? Integer.MIN_VALUE : head.data;
    }

    //入堆
    //过程：查找堆尾后一个位置的父节点，更新引用，调整，更新计数
    public void push(int data) {
        TreeNode node = new TreeNode(data);
        if (size == 0) {
            head = node;
        }else {
            tail = next();
            //堆大小为偶数时，新元素是右子节点，为奇数时，是左子节点
            if (size % 2 == 0) {
                tail.right = node;
            }else {
                tail.left = node;
            }
            node.parent = tail;
        }
        tail = node;
        //调整堆，保持有序
        bubble(tail);
        size++;
        return;
    }

    //出堆
    //限制条件：调用pop()方法前先调用isEmpty()判断
    //过程：查找堆尾前一个位置，交换元素，更新引用，调整，更新计数，返回结果
    public int pop() {
        TreeNode node = tail;
        if (size == 1) {
            head = null;
            tail = null;
        }else {
            tail = previous();
            swap(head,node);
            //堆大小为偶数时，堆尾是左子节点，为奇数时，是右子节点
            if (size % 2 == 0) {
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
            //调整堆，保持有序
            sink(head);
        }
        size--;
        return node.data;
    }

    //返回堆尾后一个位置的父节点
    //堆至少包含一个节点，tail不为null
    private TreeNode next() {
        TreeNode node = upLeft(tail);//从堆尾开始向上查找第一个左子节点或堆顶节点
        if (node == head)//若查找到堆顶节点，则返回其最左子节点
            return downLeft(head);
        node = node.parent;
        return node.right == null ? node : downLeft(node.right);//node.right可能为null
    }

    //返回堆尾前一个位置的节点
    //堆至少包含两个节点，tail不为null
    private TreeNode previous() {
        TreeNode node = upRight(tail);//从堆尾开始向上查找第一个右子节点或堆顶节点
        if (node == head)//若查找到堆顶节点，则返回其最右子节点
            return downRight(head);
        node = node.parent;
        return downRight(node.left);//node.left不为null
    }

    //从当前节点开始向上查找第一个左子节点或堆顶节点
    //node不为null
    private TreeNode upLeft(TreeNode node) {
        TreeNode temp;
        while (node.parent != null) {
            temp = node.parent;
            if (temp.left == node)
                break;
            node = temp;
        }
        return node;
    }

    //从当前节点开始向上查找第一个右子节点或堆顶节点
    //node不为null
    private TreeNode upRight(TreeNode node) {
        TreeNode temp;
        while (node.parent != null) {
            temp = node.parent;
            if (temp.right == node)
                break;
            node = temp;
        }
        return node;
    }

    //以当前节点为根的子树的最左节点
    //node不为null
    private TreeNode downLeft(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    //以当前节点为根的子树的最右节点
    //node不为null
    private TreeNode downRight(TreeNode node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    //交换两个节点保存的元素
    private void swap(TreeNode one, TreeNode other) {
        int data = one.data;
        one.data = other.data;
        other.data = data;
        return;
    }

    //优先元素上浮
    private void bubble(TreeNode node) {
        TreeNode temp;
        while (node.parent != null) {
            temp = node.parent;
            if (node.data >= temp.data)
                break;
            swap(node,temp);
            node = temp;
        }
        return;
    }

    //非优先元素下沉
    private void sink(TreeNode node) {
        TreeNode temp = node;
        while (true) {
            if (node.right != null && node.right.data < temp.data)
                temp = node.right;
            if (node.left != null && node.left.data < temp.data)
                temp = node.left;
            if (node == temp)//父节点是最优先元素，则结束
                break;
            swap(temp,node);
            node = temp;
        }
        return;
    }

    //打印
    public void print() {
        if (size == 0)
            return;
        Queue<TreeNode> queue = new Queue<>();
        TreeNode node = head;
        queue.enter(node);
        System.out.println();
        while (!queue.isEmpty()) {
            node = queue.depart();
            System.out.print(node.data + " ");
            if (node.left != null)
                queue.enter(node.left);
            if (node.right != null)
                queue.enter(node.right);
        }
        return;
    }
}
