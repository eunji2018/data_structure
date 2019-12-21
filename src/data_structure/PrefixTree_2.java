/**
 * @Author eunji
 */
package data_structure;

/* 前缀树（字典树）的第二种实现
 * 树的结构与添加元素的顺序有关
 * 节点的子节点上的字符可能相同
 */
public class PrefixTree_2 {

    private final TreeNode root;

    private final TreeNode sentry;//哨兵节点，其他节点的空指针（不包括parent）都指向哨兵节点

    private TreeNode pointer;//查找操作中，指向路径上的最后一个非哨兵节点

    private int index;//查找操作中，等于字符串在树中最长前缀的长度

    private boolean direction;//表示查找操作中的方向，正下方为true，两侧为false（前缀树为空时，一定为true）

    //节点类
    private class TreeNode{

        public char character;//节点保存的字符
        public int count;//
        public boolean end;//终止节点为true，非终止节点为false
        public TreeNode parent;
        public TreeNode left;
        public TreeNode middle;
        public TreeNode right;

        public TreeNode(char character) {
            this.character = character;
            this.count = 0;
            this.end = false;
            this.parent = null;
            this.left = null;
            this.middle = null;
            this.right = null;
        }
    }

    public PrefixTree_2() {
        this.root = new TreeNode('#');
        this.sentry = new TreeNode('/');
        this.root.left = sentry;
        this.root.middle = sentry;
        this.root.right = sentry;
        this.pointer = root;
        this.index = 0;
        this.direction = true;
    }

    public int size() {
        return root.count;
    }

    //查找字符串
    private boolean search(String string) {
        index = 0;
        pointer = root;
        TreeNode node = root.middle;
        while (index < string.length()) {
            if (node == sentry)//字符串在树中的最长前缀不是其自身
                break;
            pointer = node;
            if (string.charAt(index) == node.character) {
                node = node.middle;
                direction = true;
                index++;
            }else {
                if (string.charAt(index) < node.character)
                    node = node.left;
                else
                    node = node.right;
                direction = false;
            }
        }
        return index == string.length() && pointer.end;
    }

    public boolean contains(String string) {
        if (string == null || string.length() == 0)//非法字符串
            return false;
        return search(string);
    }

    //添加字符串
    //若字符串已存在，则返回
    public void insert(String string) {
        if (string == null || string.length() == 0 || search(string))
            return;
        //字符串不存在，有两种情况
        TreeNode node;
        TreeNode temp = pointer;
        if (index < string.length()) {//最长前缀不是其自身
            node = new TreeNode(string.charAt(index++));
            if (direction) {
                temp.middle = node;
            }else {
                if (node.character < temp.character)
                    temp.left = node;
                else
                    temp.right = node;
            }
            while (true) {
                node.left = sentry;
                node.middle = sentry;
                node.right = sentry;
                node.parent = temp;
                temp = node;
                if (index == string.length())
                    break;
                node = new TreeNode(string.charAt(index++));
                temp.middle = node;
            }
        }
        temp.end = true;
        temp.count++;
        while (temp.parent != null) {
            node = temp.parent;
            if (node.middle == temp)
                node.count++;//更新计数
            temp = node;
        }
        return;
    }

    //删除字符串
    //若字符串不存在，则返回
    public void delete(String string) {
        if (string == null || string.length() == 0 || !search(string))
            return;
        //字符串存在，更新计数，删除节点
        TreeNode temp;
        TreeNode node = pointer;
        node.end = false;
        boolean flag = true;
        while (node != root) {
            if (flag)
                node.count--;
            flag = (node.parent.middle == node);
            //优先使用左子树的最右节点取代被删节点
            if (node.count == 0) {
                if (node.left != sentry) {
                    temp = deleteMax(node.left);
                    swap(node, temp);
                }else if (node.right != sentry) {
                    temp = deleteMin(node.right);
                    swap(node, temp);
                }else {
                    replace(sentry, node);
                }
            }
            node = node.parent;
        }
        root.count--;
        pointer = root;
        return;
    }

    private TreeNode deleteMin(TreeNode node) {
        while (node.left != sentry)
            node = node.left;
        replace(node.right, node);
        return node;
    }

    private TreeNode deleteMax(TreeNode node) {
        while (node.right != sentry)
            node = node.right;
        replace(node.left, node);
        return node;
    }

    //交换节点保存的字符、终止标志、前缀计数、middle、parent指针
    private void swap(TreeNode one, TreeNode other) {
        char character = one.character;
        one.character = other.character;
        other.character = character;
        boolean end = one.end;
        one.end = other.end;
        other.end = end;
        int count = one.count;
        one.count = other.count;
        other.count = count;
        //交换节点指针
        TreeNode middle = one.middle;
        one.middle = other.middle;
        other.middle = middle;
        one.middle.parent = one;
        other.middle.parent = other;
        return;
    }

    //替换节点
    private void replace(TreeNode source, TreeNode target) {
        if (target.parent.left == target)
            target.parent.left = source;
        else if (target.parent.right == target)
            target.parent.right = source;
        else
            target.parent.middle = source;
        source.parent = target.parent;
        return;
    }

    public int prefix(String string) {
        if (string == null || string.length() == 0)
            return -1;
        search(string);
        if (index != string.length())//最长前缀不是其自身
            return 0;
        return pointer.count;
    }

    public void hierarchy() {
        Queue<TreeNode> queue = new Queue<>();
        TreeNode node = root;
        queue.enter(node);
        queue.enter(new TreeNode('/'));
        while (!queue.isEmpty()) {
            node = queue.depart();
            if (node.character == '/') {
                if (queue.isEmpty())
                    break;
                queue.enter(node);
                System.out.println();
            }else {
                System.out.print(node.character + ":" + (node.end ? "T " : "F "));
                if (node.left != sentry)
                    queue.enter(node.left);
                if (node.middle != sentry)
                    queue.enter(node.middle);
                if (node.right != sentry)
                    queue.enter(node.right);
            }
        }
        System.out.println();
        return;
    }

    //深度优先遍历
    //出栈访问式
    public void depthTraverse() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.character + ":" + (node.end ? "T " : "F "));//出栈代表访问节点
            if (node.right != sentry)
                stack.push(node.right);
            if (node.middle != sentry)
                stack.push(node.middle);
            if (node.left != sentry)
                stack.push(node.left);
        }
        System.out.println();
        return;
    }

}