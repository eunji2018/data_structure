/**
 * @Author eunji
 */
package basic_algorithm.array;

//糖果分配问题
public class AllocateCandy {

    //数组中的元素代表分值，要求每个位置至少分配一个糖果
    //并且相邻位置中分值高的必须分配更多的糖果，求解需要的最少糖果数量
    //
    //
    public static int generate1(int [] array) {
        if (array == null || array.length == 0)
            return 0;
        int length = array.length;
        int [] result = new int[length];
        result[0] = 1;
        //从左到右
        for (int i = 1; i < length; i++) {
            result[i] = 1;
            if (array[i] > array[i-1])
                result[i] = result[i-1] + 1;
        }
        for (int i = 0; i < length; i++)
            System.out.print(result[i] + " ");
        System.out.println();
        int count = result[length-1];
        //从右到左
        for (int i = length - 2; i >= 0; i--) {
            if (array[i] > array[i+1] && result[i] <= result[i+1])
                result[i] = result[i+1] + 1;
            count += result[i];
        }
        for (int i = 0; i < length; i++)
            System.out.print(result[i] + " ");
        System.out.println();
        return count;
    }

    //数组中的元素代表分值，要求每个位置至少分配一个糖果
    //并且相邻位置中分值高的必须分配更多的糖果，分值相等的必须分配相等的糖果，求解需要的最少糖果数量
    public static int generate2(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int length = array.length;
        int [] result = new int[length];
        result[0] = 1;
        //从左到右
        for (int i = 1; i < length; i++) {
            result[i] = 1;
            if (array[i] == array[i-1])
                result[i] = result[i-1];
            else if (array[i] > array[i-1])
                result[i] = result[i-1] + 1;
        }
        for (int i = 0; i < length; i++)
            System.out.print(result[i] + " ");
        System.out.println();
        int count = result[length-1];
        //从右到左
        for (int i = length - 2; i >= 0; i--) {
            if (array[i] == array[i+1])
                result[i] = result[i+1];
            else if (array[i] > array[i+1] && result[i] <= result[i+1])
                result[i] = result[i+1] + 1;
            count += result[i];
        }
        for (int i = 0; i < length; i++)
            System.out.print(result[i] + " ");
        System.out.println();
        return count;
    }

}
