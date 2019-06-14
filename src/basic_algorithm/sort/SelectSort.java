/*
    @Author：eunji
 */
package basic_algorithm.sort;

//选择排序
//平均时间复杂度O(n2)，空间复杂度O(1)，不稳定
public class SelectSort {

	public static void sort(int [] array) {
		if (array == null || array.length == 0) 
			return;
		int temp = 0, index = 0;//index代表当前循环中最小值的位置
		for(int i = 0; i < array.length; i++) {
			index = i;
			//查找当前循环中的最小值
			for(int j = i + 1; j < array.length; j++)
				if(array[j] < array[index])
					index = j;
			//交换元素
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
			//打印
			for(int n = 0; n < array.length; n++) 
				System.out.print(array[n] + " ");
			System.out.println();
		}		
		return;
	}
}
