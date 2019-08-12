/**
 * @Author eunji
 */
package basic_algorithm.array;

//最大连续子序列
public class MCSEQ {

	//最大连续子序列和
	//线性遍历
	public static int generate1(int [] array) {
		if (array == null || array.length == 0) 
			return -1;
		int index = 0, start = 0, end = 0;
		int sum = 0, max = 0;
		for(int i = 0; i < array.length; i++) {
			sum = sum + array[i];
			if (sum > max) {
				max = sum;
				start = index;
				end = i;
			}
			if (sum < 0) {
				index = i + 1;
				sum = 0;
			}
		}
		//打印最大连续子序列
		for (int i = start; i <= end; i++)
			System.out.print(array[i] + " ");
		return max;
	}

	//动态规划
	//dp[i]表示以array[i]结尾的最大连续子序列和
	public static int generate2(int [] array) {
		if (array == null || array.length ==0)
			return -1;
		int length = array.length;
		int [] dp = new int[length];
		int max = 0;
		int index = 0, start = 0, end = 0;
		dp[0] = array[0];
		for (int i = 1; i < length; i++) {
			dp[i] = array[i];
			if (dp[i-1] > 0) {
				dp[i] += dp[i-1];
			}else {
				index = i;
			}
			if (dp[i] > max) {
				max = dp[i];
				start = index;
				end = i;
			}
		}
		//打印数组
		for (int i = 0; i < length; i++)
			System.out.print(dp[i] + " ");
		System.out.println();
		//打印最大连续子序列
		for (int i = start; i <= end; i++)
			System.out.print(array[i] + " ");
		return max;
	}


	//分治算法
	//最大连续子序列出现的位置分为三种情形：
	//1.全部位于左半部子数组或者右半部子数组（递归调用）
	//2.横跨左右半部子数组（最大前缀与后缀和）
	public static int generate3(int [] array) {
		if (array == null || array.length == 0)
			return -1;
		return process(array, 0, array.length-1);
	}

	private static int process(int [] array, int left, int right) {
		if (left == right)
			return array[left];
		int middle = (left + right) / 2;
		int prefix = Integer.MIN_VALUE;
		int suffix = Integer.MIN_VALUE;
		int sum = 0;
		//最大后缀和
		for (int i = middle; i >= left; i--) {
			sum = sum + array[i];
			suffix = Math.max(suffix, sum);
		}
		sum = 0;
		//最大前缀和
		for (int i = middle + 1; i <= right; i++) {
			sum = sum + array[i];
			prefix = Math.max(sum, prefix);
		}
		int result = Math.max(process(array,left,middle), process(array,middle+1,right));
		result = Math.max(result, prefix + suffix);
		return result;
	}

	//最大子序列和
	//子序列中相邻元素在原数组中的位置之差不超过k
	//dp[i]表示以array[i]结尾的最大子序列和
	public static int generate4(int [] array, int k) {
		if (array == null || array.length == 0)
			return -1;
		int length = array.length;
		int [] dp = new int[length];
		int max = array[0], sum = array[0];
		int temp, limit;
		for (int i = 0; i < length; i++) {
			dp[i] = array[i];
			limit = i < k ? 0 : i - k;
			temp = Integer.MIN_VALUE;
			for (int j = limit; j < i; j++)
				temp = Math.max(temp, dp[j]);
			if (temp > 0)
				dp[i] += temp;
			if (dp[i] > max)
				max = dp[i];
		}
		//打印数组
		for (int i = 0; i < length; i++)
			System.out.print(dp[i] + " ");
		return max;
	}

	//最大连续子序列积
	//positive表示以array[i]结尾的最大连续子序列积（正积）
	//negative表示以array[i]结尾的最小连续子序列积（负积）
	//正数一定存在最大积，不一定存在最小积
	//负数一定存在最小积，不一定存在最大积
	//0不存在最大积与最小积
	public static int generate5(int [] array) {
		if (array == null || array.length == 0)
			return -1;
		int length = array.length;
		int max, positive, negative, lastMax, lastMin;
		max = lastMax = lastMin = array[0];
		for (int i = 1; i < length; i++) {
			positive = array[i];
			negative = array[i];
			if (array[i] > 0) {
				if (lastMax > 0)
					positive *= lastMax;
				if (lastMin < 0)
					negative *= lastMin;
			}else if (array[i] < 0) {
				if (lastMax > 0)
					negative *= lastMax;
				if (lastMin < 0)
					positive *= lastMin;
			}
			lastMax = positive;
			lastMin = negative;
			if (lastMax > max)
				max = lastMax;
		}
		return max;
	}

}
