/**
 * @Author eunji
 */
package basic_algorithm.array;

//换钱问题：类似背包问题
//sum表示兑换数目，array中每个元素表示货币面值
public class ExchangeMoney {
		
	//方法数、钱数任意
	//dp[i][j]表示使用货币0-i兑换j的方法数
	//矩阵
	public static int exchange1(int [] array, int sum) {
		if (array == null || array.length == 0 || sum < 0) 
			return 0;
		int [][] dp = new int [array.length][sum+1];
		//初始化第一列
		for(int i = 0; i < array.length; i++) 
			dp[i][0] = 1;
		//初始化第一行
		for(int i = 1; i * array[0] <= sum; i++) 
			dp[0][i*array[0]] = 1;
		//构造矩阵
		for(int i = 1; i < array.length; i++) {
			for(int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i-1][j];
				dp[i][j] += (array[i] <= j ? dp[i][j-array[i]] : 0);
			}
		}
		//打印矩阵
		System.out.println("矩阵：");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j <= sum; j++) 
				System.out.print(dp[i][j] + " ");
			System.out.println();
		}
		return dp[array.length-1][sum];
	}
	
	//数组
	public static int exchange2(int [] array, int sum) {
		if (array == null || array.length == 0 || sum < 0) 
			return 0;
		int [] dp = new int [sum+1];
		for(int i = 0; i * array[0] <= sum; i++) 
			dp[i*array[0]] = 1;
		for(int i = 1; i < array.length; i++) 
			for(int j = 1; j <= sum; j++) 
				dp[j] += (array[i] <= j ? dp[j-array[i]] : 0);
		//打印数组
		System.out.println("数组：");
		for(int i = 0; i <= sum; i++) 
			System.out.print(dp[i] + " ");
		System.out.println();
		return dp[sum];
	}
	
	//方法数、钱数有限
	
	
	//最少张数、钱数任意
	//dp[i][j]表示使用货币0-i兑换j需要的最少张数
	//矩阵
	public static int exchange5(int [] array, int sum) {
		if (array == null || array.length == 0 || sum < 0) 
			return -1;
		int max = Integer.MAX_VALUE;
		int [][] dp = new int[array.length][sum+1];
		//初始化第一行
		for(int j = 1; j <= sum; j++) {
			dp[0][j] = max;
			if (j >= array[0] && dp[0][j-array[0]] != max) 
				dp[0][j] = dp[0][j-array[0]] + 1;
		}
		//构造矩阵
		int left = 0;
		for(int i = 1; i < array.length; i++) {
			for(int j = 1; j <= sum; j++) {
				left = max;
				if (j - array[i] >= 0 && dp[i][j-array[i]] != max)
					left = dp[i][j-array[i]] + 1;
				dp[i][j] = Math.min(left, dp[i-1][j]);
			}
		}
		//打印矩阵
		System.out.println("矩阵：");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j <= sum; j++) {
				if(dp[i][j] == max) {
					System.out.print("m ");
				}else {
					System.out.print(dp[i][j] + " ");
				}
			}
			System.out.println();
		}
		return dp[array.length-1][sum] == max ? -1 : dp[array.length-1][sum];
	}
	
	//数组
	public static int exchange6(int [] array, int sum) {
		if (array == null || array.length == 0 || sum < 0) 
			return -1;
		int max = Integer.MAX_VALUE;
		int [] dp = new int [sum+1];
		for(int j = 1; j <= sum; j++) {
			dp[j] = max;
			if(j >= array[0] && dp[j-array[0]] != max)
				dp[j] = dp[j-array[0]] + 1;
		}
		int left = 0;
		for(int i = 1; i < array.length; i++) {
			for(int j = 1; j <= sum; j++) {
				left = max;
				if(j >= array[i] && dp[j-array[i]] != max)
					left = dp[j-array[i]] + 1;
				dp[j] = Math.min(left, dp[j]);
			}
		}
		//打印数组
		System.out.println("数组：");
		for(int j = 0; j <= sum; j++) {
			if (dp[j] == max) {
				System.out.print("m ");
			}else {
				System.out.print(dp[j] + " ");
			}
		}
		System.out.println();
		return dp[sum] == max ? -1 : dp[sum];
	}
	
	//最少张数、钱数有限：
	//矩阵
	public static int exchange7(int [] array, int sum) {
		if (array == null || array.length == 0 || sum < 0) 
			return -1;
		int max = Integer.MAX_VALUE;
		int [][] dp = new int [array.length][sum+1];
		//初始化第一行
		for(int j = 1; j <= sum; j++) 
			dp[0][j] = max;
		if(array[0] <= sum)
			dp[0][array[0]] = 1;
		//构造矩阵
		int leftUp = 0;
		for(int i = 1; i < array.length; i++) {
			for(int j = 1; j <= sum; j++) {
				leftUp = max;
				if(j >= array[i] && dp[i-1][j-array[i]] != max)
					leftUp = dp[i-1][j-array[i]] + 1;
				dp[i][j] = Math.min(leftUp, dp[i-1][j]);
			}
		}
		//打印矩阵
		System.out.println("矩阵：");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j <= sum; j++) {
				if (dp[i][j] == max) {
					System.out.print("m ");
				}else {
					System.out.print(dp[i][j] + " ");
				}
			}
			System.out.println();
		}
		return dp[array.length-1][sum] == max ? -1 : dp[array.length-1][sum];
	}
	
	//数组
	public static int exchange8(int [] array, int sum) {
		if (array == null || array.length == 0 || sum < 0) 
			return -1;
		int max = Integer.MAX_VALUE;
		int [] dp = new int [sum+1];
		for(int j = 1; j <= sum; j++) 
			dp[j] = max;
		if(array[0] <= sum)
			dp[array[0]] = 1;
		int leftUp = 0;
		for(int i = 1; i < array.length; i++) {
			for(int j = sum; j > 0; j--) {
				leftUp = max;
				if(j >= array[i] && dp[j-array[i]] != max)
					leftUp = dp[j-array[i]] + 1;
				dp[j] = Math.min(leftUp, dp[j]);
			}
		}
		//打印数组
		System.out.println("数组：");
		for(int i = 0; i <= sum; i++) {
			if (dp[i] == max) {
				System.out.print("m ");
			}else {
				System.out.print(dp[i] + " ");
			}
		}
		System.out.println();
		return dp[sum] == max ? -1 : dp[sum];
	}
	
}
