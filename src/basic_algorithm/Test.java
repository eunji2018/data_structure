/**
 * @Author eunji
 */
package basic_algorithm;

import basic_algorithm.array.*;
import basic_algorithm.other.*;
import basic_algorithm.sort.*;
import basic_algorithm.string.*;

public class Test {

	public static void main(String[] args) {
//	    test_Backpack();
//	    test_ExchangeMoney();
//	    test_JumpArray();
//	    test_MatrixMinPath();
//	    test_MaxSubMatrix();

//	    test_MCSEQ();
//	    test_MinBlood();
//	    test_TOPK();

//	    test_Fibonacci();

//	    test_BubbleSort();
//	    test_HeapSort();
//	    test_InsertSort();
//	    test_MergeSort();
//	    test_QuickSort();
//	    test_SelectSort();
//	    test_ShellSort();
//	    test_ThreeWaySort();

//	    test_BracketLength();
//	    test_LCSEQ();
//	    test_LCSTR();
//	    test_LISEQ();
//	    test_LPSTR();
//	    test_MinEditCost();
//	    test_PalindromeSeparate();
//	    test_PalindromeString();
//	    test_StringMatch();
        test_SubSequenceNumber();


	}

    //背包问题测试
    public static void test_Backpack() {
        int [][] array1 = {{2,1},{1,3},{3,1},{4,5},{3,5}};
        System.out.println(Backpack.generate1(10, array1));
        int [][] array2 = {{2,2},{3,4},{4,5},{5,6},{6,9}};
        System.out.println(Backpack.generate2(15, array2));
    }

    //换钱问题测试
    public static void test_ExchangeMoney() {
        int [] array1 = {2,3,5};//钱数任意
        int [] array2 = {2,3,3,5,5};//钱数有限
        //方法数：钱数任意
        System.out.println("方法数：钱数任意");
        int number1 = ExchangeMoney.exchange1(array1, 15);
        int number2 = ExchangeMoney.exchange2(array1, 15);
        System.out.println(number1);
        System.out.println(number2);
        System.out.println("--------");
        //方法数：钱数有限

        //最少货币数：钱数任意
        System.out.println("最少货币数：钱数任意");
        int number5 = ExchangeMoney.exchange5(array1, 15);
        int number6 = ExchangeMoney.exchange6(array1, 15);
        System.out.println(number5);
        System.out.println(number6);
        System.out.println("--------");
        //最少货币数：钱数有限
        System.out.println("最少货币数：钱数有限");
        int number7 = ExchangeMoney.exchange7(array2, 15);
        int number8 = ExchangeMoney.exchange8(array2, 15);
        System.out.println(number7);
        System.out.println(number8);
    }

    //跳跃数组测试
    public static void test_JumpArray() {
        int [] array = {3,2,3,1,1,3,2,2,1,2};
        int count = JumpArray.generate(array);
        System.out.println();
        System.out.println(count);
    }

    //矩阵最小路径和测试
    public static void test_MatrixMinPath() {
        int[][] matrix = {{1,3,5,9},
                          {8,1,3,4},
                          {5,0,6,1},
                          {8,8,4,0}};
        int pathSum1 = MatrixMinPath.generate1(matrix);
        int pathSum2 = MatrixMinPath.generate2(matrix);
        System.out.println(pathSum1);
        System.out.println(pathSum2);
    }

    //最大子矩阵和测试
    public static void test_MaxSubMatrix() {
	    int[][] matrix = {{5,-10,3,-1},
                          {-4,2,0,3},
                          {1,5,4,-2},
                          {2,-3,-8,4}};
	    int sum = MaxSubMatrix.generate1(matrix);
        System.out.println();
        System.out.println(sum);
    }

    //最大连续子序列测试
    public static void test_MCSEQ() {
        int [] array = {1,-2,3,5,-2,6,-1};
        int sum = MCSEQ.generate1(array);
        System.out.println("sum: " + sum);
    }

    //最低血量测试
    public static void test_MinBlood() {
        int [][] matrix = {{-3,-2,5,2},
                           {-1,2,3,-2},
                           {3,4,-3,-2},
                           {4,2,-1,-5}};
        int blood = MinBlood.generate(matrix);
        System.out.println(blood);
    }

    //TOPK测试
    public static void test_TOPK() {
        int [] array = {15,14,20,16,17,13,12,18,11,19,5,4,10,6,7,3,2,9,8,1};
        TOPK.kMin(array, 7);
    }

