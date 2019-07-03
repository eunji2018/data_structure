/**
 * @Author eunji
 */
package basic_algorithm.sort;

//归并排序
//平均时间复杂度O(nlogn)，空间复杂度O(n)，稳定
public class MergeSort {
	
	private static int [] temp;//副本数组
	
	public static void sort(int [] array) {
		if (array == null || array.length == 0) 
			return;
		temp = new int [array.length];
//		System.out.println("自顶向下：");
//		sortUpToBottom(array, 0, array.length - 1);
		System.out.println("自底向上：");
		sortBottomToUp(array);
	}

	private static void merge(int [] array, int left, int mid, int right) {
		//复制待归并的元素到副本数组
		for(int i = left; i <= right; i++) 
			temp[i] = array[i];
		int m = left, n = mid + 1;//m代表左半部分数组剩余的首元素，n代表右半部分数组剩余的首元素
		for(int k = left; k <= right; k++) {
			if (m > mid) {//左半部分数组的所有元素已归并完
				array[k] = temp[n++];
			}else if (n > right) {//右半部分数组的所有元素已归并完
				array[k] = temp[m++];
			}else if (temp[m] <= temp[n]) {
				array[k] = temp[m++];
			}else {
				array[k] = temp[n++];
			}
		}
	}
	
	//自顶向下
    //先将子数组排序，再归并
	private static void sortUpToBottom(int [] array, int left, int right) {
		if (left >= right) 
			return;
		int mid = left + (right - left) / 2;
		sortUpToBottom(array, left, mid);
		sortUpToBottom(array, mid + 1, right);
		merge(array, left, mid, right);
		//打印
		for(int i = 0; i < array.length; i++) 
			System.out.print(array[i] + " ");
		System.out.println();
	}
	
	//自底向上
	//首先，每相邻的两个元素一组，对所有组进行归并
    //然后，每相邻的四个元素一组，对所有组进行归并
	//依次类推，直到所有元素被划分为一个组，进行归并，完毕
	private static void sortBottomToUp(int [] array) {
		int size = 1;//最左有序片段的长度，初始为1
		int length = array.length;
		while(size < length) {
			for(int i = 0; i + size < length; i = i + 2 * size) 
				merge(array, i, i + size - 1, Math.min(i + 2 * size - 1, length - 1));//对同组的两个有序片段进行归并
			size = 2 * size;//一次归并后，最左有序片段的长度翻倍
			//打印
			for(int i = 0; i < length; i++) 
				System.out.print(array[i] + " ");
			System.out.println();
		}
	}
}