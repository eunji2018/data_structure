/**
 * @Author eunji
 */
package data_structure;

//并查集的有序单向链表实现
public class UnionFindSet {

    private Node head;//头节点

    private int size;//并查集中所有集合的元素个数

    private int count;//并查集中集合的个数

    private int maximum;//所有集合包含元素个数的最大值

    //节点类
    private static class Node {

        public int index;//节点代表的元素
        public int rank;//节点的秩，即以节点为根的子树包含的节点个数
        public Node next;
        public Node parent;//父节点，指向自身表示节点是其所在集合的代表元素

        public Node(int index) {
            this.index = index;
            this.rank = 1;
            this.next = null;
            this.parent = this;//新节点构成一个新的集合
        }
    }

    public UnionFindSet() {
        this.head = new Node(-1);//头节点不保存元素
        this.size = 0;
        this.count = 0;
        this.maximum = 0;
    }

    public int size() {
        return size;
    }

    public int count() {
        return count;
    }

    public int maximum() {
        return maximum;
    }

    //判空
    public boolean isEmpty() {
        return size == 0;
    }

    //清空
    public void clear() {
        head.next = null;
        size = 0;
        count = 0;
        maximum = 0;
        return;
    }

    //查找给定元素对应节点的前一个节点，包含头节点
    private Node search(int index) {
        Node node = head;
        while (node.next != null) {
            if (node.next.index >= index)
                break;
            node = node.next;
        }
        return node;
    }

    //添加元素
    public void insert(int index) {
        Node temp = search(index);
        if (temp.next != null && temp.next.index == index)//元素已存在
            return;
        Node node = new Node(index);
        node.next = temp.next;
        temp.next = node;
        size++;
        count++;
        maximum = (1 > maximum) ? 1 : maximum;
        return;
    }

    //查找节点所在集合的代表元素
    private Node root(Node node) {
        while (node.parent != node)//节点不是其所在集合的代表元素
            node = node.parent;
        return node;
    }

    //查找元素所在的集合，元素已存在
    private int find(int index) {
        Node node = search(index).next;
        Node root = root(node);
        //路径优化：将node到root路径上的所有节点直接指向root节点
        int decrement = 0;
        Node temp = node.parent;
        while (temp != root) {
            decrement += node.rank;
            node.parent = root;
            temp.rank -= decrement;
            node = temp;
            temp = node.parent;
        }
        return root.index;
    }

    //判断两个元素是否连通，即是否位于同一个集合
    public boolean connected(int one, int other) {
        return find(one) == find(other);
    }

    //合并两个元素所在的集合，元素都已存在
    //一般情况下，应该先调用connected()方法判断是否位于同一集合，再调用union()方法合并集合
    public void union(int one, int other) {
        Node root1 = search(one).next;
        root1 = root(root1);
        Node root2 = search(other).next;
        root2 = root(root2);
        if (root1 != root2) {
            if (root1.rank < root2.rank) {
                Node temp = root1;
                root1 = root2;
                root2 = temp;
            }
            root2.parent = root1;
            root1.rank += root2.rank;
            maximum = (root1.rank > maximum) ? root1.rank : maximum;
            count--;
        }
        return;
    }

    //打印
    public void print() {
        Node node = head.next;
        while (node != null) {
            System.out.print("(" + node.index + " " + node.rank + " " + node.parent.index + ") ");
            node = node.next;
        }
        System.out.println();
        return;
    }

}
