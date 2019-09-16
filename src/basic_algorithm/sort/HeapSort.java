/**
 * @Author eunji
 */
package basic_algorithm.sort;

//堆排序
//平均时间复杂度O(nlogn)，空间复杂度O(n)，不稳定
public class HeapSort {

	public static void sort(int [] array) {
		if (array == null || array.length == 0) 
			return;
		int size = array.length;
		int [] heap = new int [size];
		//添加元素，每添加一次就修正一次
		for (int i = 0; i < size; i++)
			insert(heap, i, array[i]);
		//打印初始堆
		for (int i = 0; i < size; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
		//依次出堆
		while (size > 0) {
			System.out.print(heap[0] + " ");
			heap[0] = heap[size-1];
			size--;//堆的大小递减
			adjust(heap, 0, size);
		}
		return;
	}
	
	//填充堆：小元素上浮
    //1.每添加一个元素后就修正一次	2.所有元素添加完后一次性修正
	private static void insert(int [] array, int index, int value) {
		array[index] = value;//添加到当前堆底
		int parent;
		while (index > 0) {
			parent = (index - 1) / 2;
			if (array[index] < array[parent]) {//小元素上浮
				swap(array, index, parent);
				index = parent;
			}else {
				break;
			}
		}
		return;
	}
	
	//调整堆：大元素下沉
	private static void adjust(int [] array, int index, int size) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int min = index;//父子节点中的最小元素
		//当前节点在堆中有子节点，可能需要做调整
		while (left < size) {
			if (array[left] < array[index])
				min = left;
			if (right < size && array[right] < array[min])
				min = right;
			if (index == min) //父节点是最小元素，则结束
			    break;
            swap(array, index, min);
			index = min;
			left = index * 2 + 1;
			right = index * 2 + 2;
		}
		return;
	}
	
	//父子节点交换
	private static void swap(int [] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return;
	}
}
