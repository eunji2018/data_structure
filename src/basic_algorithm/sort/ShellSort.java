/*
    @Author：eunji
 */
package basic_algorithm.sort;

//希尔排序
//增量递减的插入排序，空间复杂度O(1)，不稳定
public class ShellSort {

	public static void sort(int [] array) {
		if (array == null || array.length == 0) 
			return;
		int step = array.length / 4;//当前循环中比较的两个元素的位置之差
		int temp = 0;
		while(step > 0) {
			for(int i = step; i < array.length; i++) {
				for(int j = i; j >= step; j = j - step) {
					if (array[j] >= array[j-step]) {
						break;
					}else {
						//交换元素
						temp = array[j];
						array[j] = array[j-step];
						array[j-step] = temp;
					}
				}
			}
			//打印
			for(int n = 0; n < array.length; n++) 
				System.out.print(array[n] + " ");
			System.out.println();
			step = step / 2;//步长递减
		}
		return;
	}
}
