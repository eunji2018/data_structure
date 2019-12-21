/**
 * @Author eunji
 */
package data_structure;

/* 红黑树的第二种实现
 * 红黑树性质：
 * （1）节点为黑色或者红色，根节点保持黑色
 * （2）如果节点为红色，则两个子节点都是黑色，并且红色节点只能是左子节点
 * （3）对于每个节点，从此节点到所有叶子节点的路径上的黑色节点数量相同
 * 添加元素的情形，新节点默认为红色（主要会违反性质2）：
 * 1.父节点为黑色
 *     （1）新节点是左子节点，不需要修正
 *     （2）新节点是右子节点，需要进行旋转或者变色
 * 2.父节点为红色，并且父节点是左子节点
 *     （1）新节点是左子节点，需要进行一次旋转，变色
 *     （2）新节点是右子节点，需要进行两次旋转，变色
 * 删除元素的情形，替代元素，删除颜色（主要会违反性质3）
 * 1.被删颜色是红色，不需要修正
 * 2.被删颜色是黑色，将起始节点作为当前节点
 *     （1）当前节点是红色，对当前节点进行变色
 *     （2）当前节点是黑色
 *         1）当前节点是左子节点，兄弟节点是黑色
 *          a：兄弟节点的左子节点是红色，进行旋转变色
 *          b：兄弟节点的子节点都为黑色，对父节点进行旋转，若父节点是黑色，对其进行变色，继续向上修正
 *         2）当前节点是右子节点
 *          a：兄弟节点是红色，这种情形比较复杂
 *          b：兄弟节点是黑色，若其左子节点是红色，对父节点进行旋转，变色，若其子节点都为黑色，对其进行变色，继续向上修正
 * 红色节点有两种：没有子节点，或者有两个黑色子节点
 * 黑色节点若有一个黑色子节点，则另一个子节点一定存在
 * 添加、删除元素不会修改哨兵节点的颜色
 */
public class RBT_2 {

    private TreeNode root;

    private final TreeNode sentry;//哨兵节点，其他节点的空指针都指向哨兵节点

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

    //左旋，必要情况下交换颜色
    private void rotateLeft(TreeNode node, boolean flag) {
        TreeNode temp = node.right;
        //调整节点与其右子节点的左子节点的关系
        node.right = temp.left;
        temp.left.parent = node;
        //调整节点的父节点与其右子节点的关系
        temp.parent = node.parent;
        if (node == root)
            root = temp;
        else if (node == node.parent.left)
            node.parent.left = temp;
        else
            node.parent.right = temp;
        //调整节点与其右子节点的关系
        temp.left = node;
        node.parent = temp;
        //交换颜色
        if (flag) {
            boolean color = node.color;
            node.color = temp.color;
            temp.color = color;
        }
        return;
    }

    //右旋，必要情况下交换颜色
    private void rotateRight(TreeNode node, boolean flag) {
        TreeNode temp = node.left;
        //调整节点与其左子节点的右子节点的关系
        node.left = temp.right;
        temp.right.parent = node;
        //调整节点的父节点与其左子节点的关系
        temp.parent = node.parent;
        if (node == root)
            root = temp;
        else if (node == node.parent.left)
            node.parent.left = temp;
        else
            node.parent.right = temp;
        //调整节点与其左子节点的关系
        temp.right = node;
        node.parent = temp;
        //交换颜色
        if (flag) {
            boolean color = node.color;
            node.color = temp.color;
            temp.color = color;
        }
        return;
    }

    //变色
    private static void convert(TreeNode node) {
        node.color = true;
        node.left.color = false;
        node.right.color = false;
        return;
    }

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

