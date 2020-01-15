/**
 * @Author eunji
 */
package data_structure;

public class Test {

    public static void main(String[] args) {
//        test();
//        test_DoubleQueue();
//        test_UndirectedGraph();
//        test_DirectedGraph();
//        test_Heap();
//        test_LinkedList();
//        test_PrefixTree_1();
//        test_PrefixTree_2();
//        test_PriorityQueue();
//        test_Queue();
//        test_RBT_1();
//        test_RBT_2();
//        test_SingleList();
//        test_SkipList();
//        test_Stack();
//        test_UnionFindSet();
    }

    //测试
    public static void test() {

    }

    //双端队列测试
    public static void test_DoubleQueue() {
        DoubleQueue<Integer> queue = new DoubleQueue<>();
        for (int i = 0; i < 6; i++)
            queue.enterHead(i);
        for (int i = 6; i < 15; i++)
            queue.enterTail(i);
        System.out.println("初始");
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.print();

        System.out.println("删除");
        for (int i = 0; i < 3; i++)
            System.out.println(queue.departHead());
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.print();

        System.out.println("删除");
        for (int i = 0; i < 5; i++)
            System.out.println(queue.departTail());
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.print();

        System.out.println("清空");
        queue.clear();
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.print();

    }

    //无向图测试
    public static void test_UndirectedGraph() {
        //{from,to,weight}
        int [][] array = {{0,1,3},{0,2,5},{0,3,6},{0,4,7},{1,3,2},{2,4,3},{3,5,2},{3,6,3},
                {4,5,2},{4,6,3},{5,7,5},{5,9,7},{6,8,4},{6,9,5},{7,9,4},{8,9,3}};
        Graph graph = new Graph(false,10);
        for (int i = 0; i < array.length; i++)
            graph.insert(array[i]);
        System.out.println("初始");
        graph.print();
        System.out.println("广度优先遍历");
        graph.breadthTraverse(0).print();
        graph.breadthTraverse(5).print();
        System.out.println("深度优先遍历");
        graph.depthTraverse(0).print();
        graph.depthTraverse_recursive(0).print();
        graph.depthTraverse(5).print();
        graph.depthTraverse_recursive(5).print();
        System.out.println("最小生成树");
        graph.minSpanTree_K().print();
        graph.minSpanTree_P(0).print();
        graph.minSpanTree_P(5).print();
        System.out.println("最短路径");
        graph.shortPath_D(0).print();
        graph.shortPath_D(5).print();

        System.out.println("删除");
        graph.delete(3,6);
        graph.delete(4,5);
        graph.print();
        System.out.println("广度优先遍历");
        graph.breadthTraverse(0).print();
        graph.breadthTraverse(5).print();
        System.out.println("深度优先遍历");
        graph.depthTraverse(0).print();
        graph.depthTraverse_recursive(0).print();
        graph.depthTraverse(5).print();
        graph.depthTraverse_recursive(5).print();
        System.out.println("最小生成树");
        graph.minSpanTree_K().print();
        graph.minSpanTree_P(0).print();
        graph.minSpanTree_P(5).print();
        System.out.println("最短路径");
        graph.shortPath_D(0).print();
        graph.shortPath_D(5).print();

    }

