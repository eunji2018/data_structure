/**
 * @Author eunji
 */
package machine_test;

//位运算相关
//与1异或、同或，等效于取反
//与1相与，等于自身
//与0相或、异或、同或，等于自身

//n & (n-1)的结果等于将n最低位的1变为0
//n & (~n+1)的结果等于只保留n最低位的1
public class BitOperation {

    //不使用中间变量交换两个整数
    public static void swap(int one, int other) {
        one = one ^ other;
        other = one ^ other;
        one = one ^ other;
        System.out.println("one:" + one + " other:" + other);
        return;
    }

    //二进制数字中1的个数
    //使用2^n和二进制数字从低位到高位做与运算，记录结果不为0的个数
    public static int numberOfOne1(int n) {
        int count = 0;
        int temp = 1;
        while (temp != 0) {
            if ((temp & n) != 0)//按位与，结果不为0表示当前位为1
                count++;
            temp = temp << 1;//左移一位，从低位到高位
        }
        return count;
    }

    //优化
    //n & (n-1)的结果等于将n最低位的1变为0
    //n & (~n+1)的结果等于只保留n最低位的1
    public static int numberOfOne2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    //统计0 - n每个自然数的二进制中1的个数
    public static int[] numberOfOne3(int n) {
        int [] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            count[i] = count[i & (i - 1)] + 1;
        }
        return count;
    }


    //最大公约数
    public static int maxCommonDivisor(int one, int other){
        if (one < other)
            return maxCommonDivisor(other, one);
        if (other == 0)
            return one;
        if ((one & 1) == 0){
            if ((other & 1) == 0)
                return maxCommonDivisor(one >> 1, other >> 1) << 1;
            else
                return maxCommonDivisor(one >> 1, other);
        }else {
            if ((other & 1) == 0)
                return maxCommonDivisor(one, other >> 1);
            else
                return maxCommonDivisor(other, one - other);
        }
    }

    //数组中出现奇数次的一个数字，其他数字都出现偶数次
    public static void oneOdd(int [] array) {
        int result = 0;
        for (int one : array)
            result = result ^ one;
        System.out.println(result);
        return;
    }

    //数组长度为n，包含自然数0 - n中的n个数，求解缺失的自然数
    //将数组中的所有元素与自然数0 - n共2n + 1个数依次做异或运算，得到的结果即为缺失的自然数
    public static void missNumber(int [] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = result ^ i ^ array[i];
        }
        System.out.println(result ^ array.length);
        return;
    }


    //数组中出现奇数次的两个数字，其他数字都出现偶数次
    public static void twoOdd(int [] array) {
        int result = 0;
        for (int one : array)
            result = result ^ one;
        //result为出现奇数次的两个数字的异或
        int temp = result & (~result + 1);
        int other = 0;
        for (int one : array) {
            if ((one & temp) != 0)
                other = other ^ one;
        }
        System.out.println(other + " " + (other ^ result));
        return;
    }

    //数组中出现一次的一个数字，其他数字都出现K次（K > 1）
    //将每个数字转换成K进制表示，再将所有数字无进位求和
    //所有出现K次的数字进行无进位求和后，结果为0
    //最终结果为出现一次的数字，转换回十进制表示

    //汉明距离
    //两个数字对应二进制位不同的位置的数目
    //先求异或，再求结果中1的个数
}
