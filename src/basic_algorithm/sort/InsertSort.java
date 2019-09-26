/**
 * @Author eunji
 */
package basic_algorithm.sort;

//插入排序
//平均时间复杂度O(n2)，空间复杂度O(1)，稳定
public class InsertSort {

    public static void sort(int [] array) {
        if (array == null || array.length == 0) 
            return;
        int temp;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] >= array[j-1]) {
                    break;
                }else {
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
            //打印
            for (int n = 0; n < array.length; n++)
                System.out.print(array[n] + " ");
            System.out.println();
        }
        return;
    }
}
