/**
 * @Author eunji
 */
package basic_algorithm.string;

//交错字符串
//target包含且只包含source1和source2中的所有字符
//并且target保持两个字符串中字符的相对顺序，则target是source1和source2的交错字符串
public class StaggeredString {

    //dp[i][j]表示target[0 - i+j-1]是否为source1[0 - i-1]和source2[0 - j-1]的交错字符串
    //dp[i][j]分为以下三种情况：
    //若dp[i-1][j]为true，并且source1[i-1] == target[i+j-1]，则dp[i][j]为true
    //若dp[i][j-1]为true，并且source2[j-1] == target[i+j-1]，则dp[i][j]为true
    //其他情况下，dp[i][j]为false
    //最终结果即为dp矩阵右下角的值
    public static boolean generate(char [] source1, char [] source2, char [] target) {
        if (source1 == null || source2 == null || target == null)
            return false;
        if (source1.length + source2.length != target.length)
            return false;
        int row = source1.length;
        int column = source2.length;
        boolean [][] dp = new boolean[row+1][column+1];
        dp[0][0] = true;
        //初始化第一列
        for (int i = 1; i <= row; i++) {
            if (source1[i-1] != target[i-1])
                break;
            dp[i][0] = true;
        }
        //初始化第一行
        for (int j = 1; j <= column; j++) {
            if (source2[j-1] != target[j-1])
                break;
            dp[0][j] = true;
        }
        //构造矩阵
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (dp[i-1][j] && source1[i-1] == target[i+j-1])
                    dp[i][j] = true;
                else if (dp[i][j-1] && source2[j-1] == target[i+j-1])
                    dp[i][j] = true;
            }
        }
        //打印矩阵
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= column; j++) {
                System.out.print(dp[i][j] ? "t " : "f ");
            }
            System.out.println();
        }
        return dp[row][column];
    }
}