    //斐波那契测试
	public static void test_Fibonacci() {
		for(int i = 1; i < 20; i++) {
			System.out.println(Fibonacci.fibonacci(i));
		}
	}

    //冒泡排序测试
    public static void test_BubbleSort() {
        int [] array = {5,4,0,6,7,3,2,9,8,1};
        BubbleSort.sort(array);
    }

    //堆排序测试
    public static void test_HeapSort() {
        int [] array = {15,7,13,2,11,5,4,10,6,14,3,12,9,8,1};
        HeapSort.sort(array);
    }

    //插入排序测试
    public static void test_InsertSort() {
        int [] array = {5,6,4,7,3,8,2,9,1,0};
        InsertSort.sort(array);
    }

    //归并排序测试
    public static void test_MergeSort() {
        int [] array = {5,6,4,7,3,8,2,9,1,0,0,1,9,2,8,3,7};
        MergeSort.sort(array);
    }

    //快速排序测试
    public static void test_QuickSort() {
        int [] array = {5,6,4,7,3,8,2,9,1,0};
        QuickSort.sort(array);
    }

    //选择排序测试
    public static void test_SelectSort() {
        int [] array = {5,6,4,7,3,8,2,9,1,0};
        SelectSort.sort(array);
    }

    //希尔排序测试
    public static void test_ShellSort() {
        int [] array = {5,6,4,7,3,8,2,9,1,0,0,1,9,2,8,3,7,4,6,5};
        ShellSort.sort(array);
    }

    //三向排序测试
    public static void test_ThreeWaySort() {
        int [] array = {0,2,1,0,1,2,0};
        ThreeWaySort.sort(array);
    }

    //括号字符串测试
    public static void test_BracketLength() {
	    String string1 = "(()()(())((()()";
	    int length1 = BracketLength.generate(string1.toCharArray());
	    System.out.println();
	    System.out.println(length1);
    }

    //最长公共子序列测试
    public static void test_LCSEQ() {
        String string1 = "ab12c345d67";
        String string2 = "a1b23c4d56e";
        int length = LCSEQ.generate(string1.toCharArray(), string2.toCharArray());
        System.out.println("length: " + length);
    }

    //最长公共子串测试
    public static void test_LCSTR() {
        String string1 = "aabb11";
        String string2 = "aacc111";
        int length1 = LCSTR.generate1(string1.toCharArray(), string2.toCharArray());
        System.out.println(length1);
        int length2 = LCSTR.generate2(string1.toCharArray(), string2.toCharArray());
        System.out.println(length2);
    }

	//最长递增子序列测试
	public static void test_LISEQ() {
		int [] array = {2,1,5,3,6,4,8,9,7};
		int length = LISEQ.generate(array);
		System.out.println();
		System.out.println(length);
	}

	//最长回文子串测试
	public static void test_LPSTR() {
		String string = "abaabcdcbab";
		int length = LPSTR.generate(string.toCharArray());
		System.out.println(length);
	}
	
	//最小编辑代价测试
	public static void test_MinEditCost() {
	    String string1 = "abcdefg";
	    String string2 = "abefh";
	    int [] array = {5,3,2};
	    int cost = MinEditCost.generate(string1.toCharArray(),string2.toCharArray(),array);
	    System.out.println(cost);
    }

    //回文最少切分数测试
    public static void test_PalindromeSeparate() {
	    String string = "abacbcadac";
	    int count = PalindromeSeparate.generate(string.toCharArray());
	    System.out.println(count);
    }

    //回文字符串测试
    public static void test_PalindromeString() {
	    String string1 = "a1bab2";
	    String string2 = "aabab1";
        int count1 = PalindromeString.generate(string1.toCharArray());
        System.out.println(count1);
        int count2 = PalindromeString.generate(string2.toCharArray());
        System.out.println(count2);
    }

	//KMP测试
	public static void test_StringMatch() {
		String source = "abcdeabcabcdabcdeabcabc";
		String match = "abcdabcdeabc";
		int index = StringMatch.generate(source.toCharArray(), match.toCharArray());
		System.out.println(index);
	}

	//子序列个数测试
    public static void test_SubSequenceNumber() {
	    String string1 = "rabbbit";
	    String string2 = "rabbit";
	    int count = SubSequenceNumber.generate1(string1.toCharArray(), string2.toCharArray());
        System.out.println(count);
    }

	
}