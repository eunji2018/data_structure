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

    /* string所有子序列的个数，不考虑重复子序列、空字符
     * dp[i]表示string[0 - i]中不同的子序列个数
     * last[i]表示在string[0 - i-1]中string[i]最后出现的位置
     * string[0 - i]的所有子序列分为两种情形：
     * 1.不包含string[i]的子序列，结果为dp[i-1]
     * 2.包含string[i]的子序列：（1）只包含string[i]，结果为1（2）包含string[i]并且包含string[0 - i-1]的至少一个字符，结果为dp[i-1]
     * 第二种情形可能包含重复的子序列，并且重复子序列的个数为dp[last[i]-1]
     * 以ababab为例，每行的子序列表示因为当前字符而构成的新的子序列，即用当前字符作为尾字符去拼接上方所有行的子序列，其中一部分可以作为新的子序列
     * 所以前i行包含了string[0 - i]所有不同的子序列
     * # : #（#表示空字符）
     * a : a
     * b : b ab
     * a : aa ba aba
     * b : bb abb aab bab abab
     * a : aaa baa abaa bba abba aaba baba ababa
     * b : bbb abbb aabb babb ababb aaab baab abaab bbab abbab aabab babab ababab
     * 规律：使用string[i]拼接last[i]上方所有行的子序列时，得到的都是重复子序列，拼接其余行的子序列时，得到的都是新的子序列
     * 递推关系为，dp[i] = 2 * dp[i-1] + 1（当last[i]不存在）或者2 * dp[i-1] - dp[last[i]-1]（当last[i]存在）
     */

    /* string所有子串的个数，不考虑重复子串、空字符
     *
     */

}
