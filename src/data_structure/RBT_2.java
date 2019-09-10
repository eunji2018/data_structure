/**
 * @Author eunji
 */
package data_structure;

/* 红黑树的第二种实现
 * 红黑树性质：
 * （1）节点为黑色或者红色，根节点保持黑色
 * （2）如果节点为红色，则两个子节点都是黑色
 * （3）对于每个节点，从此节点到所有叶子节点的路径上的黑色节点数量相同
 * 添加元素的情形，新节点默认为红色（主要会违反性质2）：
 * 1.父节点为黑色，不需要修正
 * 2.父节点为红色，并且父节点不是根节点
 *     （1）父节点的兄弟节点为黑色，需要进行旋转
 *     （2）父节点的兄弟节点为红色，需要进行变色
 * 删除元素的情形，替代元素，删除颜色（主要会违反性质3）：
 * 1.被删颜色是红色，不需要修正
 * 2.被删颜色是黑色，将起始节点作为当前节点
 *      （1）当前节点是红色，对当前节点进行变色
 *      （2）当前节点是黑色
 *          1）兄弟节点是红色，对父节点进行旋转，转化为下列三种情况
 *          2）兄弟节点是黑色，并且其子节点都为黑色，对兄弟节点进行变色，使当前节点指向父节点
 *          3）兄弟节点是黑色，并且其存在红色子节点，进行旋转变色
 * 红色节点有两种：没有子节点，或者有两个黑色子节点
 * 黑色节点若有一个黑色子节点，则另一个子节点一定存在
 * 添加、删除元素不会修改哨兵节点的颜色
 */
public class RBT_2 {

    private TreeNode root;

    private final TreeNode sentry;//哨兵节点，实际存在的节点的空指针都指向哨兵节点

    private int size;

    private int height;//红黑树的黑色高度

    //节点类
    private static class TreeNode {

        public Comparable comparable;
        public TreeNode parent;
        public TreeNode left;
        public TreeNode right;
        public boolean color;//红色节点为true，黑色节点为false

