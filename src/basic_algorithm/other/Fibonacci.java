/**
 * @Author eunji
 */
package basic_algorithm.other;

//斐波那契数列：类似跳台阶问题，矩形覆盖问题
public class Fibonacci {

	//非递归式：时间复杂度O(n)
	public static int fibonacci(int n) {
		if (n == 1 || n == 2) 
			return 1;
		//leftleft,left,current
		int leftleft = 1;
		int left = 1;
		int current = 0;
		for (int i = 3; i <= n; i++) {
			current = leftleft + left;
			leftleft = left;
			left = current;
		}
		return current;
	}
	
}
