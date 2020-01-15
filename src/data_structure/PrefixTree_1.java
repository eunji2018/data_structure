/**
 * @Author eunji
 */
package data_structure;

/* 前缀树（字典树）的第一种实现
 * 利用字符串的公共前缀节约存储空间，字符串不重复存储，使用26个小写英文字符集
 * 前缀树性质：
 * 根节点没有对应的字符
 * 从根节点到某一节点路径上的字符拼接起来就是对应的字符串
 * 对于每个节点，其子节点上的字符都不相同
 * 树的结构与添加、删除元素的顺序无关
 */
public class PrefixTree_1 {

    private final TreeNode root;

    private static final char START = 'a';//字符集的首字符

    private static final int SIZE = 26;//字符集的大小

    private Stack<TreeNode> stack;//保存查询时遍历的节点

    //节点类
    private class TreeNode{

        public char character;//节点保存的字符
        public int count;//以根节点到此节点路径上的字符串为前缀的单词数目
        public boolean end;//终止节点为true（表示存在根节点到此节点路径上的字符串），非终止节点为false
        public TreeNode[] children;

        public TreeNode(char character) {
            this.character = character;
            this.count = 0;
            this.end = false;
            this.children = new TreeNode[SIZE];
        }
    }

    public PrefixTree_1() {
        this.root = new TreeNode('#');//根节点保存'#'字符，且不是终止节点
        this.stack = new Stack<>();
    }

    //前缀树中保存的字符串数目
    public int size() {
        return root.count;
    }

    //查找字符串
    //string只包含字符集内的字符，即不为null且不为空串
    //stack保存string在前缀树中的最长前缀对应的所有节点
    private boolean search(String string) {
        stack.clear();
        int index;
        TreeNode node = root;
        //从前向后依次查找string中的字符
        for (int i = 0; i < string.length(); i++) {
            index = string.charAt(i) - START;//当前字符在字符集中的位置
            if (node.children[index] == null)
                return false;
            node = node.children[index];
            stack.push(node);//对应节点入栈
        }
        return node.end;//终止节点表示存在此字符串，非终止节点表示不存在
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
        TreeNode node = stack.isEmpty() ? root : stack.peek();//指向最长前缀的最后一个字符，若不存在，则指向root
        if (stack.length() != string.length()) {//字符串在前缀树中的最长前缀不是其自身，构造新节点，标记节点，更新计数
            int index;
            for (int i = stack.length(); i < string.length(); i++) {//从最长前缀的下一个字符开始
                index = string.charAt(i) - START;//当前字符在字符集中的位置
                node.children[index] = new TreeNode(string.charAt(i));//构造新节点
                node = node.children[index];
                stack.push(node);//可以看作一边构造新的节点，一边遍历新的节点
            }
        }
        node.end = true;//string最后一个字符对应的节点成为新的终止节点
        while (!stack.isEmpty()) {
            node = stack.pop();
            node.count++;//更新计数
        }
        root.count++;
        return;
    }

    //删除字符串
    //若字符串不存在，则返回
    public void delete(String string) {
        if (string == null || string.length() == 0 || !search(string))
            return;
        //字符串存在，标记节点，更新计数，删除不必要的节点
        TreeNode node, temp;
        stack.peek().end = false;//stack不为空，string最后一个字符对应的节点成为新的非终止节点
        while (!stack.isEmpty()) {
            node = stack.pop();
            node.count--;//更新计数
            if (node.count == 0) {//若当前节点不是终止节点且没有子节点（count为0），则删除
                temp = stack.isEmpty() ? root : stack.peek();
                temp.children[node.character-START] = null;//删除节点
            }
        }
        root.count--;
        return;
    }

    //返回前缀树中存在的以string为前缀的字符串数目
    public int prefix(String string) {
        if (string == null || string.length() == 0)
            return -1;
        search(string);
        if (stack.length() != string.length())//若字符串的最长前缀不是其自身
            return 0;
        return stack.peek().count;//stack不为空
    }

    //层次遍历（从左到右）
    public void hierarchy() {
        Queue<TreeNode> queue = new Queue<>();
        TreeNode node = root;
        queue.enter(node);
        queue.enter(new TreeNode('/'));//虚拟节点，表示当前层遍历完毕
        while (!queue.isEmpty()) {
            node = queue.depart();
            if (node.character == '/') {//当前层遍历完毕
                if (queue.isEmpty())//所有节点遍历完毕
                    break;
                queue.enter(node);//代表每层最后的虚拟节点
                System.out.println();
            }else {
                System.out.print(node.character + ":" + (node.end ? "T " : "F "));
                for (int i = 0; i < SIZE; i++) {
                    if (node.children[i] != null)
                        queue.enter(node.children[i]);
                }
            }
        }
        System.out.println();
        return;
    }

    //深度优先遍历
    //入栈访问式
    public void depthTraverse1() {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> other = new Stack<>();//保存对应节点在父节点children数组中的位置，入栈、出栈与stack同步
        TreeNode node = root;
        stack.push(node);
        other.push(-1);
        int index = -1;
        while (!stack.isEmpty()) {
            node = stack.peek();//取栈顶
            //将此节点的子节点中未被访问过的【第一个】节点加入栈，从index + 1位置开始遍历子节点
            for (index = index + 1; index < SIZE; index++) {
                if (node.children[index] != null) {
                    node = node.children[index];
                    stack.push(node);
                    other.push(index);
                    System.out.print(node.character + ":" + (node.end ? "T " : "F "));//入栈代表访问节点
                    index = -1;//新节点从第一个位置开始遍历子节点
                    break;
                }
            }
            if (index == SIZE) {//所有子节点都被访问过，则出栈
                stack.pop();
                index = other.pop();//当前节点在父节点children数组中的位置
            }
        }
        System.out.println();
        return;
    }

    //深度优先遍历
    //出栈访问式
    public void depthTraverse2() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.character + ":" + (node.end ? "T " : "F "));//出栈代表访问节点
            for (int i = SIZE - 1; i >= 0; i--) {
                if (node.children[i] != null)
                    stack.push(node.children[i]);
            }
        }
        return;
    }

}
