package data_structure;

/* 红黑树的第二种实现
 * 红黑树性质：
 * 节点是黑色或者红色，根节点保持黑色
 * 如果节点是红色，则两个子节点（若存在的话）都是黑色
 * 对于每个节点，从此节点到所有叶子节点的路径上的黑色节点数量相同
 * 添加元素的情形：
 *
 * 删除元素的情形：
 */
public class RBT_2 {

    private TreeNode root;

    private int size;

    private static class TreeNode {

        public Comparable comparable;
        public TreeNode parent;
        public TreeNode left;
        public TreeNode right;
        public boolean color;//红节点为true，黑节点为false

        public TreeNode(Comparable comparable, boolean color) {
            this.comparable = comparable;
            this.color = color;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    public RBT_2() {
        this.root = null;
        this.size = 0;
    }
}
