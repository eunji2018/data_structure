/**
 * @Author eunji
 */
package data_structure;

//堆的完全二叉树实现
public class Heap {

    private int size;//堆的大小

    private TreeNode head;//堆顶节点

    private TreeNode tail;//堆尾节点

    private final boolean order;//true表示正序，false表示逆序

    //节点类
    private static class TreeNode{

        public Comparable comparable;
        private TreeNode left;//左子节点
        private TreeNode right;//右子节点
        private TreeNode parent;//父节点

        public TreeNode(Comparable comparable) {
            this.comparable = comparable;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public Heap(boolean order) {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.order = order;
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
        this.head = null;
        this.tail = null;
        this.size = 0;
        return;
    }

    //取堆顶
    //限制条件：调用peek()方法前先调用isEmpty()方法判断
    public Comparable peek() {
        return size == 0 ? null : head.comparable;
    }

    //入堆
    //过程：查找堆尾后一个位置的【父节点】，更新引用，调整堆，更新计数
    public void push(Comparable comparable) {
        TreeNode node = new TreeNode(comparable);
        if (size == 0) {//堆为空
            head = node;
        }else {
            tail = next();//堆尾后一个位置的父节点
            //更新引用
            //堆大小为偶数时，新元素是右子节点，为奇数时，是左子节点
            if ((size & 1) == 0)
                tail.right = node;
            else
                tail.left = node;
            node.parent = tail;
        }
        tail = node;
        //调整堆，保持有序
        bubble(tail);
        size++;
        return;
    }

    //出堆
    //限制条件：调用pop()方法前先调用isEmpty()方法判断
    //过程：查找堆尾前一个位置的节点，交换元素，更新引用，调整堆，更新计数
    public Comparable pop() {
        TreeNode node = tail;
        if (size == 1) {//只有一个元素
            head = null;
            tail = null;
        }else {
            tail = previous();//堆尾前一个位置
            swap(head, node);//交换元素
            //更新引用
            //堆大小为偶数时，堆尾是左子节点，为奇数时，是右子节点
            if ((size & 1) == 0)
                node.parent.left = null;
            else
                node.parent.right = null;
            //调整堆，保持有序
            sink(head);
        }
        size--;
        return node.comparable;
    }

    //查找堆尾后一个位置的父节点
    //堆至少包含一个节点，tail不为null
    private TreeNode next() {
        TreeNode node = upLeft(tail);//从堆尾开始向上查找第一个左子节点或堆顶节点
        if (node == head)//若查找到堆顶节点，则返回其最左子节点
            return downLeft(head);
        node = node.parent;
        return node.right == null ? node : downLeft(node.right);//node.right可能为null
    }

    //查找堆尾前一个位置的节点
    //堆至少包含两个节点，tail不为null
    private TreeNode previous() {
        TreeNode node = upRight(tail);//从堆尾开始向上查找第一个右子节点或堆顶节点
        if (node == head)//若查找到堆顶节点，则返回其最右子节点
            return downRight(head);
        node = node.parent;
        return downRight(node.left);//node.left不为null
    }

    //从当前节点开始向上查找第一个左子节点或堆顶节点
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
    private TreeNode downLeft(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    //以当前节点为根的子树的最右节点
    private TreeNode downRight(TreeNode node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    //交换两个节点保存的元素
    private void swap(TreeNode one, TreeNode other) {
        Comparable comparable = one.comparable;
        one.comparable = other.comparable;
        other.comparable = comparable;
        return;
    }

    //正序时优先元素上浮或者逆序时非优先元素上浮
    private void bubble(TreeNode node) {
        TreeNode temp;
        while (node.parent != null) {
            temp = node.parent;
            if (order && node.comparable.compareTo(temp.comparable) >= 0)//正序并且父节点的元素优先，或者优先级相同
                break;
            if (!order && node.comparable.compareTo(temp.comparable) <= 0)//逆序并且子节点的元素优先，或者优先级相同
                break;
            swap(node, temp);
            node = temp;
        }
        return;
    }

    //正序时非优先元素下沉或者逆序时优先元素下沉
    private void sink(TreeNode node) {
        TreeNode temp = node;
        while (true) {
            if (order) {//正序
                if (node.right != null && node.right.comparable.compareTo(temp.comparable) < 0)//右子节点的元素优先
                    temp = node.right;
                if (node.left != null && node.left.comparable.compareTo(temp.comparable) < 0)//左子节点的元素优先
                    temp = node.left;
            }else {//逆序
                if (node.right != null && node.right.comparable.compareTo(temp.comparable) > 0)//右子节点的元素非优先
                    temp = node.right;
                if (node.left != null && node.left.comparable.compareTo(temp.comparable) > 0)//左子节点的元素非优先
                    temp = node.left;
            }
            if (node == temp)//父节点的元素优先级最高或者最低，则结束
                break;
            swap(node, temp);
            node = temp;
        }
        return;
    }

    //层次遍历
    public void hierarchy() {
        if (size == 0)
            return;
        Queue<TreeNode> queue = new Queue<>();
        TreeNode node = head;
        queue.enter(node);
        queue.enter(new TreeNode(null));//虚拟节点，表示当前层遍历完毕
        while (!queue.isEmpty()) {
            node = queue.depart();
            if (node.comparable == null) {//当前层遍历完毕
                if (queue.isEmpty())//所有层遍历完毕
                    break;
                queue.enter(node);
                System.out.println();
            }else {
                System.out.print(node.comparable + " ");
                if (node.left != null)
                    queue.enter(node.left);
                if (node.right != null)
                    queue.enter(node.right);
            }
        }
        System.out.println();
        return;
    }
}
