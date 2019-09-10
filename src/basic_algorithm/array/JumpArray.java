/**
 * @Author eunji
 */
package basic_algorithm.array;

//跳跃数组
//所有元素都为非负整数
public class JumpArray {

    /* array[i]表示在位置i可以向终点方向跳跃的最大长度，以第一个元素作为起点，最后一个元素作为终点
     */
    //能否到达终点
    public static boolean generate1(int [] array) {
        if (array == null || array.length == 0)
            return false;
        int length = array.length;
        boolean [] temp = new boolean[length];
        temp[length-1] = true;
        for (int i = length - 2; i >= 0; i--) {
            for (int j = 1; j <= array[i]; j++) {
                if (temp[i+j]) {
                    temp[i] = true;
                    break;
                }
            }
        }
        //打印数组
        for (int i = 0; i < length; i++)
            System.out.print(temp[i] ? "t " : "f ");
        return temp[0];
    }

    //从右到左遍历数组，记录当前能到达终点的最左位置
    //若最左位置在当前位置的跳跃范围内，则更新最左位置为当前位置
    public static boolean generate2(int [] array) {
        if (array == null || array.length == 0)
            return false;
        int length = array.length;
        int left = length - 1;
        for (int i = length - 2; i >= 0; i--) {
            if (i + array[i] >= left)
                left = i;
        }
        return left == 0;
    }

    //到达终点需要的最少跳跃次数
    //dp[i]表示从点i开始跳跃到终点需要的最少次数
    //dp[i]等于dp[j] + 1的最小值，其中i+1 <= j <= i+array[i]
    //从右向左计算每个位置的最少次数
    public static int generate3(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int length = array.length;
        int [] dp = new int[length];
        dp[length-1] = 0;
        int min;
        for (int i = length - 2; i >= 0; i--) {
            min = length;
            for (int j = 1; j <= array[i]; j++) {
                if (i + j < length && dp[i+j] < min)
                    min = dp[i+j];
            }
            dp[i] = min + 1;
        }
        //打印数组
        for (int i = 0; i < length; i++)
            System.out.print(dp[i] + " ");
        return (dp[0] == length + 1) ? -1 : dp[0];//length+1表示不能到达终点
    }

    //到达终点需要的最少跳跃次数
    //
    public static int generate4(int [] array) {

        return 0;
    }

}
