/*
    @Author：eunji
 */
package basic_algorithm.string;

//回文最少切分数
//使用最少的次数，将源字符串切分为回文子串
//dp[i]表示string[i - length-1]需要切分的最少次数
//dp[i]等于dp[j+1] + 1的最小值，其中i <= j < length，且string[i - j]是回文子串
//从右向左计算每个子串需要切分的最少次数，回文最少切分数即为dp数组的第一个值
//判断string[i - j]是否为回文子串：
//i == j，则返回true
//j - i == 1，若string[i]与string[j]相等，则返回true，否则返回false
//j - i > 1，若string[i+1 - j-1]是回文子串且string[i]与string[j]相等，则返回true，否则返回false
public class PalindromeSeparate {

    public static int generate(char [] string) {
        if (string == null || string.length == 0)
            return -1;
        int length = string.length;
        int [] dp = new int[length+1];
        dp[length] = -1;
        boolean [][] palindrome = new boolean[length][length];//palindrome[i][j]表示string[i - j]是否为回文子串
        for (int i = length - 1; i >= 0; i--) {
            dp[i] = length;//切分次数不超过length
            for (int j = i; j < length; j++) {
                if (string[i] == string[j] && (j - i < 2 || palindrome[i+1][j-1])) {//string[i - j]是回文子串
                    palindrome[i][j] = true;
                    dp[i] = Math.min(dp[i],dp[j+1]+1);
                }
            }
        }
        //打印数组
        for (int i = 0; i < length; i++)
            System.out.print(dp[i] + " ");
        System.out.println();
        //打印矩阵
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++)
                System.out.print(" " + " ");
            for (int j = i; j < length; j++)
                System.out.print((palindrome[i][j] ? "t" : "f") + " ");
            System.out.println();
        }
        return dp[0];
    }
}