        public TreeNode(Comparable comparable, boolean color) {
            this.comparable = comparable;
            this.color = color;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    public RBT_2() {
        this.sentry = new TreeNode(null, false);//哨兵节点为黑色
        this.root = sentry;
        this.size = 0;
        this.height = 0;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height;
    }

    //左旋：将节点与右子节点交换角色与颜色
    private void rotateLeft(TreeNode node) {
        TreeNode temp = node.right;
        //调整节点与其右子节点的左子节点的关系
        node.right = temp.left;
        if (temp.left != sentry)
            temp.left.parent = node;
        //调整节点的父节点与其右子节点的关系
        temp.parent = node.parent;
        if (node.parent == sentry)
            root = temp;
        else if (node == node.parent.left)
            node.parent.left = temp;
        else
            node.parent.right = temp;
        //调整节点与其右子节点的关系，交换颜色
        temp.left = node;
        node.parent = temp;
        boolean flag = node.color;
        node.color = temp.color;
        temp.color = flag;
        return;
    }

    //右旋：将节点与左子节点交换角色与颜色
    private void rotateRight(TreeNode node) {
        TreeNode temp = node.left;
        //调整节点与其左子节点的右子节点的关系
        node.left = temp.right;
        if (temp.right != sentry)
            temp.right.parent = node;
        //调整节点的父节点与其左子节点的关系
        temp.parent = node.parent;
        if (node.parent == sentry)
            root = temp;
        else if (node == node.parent.left)
            node.parent.left = temp;
        else
            node.parent.right = temp;
        //调整节点与其左子节点的关系，交换颜色
        temp.right = node;
        node.parent = temp;
        boolean flag = node.color;
        node.color = temp.color;
        temp.color = flag;
        return;
    }

    //变色：子节点都变为黑色，节点变为红色
    private static void convert(TreeNode node) {
        node.color = true;
        node.left.color = false;
        node.right.color = false;
        return;
    }

    //查找元素
    private TreeNode search(Comparable comparable) {
        TreeNode node = root;
        while (node != sentry) {
            if (comparable.compareTo(node.comparable) == 0)
                break;
            else if (comparable.compareTo(node.comparable) < 0)
                node = node.left;
            else
                node = node.right;
        }
        return node;
    }

    //替换节点
    private void replace(TreeNode source, TreeNode target) {
        if (target.parent == sentry)
            root = source;
        else if (target == target.parent.left)
            target.parent.left = source;
        else
            target.parent.right = source;
        source.parent = target.parent;
        return;
    }

    private TreeNode getMin(TreeNode node) {
        while (node.left != sentry)
            node = node.left;
        return node;
    }

    private TreeNode getMax(TreeNode node) {
        while (node.right != sentry)
            node = node.right;
        return node;
    }

    //交换节点保存的元素
    private void swap(TreeNode one, TreeNode other) {
        Comparable comparable = one.comparable;
        one.comparable = other.comparable;
        other.comparable = comparable;
        return;
    }

    //添加元素后修正节点，保持红黑树的性质
    private void correct_insert(TreeNode node) {
        TreeNode temp;
        while (node.parent.color) {//节点和父节点都为红色，并且父节点不是根节点
            if (node.parent == node.parent.parent.left) {//父节点是左子节点
                temp = node.parent.parent.right;
                if (!temp.color) {//父节点的兄弟节点为黑色，进行旋转，旋转之后，修正结束
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    rotateRight(node.parent.parent);
                }else {//父节点的兄弟节点为红色，进行变色，变色之后，向上继续修正
                    node = node.parent.parent;
                    convert(node);
                }
            }else {//父节点是右子节点
                temp = node.parent.parent.left;
                if (!temp.color) {//父节点的兄弟节点为黑色，进行旋转，旋转之后，修正结束
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    rotateLeft(node.parent.parent);
                }else {//父节点的兄弟节点为红色，进行变色，变色之后，向上继续修正
                    node = node.parent.parent;
                    convert(node);
                }
            }
        }
        if (node == root) {//当前节点为根节点，则根节点变为红色，红黑树的黑色高度将加一
            root.color = false;
            height++;
        }
        return;
    }

    //删除元素后修正节点，保持红黑树的性质
    private void correct_delete(TreeNode node) {
        TreeNode temp;
        while (node != root && !node.color) {//当前节点为黑色节点，并且不为根节点
            if (node == node.parent.left) {//当前节点是左子节点
                temp = node.parent.right;
                if (temp.color) {//兄弟节点为红色
                    rotateLeft(node.parent);
                }else if (!temp.left.color && !temp.right.color) {//兄弟节点为黑色，左右子节点都为黑色
                    temp.color = true;
                    node = node.parent;
                }else {//兄弟节点为黑色，左右子节点中存在红色
                    if (!temp.right.color) {//兄弟节点为黑色，其右子节点为黑色，则左子节点为红色
                        rotateRight(temp);
                    }else {//兄弟节点为黑色，其右子节点为红色
                        temp.right.color = false;
                        rotateLeft(node.parent);
                        break;
                    }
                }
            }else {//当前节点为右子节点
                temp = node.parent.left;
                if (temp.color) {//兄弟节点为红色
                    rotateRight(node.parent);
                }else if (!temp.left.color && !temp.right.color) {//兄弟节点为黑色，左右子节点都为黑色
                    temp.color = true;
                    node = node.parent;
                }else {//兄弟节点为黑色，左右子节点中存在红色
                    if (!temp.left.color) {//兄弟节点为黑色，其左子节点为黑色，则右子节点为红色
                        rotateLeft(temp);
                    }else {//兄弟节点为黑色，其左子节点为红色
                        temp.left.color = false;
                        rotateRight(node.parent);
                        break;
                    }
                }
            }
        }
        if (node == root && !root.color)//当前节点为根节点，并且根节点为黑色，则红黑树的黑色高度将减一
            height--;
        node.color = false;
        return;
    }

    //添加元素
    public void insert(Comparable comparable) {
        TreeNode node = root;
        TreeNode temp = sentry;//新节点的父节点
        //查找添加的位置
        while (node != sentry) {
            temp = node;
            if (comparable.compareTo(node.comparable) < 0)
                node = node.left;
            else
                node = node.right;
        }
        node = new TreeNode(comparable, true);//新节点都为红色
        node.parent = temp;
        if (temp == sentry)//添加第一个元素
            root = node;
        else if (comparable.compareTo(temp.comparable) < 0)
            temp.left = node;
        else
            temp.right = node;
        node.left = sentry;
        node.right = sentry;
        //自底向上调整红黑树
        correct_insert(node);
        size++;
        return;
    }

    //删除元素
    //查找被删节点与替代节点，使用替代节点的元素替代被删节点的元素，保留被删节点的颜色，删除替代节点
    public Comparable delete(Comparable comparable) {
        TreeNode node = search(comparable);
        if (node == sentry)//元素不存在
            return null;
        TreeNode substitute;//替代节点
        TreeNode start;//调整红黑树的起始节点
        //优先使用后继节点作为替代节点
        if (node.right != sentry) {//存在右子节点，以后继节点替代被删节点
            substitute = getMin(node.right);
            start = substitute.right;
        }else if (node.left != sentry) {//只存在左子节点，以前驱节点替代被删节点
            substitute = getMax(node.left);
            start = substitute.left;
        }else {//不存在子节点，被删节点作为替代节点
            substitute = node;
            start = node.left;
        }
        swap(node, substitute);//替代元素
        replace(start, substitute);//起始节点取代替代节点的位置
        if (!substitute.color)//替代节点的颜色，即删除的颜色是黑色
            correct_delete(start);
        size--;
        return substitute.comparable;
    }

    //中序遍历
    public void inTraverse() {
        if (root != sentry) {
            TreeNode node = root;
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || node != sentry) {
                if (node != sentry) {
                    stack.push(node);
                    node = node.left;
                }else {
                    node = stack.pop();
                    System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));
                    node = node.right;
                }
            }
            System.out.println();
        }
        return;
    }

    //层次遍历
    public void hierarchy() {
        if (root == sentry)
            return;
        TreeNode node = root;
        Queue<TreeNode> queue = new Queue<>();
        queue.enter(node);
        queue.enter(new TreeNode(null, false));
        while (!queue.isEmpty()) {
            node = queue.depart();
            if (node.comparable == null) {
                if (queue.isEmpty())
                    break;
                queue.enter(node);
                System.out.println();
            }else {
                System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));
                if (node.left != sentry)
                    queue.enter(node.left);
                if (node.right != sentry)
                    queue.enter(node.right);
            }
        }
        System.out.println();
        return;
    }

}
