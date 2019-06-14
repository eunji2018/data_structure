/*
    @Author：eunji
 */
package data_structure;

//图的邻接链表实现
//无向图只考虑连通图，有向图可以不强连通
//实现了添加，删除边，广度优先遍历，深度优先遍历，最小生成树，最短路径，拓扑排序的操作
public class Graph {

    private int size;//顶点数目

    private Edge[] edges;

	//边类（终点，权重，下一条边）
	private static class Edge {
		
		private int to;//终点
		private int weight;//边的权重
		private Edge next;//下一条边
		
		public Edge(int to, int weight, Edge next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public Graph(int size, int [][] edges) {
		this.size = size;
		this.edges = new Edge[size];
		for(int i = 0; i < edges.length; i++) {
//			insert_undirected(edges[i]);
			insert_directed(edges[i]);
		}
	}
	
	//添加边（无向图）
	public void insert_undirected(int [] array) {
		//{from,to,weight}
		int from = array[0];
		int to = array[1];
		int weight = array[2];
		//无向边具有两个方向，头插法
		Edge edge = new Edge(to, weight, edges[from]);
		edges[from] = edge;
		edge = new Edge(from, weight, edges[to]);
		edges[to] = edge;
		return;
	}
	
	//添加边（有向图）
	public void insert_directed(int [] array) {
		//{from,to,weight}
		int from = array[0];
		int to = array[1];
		int weight = array[2];
		//有向边只有一个方向，头插法
		Edge edge = new Edge(to, weight, edges[from]);
		edges[from] = edge;
		return;
	}
	
	//删除边（无向图）
	public void delete_undirected(int from, int to) {
		return;
	}
	
	//删除边（有向图）
	public void delete_directed(int from, int to) {
		return;
	}
	
	//打印邻接表-from: (to weight)
	public void print() {
		Edge edge = new Edge(0, 0, null);
		System.out.println();
		for(int i = 0; i < size; i++) {
			System.out.print(i + ": ");
			edge = edges[i];
			while(edge != null) {
				System.out.print("(" + edge.to + " " + edge.weight + ") ");
				edge = edge.next;
			}
			System.out.println();
		}
	}
	
	//对边按权重排序（无向图）
//	private static Edge[] sort_undirected(Graph graph) {
//		if(graph == null)
//			return null;
//		int size = graph.size;
//		boolean [] flag = new boolean[size];//true代表边已遍历过
//		Edge [] edges = new Edge[size*(size-1)/2];
//		int count = 0;//已排序的边数
//		Edge edge;
//		for(int i = 0; i < size; i++) {
//			edge = graph.edges[i];
//			while(edge != null) {
//				if(!flag[edge.to]) {//边未加入 
//					for(int j = count - 1; j >= 0; j--) {
//						if(edges[j].weight > edge.weight) {
//							edges[j+1] = edges[j];
//						}else {
//							edges[j+1] = edge;
//							break;
//						}
//					}
//					count++;
//				}
//				edge = edge.next;
//			}
//			flag[i] = true;
//		}
//		return null;
//	}
	
	//广度优先遍历（队列）
	public Queue<Integer> breadthFirstTraverse(int from) {
		boolean [] flag = new boolean[size];//true代表相应顶点已放入过队列，已被访问过
		Queue<Edge> queue = new Queue<>();
		Queue<Integer> other = new Queue<>();//访问顺序
		Edge edge = new Edge(from, 0, null);//虚拟边，方便起点入队
		queue.enter(edge);
		flag[from] = true;
		other.enter(from);
		while(!queue.isEmpty()) {
			edge = queue.depart();
			//将与此顶点邻接的且未被访问过的【所有】顶点加入队列
			for(edge = edges[edge.to]; edge != null; edge = edge.next) {
				if(!flag[edge.to]) {
					queue.enter(edge);
					flag[edge.to] = true;//入队代表访问顶点
					other.enter(edge.to);
				}
			}
		}
		return other;
	}
	
	//深度优先遍历：非递归式
	public Queue<Integer> depthFirstTraverse(int from) {
		boolean [] flag = new boolean[size];//true代表相应顶点已放入过栈，已被访问过
		Stack<Edge> stack = new Stack<>();
		Queue<Integer> queue = new Queue<>();//访问顺序
		Edge edge = new Edge(from, 0, null);//虚拟边，方便起点入栈
		stack.push(edge);
		flag[from] = true;
		queue.enter(from);
		while(!stack.isEmpty()) {
			edge = stack.peek();//取栈顶
			//将与此顶点邻接的且未被访问过的【第一个】顶点加入栈
			for(edge = edges[edge.to]; edge != null; edge = edge.next) {
				if(!flag[edge.to]) {
					stack.push(edge);
					flag[edge.to] = true;//入栈代表访问顶点
					queue.enter(edge.to);
					break;//找到第一个顶点
				}
			}
			//所有邻接顶点都被访问过，则出栈
			if(edge == null)
				stack.pop();
		}
		return queue;
	}
	
	//深度优先遍历：递归式
	public Queue<Integer> depthFirstTraverse_recursive(int from) {
		boolean [] flag = new boolean[size];//true代表相应顶点已放入过队列，已被访问过
		Queue<Integer> queue = new Queue<>();//访问顺序
		depthFirstTraverse_recursive(from, flag, queue);
		return queue;
	}
	
	private void depthFirstTraverse_recursive(int from, boolean [] flag, Queue<Integer> queue) {
		queue.enter(from);
		flag[from] = true;//入队代表访问顶点
		for(Edge edge = edges[from]; edge != null; edge = edge.next) {
			if(!flag[edge.to])
				depthFirstTraverse_recursive(edge.to, flag, queue);
		}
		return;
	}
	
	//最小生成树：P算法	单颗树的生长	无向图
	public Edge[] minSpanTree_P() {
		return null;
	}
	
	//最小生成树：K算法	多棵树的合并	无向图
	public Edge[] minSpanTree_K() {
//		if(graph == null)
//			return null;
//		int size = graph.size;
//		boolean [] flag = new boolean[size];//true代表当前顶点已加入生成树
//		Edge [] min = new Edge[size-1]; //保存生成树的边
//		Edge [] edges = sort_undirected(graph);
//		Edge edge = edges[0];
//		//构造生成树
//		int i = 0;
//		while(edge != null) {
//			if(flag[edge])
//		}
		return null;
	}
	
	//最短路径：D算法
	public int[] shortPath(int from, int to) {
		return null;
	}
	
	//拓扑排序（有向图）
	public Queue<Integer> topologicalSort() {
		int [] count = new int[size]; //指向当前顶点的边数
		boolean [] flag = new boolean[size];//true代表相应顶点已被遍历过
		Queue<Integer> queue = new Queue<>();//遍历顺序
		Edge edge = new Edge(0, 0, null);
		for(int i = 0; i < size; i++) 
			for(edge = edges[i]; edge != null; edge = edge.next) 
				count[edge.to]++;
		//遍历
		int i = 0;
		while(true) {
			//查找当前第一个没有入边且未被遍历过的顶点
			for(i = 0; i < size; i++) {
				if(count[i] == 0 && !flag[i]) {
					queue.enter(i);
					flag[i] = true;
					break;
				}
			}
			//当前剩余顶点都有入边（存在环）或已遍历完（无环）
			if(i == size)
				return queue;
			//当前顶点所指向顶点的入度递减
			edge = edges[i];
			while(edge != null) {
				count[edge.to]--;
				edge = edge.next;
			}
		}
	}
	
}
