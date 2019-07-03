/**
 * @Author eunji
 */
package basic_algorithm.string;

//括号字符串的最长有效长度
//dp[i]表示以string[i]结尾的最长有效括号子串的长度
//dp[i]分为以下三种情形：
//string[i] == '('，dp[i] = 0
//string[i] == ')'，若string[i-1-dp[i-1]] == '('，dp[i] = dp[i-1] + 2 + dp[i-2-dp[i-1]]
//若string[i-1-dp[i-1]] == ')'，dp[i] = 0
//最长有效长度即为dp数组中的最大值
public class BracketLength {

    public static int generate(char [] string) {
        if (string == null || string.length == 0)
            return -1;
        int length = string.length;
        int [] dp = new int[length];
        int index = 0, max = 0, temp = 0;
        for (int i = 1; i < length; i++) {
            if (string[i] == ')') {
                temp = i - 1 - dp[i-1];//左边对应字符的位置
                if (temp >= 0 && string[temp] == '(')
                    dp[i] = dp[i-1] + 2 + (temp > 0 ? dp[i-2-dp[i-1]] : 0);
                if (dp[i] > max) {
                    max = dp[i];
                    index = i;
                }
            }
        }
        //打印数组
        for (int i = 0; i < length; i++)
            System.out.print(dp[i] + " ");
        System.out.println();
        //打印最长有效括号子串
        for (int i = index - max + 1; i <= index; i++)
            System.out.print(string[i] + " ");
        return max;
    }
}
