/*
    @Author：eunji
 */
package data_structure;

public class Test {

	public static void main(String[] args) {
//	    test_AVL();
//		test_UndirectedGraph();
//		test_DirectedGraph();
		test_Heap();
//		test_LinkedList();
//		test_PrefixTree();
//		test_Queue();
//		test_RBT();
//		test_SingleList();
//		test_SkipList();
//		test_Stack();

	}

    //平衡查找树测试
    public static void test_AVL() {
        int [] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        AVL avl = new AVL();
        for(int i = 0; i < array.length; i++)
            avl.insert(array[i]);
//		avl.preOrder();
//		avl.inOrder();
//		avl.mirror();
//		avl.preOrder();
//		avl.inOrder();
//		avl.mirror_recursive();
//		avl.preOrder();
//		avl.inOrder();
        avl.hierarchy_inturn_stack();
        avl.hierarchy_inturn_queue();
    }

    //双端队列测试
    public static void test_DoubleQueue() {
    }

    //无向图测试
    public static void test_UndirectedGraph() {
        //{from,to,weight}
        int [][] array = {{0,1,3},{0,2,5},{0,3,6},{0,4,7},{1,3,2},{2,4,3},{3,5,2},{3,6,3},
                {4,5,2},{4,6,3},{5,7,5},{5,9,7},{6,8,4},{6,9,5},{7,9,4},{8,9,3}};
        Graph graph = new Graph(10, array);
        graph.print();

//		int [] trace1 = graph.breadthFirstTraverse(0);
//		for(int i = 0; i < trace1.length; i++)
//			System.out.print(trace1[i] + " ");
//		System.out.println();
//		int [] trace2 = graph.breadthFirstTraverse(5);
//		for(int i = 0; i < trace2.length; i++)
//			System.out.print(trace2[i] + " ");
//		System.out.println();
//		int [] trace3 = graph.depthFirstTraverse(0);
//		for(int i = 0; i < trace3.length; i++)
//			System.out.print(trace3[i] + " ");
//		System.out.println();
//		int [] trace4 = graph.depthFirstTraverse(5);
//		for(int i = 0; i < trace4.length; i++)
//			System.out.print(trace4[i] + " ");
    }

    //有向图测试
    public static void test_DirectedGraph() {
        int [][] array = {{0,1,3},{0,2,5},{0,3,6},{0,4,7},{1,3,2},{2,4,3},{3,5,2},{3,6,3},
                {4,5,2},{4,6,3},{5,7,5},{5,9,7},{6,8,4},{6,9,5},{7,9,4},{8,9,3}};
        Graph graph = new Graph(10, array);
        graph.print();
//		int [] trace1 = graph.breadthFirstTraverse(0);
//		if(trace1 != null)
//			for(int i = 0; i < trace1.length; i++)
//				System.out.print(trace1[i] + " ");
//		System.out.println();
//		int [] trace2 = graph.breadthFirstTraverse(5);
//		if(trace2 != null)
//			for(int i = 0; i < trace2.length; i++)
//				System.out.print(trace2[i] + " ");
//		System.out.println();
//		int [] trace3 = graph.depthFirstTraverse(0);
//		if(trace3 != null)
//			for(int i = 0; i < trace3.length; i++)
//				System.out.print(trace3[i] + " ");
//		System.out.println();
//		int [] trace4 = graph.depthFirstTraverse(5);
//		if(trace4 != null)
//			for(int i = 0; i < trace4.length; i++)
//				System.out.print(trace4[i] + " ");
//		System.out.println();
//		int [] trace5 = graph.topologicalSort();
//		if(trace5 != null)
//			for(int i = 0; i < trace5.length; i++)
//				System.out.print(trace5[i] + " ");
    }

    //堆测试
    public static void test_Heap() {
	    Heap heap = new Heap();
	    for (int i = 0; i < 20; i++)
	        heap.push(i);
	    System.out.println(heap.size());
	    heap.print();
	    heap.pop();
	    heap.print();
    }

    //双向链表测试
    public static void test_LinkedList() {
    }

    //前缀树测试
    public static void test_PrefixTree() {
        PrefixTree tree = new PrefixTree();
        tree.insert("ca");
        tree.insert("can");
        tree.insert("f");
        tree.insert("fei");
        tree.insert("fen");
        tree.insert("mei");
        tree.insert("meitu");
        tree.insert("t");
        tree.insert("tuan");
        tree.insert("tui");
        System.out.println(tree.size());
        System.out.println(tree.contains("meitu"));
        System.out.println(tree.contains("meituan"));
        System.out.println(tree.prefix("mei"));
        System.out.println(tree.prefix("t"));
        System.out.println(tree.prefix("c"));
        tree.hierarchy();
        tree.depthTraverse();
        System.out.println();
        tree.delete("meitu");
        tree.delete("tuan");
        System.out.println(tree.size());
        System.out.println(tree.contains("meitu"));
        System.out.println(tree.contains("tuan"));
        tree.hierarchy();
        tree.depthTraverse();
    }

    //优先队列测试
    public static void test_PriorityQueue() {

    }

    //队列测试
    public static void test_Queue() {
    }

    //红黑树测试
    public static void test_RBT() {
        int [] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        RBT rbt = new RBT();
        for(int i = 0; i < array.length; i++) {
            rbt.insert(array[i]);
            rbt.preTraverse();
            System.out.println();
            rbt.inTraverse();
            System.out.println();
            rbt.postTraverse();
            System.out.println();
        }
    }

	//单向链表测试
	public static void test_SingleList() {
		SingleList<Integer> list = new SingleList<>();
		for(int i = 0; i < 10; i++)
			list.insert(i);
		list.print();
		System.out.println(list.middle());
		System.out.println(list.reciprocal(3));
		list.inverse();
		list.print();
		System.out.println(list.middle());
		System.out.println(list.reciprocal(6));
		list.inverse_recursive();
		list.print();
		System.out.println(list.middle());
		System.out.println(list.reciprocal(9));
		for(int i = 0; i < 3; i++)
			list.remove();
		list.print();
		System.out.println(list.middle());
		System.out.println(list.reciprocal(5));
	}

    //跳跃表测试
    public static void test_SkipList() {
        SkipList skipList = new SkipList(4, 4);
        for(int i = 0; i < 100; i++) {
            skipList.insert(i);
            skipList.print();
            System.out.println("-----");
        }
        skipList.trace(0);
        System.out.println();
        skipList.trace(25);
        System.out.println();
        skipList.trace(50);
        System.out.println();
        skipList.trace(99);
        skipList.delete(0);
        skipList.delete(25);
        skipList.delete(50);
        skipList.delete(99);
        System.out.println();
        skipList.print();
        System.out.println("-----");
        skipList.clear();
        skipList.print();
    }

	
	//栈测试
	public static void test_Stack() {
	}
	
}

