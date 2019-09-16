/**
 * @Author eunji
 */
package basic_algorithm.sort;

//快速排序（三向排序）
//平均时间复杂度O(nlogn)，空间复杂度O(logn)，不稳定
//优化：子数组规模较小时可以使用插入排序，切分元素数目较多时可以使用三向排序
public class QuickSort {

	public static void sort(int [] array) {
		if (array == null || array.length == 0) 
			return;
		sort(array, 0, array.length - 1);
	}
	
	private static void sort(int [] array, int left, int right) {
		if (left >= right) 
			return;
		int index = partition(array, left, right);
		//打印
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
		sort(array, left, index - 1);
		sort(array, index + 1, right);
	}
	
	//对子数组排序，返回切分点的位置
	private static int partition(int [] array, int left, int right) {
		int temp = array[left];//使用最左边的元素作为切分元素
		int i = left, j = right;
		while (i < j) {
			while (i < j && array[j] >= temp)//先从右边比较，找到小于切分元素的元素
				j--;
			array[i] = array[j];
			while (i < j && array[i] <= temp)//再从左边比较，找到大于切分元素的元素
				i++;
			array[j] = array[i];
		}
		array[i] = temp;
		return i;
	}
}
