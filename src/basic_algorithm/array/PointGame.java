/**
 * @Author eunji
 */
package basic_algorithm.array;

//点数游戏
public class PointGame {

    //array中元素表示点数，每人每次只能挑选最左或者最右位置的点数
    //两个人进行游戏，并且都足够聪明，求解先手获得最大点数和时，两者总和之差
    //返回结果大于0，表示先手赢，小于0，表示后手赢，等于0，表示平手
    //first[i][j]表示在array[i - j]中，先手能够获得的最大点数和
    //second[i][j]表示在array[i - j]中，先手获得最大点数和时，后手获得的点数和
    //first[i][j]等于array[i] + second[i+1][j]与array[j] + second[i][j-1]中的较大值
    //second[i][j] == first[i+1][j]，当array[i] + second[i+1][j] > array[j] + second[i][j-1]
    //second[i][j] == first[i][j-1]，当array[i] + second[i+1][j] < array[j] + second[i][j-1]
    //最终结果即为first与second矩阵右上角元素之差
    //TODO array[i] + second[i+1][j] == array[j] + second[i][j-1]
    public static int generate1(int [] array) {
        if (array == null || array.length == 0)
            return Integer.MIN_VALUE;
        int length = array.length;
        int [][] first = new int[length][length];
        int [][] second = new int[length][length];
        //初始化first矩阵
        for (int i = 0; i < length; i++)
            first[i][i] = array[i];
        //构造first，second矩阵
        for (int count = 1; count < length; count++) {
            for (int i = 0, j = count; j < length; i++, j++) {
                if (array[i] + second[i+1][j] > array[j] + second[i][j-1]) {
                    first[i][j] = array[i] + second[i+1][j];
                    second[i][j] = first[i+1][j];
                }else {
                    first[i][j] = array[j] + second[i][j-1];
                    second[i][j] = first[i][j-1];
                }
            }
        }
        //打印矩阵
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++)
                System.out.print(first[i][j] + " ");
            System.out.println();
        }
        System.out.println("---");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++)
                System.out.print(second[i][j] + " ");
            System.out.println();
        }
        System.out.println("---");
        return first[0][length-1] - second[0][length-1];
    }

    //array中元素表示点数，每次只能挑选最左或者最右位置的点数
    //每次挑选的分数为点数与2^i的乘积（i表示挑选次数）
    //返回挑选完后能够得到的最大分数
    //dp[i][j]表示
    //最大分数即为dp矩阵右上角的值
    public static int generate2(int [] array) {
        if (array == null || array.length == 0)
            return 0;
        int length = array.length;
        int [][] dp = new int[length][length];
        //初始化主对角线
        for (int i = 0; i < length; i++)
            dp[i][i] = array[i] * 2;
        //构造矩阵右上部分
        for (int count = 1; count < length; count++) {
            for (int i = 0, j = count; j < length; i++, j++) {
                dp[i][j] = Math.max(array[i]+dp[i+1][j], array[j]+dp[i][j-1]) * 2;
            }
        }
        //打印矩阵
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
