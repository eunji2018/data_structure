/**
 * @Author eunji
 */
package basic_algorithm.array;

import data_structure.Stack;

//最长上升子序列
//长度数组：dp[i]表示以array[i]结尾的最长上升子序列的长度
//dp[i]等于dp[j] + 1的最大值，其中0 <= j <= i-1，且array[j] < array[i]
//最长上升子序列的长度即为长度数组中的最大值，从后向前回溯可以得到序列中的元素
public class LISEQ {
    
    //生成长度数组
    private static int[] length(int [] array) {
        int [] dp = new int [array.length];
        for (int i = 0; i < array.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
                if (array[j] < array[i]) 
                    dp[i] = Math.max(dp[i],dp[j]+1);
        }        
        return dp;
    }
    
    //生成最长上升子序列
    public static int generate1(int [] array) {
        if (array == null || array.length == 0) 
            return 0;
        int [] dp = length(array);
        //查找最长递增子序列的最后一个元素
        int length = 0, index = 0;
        for (int i = 0; i < dp.length; i++)
            if (dp[i] > length) {
                length = dp[i];
                index = i;
            }
        //从后向前查找序列的所有元素
        Stack<Integer> stack = new Stack<>();
        stack.push(array[index]);
        for (int i = index; i >= 0; i--)
            if (array[i] < array[index] && dp[i] == dp[index] - 1) {//可以作为前一个元素
                stack.push(array[i]);
                index = i;
            }
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        return length;
    }

    //贪心算法：从左到右遍历array[i]，数组temp[j]表示在array[0 - i]中长度为j的上升子序列的末尾元素的最小值
    //可以证明，temp[j]是严格单调递增的
    //查找temp数组中小于array[i]的最大元素temp[j]，执行temp[j+1]=array[i]
    //优化：使用二分查找确定临界元素的位置
    public static int generate2(int [] array) {
        if (array == null || array.length == 0)
            return 0;
        int length = array.length;
        int [] temp = new int[length];
        int result = 1, index = 0;
        temp[0] = array[0];
        for (int i = 1; i < length; i++) {
            index = 0;
            while (index < result) {
                if (temp[index] >= array[i])
                    break;
                index++;
            }
            temp[index] = array[i];
            if (index == result)
                result++;
        }
        //打印temp数组
        for (int i = 0; i < result; i++)
            System.out.print(temp[i] + " ");
        return result;
    }

}
