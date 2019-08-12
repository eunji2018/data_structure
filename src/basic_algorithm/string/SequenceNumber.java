/**
 * @Author eunji
 */
package basic_algorithm.string;

//子序列个数
public class SequenceNumber {

    //string1的子序列中string2出现的个数，不考虑空字符
    //dp[i][j]表示string1[0 - j-1]的子序列中string2[0 - i-1]出现的个数
    //dp[i][j]分为以下两种情况
    //若string1[j-1]与string2[i-1]相等，则dp[i][j] = dp[i-1][j-1] + dp[i][j-1]
    //若string1[j-1]与string2[i-1]不相等，则dp[i][j] = dp[i][j-1]
    //出现的个数即为dp矩阵右下角的值
    //优化：使用数组，从左到右，从下到上更新dp矩阵的每一列
    public static int generate1(char [] string1, char [] string2) {
        if (string1 == null || string1.length == 0 ||
            string2 == null || string2.length == 0)
            return -1;
        int row = string2.length + 1, column = string1.length + 1;
        int [][] dp = new int[row][column];
        //初始化第一列
//        for (int i = 0; i < row; i++)
//            dp[i][0] = 0;
        //初始化第一行
        for (int i = 0; i < column; i++)
            dp[0][i] = 1;
        //构造矩阵
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = dp[i][j-1];
                if (string1[j-1] == string2[i-1])
                    dp[i][j] = dp[i][j] + dp[i-1][j-1];
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

    //string所有子序列的个数，不考虑重复子序列、空字符
}
