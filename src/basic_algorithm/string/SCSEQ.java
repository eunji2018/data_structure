/**
 * @Author eunji
 */
package basic_algorithm.string;

//最短公共超序列
//先求解最长公共子序列，再构造最短公共超序列
public class SCSEQ {

    public static int generate(char [] string1, char [] string2) {
        if (string1 == null || string1.length == 0 ||
            string2 == null || string2.length == 0)
            return -1;
        int row = string1.length, column = string2.length;
        int [][] dp = new int[row][column];
        dp[0][0] = string1[0] == string2[0] ? 1 : 0;
        //初始化第一列
        for (int i = 1; i < row; i++)
            dp[i][0] = Math.max(dp[i-1][0],(string1[i] == string2[0] ? 1 : 0));
        //初始化第一行
        for (int i = 1; i < column; i++)
            dp[0][i] = Math.max(dp[0][i-1],(string1[0] == string2[i] ? 1 : 0));
        //构造矩阵
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (string1[i] == string2[j])
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
            }
        }
        //构造最短公共超序列
        int x = row - 1, y = column - 1;
        int count = dp[x][y];
        int length = row + column - count;
        char [] result = new char[length];
        int index = length - 1;
        while (count > 0) {
            if (x > 0 && dp[x][y] == dp[x-1][y]) {
                result[index] = string1[x];
                x--;
            }else if (y > 0 && dp[x][y] == dp[x][y-1]) {
                result[index] = string2[y];
                y--;
            }else {
                result[index] = string1[x];
                count--;
                x--;
                y--;
            }
            index--;
        }
        if (index >= 0) {
            while (y >= 0) {
                result[index] = string2[y];
                y--;
                index--;
            }
            while (x >= 0) {
                result[index] = string1[x];
                x--;
                index--;
            }
        }
        //打印最短公共超序列
        System.out.println(String.valueOf(result));
        return length;
    }
}
