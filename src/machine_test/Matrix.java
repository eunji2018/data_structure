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

    /* 由外向里，顺时针遍历矩阵
     */

    //斜向遍历矩阵
}
