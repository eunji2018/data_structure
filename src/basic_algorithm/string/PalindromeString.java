/**
 * @Author eunji
 */
package basic_algorithm.string;

//回文字符串
//可以在任意位置添加字符，使用最少的字符构造回文字符串
//dp[i][j]表示string[i - j]需要添加字符的最少个数
//dp[i][j]分为以下四种情形：
//i与j相等，则dp[i][j] = 0
//i与j不相等，j - i = 1，dp[i][j] = 1
//j - i > 1，string[i]与string[j]相等，则dp[i][j] = dp[i+1][j-1]
//string[i]与string[j]不相等，则dp[i][j]等于dp[i+1][j] + 1，dp[i][j-1] + 1的较小值
//从正对角线开始，向右上角依次计算每条斜线，最少字符数即为dp矩阵右上角的值
//最终的回文字符串由如下得到：
//比较string第一个与最后一个字符，若相等，则都向中间移动一步
//若不相等，比较去掉最左边或最右边的字符后需要添加的字符数，选择较少的策略，在当前子串中相对的一端添加字符
//剩余一个字符时，直接结束，剩余两个字符时，若相等，直接结束，若不相等，则在任意一端添加字符即可
public class PalindromeString {

    public static int generate(char [] string) {
        if (string == null || string.length == 0)
            return -1;
        int length = string.length;
        int [][] dp = new int[length][length];
        //构造矩阵右上部分
        for (int count = 1; count < length; count++) {
            for (int i = 0, j = count; j < length; i++, j++) {
                if (j - i == 1) {//子字符串含有两个字符
                    dp[i][j] = string[i] == string[j] ? 0 : 1;
                }else if (string[i] == string[j]) {//多于两个字符
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i+1][j],dp[i][j-1]) + 1;
                }
            }
        }
        //打印矩阵右上部分
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++)
                System.out.print(" " + " ");
            for (int j = i; j < length; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        return dp[0][length-1];
    }
}
