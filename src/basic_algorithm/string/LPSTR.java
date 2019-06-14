/*
    @Author：eunji
 */
package basic_algorithm.string;

//最长回文子串
//
public class LPSTR {

	public static int generate(char [] string) {
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
				if (temp[i+radius[i]] == temp[i-radius[i]]) {
					radius[i]++;
				}else {
					break;
				}
			}
			if (i + radius[i] > right) {
				right = i + radius[i];
				index = i;
			}
			if(radius[i] > max) {
				position = i;
				max = radius[i];
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
}
