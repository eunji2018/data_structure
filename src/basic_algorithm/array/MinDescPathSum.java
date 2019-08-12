/**
 * @Author eunji
 */
package basic_algorithm.array;

//最小下降路径和
//从矩阵的第一行选择任何一列作为起点，每次在下一行中只能选择相同或者相邻的一列往下走
//在所有下降路径中，查找路径和的最小值
//dp[i][j]表示以对应位置作为终点的所有路径中的最小路径和
public class MinDescPathSum {

    public static int generate(int [][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0)
            return -1;
        int row = matrix.length, column = matrix[0].length;
        int [][] dp = new int[row][column];
        //初始化第一行
        for (int i = 0; i < column; i++)
            dp[0][i] = matrix[0][i];
        //构造矩阵
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++) {
                dp[i][j] = dp[i-1][j];
                if (j > 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                if (j < column - 1)
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1]);
                dp[i][j] += matrix[i][j];
            }
        }
        //打印矩阵
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        //查找最小路径和
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < column; i++)
            if (dp[row-1][i] < min)
                min = dp[row-1][i];
        return min;
    }

}
