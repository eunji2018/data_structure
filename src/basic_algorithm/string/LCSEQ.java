/*
    @Author：eunji
 */
package basic_algorithm.string;

import data_structure.Stack;

//最长公共子序列
//dp[i][j]表示string1[0 - i]与string2[0 - j]的最长公共子序列的长度
//dp[i][j]等于以下三个值中的最大值：
//若string1[i]与string2[j]不相等，比较dp[i-1][j]与dp[i][j-1]
//若string1[i]与string2[j]相等，比较dp[i-1][j-1] + 1
//最长公共子序列长度即为dp矩阵右下角的值，从右下向左上回溯可以得到序列中的元素
public class LCSEQ {
	
	public static int generate(char [] string1, char [] string2) {
		if (string1 == null || string1.length == 0 ||
			string2 == null || string2.length == 0) 
			return -1;
		int row = string1.length, column = string2.length;
		int [][] dp = new int [row][column];
		dp[0][0] = string1[0] == string2[0] ? 1 : 0;
		//初始化第一列
		for(int i = 1; i < row; i++) 
			dp[i][0] = Math.max(dp[i-1][0],(string1[i] == string2[0] ? 1 : 0));
		//初始化第一行
		for(int i = 1; i < column; i++) 
			dp[0][i] = Math.max(dp[0][i-1],(string1[0] == string2[i] ? 1 : 0));
		//构造矩阵
		for(int i = 1; i < row; i++) {
			for(int j = 1; j < column; j++) {
				dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
				if (string1[i] == string2[j]) 
					dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);
			}
		}
		//打印最长公共子序列
		int x = row - 1, y = column - 1;
		int count = dp[x][y];
		Stack<Character> stack = new Stack<>();
		while(count > 0) {
			if (x > 0 && dp[x][y] == dp[x-1][y]) {
				x--;
			}else if(y > 0 && dp[x][y] == dp[x][y-1]) {
				y--;
			}else {
				stack.push(string1[x]);
				count--;
				x--;
				y--;
			}
		}
		while(!stack.isEmpty()) 
			System.out.print(stack.pop() + " ");
		return dp[row-1][column-1];
	}

}
