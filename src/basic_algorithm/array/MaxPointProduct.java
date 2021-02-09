/**
 * @Author eunji
 */
package basic_algorithm.array;

//最大点积
//给定两个数组，求解长度相同的两个非空子序列的最大点积
//array1[0 - j]与array2[0 - i]的最大点积
//（1）小于等于array1[0 - j+1]与array2[0 - i]的最大点积
//（2）小于等于array1[0 - j]与array2[0 - i+1]的最大点积
public class MaxPointProduct {

    public static int generate(int [] array1, int [] array2) {
        if (array1 == null || array1.length == 0 ||
            array2 == null || array2.length == 0)
            return -1;
        int row = array2.length, column = array1.length;
        int [][] dp = new int[row][column];
        dp[0][0] = array1[0] * array2[0];
        int product = 0;
        //初始化第一列
        for (int i = 1; i < row; i++)
            dp[i][0] = Math.max(dp[i-1][0], array1[0] * array2[i]);
        //初始化第一行
        for (int j = 1; j < column; j++)
            dp[0][j] = Math.max(dp[0][j-1], array1[j] * array2[0]);
        //构造矩阵
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                product = array1[j] * array2[i];
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = Math.max(dp[i][j], product);
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + product);
            }
        }
        //打印矩阵
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        return dp[row-1][column-1];
    }
}
