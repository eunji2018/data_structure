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
public class RBT {

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

    public RBT() {
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

    public Comparable deleteMin() {
        if (size == 0)
            return null;
        TreeNode node = getMin(root);
        TreeNode substitute;
        TreeNode start;
        if (node.right != sentry) {
            substitute = getMin(node.right);
            start = substitute.right;
        }else {
            substitute = node;
            start = node.left;
        }
        swap(node, substitute);
        replace(start, substitute);
        if (!substitute.color)
            correct_delete(start);
        size--;
        return substitute.comparable;
    }

    public Comparable deleteMax() {
        if (size == 0)
            return null;
        TreeNode node = getMax(root);
        TreeNode substitute;
        TreeNode start;
        if (node.left != sentry) {
            substitute = getMax(node.left);
            start = substitute.left;
        }else {
            substitute = node;
            start = node.left;
        }
        swap(node, substitute);
        replace(start, substitute);
        if (!substitute.color)
            correct_delete(start);
        size--;
        return substitute.comparable;
    }

    //最近公共父节点
    public Comparable shared(Comparable one, Comparable other) {
        TreeNode node1 = search(one);
        TreeNode node2 = search(other);
        if (node1 == sentry || node2 == sentry)
            return null;
        return shared(root, node1, node2).comparable;
    }

    //当以node为根的子树不包含两个节点时，返回sentry
    //只包含一个节点时，返回此节点；包含两个节点时，返回最近公共父节点
    private TreeNode shared(TreeNode node, TreeNode one, TreeNode other) {
        if (node == sentry || node == one || node == other)
            return node;
        TreeNode left = shared(node.left, one, other);
        TreeNode right = shared(node.right, one, other);
        if (left != sentry && right != sentry)
            return node;
        return left == sentry ? right : left;
    }

    /* 非递归式前序遍历
     * 1.使当前节点指向根节点，当前节点入栈
     * 2.若栈非空，则使当前节点指向出栈的栈顶节点，遍历节点，将右左子节点依次入栈
     * 3.重复第2步，直到栈为空
     * 总结：根节点入栈，执行循环：若栈非空，则出栈并遍历该节点，将右、左子节点依次入栈
     */
    public void preTraverse() {
        if (root != sentry) {
            TreeNode node = root;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {//栈非空
                node = stack.pop();
                System.out.print(node.comparable + " ");//遍历节点
                if (node.right != sentry)
                    stack.push(node.right);
                if (node.left != sentry)
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
    public void inTraverse() {
        if (root != sentry) {
            TreeNode node = root;
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || node != sentry) {
                if (node != sentry) {//当前节点非空
                    stack.push(node);
                    node = node.left;
                }else {//栈非空
                    node = stack.pop();
                    System.out.print(node.comparable + " ");//遍历节点
                    node = node.right;
                }
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
    public void postTraverse_one() {
        if (root != sentry) {
            TreeNode node = root;
            TreeNode last = root;//最近遍历的节点
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                node = stack.peek();
                if ((node.left != sentry || node.right != sentry) && last != node.left && last != node.right) {
                    //栈顶节点有子节点且最近出栈的节点不是其子节点，则右左子节点依次入栈
                    if (node.right != sentry)
                        stack.push(node.right);
                    if (node.left != sentry)
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

    /* 非递归式后序遍历（两个栈）
     * 1.使当前节点指向根节点，当前节点入栈one
     * 2.若栈one非空，则使当前节点指向出栈的栈顶节点，当前节点入栈other，将左右子节点依次入栈one
     * 3.重复第2步，直到栈one为空
     * 4.若栈other非空，则使当前节点指向出栈的栈顶节点，遍历节点
     * 5.重复第4步，直到栈other为空
     * 总结：
     */
    public void postTraverse_two() {
        if (root != sentry) {
            TreeNode node = root;
            Stack<TreeNode> one = new Stack<>();
            Stack<TreeNode> other = new Stack<>();
            one.push(node);
            while (!one.isEmpty()) {//栈one非空
                node = one.pop();
                other.push(node);
                if (node.left != sentry)
                    one.push(node.left);
                if (node.right != sentry)
                    one.push(node.right);
            }
            while (!other.isEmpty()) {//栈other非空
                node = other.pop();
                System.out.print(node.comparable + " ");//遍历节点
            }
            System.out.println();
        }
        return;
    }

    /* 层次遍历（从左到右，队列）
     * 1.使当前节点指向根节点，当前节点入队，虚拟节点入队
     * 2.若队非空，则使当前节点指向出队的队首节点
     * （1）当前节点是虚拟节点，若队为空，则结束，若队非空，则当前节点入队
     * （2）当前节点不是虚拟节点，遍历节点，将左右子节点依次入队
     * 3.重复第2步，直到队为空
     */
    public void hierarchy() {
        if (root == sentry)
            return;
        TreeNode node = root;
        Queue<TreeNode> queue = new Queue<>();
        queue.enter(node);
        queue.enter(new TreeNode(null, false));//虚拟节点
        while (!queue.isEmpty()) {//队非空
            node = queue.depart();
            if (node.comparable == null) {//虚拟节点
                if (queue.isEmpty())//队为空
                    break;
                queue.enter(node);
                System.out.println();
            }else {
                System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
                if (node.left != sentry)
                    queue.enter(node.left);
                if (node.right != sentry)
                    queue.enter(node.right);
            }
        }
        System.out.println();
        return;
    }

    //层次遍历（左右交替，两个栈）
    public void hierarchy_inturn_stack() {
        if (root == sentry)
            return;
        TreeNode node = root;
        Stack<TreeNode> left = new Stack<>();//保存从左到右遍历的节点
        Stack<TreeNode> right = new Stack<>();//保存从右到左遍历的节点
        left.push(node);//先从左到右
        while (!left.isEmpty() || !right.isEmpty()) {
            if (!left.isEmpty()) {//从左到右遍历
                while (!left.isEmpty()) {
                    node = left.pop();
                    System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
                    if (node.left != sentry)
                        right.push(node.left);
                    if (node.right != sentry)
                        right.push(node.right);
                }
                System.out.println();
            }else {//从右到左遍历
                while (!right.isEmpty()) {
                    node = right.pop();
                    System.out.print(node.comparable + ":" + (node.color ? "T " : "F "));//遍历节点
                    if (node.right != sentry)
                        left.push(node.right);
                    if (node.left != sentry)
                        left.push(node.left);
                }
                System.out.println();
            }
        }
        return;
    }

    //层次遍历（左右交替，双端队列）
    public void hierarchy_inturn_queue() {
        if (root == sentry)
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
                    if (node.left != sentry) {
                        queue.enterTail(node.left);
                        right++;
                    }
                    if (node.right != sentry) {
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
                    if (node.right != sentry) {
                        queue.enterHead(node.right);
                        left++;
                    }
                    if (node.left != sentry) {
                        queue.enterHead(node.left);
                        left++;
                    }
                }
                System.out.println();
            }
        }
        return;
    }

    /* Morris遍历
     *
     */
    public void morrisTraverse() {
        TreeNode node = root;
        TreeNode temp;
        while (node != sentry) {
            System.out.print(node.comparable + " ");
            temp = node.left;
            if (temp == sentry) {
                node = node.right;
            } else {
                while (temp.right != sentry && temp.right != node)
                    temp = temp.right;
                if (temp.right == sentry) {
                    temp.right = node;
                    node = node.left;
                }else {
                    temp.right = sentry;
                    node = node.right;
                }
            }
        }
        System.out.println();
        return;
    }

    /* 基于Morris遍历的前序遍历
     *
     */
    public void morrisPreTraverse() {
        TreeNode node = root;
        TreeNode temp;
        while (node != sentry) {
            temp = node.left;
            if (temp == sentry) {
                System.out.print(node.comparable + " ");
                node = node.right;
            }else {
                while (temp.right != sentry && temp.right != node)
                    temp = temp.right;
                if (temp.right == sentry) {
                    System.out.print(node.comparable + " ");
                    temp.right = node;
                    node = node.left;
                }else {
                    temp.right = sentry;
                    node = node.right;
                }
            }
        }
        System.out.println();
        return;
    }

    /* 基于Morris遍历的中序遍历
     *
     */
    public void morrisInTraverse() {
        TreeNode node = root;
        TreeNode temp;
        while (node != sentry) {
            temp = node.left;
            if (temp != sentry) {
                while (temp.right != sentry && temp.right != node)
                    temp = temp.right;
                if (temp.right == sentry) {
                    temp.right = node;
                    node = node.left;
                    continue;
                }else
                    temp.right = sentry;
            }
            System.out.print(node.comparable + " ");
            node = node.right;
        }
        System.out.println();
        return;
    }

    /* 基于Morris遍历的后序遍历
     *
     */
    public void morrisPostTraverse() {
        TreeNode node = root;
        TreeNode temp;
        while (node != sentry) {
            temp = node.left;
            if (temp != sentry) {
                while (temp.right != sentry && temp.right != node)
                    temp = temp.right;
                if (temp.right == sentry) {
                    temp.right = node;
                    node = node.left;
                    continue;
                }else {
                    temp.right = sentry;
                    while (temp != node) {
                        System.out.print(temp.comparable + " ");
                        temp = temp.parent;
                    }
                }
            }
            node = node.right;
        }
        if (root != sentry) {
            temp = getMax(root);
            while (temp != sentry) {
                System.out.print(temp.comparable + " ");
                temp = temp.parent;
            }
        }
        System.out.println();
        return;
    }

}