    //有向图测试1
    public static void test_DirectedGraph() {
        int [][] array = {{0,1,3},{0,2,5},{0,3,6},{0,4,7},{1,3,2},{2,4,3},{3,5,2},{3,6,3},
                {4,5,2},{4,6,3},{5,7,5},{5,9,7},{6,8,4},{6,9,5},{7,9,4},{8,9,3}};
        Graph graph = new Graph(true,10);
        for (int i = 0; i < array.length; i++)
            graph.insert(array[i]);
        System.out.println("初始");
        graph.print();
        System.out.println("广度优先遍历");
        graph.breadthTraverse(0).print();
        graph.breadthTraverse(5).print();
        System.out.println("深度优先遍历");
        graph.depthTraverse(0).print();
        graph.depthTraverse_recursive(0).print();
        graph.depthTraverse(5).print();
        graph.depthTraverse_recursive(5).print();
        System.out.println("最短路径");
        graph.shortPath_D(0).print();
        graph.shortPath_D(5).print();
        System.out.println("拓扑排序");
        graph.topologicalSort().print();

        System.out.println("删除");
        graph.delete(3,5);
        graph.delete(3,6);
        graph.delete(4,5);
        graph.delete(4,6);
        graph.print();
        System.out.println("广度优先遍历");
        graph.breadthTraverse(0).print();
        graph.breadthTraverse(5).print();
        System.out.println("深度优先遍历");
        graph.depthTraverse(0).print();
        graph.depthTraverse_recursive(0).print();
        graph.depthTraverse(5).print();
        graph.depthTraverse_recursive(5).print();
        System.out.println("最短路径");
        graph.shortPath_D(0).print();
        graph.shortPath_D(5).print();
        System.out.println("拓扑排序");
        graph.topologicalSort().print();

        System.out.println("---");
        //不存在可以到达的负权重环
        int [][] array1 = {{0,1,3},{0,2,4},{1,3,5},{1,4,-2},{2,3,-1},{2,4,-3},
                {3,1,-2},{4,0,1},{4,3,5},{5,6,2},{6,5,-4}};
        graph = new Graph(true,7);
        for (int i = 0; i < array1.length; i++)
            graph.insert(array1[i]);
        graph.print();
        System.out.println(graph.shortPath_B(0));

        System.out.println("---");
        //存在可以到达的负权重环
        int [][] array2 = {{0,1,3},{0,2,4},{1,3,5},{1,4,-4},{2,3,-1},{2,4,-3},
                {3,1,-2},{4,0,1},{4,3,5},{5,6,2},{6,5,-4}};
        graph = new Graph(true,7);
        for (int i = 0; i < array2.length; i++)
            graph.insert(array2[i]);
        graph.print();
        System.out.println(graph.shortPath_B(0));
    }

    //堆测试
    public static void test_Heap() {
        //正序堆测试
        System.out.println("正序");
        Heap heap = new Heap(true);
        heap.push(0);
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

        for (int i = 1; i < 20; i++)
            heap.push(i);
        System.out.println("初始");
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(heap.pop());
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

        System.out.println("清空");
        heap.clear();
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

        //逆序堆测试
        System.out.println("逆序");
        heap = new Heap(false);
        heap.push(0);
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

        for (int i = 1; i < 20; i++)
            heap.push(i);
        System.out.println("初始");
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(heap.pop());
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

        System.out.println("清空");
        heap.clear();
        System.out.println(heap.isEmpty());
        System.out.println(heap.size());
        heap.hierarchy();

    }

