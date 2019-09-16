/**
 * @Author eunji
 */
package basic_algorithm.sort;

//三向排序
//数组中元素分为三类值，小于某值，等于某值，大于某值（例如0，1，2）
//要求小于某值的所有元素在左边，等于某值的所有元素在中间，大于某值的所有元素在右边
public class ThreeWaySort {

	public static void sort(int [] array) {
		if (array == null || array.length == 0)
			return;
		int left = 0, right = array.length - 1;//left左侧都是最小值，right右侧都是最大值
		int i = 0;//当前元素的位置
		while (i <= right) {
			switch (array[i]) {
				case 0 : swap(array, left, i);//最小值，交换元素
						 left++;
						 i++;
						 break;
				case 1 : i++;//中值，跳过元素
						 break;
				case 2 : swap(array, i, right);//最大值，交换元素
						 right--;
			}
			//打印
			for (int n = 0; n < array.length; n++) {
				if (n == left) {
					System.out.print("(" + array[n] + " ");
				}else if (n == right) {
					System.out.print(array[n] + ") ");
				}else {
					System.out.print(array[n] + " ");
				}
			}
			System.out.println();
		}
		return;
	}
	
	private static void swap(int [] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return;
	}
}
