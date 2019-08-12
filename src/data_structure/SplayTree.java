/**
 * @Author eunji
 */
package data_structure;

//伸展树（自适应查找树）的自顶向下实现
//伸展树的性质：
//是一种二叉查找树，添加、删除、查找操作都依赖于伸展操作
//每次伸展操作后都会重构树，将相应节点作为新的根节点
//最近伸展过的元素都位于根节点附近，适用于时间局部性原理
public class SplayTree {

    private TreeNode root;

    private int size;//树的大小

    //树节点类
    private static class TreeNode {

        public Comparable comparable;
        public TreeNode parent;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Comparable comparable) {
            this.comparable = comparable;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    public SplayTree() {
        this.root = null;
        this.size = 0;
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
        root = null;
        size = 0;
        return;
    }

    //伸展元素
    //若元素存在，则使相应节点成为根节点，若元素不存在，则使遍历过程中的最后一个节点成为根节点
    private TreeNode splay(Comparable comparable) {
        return root;
    }

    //查找元素
    public TreeNode search(Comparable comparable) {
        return null;
    }

    //添加元素
    public void insert(Comparable comparable) {
        return;
    }

    //删除元素
    public void delete(Comparable comparable) {
        return;
    }

    //层次遍历
    public void hierarchy() {
        if (size == 0)
            return;
        TreeNode node = root;
        Queue<TreeNode> queue = new Queue<>();
        queue.enter(node);
        queue.enter(new TreeNode(null));//虚拟节点
        System.out.println();
        while (!queue.isEmpty()) {
            node = queue.depart();
            if (node.comparable == null) {//虚拟节点
                if (queue.isEmpty())
                    break;
                queue.enter(node);
                System.out.println();
            }else {
                System.out.print(node.comparable + " ");//遍历节点
                if (node.left != null)
                    queue.enter(node.left);
                if (node.right != null)
                    queue.enter(node.right);
            }
        }
        return;
    }

    //中序遍历
    public void inOrder() {
        System.out.println();
        inOrder(root);
        return;
    }

    private static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.comparable + " ");
            inOrder(node.right);
        }
    }

}
