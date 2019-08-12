/**
 * @Author eunji
 */
package basic_algorithm.string;

//最长回文子序列：类似回文填充
public class LPSEQ {

    //动态规划
    //dp[i][j]表示string[i - j]最长回文子序列的长度
    //dp[i][j]分为两种情形
    //若string[i]与string[j]相等，则dp[i][j] = dp[i+1][j-1] + 2
    //若string[i]与string[j]不相等，则dp[i][j]等于dp[i+1][j]，dp[i][j-1]的较大值
    //最长回文子序列的长度即为dp矩阵右上角的值
    //最长回文子序列由如下得到，从两端向中间构造子序列：
    //比较string第一个与最后一个字符，若相等，则添加到序列中，并且同时向中间移动一步
    //若不相等，比较去掉最左边或最右边的字符后的最长回文子序列长度，选择较大的策略，相应的一端向中间移动一步
    //剩余一个字符时，直接添加，剩余两个字符时，若相等，都添加到序列中，若不相等，添加任意一个字符
    public static int generate(char [] string) {
        if (string == null || string.length == 0)
            return -1;
        int length = string.length;
        int [][] dp = new int[length][length];
        //构造矩阵右上部分
        for (int count = 0; count < length; count++) {
            for (int i = 0, j = count; j < length; i++, j++) {
                if (i == j) {
                    dp[i][j] = 1;
                }else if (string[i] == string[j]){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        //打印矩阵右上部分
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++)
                System.out.print(" " + " ");
            for (int j = i; j < length; j++)
                System.out.print(dp[i][j]  + " ");
            System.out.println();
        }
        //构造最长回文子序列
        return dp[0][length-1];
    }
}
