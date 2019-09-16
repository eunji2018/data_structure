package demo;

import java.util.*;

//牛客笔试专用类
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int row = scanner.nextInt();
        int column = scanner.nextInt();
        long result = 0;
        int [] array = new int[column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                array[j] = scanner.nextInt();
            result += generate2(array);
        }
        System.out.println(result);

        return;

    }

    public static long generate2(int [] array) {
        if (array == null || array.length == 0)
            return 0;
        int length = array.length;
        long [][] dp = new long[length][length];
        //初始化主对角线
        for (int i = 0; i < length; i++)
            dp[i][i] = array[i] * 2;
        //构造矩阵右上部分
        for (int count = 1; count < length; count++) {
            for (int i = 0, j = count; j < length; i++, j++) {
                dp[i][j] = Math.max(array[i]+dp[i+1][j], array[j]+dp[i][j-1]) * 2;
            }
        }
        return dp[0][length-1];
    }

}