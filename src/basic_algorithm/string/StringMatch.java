/*
    @Author：eunji
 */
package basic_algorithm.string;

//字符串匹配：KMP算法
public class StringMatch {

	public static int generate(char [] source, char [] match) {
		if (source == null || source.length == 0 ||
			match == null || match.length == 0) 
			return -1;
		int [] next = next(match);
		int i = 0, j = 0;
		while(i < source.length && j < match.length) {
			if (source[i] == match[j]) {//元素匹配，模式串和源串都转到下一个元素
				i++;
				j++;
			}else if (next[j] == -1) {//不匹配，模式串比较的是第一个元素，源串转到下一个元素
				i++;
			}else {
				j = next[j];
			}
		}
		return j == match.length ? i - j : -1;
	}
	
	//求解next数组：next[i]表示模式串在i位置与源串不匹配时，下次比较时元素的位置
	private static int [] next(char [] string) {
		if(string.length == 1)
			return new int [] {-1};
		int [] next = new int [string.length];
		next[0] = -1;
		int j = 0;
		for(int i = 2; i < string.length; i++) {
			j = next[i-1];
			while(j >= 0) {
				if(string[i-1] == string[j]) {
					next[i] = j + 1;
					break;
				}else {
					j = next[j];
				}
			}
		}
		//打印
		for(int i = 0; i < next.length; i++) 
			System.out.print(next[i] + " ");
		System.out.println();
		return next;
	}
}
