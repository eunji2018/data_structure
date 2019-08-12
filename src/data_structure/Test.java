/**
 * @Author eunji
 */
package data_structure;

public class Test {

	public static void main(String[] args) {
//	    test_AVL();
//	    test_DoubleQueue();
//		test_UndirectedGraph();
//		test_DirectedGraph();
//		test_Heap();
//		test_LinkedList();
//		test_PrefixTree();
//		test_PriorityQueue();
//		test_Queue();
//		test_RBT_1();
//		test_SingleList();
//		test_SkipList();
//		test_SplayTree();
//		test_Stack();
//		test_UnionFindSet();

	}

    //平衡查找树测试
    public static void test_AVL() {
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
        graph.breadthTraverse(0).print();
        graph.breadthTraverse(5).print();
        graph.depthTraverse(0).print();
        graph.depthTraverse_recursive(0).print();
        graph.depthTraverse(5).print();
        graph.depthTraverse_recursive(5).print();
        graph.minSpanTree_K().print();
        graph.minSpanTree_P(0).print();
        graph.minSpanTree_P(5).print();
        graph.shortPath_D(0).print();
        graph.shortPath_D(5).print();

        System.out.println();
        System.out.println("删除");
        graph.delete(3,6);
        graph.delete(4,5);
        graph.print();
        graph.breadthTraverse(0).print();
        graph.breadthTraverse(5).print();
        graph.depthTraverse(0).print();
        graph.depthTraverse_recursive(0).print();
        graph.depthTraverse(5).print();
        graph.depthTraverse_recursive(5).print();
        graph.minSpanTree_K().print();
        graph.minSpanTree_P(0).print();
        graph.minSpanTree_P(5).print();
        graph.shortPath_D(0).print();
        graph.shortPath_D(5).print();

    }

    //有向图测试
    public static void test_DirectedGraph() {
        int [][] array = {{0,1,3},{0,2,5},{0,3,6},{0,4,7},{1,3,2},{2,4,3},{3,5,2},{3,6,3},
                {4,5,2},{4,6,3},{5,7,5},{5,9,7},{6,8,4},{6,9,5},{7,9,4},{8,9,3}};
        Graph graph = new Graph(true,10);
        for (int i = 0; i < array.length; i++)
            graph.insert(array[i]);
        graph.print();
        graph.breadthTraverse(0).print();
        graph.breadthTraverse(5).print();
        graph.depthTraverse(0).print();
        graph.depthTraverse_recursive(0).print();
        graph.depthTraverse(5).print();
        graph.depthTraverse_recursive(5).print();
        graph.topologicalSort().print();
    }

    //堆测试
    public static void test_Heap() {
	    //正序堆测试
        System.out.println("正序");
	    Heap heap = new Heap(true);
	    heap.push(0);
        System.out.println(heap.size());
        System.out.println(heap.isEmpty());
        heap.hierarchy();

	    for (int i = 1; i < 20; i++)
	        heap.push(i);
        System.out.println();
        System.out.println("初始");
	    System.out.println(heap.size());
        System.out.println(heap.isEmpty());
	    heap.hierarchy();

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(heap.pop());
        System.out.println(heap.size());
        System.out.println(heap.isEmpty());
        heap.hierarchy();

        System.out.println();
        System.out.println("清空");
        heap.clear();
        System.out.println(heap.size());
        System.out.println(heap.isEmpty());
        heap.hierarchy();

        //逆序堆测试
        System.out.println("逆序");
        heap = new Heap(false);
        heap.push(0);
        System.out.println(heap.size());
        System.out.println(heap.isEmpty());
        heap.hierarchy();

        for (int i = 1; i < 20; i++)
            heap.push(i);
        System.out.println();
        System.out.println("初始");
        System.out.println(heap.size());
        System.out.println(heap.isEmpty());
        heap.hierarchy();

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(heap.pop());
        System.out.println(heap.size());
        System.out.println(heap.isEmpty());
        heap.hierarchy();

        System.out.println();
        System.out.println("清空");
        heap.clear();
        System.out.println(heap.size());
        System.out.println(heap.isEmpty());
        heap.hierarchy();

    }

    //双向链表测试
    public static void test_LinkedList() {
	    LinkedList<Integer> list = new LinkedList<>();
	    for (int i = 0; i < 10; i++)
	        list.insert(i);
        System.out.println("初始");
        System.out.println(list.length());
        System.out.println(list.isEmpty());
        list.print();

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 5; i++)
            System.out.println(list.remove());
        System.out.println(list.length());
        System.out.println(list.isEmpty());
        list.print();

