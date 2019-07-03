/**
 * @Author eunji
 */
package basic_algorithm.string;

//最长公共子串
//dp[i][j]表示以string1[i]、string2[j]结尾的最长公共子串的长度
//若string1[i]与string2[j]相等，dp[i][j] = dp[i-1][j-1] + 1
//若string1[i]与string2[j]不相等，dp[i][j] = 0
//最长公共子串的长度即为dp矩阵中的最大值，从右下向左上回溯可以得到子串中的元素
public class LCSTR {

	//矩阵实现
	public static int generate1(char [] string1, char [] string2) {
		if (string1 == null || string1.length == 0 ||
			string2 == null || string2.length == 0) 
			return -1;
		int row = string1.length, column = string2.length;
		int [][] dp = new int[row][column];
		//初始化第一列
		for(int i = 0; i < row; i++)
			if(string1[i] == string2[0])
				dp[i][0] = 1;
		//初始化第一行
		for(int i = 1; i < column; i++)
			if(string1[0] == string2[i])
				dp[0][i] = 1;
		//构造矩阵
		for(int i = 1; i < row; i++)
			for(int j = 1; j < column; j++)
				if(string1[i] == string2[j])
					dp[i][j] = dp[i-1][j-1] + 1;
		//打印最长公共子串
		int length = 0, end = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(dp[i][j] > length) {
					length = dp[i][j];
					end = i;
				}
			}
		}
		for(int i = end - length + 1; i <= end; i++)
			System.out.print(string1[i] + " ");
		return length;
	}
	
	//单变量实现
    //从右上到左下计算矩阵中的斜线，斜线的方向为从左上指向右下，起点在最左一列或最上一行，终点在最下一行或最右一列
	public static int generate2(char [] string1, char [] string2) {
		if (string1 == null || string1.length == 0 ||
			string2 == null || string2.length == 0) 
			return -1; 
		int row = 0, column = string2.length - 1;//斜线起点位置
		int length = 0, max = 0, end = 0;
		int i = 0, j = 0;
		while(row < string1.length) {
			i = row;
			j = column;
			length = 0;
			//从（i，j）开始向右下遍历
			while(i < string1.length && j < string2.length) {
				if (string1[i] != string2[j]) {
					length = 0;
				}else {
					length++;
				}
				if (length > max) {
					max = length;
					end = i;
				}
				i++;
				j++;
			}
			if (column > 0) {//位于右上部分
				column--;
			}else {//位于左下部分
				row++;
			}
		}
		for(i = end - max + 1; i <= end; i++)
			System.out.println(string1[i]);
		return max;
	}
}

