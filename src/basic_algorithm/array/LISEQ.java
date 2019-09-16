/**
 * @Author eunji
 */
package basic_algorithm.array;

import data_structure.Stack;

//最长递增子序列
//长度数组：dp[i]表示以array[i]结尾的最长递增子序列的长度
//dp[i]等于dp[j] + 1的最大值，其中0 <= j <= i-1，且array[j] < array[i]
//最长递增子序列的长度即为长度数组中的最大值，从后向前回溯可以得到序列中的元素
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
	
	//生成最长递增子序列
	public static int generate(int [] array) {
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

}