        System.out.println();
        System.out.println("清空");
        list.clear();
        System.out.println(list.length());
        System.out.println(list.isEmpty());
        list.print();

    }

    //前缀树测试
    public static void test_PrefixTree() {
        PrefixTree tree = new PrefixTree();
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
        tree.depthTraverse();

        System.out.println();
        System.out.println("删除");
        tree.delete("meitu");
        tree.delete("tuan");
        System.out.println(tree.size());
        System.out.println(tree.contains("meitu"));
        System.out.println(tree.prefix("mei"));
        System.out.println(tree.prefix("t"));
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

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println();
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

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println();
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

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println();
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

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.print();

        System.out.println();
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
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.print();

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 10; i++)
            System.out.println(queue.depart());
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.print();

        System.out.println();
        System.out.println("清空");
        queue.clear();
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.print();

    }

    //红黑树测试
    public static void test_RBT_1() {
        int [] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        RBT_1 rbt = new RBT_1();
        for(int i = 0; i < array.length; i++)
            rbt.insert(array[i]);
        System.out.println("初始");
        System.out.println(rbt.size());
        rbt.preTraverse();
        rbt.inTraverse();
        rbt.postTraverse();

        System.out.println();
        System.out.println("删除");

    }

	//单向链表测试
	public static void test_SingleList() {
		SingleList<Integer> list = new SingleList<>();
		for(int i = 0; i < 20; i++)
			list.insert(i);
        System.out.println("初始");
        System.out.println(list.isEmpty());
        System.out.println(list.length());
		list.print();
        System.out.println();
		System.out.println(list.middle());
		System.out.println(list.reciprocal(7));

        System.out.println("删除");
        for (int i = 0; i < 5; i++)
            System.out.println(list.remove());
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println();
        System.out.println(list.middle());
        System.out.println(list.reciprocal(5));

        System.out.println("逆置");
		list.inverse();
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println();
        System.out.println(list.middle());
        System.out.println(list.reciprocal(7));

        System.out.println("逆置");
        list.inverse_recursive();
        System.out.println(list.isEmpty());
        System.out.println(list.length());
        list.print();
        System.out.println();
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
        for(int i = 0; i < 20; i++) {
            skipList.insert(i);
            skipList.print();
        }
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        for (int i = 0; i < 5; i++)
            skipList.trace(5 * i);

        System.out.println("删除");
        skipList.delete(0);
        skipList.delete(10);
        skipList.delete(19);
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();
        for (int i = 0; i < 5; i++)
            skipList.trace(5 * i);

        System.out.println("清空");
        skipList.clear();
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();
        skipList.trace(0);

        //逆序跳跃表测试
        System.out.println("逆序");
        skipList = new SkipList(4, 3, false);
        System.out.println("初始");
        for(int i = 0; i < 20; i++) {
            skipList.insert(i);
            skipList.print();
        }
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        for (int i = 0; i < 5; i++)
            skipList.trace(5 * i);

        System.out.println("删除");
        skipList.delete(0);
        skipList.delete(10);
        skipList.delete(19);
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();
        for (int i = 0; i < 5; i++)
            skipList.trace(5 * i);

        System.out.println("清空");
        skipList.clear();
        System.out.println(skipList.isEmpty());
        System.out.println(skipList.length());
        System.out.println(skipList.size());
        skipList.print();
        skipList.trace(0);

    }

    //伸展树测试
    public static void test_SplayTree() {
	    SplayTree tree = new SplayTree();
	    for (int i = 0; i < 10; i++)
	        tree.insert(i);
        System.out.println("初始");
        System.out.println(tree.isEmpty());
        System.out.println(tree.size());
        tree.hierarchy();
        tree.inOrder();
        for (int i = 0; i < 6; i++) {
            System.out.println();
            System.out.println(tree.search(2 * i));
            tree.hierarchy();
            tree.inOrder();
        }

        System.out.println();
        System.out.println("删除");
        tree.delete(1);
        tree.delete(4);
        tree.delete(7);
        tree.delete(10);
        System.out.println(tree.isEmpty());
        System.out.println(tree.size());
        tree.hierarchy();
        tree.inOrder();

        System.out.println();
        System.out.println("清空");
        tree.clear();
        System.out.println(tree.isEmpty());
        System.out.println(tree.size());
        tree.hierarchy();
        tree.inOrder();

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

        System.out.println();
        System.out.println("删除");
        for (int i = 0; i < 5; i++)
            System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.length());
        stack.print();

        System.out.println();
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
        set.print();
        System.out.println();
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
        set.print();
        System.out.println();
        System.out.println(set.connected(0,9));
        System.out.println(set.connected(3,6));

        System.out.println("清空");
        set.clear();
        System.out.println(set.isEmpty());
        System.out.println(set.size());
        System.out.println(set.count());
        set.print();

    }
	
}
