/*
    @Author：eunji
 */
package basic_algorithm.array;

//最低血量
//从矩阵左上角走到右下角，只能向右或向下
//经过每个点时，血量加上相应的数量matrix[i][j]，并且不能低于1
//dp[i][j]表示到点（i，j）之前需要的最低血量
//dp[i][j]等于到点（i，j）之后向右走或向下走需要的血量的较小值
//从右向左，从下向上计算每个点需要的血量，最低血量即为dp矩阵左上角的值
public class MinBlood {

    public static int generate(int [][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0)
            return 0;
        int row = matrix.length, column = matrix[0].length;
        int [][] dp = new int[row][column];
        int x = row - 1, y = column - 1;
        dp[x][y] = Math.max(1,1-matrix[x][y]);
        //初始化最后一列
        for (int i = x - 1; i >= 0; i--)
            dp[i][y] = Math.max(1,dp[i+1][y]-matrix[i][y]);
        //初始化最后一行
        for (int i = y - 1; i >= 0; i--)
            dp[x][i] = Math.max(1,dp[x][i+1]-matrix[x][i]);
        //构造矩阵
        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i+1][j]-matrix[i][j],dp[i][j+1]-matrix[i][j]);
                dp[i][j] = Math.max(1,dp[i][j]);
            }
        }
        //打印矩阵
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        return dp[0][0];
    }
}
