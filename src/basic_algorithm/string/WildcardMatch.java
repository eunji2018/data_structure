/**
 * @Author eunji
 */
package basic_algorithm.string;

//通配符匹配
//string不包含'.'、'*'两种字符，expression可以包含'.'、'*'两种字符，且'*'不能是首字符，任意两个'*'不能相邻
//'.'代表任何一个字符，'*'表示前一个字符可以有0个或多个，判断string能否被expression匹配
public class WildcardMatch {

    //从左向右判断string右半部分的子串能否被expression右半部分的子串匹配
    //string、expression的当前位置分别表示为i、j，字符串长度记为m、n，即判断string[i - m-1]与expression[j - n-1]
    //初始时，i和j都等于0，每次循环时expression[j]不可能为'*'字符，分为以下四种情况：
    //j+1位置为'*'时，若string[i]与expression[j]不匹配，则j = j + 2
    //若string[i]与expression[j]匹配，则j = j + 2，依次判断i = i + k（k >= 0）的结果
    //j+1位置不为'*'时，若string[i]与expression[j]不匹配，则返回false
    //若string[i]与expression[j]匹配，则i = i + 1，j = j + 1
    //边界情形：当j == n时，i == m表示匹配成功，i != m表示匹配失败
    public static boolean generate(char [] string, char [] expression) {
        if (string == null || expression == null)
            return false;
        //假设string、expression都是有效字符串
        return process1(string, expression, 0, 0);
    }

    //递归法
    private static boolean process1(char [] string, char [] expression, int i, int j) {
        if (j == expression.length)//j遍历完expression
            return i == string.length;
        if (j == expression.length - 1 || expression[j+1] != '*') {//j指向expression最后一个字符或者下一个字符不为'*'
            //此时expression[j]只能出现一次，string[i] == expression[j] || expression[j] == '.'表示两个字符可以匹配
            return i != string.length && (string[i] == expression[j] || expression[j] == '.') && process1(string, expression, i+1, j+1);
        }
        //expression[j]的下一个字符为'*'
        while (i != string.length && (string[i] == expression[j] || expression[j] == '.')) {
            if (process1(string, expression, i, j+2))
                return true;
            i++;
        }
        return process1(string, expression, i, j+2);
    }

    //动态规划法
    //dp[i][j]表示string[i - m-1]与expression[j - n-1]的匹配结果
    //dp[i][j]依赖于dp[i+1][j+1]、dp[i+k][j+2]（k >= 0）
    private static boolean process2(char [] string, char [] expression) {
        return false;
    }

}