    //双向链表测试
    public static void test_LinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 9; i >= 0; i--)
            list.insertHead(i);
        for (int i = 9; i >= 0; i--)
            list.insertTail(i);
        System.out.println("初始");
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();

        System.out.println("添加");
        for (int i = 0; i < 5; i++)
            list.insert(i, i);
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();

        System.out.println("删除");
        System.out.println(list.removeHead());
        System.out.println(list.removeTail());
        System.out.println(list.remove(5));
        System.out.println(list.remove(10));
        System.out.println(list.removeFirst(10));
        System.out.println(list.removeFirst(1));
        System.out.println(list.removeLast(1));
        System.out.println(list.removeLast(1));
        System.out.println(list.removeFirst(5));
        System.out.println(list.removeFirst(5));
        System.out.println(list.removeLast(5));

        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();

        System.out.println("清空");
        list.clear();
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();

    }

    //第一种前缀树测试
    public static void test_PrefixTree_1() {
        PrefixTree_1 tree = new PrefixTree_1();
        String [] strings = {"ca", "can", "f", "fei", "fen", "mei", "meitu", "t", "tuan", "tui"};
        for (int i = 0; i < strings.length; i++)
            tree.insert(strings[i]);
        System.out.println("初始");
        System.out.println(tree.size());
        System.out.println(tree.contains("meitu"));
        System.out.println(tree.contains("meituan"));
        System.out.println(tree.prefix("mei"));
        System.out.println(tree.prefix("t"));
        tree.hierarchy();
        tree.depthTraverse1();
        tree.depthTraverse2();

        System.out.println("删除");
        tree.delete("meitu");
        tree.delete("tuan");
        System.out.println(tree.size());
        System.out.println(tree.contains("meitu"));
        System.out.println(tree.prefix("mei"));
        System.out.println(tree.prefix("t"));
        tree.hierarchy();
        tree.depthTraverse1();
        tree.depthTraverse2();

    }

    //第二种前缀树测试
    public static void test_PrefixTree_2() {
        PrefixTree_2 tree = new PrefixTree_2();
        String [] strings1 = {"fen", "feng", "fan", "fou", "bei", "mei", "mi"};
        for (int i = 0; i < strings1.length; i++)
            tree.insert(strings1[i]);
        System.out.println("初始");
        System.out.println(tree.size());
        System.out.println(tree.contains("fan"));
        System.out.println(tree.contains("min"));
        System.out.println(tree.prefix("fe"));
        System.out.println(tree.prefix("mai"));
        tree.hierarchy();
        tree.depthTraverse();

        System.out.println("删除");
        tree.delete("bei");
        tree.delete("mei");
        tree.delete("fen");
        tree.delete("feng");
        tree.delete("fan");
        tree.delete("fou");
        tree.delete("mi");
        System.out.println(tree.size());
        tree.hierarchy();
        tree.depthTraverse();

        String [] strings2 = {"bei", "fan", "fen", "feng", "fou", "mei", "mi"};
        for (int i = 0; i < strings2.length; i++)
            tree.insert(strings2[i]);
        System.out.println("初始");
        System.out.println(tree.size());
        System.out.println(tree.contains("fen"));
        System.out.println(tree.contains("ming"));
        System.out.println(tree.prefix("fe"));
        System.out.println(tree.prefix("mai"));
        tree.hierarchy();
        tree.depthTraverse();

        System.out.println("删除");
        tree.delete("bei");
        tree.delete("fan");
        tree.delete("fen");
        tree.delete("feng");
        tree.delete("fou");
        tree.delete("mei");
        tree.delete("mi");
        System.out.println(tree.size());
        tree.hierarchy();
        tree.depthTraverse();

    }

    //优先队列测试
    public static void test_PriorityQueue() {
        //正序优先队列测试
        System.out.println("正序");
        PriorityQueue queue = new PriorityQueue(true);
        for (char c = 'a'; c <= 'z'; c++)
            queue.enter(c);
        System.out.println("初始");
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("清空");
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        for (int i = 0; i < 20; i++)
            queue.enter(i);
        System.out.println("初始");
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("清空");
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        //逆序优先队列测试
        System.out.println("逆序");
        queue = new PriorityQueue(false);
        for (char c = 'a'; c <= 'z'; c++)
            queue.enter(c);
        System.out.println("初始");
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("清空");
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        for (int i = 0; i < 20; i++)
            queue.enter(i);
        System.out.println("初始");
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("清空");
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

    }

    //队列测试
    public static void test_Queue() {
        Queue<Integer> queue = new Queue();
        for (int i = 0; i < 20; i++)
            queue.enter(i);
        System.out.println("初始");
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println("清空");
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

    }

    //第一种红黑树测试
    public static void test_RBT_1() {
        int [] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        RBT_1 rbt = new RBT_1();
        for (int i = 0; i < array.length; i++)
            rbt.insert(array[i]);
        System.out.println("初始");
        System.out.println(rbt.size());
        System.out.println(rbt.height());
        System.out.println("Morris遍历");
        rbt.morrisTraverse();
        rbt.morrisPreTraverse();
        rbt.morrisInTraverse();
        rbt.morrisPostTraverse();
        System.out.println("非递归遍历");
        rbt.preTraverse();
        rbt.inTraverse();
        rbt.postTraverse_one();
        rbt.postTraverse_two();
        System.out.println("层次遍历");
        rbt.hierarchy_queue();
        rbt.hierarchy_inturn_stack();
        rbt.hierarchy_inturn_queue();
        System.out.println("最近公共父节点");
        System.out.println(rbt.shared(0,20));
        System.out.println(rbt.shared(1,19));
        System.out.println(rbt.shared(2,6));
        System.out.println(rbt.shared(9,14));

        System.out.println("删除");
        for (int i = 0; i < 5; i++) {
            rbt.deleteMin();
            System.out.println(rbt.size());
            System.out.println(rbt.height());
            rbt.inTraverse();
            rbt.hierarchy_queue();
        }
        for (int i = 0; i < 5; i++) {
            rbt.deleteMax();
            System.out.println(rbt.size());
            System.out.println(rbt.height());
            rbt.inTraverse();
            rbt.hierarchy_queue();
        }
        for (int i = 0; i < 5; i++) {
            rbt.delete(5 + 2 * i);
            System.out.println(rbt.size());
            System.out.println(rbt.height());
            rbt.inTraverse();
            rbt.hierarchy_queue();
        }
        rbt.delete(6);
        rbt.delete(8);
        rbt.delete(10);
        rbt.delete(12);
        rbt.delete(14);
        System.out.println(rbt.size());
        System.out.println(rbt.height());
        rbt.inTraverse();
        rbt.hierarchy_queue();

    }

    //第二种红黑树测试
    public static void test_RBT_2() {
        int [] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        RBT_2 rbt = new RBT_2();
        for (int i = 0; i < array.length; i++)
            rbt.insert(array[i]);
        System.out.println("初始");
        System.out.println(rbt.size());
        System.out.println(rbt.height());
        System.out.println("遍历");
        rbt.preTraverse();
        rbt.inTraverse();
        rbt.postTraverse();
        rbt.hierarchy();

        System.out.println("删除");
        for (int i = 0; i < 5; i++) {
            rbt.deleteMin();
            System.out.println(rbt.size());
            System.out.println(rbt.height());
            rbt.inTraverse();
            rbt.hierarchy();
        }
        for (int i = 0; i < 5; i++) {
            rbt.deleteMax();
            System.out.println(rbt.size());
            System.out.println(rbt.height());
            rbt.inTraverse();
            rbt.hierarchy();
        }
        for (int i = 0; i < 5; i++) {
            rbt.delete(5 + 2 * i);
            System.out.println(rbt.size());
            System.out.println(rbt.height());
            rbt.inTraverse();
            rbt.hierarchy();
        }
        rbt.delete(6);
        rbt.delete(8);
        rbt.delete(10);
        rbt.delete(12);
        rbt.delete(14);
        System.out.println(rbt.size());
        System.out.println(rbt.height());
        rbt.inTraverse();
        rbt.hierarchy();

    }

    //单向链表测试
    public static void test_SingleList() {
        SingleList<Integer> list = new SingleList<>();
        for (int i = 0; i < 20; i++)
            list.insert(i);
        System.out.println("初始");
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println(list.middle());
        System.out.println(list.reciprocal(7));

        System.out.println("删除");
        for (int i = 0; i < 5; i++)
            System.out.println(list.remove());
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println(list.middle());
        System.out.println(list.reciprocal(5));

        System.out.println("逆置");
        list.inverse();
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println(list.middle());
        System.out.println(list.reciprocal(7));

        System.out.println("逆置");
        list.inverse_recursive();
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println(list.middle());
        System.out.println(list.reciprocal(3));

        System.out.println("清空");
        list.clear();
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println(list.middle());
        System.out.println(list.reciprocal(1));

    }

    //跳跃表测试
    public static void test_SkipList() {
        //正序跳跃表测试
        System.out.println("正序");
        SkipList skipList = new SkipList(4, 3, true);
        System.out.println("初始");
        for (int i = 0; i < 20; i++) {
            skipList.insert(i);
            skipList.print();
        }
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());

        System.out.println("删除");
        skipList.delete(0);
        skipList.delete(10);
        skipList.delete(19);
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();

        System.out.println("清空");
        skipList.clear();
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();

        //逆序跳跃表测试
        System.out.println("逆序");
        skipList = new SkipList(4, 3, false);
        System.out.println("初始");
        for (int i = 0; i < 20; i++) {
            skipList.insert(i);
            skipList.print();
        }
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());

        System.out.println("删除");
        skipList.delete(0);
        skipList.delete(10);
        skipList.delete(19);
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();

        System.out.println("清空");
        skipList.clear();
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();

    }

    //栈测试
    public static void test_Stack() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++)
            stack.push(i);
        System.out.println("初始");
        System.out.println(stack.isEmpty());
        System.out.println(stack.length());
        stack.print();

        System.out.println("删除");
        for (int i = 0; i < 5; i++)
            System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.length());
        stack.print();

        System.out.println("清空");
        stack.clear();
        System.out.println(stack.isEmpty());
        System.out.println(stack.length());
        stack.print();

    }

    //并查集测试
    public static void test_UnionFindSet() {
        UnionFindSet set = new UnionFindSet();
        for (int i = 0; i < 10; i++)
            set.insert(i);
        System.out.println("初始");
        System.out.println(set.isEmpty());
        System.out.println(set.size());
        System.out.println(set.count());
        System.out.println(set.maximum());
        set.print();
        System.out.println(set.connected(0,9));
        System.out.println(set.connected(3,6));

        System.out.println("合并");
        set.union(0,1);
        set.union(1,2);
        set.union(5,6);
        set.union(7,8);
        set.union(8,9);
        set.union(2,7);
        System.out.println(set.isEmpty());
        System.out.println(set.size());
        System.out.println(set.count());
        System.out.println(set.maximum());
        set.print();
        System.out.println(set.connected(0,9));
        System.out.println(set.connected(3,6));
        set.print();

        System.out.println("清空");
        set.clear();
        System.out.println(set.isEmpty());
        System.out.println(set.size());
        System.out.println(set.count());
        System.out.println(set.maximum());
        set.print();

    }
    
}
