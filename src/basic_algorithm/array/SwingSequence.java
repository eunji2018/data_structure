/**
 * @Author eunji
 */
package basic_algorithm.array;

//摆动序列
//序列中相邻元素之间的大小关系相互交替（不包含相等情形），称为摆动序列
public class SwingSequence {

    //最长连续摆动序列
    //从前向后遍历数组，记录以当前字符结尾的最长连续摆动序列的长度
    //相邻三个元素的大小关系共有九种情形，分别记为left，middle，right，当前元素为right
    //若middle == right，则length = 1
    //若left < middle < right或者left > middle > right，则length = 2；
    //若left >= middle < right或者left <= middle > right，则length++
    public static int generate1(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int length = 1;
        int max = 1;
        int end = 0;
        int difference;
        int previous = 0;
        for (int i = 1; i < array.length; i++) {
            difference = array[i] - array[i-1];
            if ((difference > 0 && previous <= 0) || (difference < 0 && previous >= 0)) {
                length++;
            }else {
                length = (difference == 0) ? 1 : 2;
            }
            previous = difference;
            if (length > max) {
                max = length;
                end = i;
            }
        }
        //打印最长连续摆动序列
        for (int i = end - max + 1; i <= end; i++)
            System.out.print(array[i] + " ");
        return max;
    }

    //最长摆动序列：类似股票利润问题（股票可以买卖多次）
    //从前向后遍历数组，每个升序或者降序子数组（非严格地递增或者递减）的端点可以作为序列中的元素
    //遍历完毕后，结果即为最长摆动序列，再向序列中添加任何其他的元素，都将导致序列不满足摆动性质
    public static int generate2(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int difference;
        int previous = 0;
        int length = 0;
        for (int i = 1; i < array.length; i++) {
            difference = array[i] - array[i-1];
            if (difference > 0 && previous <= 0) {
                length++;
                previous = difference;
            }else if (difference < 0 && previous >= 0) {
                length++;
                previous = difference;
            }
        }
        return length + 1;
    }


}
