/**
 * @Author eunji
 */
package basic_algorithm.array;

import data_structure.Stack;

//最大子矩阵
public class MaxSubMatrix {

    //子矩阵元素的最大和
    //将矩阵相邻的几行按列相加成一行，再根据最大连续子序列和可以求出最大子矩阵和
    public static int generate1(int [][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0)
            return -1;
        int row = matrix.length;
        int column = matrix[0].length;
        int [] temp;
        int sum = 0, max = -1;
        int right = 0, up = 0, bottom = 0;//保存当前子矩阵的最右一列，最上一行，最下一行
        for (int i = 0; i < row; i++) {
            temp = new int[column];
            for (int j = i; j < row; j++) {
                sum = 0;
                for (int k = 0; k < column; k++) {
                    temp[k] = temp[k] + matrix[j][k];
                    sum = sum + temp[k];
                    if (sum > max) {
                        max = sum;
                        right = k;
                        up = i;
                        bottom = j;
                    }
                    if (sum < 0)
                        sum = 0;
                }
            }
        }
        //打印子矩阵，从上到下，从左到右
        sum = max;
        Stack<Integer> stack = new Stack<>();
        for (int i = right; sum != 0; i--) {
            for (int j = bottom; j >= up; j--) {
                stack.push(matrix[j][i]);
                sum = sum - matrix[j][i];
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        return max;
    }

    //元素全部为1的最大子矩阵，矩阵元素为1或者0
    public static void generate2(int [][] matrix) {

    }

}
