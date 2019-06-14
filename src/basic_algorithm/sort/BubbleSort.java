/*
    @Author：eunji
 */
package basic_algorithm.sort;

//冒泡排序
//平均时间复杂度O(n2)，空间复杂度O(1)，稳定
public class BubbleSort {

	public static void sort(int [] array) {
		if (array == null || array.length == 0) 
			return;
		boolean flag = false;
		int temp = 0;
		for(int i = 0; i < array.length - 1; i++) {
			flag = false;//默认当前循环不发生元素交换
			for(int j = array.length - 1; j > i; j--) 
				if (array[j-1] > array[j]) {
					temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
					flag = true;//发生交换
				}
			if(!flag)
				return;
			//打印
			for(int n = 0; n < array.length; n++) 
				System.out.print(array[n] + " ");
			System.out.println();
		}
		return;
	}
}
