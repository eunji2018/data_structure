/**
 * @Author eunji
 */
package basic_algorithm.string;

//最长回文子串
public class LPSTR {

	//Manacher算法
	//对字符串填充'#'字符，处理后的字符串长度为奇数
	//从左到右遍历字符数组，记录当前回文子串到达的最右端和最长回文子串的半径
	//以当前字符为中心的最长回文子串可以借助当前回文子串到达的最右端来快速求出
	public static int generate1(char [] string) {
		if (string == null || string.length == 0) 
			return -1;
		char [] temp = process(string);
		//回文半径数组
		int [] radius = new int [temp.length];
		//当前回文子串到达的最右端的下一点
		int right = 0;
		int index = 0;
		//当前最长回文子串的半径与中心位置
		int max = 0;
		int position = 0;
		for(int i = 0; i < temp.length; i++) {
			radius[i] = i < right ? Math.min(radius[2*index-i],right-i) : 1;
			while(i + radius[i] < temp.length && i - radius[i] >= 0) {
				if (temp[i+radius[i]] != temp[i-radius[i]])
					break;
				radius[i]++;
			}
			if (i + radius[i] > right) {//更新最右端
				right = i + radius[i];
				index = i;
			}
			if(radius[i] > max) {//更新最长回文半径
				max = radius[i];
				position = i;
			}
		}
		//打印最长回文子串
		for(int i = position - max + 1; i < position + max; i++) {
			if(temp[i] == '#')
				continue;
			System.out.print(temp[i] + " ");
		}
		return max - 1;
	}
	
	//填充#字符
	private static char [] process(char [] string) {
		char [] temp = new char [string.length*2+1];
		int i = 0;
		for(; i < string.length; i++) {
			temp[2*i] = '#';
			temp[2*i+1] = string[i];
		}
		temp[2*i] = '#';
		return temp;
	}

	//动态规划
	//dp[i][j]表示string[i - j]是否为回文子串
	//dp[i][j] == true当且仅当dp[i+1][j-1] == true且string[i] == string[j]
	public static int generate2(char [] string) {
		if (string == null || string.length == 0)
			return -1;
		int position = 0;
		int max = 0;
		int length = string.length;
		boolean [][] dp = new boolean[length][length];
		//构造矩阵右上部分
		for (int count = 0; count < length; count++) {
			for (int i = 0, j = count; j < length; i++, j++) {
				if (j - i <= 1) {//子字符串含有一个或者两个字符
					dp[i][j] = string[i] == string[j];
				}else {//含有多个字符
					dp[i][j] = (string[i] == string[j] && dp[i+1][j-1]);
				}
				if (dp[i][j] && j - i + 1 > max) {
					max = j - i + 1;
					position = i;
				}
			}
		}
		//打印矩阵右上部分
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < i; j++)
				System.out.print(" " + " ");
			for (int j = i; j < length; j++)
				System.out.print((dp[i][j] ? "t" : "f") + " ");
			System.out.println();
		}
		//打印最长回文子串
		for (int i = position; i < position + max; i++)
			System.out.print(string[i] + " ");
		return max;
	}

	//中心扩张
	//从左向右遍历数组，以单个字符或者两个字符向两端扩张，查找当前最长回文子串
	public static int generate3(char [] string) {
		if (string == null || string.length == 0)
			return -1;
		int length = string.length;
		int max = 0;
		int position = 0;
		int temp;
		for (int i = 0; i < length; i++) {
			temp = expand(string, i, i);
			if (temp > max) {
				max = temp;
				position = i - (temp - 1) / 2;
			}
			temp = expand(string, i, i + 1);
			if (temp > max) {
				max = temp;
				position = i + 1 - temp / 2;
			}
		}
		//打印最长回文子串
		for (int i = position; i < position + max; i++)
			System.out.print(string[i] + " ");
		return max;
	}

	private static int expand(char [] string, int start, int end) {
		int count = (start == end ? -1 : 0);
		while (start >= 0 && end < string.length) {
			if (string[start] != string[end])
				break;
			count += 2;
			start--;
			end++;
		}
		return count;
	}
}
