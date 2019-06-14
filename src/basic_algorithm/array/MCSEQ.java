/*
    @Author：eunji
 */
package basic_algorithm.array;

import data_structure.Stack;

//最大连续子序列和
public class MCSEQ {

	public static int generate(int [] array) {
		if (array == null || array.length == 0) 
			return -1;
		int end = 0, sum = 0, max = 0;
		for(int i = 0; i < array.length; i++) {
			sum = sum + array[i];
			if (sum > max) {
				max = sum;
				end = i;
			}
			if (sum < 0) 
				sum = 0;
		}
		//打印最大连续子序列
		sum = max;
		Stack<Integer> stack = new Stack<>();
		for(int i = end; sum != 0; i-- ) {
			stack.push(array[i]);
			sum = sum - array[i];
		}
		while(!stack.isEmpty()) 
			System.out.print(stack.pop() + " ");
		return max;
	}
}
