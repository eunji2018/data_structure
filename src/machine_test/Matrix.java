/**
 * @Author eunji
 */
package machine_test;

//矩阵相关
public class Matrix {

    /* 二维数组从左到右，从上到下递增，查找是否存在某元素
     * 从矩阵右上角（或者左下角）的元素开始比较，若相等，则返回
     * 若待查元素大于右上角，排除最上一行，若待查元素小于右上角，排除最右一列
     * 每次排除一行或一列（缩小查找范围），会得到新的子矩阵
     * 依次将待查元素与当前矩阵的右上角比较，直到找到（存在）或越界（不存在）
     */
    public static boolean twoDimensionSearch(int [][] array, int data) {
        if(array == null || array[0] == null)
            return false;
        int row = array.length;
        int column = array[0].length;
        for(int i = 0, j = column - 1; i < row && j >= 0;) {
            if(array[i][j] == data)//找到元素
                return true;
            if(data > array[i][j]) {//待查元素大于右上角
                i++;
            }else {//待查元素小于右上角
                j--;
            }
        }
        return false;
    }

    /* 二维数组从左到右，从上到下递增，查找第K小元素
     *
     */


    /* 由外向里，顺时针遍历矩阵
     */

    //斜向遍历矩阵

    //以矩阵中某位置为起点，只能向上、下、左、右移动
    //求解在最多移动n步的情况下，走出矩阵的路径数，一旦走出矩阵，则不能再进入矩阵
    public static int moveOut(int row, int column, int x, int y, int n) {
        if (x == -1 || x == row || y == -1 || y == column)//在矩阵的外面
            return 1;
        if (n == 0)
            return 0;
        int count = 0;
        count += moveOut(row, column, x - 1, y, n - 1);
        count += moveOut(row, column, x + 1, y, n - 1);
        count += moveOut(row, column, x, y - 1, n - 1);
        count += moveOut(row, column, x, y + 1, n - 1);
        return count;
    }

}
