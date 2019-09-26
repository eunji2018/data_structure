/**
 * @Author eunji
 */
package basic_algorithm.array;

//TOK问题
public class TOPK {

    //前K个最小数：堆排序实现
    public static void kMin(int [] array, int k) {
        if (array == null || array.length == 0 || k < 1 || k > array.length) 
            return;
        int [] heap = new int [k];
        for (int i = 0; i < k; i++)
            insert(heap, i, array[i]);
        //打印初始堆
        for (int i = 0; i < k; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
        //比较剩余元素
        for (int i = k; i < array.length; i++)
            if (array[i] < heap[0]) {
                heap[0] = array[i];
                adjust(heap, 0);
            }
        //打印最终堆
        for (int i = 0; i < k; i++)
            System.out.print(heap[i] + " ");
        return;
    }
    
    //填充堆：大元素上浮
    private static void insert(int [] array, int index, int value) {
        array[index] = value;
        int parent = 0;
        while (index > 0) {
            parent = (index - 1) / 2;
            if (array[index] > array[parent]) {
                swap(array, index, parent);
                index = parent;
            }else {
                break;
            }
        }
        return;
    }
    
    //调整堆：小元素下沉
    private static void adjust(int [] array, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int max = index;//父子节点中的最大元素
        //当前节点在堆中有子节点，可能需要做调整
        while (left < array.length) {
            if (array[left] > array[index])
                max = left;
            if (right < array.length && array[right] > array[max])
                max = right;
            if (max != index) {//父节点不是最大元素，则交换
                swap(array, max, index);
            }else {
                break;
            }
            index = max;
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
