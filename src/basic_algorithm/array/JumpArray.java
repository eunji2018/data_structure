/*
    @Author：eunji
 */
package basic_algorithm.array;

//跳跃数组的最少次数
//array[i]表示在点i可以向后跳跃1 - array[i]步
//dp[i]表示从点i开始跳跃到终点需要的最少次数
//dp[i]等于dp[j] + 1的最小值，其中i+1 <= j <= i+array[i]
//从右向左计算每个位置的最少次数
public class JumpArray {

    public static int generate(int [] array) {
        if (array == null || array.length == 0)
            return 0;
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
        return dp[0];
    }
}
