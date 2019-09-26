/**
 * @Author eunji
 */
package basic_algorithm.array;

import data_structure.Stack;

/* 背包问题：类似换钱问题
 * capacity表示背包容量，array中每个元素(weight, value)：weight表示物品权重，value表示物品价值
 * dp[i][j]表示容量为j时，在物品0-i中能够挑选的最大价值
 */
public class Backpack {
    
    //0-1背包：每个物品的数量为1
    public static int generate1(int capacity, int [][] array) {
        if (array == null || array.length == 0 ||
            array[0] == null || array[0].length == 0)
            return -1;
        int row = array.length;
        int column = capacity + 1;
        int [][] dp = new int[row][column];
        //初始化第一行
        for (int i = 0; i < column; i++) {
            if (i >= array[0][0])
                dp[0][i] = array[0][1];
        }
        //构造矩阵
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                //dp[i][j]：容量为j，在物品0-i中能够挑选的最大价值等于
                //dp[i-1][j]（不挑选物品i）、dp[i-1][j-weight]+value（挑选物品i）的最大值
                dp[i][j] = dp[i-1][j];
                if (j - array[i][0] >= 0)//能够容纳物品i
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-array[i][0]] + array[i][1]);
            }
        }
        //打印矩阵
        System.out.println("矩阵：");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        //打印挑选的物品
        int x = 0, y = 0;
        Stack<Integer> stack = new Stack<>();
        for (x = row - 1, y = column - 1; x > 0; x--) {
            if (dp[x][y] > dp[x-1][y]) {//挑选了物品x，若两者相等，则默认没有挑选物品x
                stack.push(x);
                y = y - array[x][0];
            }
        }
        if (dp[0][y] != 0)//挑选了物品0
            stack.push(0);
        while (!stack.isEmpty()) {
            x = stack.pop();
            System.out.print("(" + array[x][0] + "," + array[x][1] + ") ");
        }
        return dp[row-1][column-1];
    }
    
    //完全背包：每个物品的数量无限
    public static int generate2(int capacity, int [][] array) {
        if (array == null || array.length == 0 ||
            array[0] == null || array[0].length == 0)
            return -1;
        int row = array.length;
        int column = capacity + 1;
        int [][] dp = new int[row][column];
        //初始化第一行
        for (int i = 0; i < column; i++)
            dp[0][i] = i / array[0][0] * array[0][1];
        //构造矩阵
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                //dp[i][j]：容量为j，在物品0-i中能够挑选的最大价值等于
                //dp[i-1][j]（不挑选物品i）、dp[i][j-weight]+value（挑选一个物品i）的最大值
                dp[i][j] = dp[i-1][j];
                if (j - array[i][0] >= 0)//能够容纳物品i
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-array[i][0]] + array[i][1]);
            }
        }
        //打印矩阵
        System.out.println("矩阵：");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        //打印挑选的物品，重复次数代表个数
        int x = 0, y = 0;
        Stack<Integer> stack = new Stack<>();
        for (x = row - 1, y = column -1; x > 0; x--) {
            while (dp[x][y] != dp[x-1][y]) {//挑选了物品x，统计个数
                stack.push(x);
                y = y - array[x][0];
            }
        }
        while (dp[0][y] != 0) {//挑选了物品0
            stack.push(0);
            y = y - array[0][0];
        }
        while (!stack.isEmpty()) {
            x = stack.pop();
            System.out.print("(" + array[x][0] + "," + array[x][1] + ") ");
        }
        return dp[row-1][column-1];
    }
}
