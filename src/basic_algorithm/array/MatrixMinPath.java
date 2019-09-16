/**
 * @Author eunji
 */
package basic_algorithm.array;

import data_structure.Stack;

//矩阵最小路径和及路径
//从矩阵左上角走到右下角，只能向右或向下，路径和等于路径上点的值的和
//dp[i][j]表示从起点到当前点(i,j)的最小路径和
public class MatrixMinPath {

	public static int generate1(int [][] matrix) {
		if (matrix == null || matrix.length == 0 ||
			matrix[0] == null || matrix[0].length == 0) 
			return 0;
		int row = matrix.length;
		int column = matrix[0].length;
		int [][] dp = new int [row][column];
		dp[0][0] = matrix[0][0];
		//初始化第一列
		for (int i = 1; i < row; i++)
			dp[i][0] = dp[i-1][0] + matrix[i][0];
		//初始化第一行
		for (int j = 1; j < column; j++)
			dp[0][j] = dp[0][j-1] + matrix[0][j];
		//构造矩阵
		for (int i = 1; i < row; i++)
			for (int j = 1; j < column; j++)
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
		//打印矩阵
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		//打印最小路径
		Stack<Integer> stack = new Stack<>();
		int x = row - 1, y = column - 1;
		while (x > 0 && y > 0) {
			stack.push(matrix[x][y]);
			if (dp[x][y] - matrix[x][y] == dp[x-1][y]) {
				x--;//回溯时，优先向上
			}else {
				y--;			
			}
		}
		if (x == 0) {//回溯到第一行
			while (y >= 0) {
				stack.push(matrix[0][y]);
				y--;
			}
		}else {//回溯到第一列
			while (x >= 0) {
				stack.push(matrix[x][0]);
				x--;
			}
		}				
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");
		System.out.println();
		return dp[row-1][column-1];
	}
	
	public static int generate2(int [][] matrix) {
		if (matrix == null || matrix.length == 0 ||
			matrix[0] == null || matrix[0].length == 0) 
			return 0;
		int more = Math.max(matrix.length, matrix[0].length);
		int less = Math.min(matrix.length, matrix[0].length);
		boolean rowmore = (more == matrix.length);
		int [] array = new int [less];
		array[0] = matrix[0][0];
		for (int i = 1; i < less; i++)
			array[i] = array[i-1] + (rowmore ? matrix[0][i] : matrix[i][0]);
		for (int i = 1; i < more; i++) {
			array[0] = array[0] + (rowmore ? matrix[i][0] : matrix[0][i]);
			for (int j = 1; j < less; j++)
				array[j] = Math.min(array[j], array[j-1]) + (rowmore ? matrix[i][j] : matrix[j][i]);
		}				
		return array[less-1];
	}
}
