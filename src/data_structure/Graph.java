/**
 * @Author eunji
 */
package data_structure;

//图的邻接链表实现
//无向图只考虑连通图，有向图可以不强连通
//实现了添加，删除边，广度优先遍历，深度优先遍历，最小生成树，最短路径，拓扑排序的操作
public class Graph {

	private boolean directed;//true代表有向图，false代表无向图

    private int size;//顶点数目

    private Edge[] edges;

	//边类（起点，终点，权重，下一条边）
	private static class Edge implements Comparable<Edge>{

		public int from;//起点
		public int to;//终点
		public int weight;//边的权重
		public Edge next;//下一条边
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
			this.next = null;
		}

		@Override
		public int compareTo(Edge edge) {
			return weight - edge.weight;
		}

		@Override
		public String toString() {
			return "(" + from + " " + to + " " + weight + ")";
		}
	}
	
	public Graph(boolean directed, int size) {
		this.directed = directed;
		this.size = size;
		this.edges = new Edge[size];
	}

	//添加边
	//每条边只能添加一次，默认不存在，不做重复性检查
	public void insert(int [] array) {
		//{from,to,weight}
		int from = array[0];
		int to = array[1];
		int weight = array[2];
		//头插法
		Edge edge = new Edge(from, to, weight);
		edge.next = edges[from];
		edges[from] = edge;
		if (!directed) {
			edge = new Edge(to, from, weight);
			edge.next = edges[to];
			edges[to] = edge;
		}
		return;
	}
	
	//删除边
	public void delete(int from, int to) {
		delete_directed(from, to);
		if (!directed)
			delete_directed(to, from);
		return;
	}

	//删除有向边
	private void delete_directed(int from, int to) {
		Edge edge = edges[from];
		if (edge == null)
			return;
		if (edge.to == to) {
			edges[from] = edge.next;
			return;
		}
		Edge other = edge;
		edge = edge.next;
		while (edge != null) {
			if (edge.to == to) {
				other.next = edge.next;
				break;
			}
			other = edge;
			edge = edge.next;
		}
		return;
	}
	
	//打印邻接表 from: (to weight)
	public void print() {
		Edge edge;
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
		return;
	}

	//广度优先遍历（队列）
	public Queue breadthTraverse(int start) {
		boolean [] flag = new boolean[size];//true代表相应顶点已放入过队列，已被访问过
		Queue<Edge> queue = new Queue<>();
		Queue<Integer> other = new Queue<>();//访问顺序
		Edge edge = new Edge(0, start, 0);//虚拟边，方便起点入队
		queue.enter(edge);
		flag[edge.to] = true;
		other.enter(edge.to);
		while(!queue.isEmpty()) {
			edge = queue.depart();//出队
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
	public Queue depthTraverse(int start) {
		boolean [] flag = new boolean[size];//true代表相应顶点已放入过栈，已被访问过
		Stack<Edge> stack = new Stack<>();
		Queue<Integer> queue = new Queue<>();//访问顺序
		Edge edge = new Edge(0, start, 0);//虚拟边，方便起点入栈
		stack.push(edge);
		flag[edge.to] = true;
		queue.enter(edge.to);
		while(!stack.isEmpty()) {
			edge = stack.peek();//取栈顶
			//将与此顶点邻接的且未被访问过的【第一个】顶点加入栈
			for(edge = edges[edge.to]; edge != null; edge = edge.next) {
				if(!flag[edge.to]) {
					stack.push(edge);
					flag[edge.to] = true;//入栈代表访问顶点
					queue.enter(edge.to);
					break;
				}
			}
			//所有邻接顶点都被访问过，则出栈
			if(edge == null)
				stack.pop();
		}
		return queue;
	}
	
	//深度优先遍历：递归式
	public Queue depthTraverse_recursive(int start) {
		boolean [] flag = new boolean[size];//true代表相应顶点已放入过队列，已被访问过
		Queue<Integer> queue = new Queue<>();//访问顺序
		depthTraverse_recursive(start, flag, queue);
		return queue;
	}
	
	private void depthTraverse_recursive(int start, boolean [] flag, Queue<Integer> queue) {
		queue.enter(start);
		flag[start] = true;//入队代表访问顶点
		for(Edge edge = edges[start]; edge != null; edge = edge.next) {
			if(!flag[edge.to])
				depthTraverse_recursive(edge.to, flag, queue);
		}
		return;
	}
	
	//最小生成树：P算法（无向图）
	//单颗树的生长
	//将所有顶点划分为两个集合，已加入到生成树的顶点集合，剩余的顶点集合
	//每次循环从剩余的边中选择连接两个集合顶点的最小权重的边添加到生成树中，直到所有顶点加入到生成树
	public Queue minSpanTree_P(int start) {
		UnionFindSet set = new UnionFindSet();
		for (int i = 0; i < size; i++)//初始时每个顶点构成一棵树
			set.insert(i);
		Queue<String> queue = new Queue<>();//保存构造过程中符合条件的边，(from to weght)
		PriorityQueue priorityQueue = new PriorityQueue();
		boolean [] flag = new boolean[size];//true代表相应顶点所有的边都已放入队列，已被访问过
		int last = start;//最近添加到最小生成树的顶点
		//构造生成树
		Edge edge;
		while (true) {
			edge = edges[last];
			//将当前顶点所有【未被访问过】的边加入队列
			while (edge != null) {
				if (!flag[edge.to])
					priorityQueue.enter(edge);
				edge = edge.next;
			}
			flag[last] = true;//最后加入生成树的顶点对应的标记为false
			//挑选当前生成树中的顶点与其他顶点之间的所有边中最小权重的边
			while (!priorityQueue.isEmpty()) {
				edge = (Edge) priorityQueue.depart();
				if (!set.connected(edge.from, edge.to)) {//符合条件的边
					queue.enter("(" + edge.from + " " + edge.to + " " + edge.weight + ")");
					last = set.connected(start, edge.from) ? edge.to : edge.from;//未连通的顶点
					set.union(start, last);
					break;
				}
			}
			//判断生成树是否构造完成
			//不应该使用队列是否为空来判断（构造完成之前，队列有可能为空），而应该使用并查集来判断
			if (set.count() == 1)
				break;
		}
		set.print();
		return queue;
	}
	
	//最小生成树：K算法（无向图）
	//多棵树的合并
	//每次循环从剩余的边中选择权重最小的边添加到生成树中，并且不会出现回路，直到合并为一棵生成树
	public Queue minSpanTree_K() {
		UnionFindSet set = new UnionFindSet();
		for (int i = 0; i < size; i++)//初始时每个顶点构成一棵树
			set.insert(i);
		Queue<String> queue = new Queue<>();//保存构造过程中符合条件的边，(from to weght)
		//对边按权重排序
		PriorityQueue priorityQueue = new PriorityQueue();
		boolean [] flag = new boolean[size];//true代表相应顶点所有的边都已放入队列，已被访问过
		Edge edge;
		for (int i = 0; i < size; i++) {
			edge = edges[i];
			while (edge != null) {
				if (!flag[edge.to])
					priorityQueue.enter(edge);
				edge = edge.next;
			}
			flag[i] = true;//顶点所有的边都已放入队列
		}
		priorityQueue.print();
		//构造生成树
		while (!priorityQueue.isEmpty()) {
			edge = (Edge) priorityQueue.depart();
			if (!set.connected(edge.from, edge.to)) {//边的两个顶点不在同一棵树中
				queue.enter("(" + edge.from + " " + edge.to + " " + edge.weight + ")");
				set.union(edge.from, edge.to);//合并两棵树
			}
		}
		set.print();
		return queue;
	}
	
	//最短路径：D算法
	//单源最短路径
	//定理：若存在从start到end的最短路径，路径上的某顶点为middle，则路径中middle之前的一段就是从start到middle的最短路径
	//类似广度优先遍历，每次循环从当前可达的剩余顶点中选择距离起点最近的顶点，更新起点到顶点的距离
	//直到所有顶点遍历完毕或者剩余的顶点不可达
	//回溯最短路径（除起点外）：与顶点邻接的所有顶点中，若两点最短路径长度之差等于连接边的权重，则邻接顶点可以作为前一个顶点
	public Queue shortPath_D(int start) {
		Queue<String> queue = new Queue<>();//依次保存与起点最近的顶点，以及最短路径的长度
		boolean [] flag = new boolean[size];//true代表相应顶点已放入队列，其所有的边已被访问过
		int [] distance = new int[size];//相应顶点与起点的当前距离
		//初始化距离数组
		for (int i = 0; i < size; i++)
			distance[i] = Integer.MAX_VALUE;
		distance[start] = 0;
		//构造最短路径
		Edge edge;
		int temp;
		int last = start;//最近加入队列的顶点
		while (true) {
			queue.enter("(" + last + " " + distance[last] + ")");
			edge = edges[last];
			//更新与当前顶点邻接且未加入队列的顶点的距离
			while (edge != null) {
				if (!flag[edge.to] && distance[last] + edge.weight < distance[edge.to])
					distance[edge.to] = distance[last] + edge.weight;//找到更短的路径，更新
				edge = edge.next;
			}
			flag[last] = true;
			//选择下一个最近的顶点
			temp = Integer.MAX_VALUE;
			for (int i = 0; i < size; i++) {
				if (!flag[i] && distance[i] < temp) {
					temp = distance[i];
					last = i;
				}
			}
			if (temp == Integer.MAX_VALUE)//所有顶点遍历完毕或者剩余的顶点不可达
				break;
		}
		//打印距离数组
		System.out.println();
		for (int i = 0; i < size; i++)
			System.out.print(distance[i] + " ");
		return queue;
	}
	
	//拓扑排序（有向图）
	//每次循环选择一个没有入边的顶点，更新邻接顶点，直到所有顶点遍历完毕或者剩余的顶点构成环
	public Queue topologicalSort() {
		int [] count = new int[size]; //指向相应顶点的边的数量
		boolean [] flag = new boolean[size];//true代表相应顶点已被遍历过
		Queue<Integer> queue = new Queue<>();//遍历顺序
		Edge edge;
		int i;
		//初始化count数组
		for(i = 0; i < size; i++)
			for(edge = edges[i]; edge != null; edge = edge.next) 
				count[edge.to]++;
		//开始排序
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
				break;
			//当前顶点所指向顶点的入度递减
			edge = edges[i];
			while(edge != null) {
				count[edge.to]--;
				edge = edge.next;
			}
		}
		return queue;
	}
	
}
