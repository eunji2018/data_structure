/**
 * @Author eunji
 */
package basic_algorithm.string;

//最小编辑代价，通过添加，删除，替换三种操作将string1编辑成string2
//insert，delete，replace表示三种操作的代价
//dp[i][j]表示将string1[0 - i-1]编辑成string2[0 - j-1]的最小代价
//dp[i][j]等于以下四种情况中的最小值：
//先删除string1[i-1]，再将string1[0 - i-2]编辑成string2[0 - j-1]，代价为dp[i-1][j] + delete
//先将string1[0 - i-1]编辑成string2[0 - j-2]，再添加string2[j-1]，代价为dp[i][j-1] + insert
//若string1[i-1]与string2[j-1]不相等，先将string1[0 - i-2]编辑成string2[0 - j-2]，再将string1[i-1]替换为string2[j-1]，代价为dp[i-1][j-1] + replace
//若string1[i-1]与string2[j-1]相等，将string1[0 - i-2]编辑成string2[0 - j-2]，代价为dp[i-1][j-1]
public class MinEditCost {

    public static int generate(char [] string1, char [] string2, int [] cost) {
        if (string1 == null || string1.length == 0 ||
            string2 == null || string2.length == 0)
            return -1;
        int row = string1.length + 1, column = string2.length + 1;
        int insert = cost[0], delete = cost[1], replace = cost[2];
        int [][] dp = new int[row][column];
        //两个字符串前面都补充一个空字符
        //初始化第一列
        for (int i = 1; i < row; i++)
            dp[i][0] = delete * i;
        //初始化第一行
        for (int i = 1; i < column; i++)
            dp[0][i] = insert * i;
        //构造矩阵
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = dp[i-1][j-1] + (string1[i-1] == string2[j-1] ? 0 : replace);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+delete);
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+insert);
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