    private void replace(TreeNode source, TreeNode target) {
        if (target == root)
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

    public Comparable getMin() {
        if (size == 0)
            return null;
        return getMin(root).comparable;
    }

    public Comparable getMax() {
        if (size == 0)
            return null;
        return getMax(root).comparable;
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
        while (node != root) {//当前节点是红色节点
            temp = node.parent;
            if (temp.color) {//父节点是红色节点，并且是左子节点
                if (node == temp.right) {//节点是右子节点
                    node = temp;
                    rotateLeft(node, false);
                }
                //节点是左子节点
                rotateRight(node.parent.parent, true);
                node = node.parent;
                convert(node);
            }else {//父节点是黑色节点
                if (node == temp.left)//节点是左子节点
                    break;
                //节点是右子节点
                node = temp;
                if (temp.left.color)
                    convert(node);//兄弟节点是红色节点
                else
                    rotateLeft(node, true);
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
                temp = node.parent.right;//兄弟节点为黑色节点
                if (temp.left.color) {//兄弟节点的左子节点为红色节点
                    rotateRight(temp, true);
                    temp.color = false;
                    rotateLeft(node.parent, true);
                    break;
                }else {//兄弟节点的左右子节点都为黑色
                    rotateLeft(node.parent, false);
                    if (node.parent.color) //父节点为红色
                        break;
                    else {//父节点为黑色
                        node.parent.color = true;
                        node = node.parent.parent;
                    }
                }
            }else {//当前节点是右子节点
                temp = node.parent.left;
                if (temp.color) {//兄弟节点为红色节点
                    //这里再分为两种情形
                    if (temp.right.left.color) {
                        rotateLeft(temp, false);
                        rotateRight(node.parent, false);
                        temp.right.color = false;
                    }else {
                        rotateRight(node.parent, true);
                        node.parent.color = false;
                        node.parent.left.color = true;
                    }
                    break;
                }else {//兄弟节点为黑色节点
                    if (temp.left.color) {//兄弟节点的左子节点为红色节点
                        rotateRight(node.parent, true);
                        temp.left.color = false;
                        break;
                    }else {//兄弟节点的左右子节点都为黑色
                        temp.color = true;
                        node = node.parent;
                    }
                }
            }
        }
        if (node == root)//当前节点为根节点，则红黑树的黑色高度将减一
            height--;
        else
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

    //传入被删节点，查找替代节点，使用替代节点的元素替代被删节点的元素，保留被删节点的颜色，删除替代节点
    private Comparable delete(TreeNode node) {
        TreeNode substitute;//替代节点
        TreeNode start;//调整红黑树的起始节点
        //优先使用前驱节点作为替代节点
        if (node.left != sentry) {
            substitute = getMax(node.left);
            start = substitute.left;
        }else if (node.right != sentry) {
            substitute = getMin(node.right);
            start = substitute.right;
        }else {
            substitute = node;
            start = sentry;
        }
        swap(node, substitute);
        replace(start, substitute);
        if (!substitute.color)
            correct_delete(start);//自底向上调整红黑树
        size--;
        return substitute.comparable;
    }

    //删除元素
    public Comparable delete(Comparable comparable) {
        TreeNode node = search(comparable);
        if (node == sentry)
            return null;
        return delete(node);
    }

    public Comparable deleteMin() {
        if (size == 0)
            return null;
        TreeNode node = getMin(root);
        return delete(node);
    }

    public Comparable deleteMax() {
        if (size == 0)
            return null;
        TreeNode node = getMax(root);
        return delete(node);
    }

    //前序遍历
    public void preTraverse() {
        if (root != sentry) {
            TreeNode node = root;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.comparable + " ");
                if (node.right != sentry)
                    stack.push(node.right);
                if (node.left != sentry)
                    stack.push(node.left);
            }
            System.out.println();
        }
        return;
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
                    System.out.print(node.comparable + " ");
                    node = node.right;
                }
            }
            System.out.println();
        }
        return;
    }

    //后序遍历
    public void postTraverse() {
        if (root != sentry) {
            TreeNode node = root;
            TreeNode last = root;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                node = stack.peek();
                if ((node.left != sentry || node.right != sentry) && last != node.left && last != node.right) {
                    if (node.right != sentry)
                        stack.push(node.right);
                    if (node.left != sentry)
                        stack.push(node.left);
                }else {
                    last = stack.pop();
                    System.out.print(last.comparable + " ");
                }
            }
            System.out.println();
        }
        return;
    }

    //层次遍历
    public void hierarchy() {
        if (root != sentry) {
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
        }
        return;
    }

}
