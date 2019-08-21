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
//	    test_AllocateCandy();
//	    test_Backpack();
//	    test_ExchangeMoney();
//	    test_JumpArray();
//	    test_MatrixMinPath();
//	    test_MaxSubMatrix();

	    test_MCSEQ();
//	    test_MinBlood();
//        test_MinDescPathSum();
//	    test_MinRefuel();
//	    test_StockProfit();
//	    test_SwingSequence();

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
//	    test_LPSEQ();
//	    test_LPSTR();
//	    test_MinEditCost();
//	    test_PalindromeSeparate();
//	    test_PalindromeFill();
//        test_SCSEQ();
//        test_SequenceNumber();
//	    test_StringMatch();


	}

	//糖果分配问题测试
    public static void test_AllocateCandy() {
	    int [] array = {5,4,1,1};
        System.out.println(AllocateCandy.generate1(array));
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
        int count = JumpArray.generate2(array);
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
        int [] array1 = {1,-2,3,5,-2,6,-1};

        System.out.println("最大连续子序列和：");
        System.out.println("线性遍历");
        System.out.println("sum: " + MCSEQ.generate1(array1));
        System.out.println("动态规划");
        System.out.println("sum: " + MCSEQ.generate2(array1));
        System.out.println("分治");
        System.out.println("sum: " + MCSEQ.generate3(array1));

        System.out.println("最大子序列和：");
        System.out.println("sum: " + MCSEQ.generate4(array1, 1));
        System.out.println("sum: " + MCSEQ.generate4(array1, 2));
        System.out.println("sum: " + MCSEQ.generate4(array1, 3));

        System.out.println();
        System.out.println("sum: " + MCSEQ.generate5(array1, 1));
        System.out.println("sum: " + MCSEQ.generate5(array1, 2));
        System.out.println("sum: " + MCSEQ.generate5(array1, 3));

        System.out.println("最大连续子序列积：");
        System.out.println("multiply: " + MCSEQ.generate6(array1));
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

    //最小下降路径和测试
    public static void test_MinDescPathSum() {
	    int [][] matrix = {{5,2,9,6,4,5},
                           {7,8,4,3,6,7},
                           {4,2,5,6,4,3},
                           {8,6,4,5,7,6}};
	    int sum = MinDescPathSum.generate(matrix);
        System.out.println(sum);
    }

    //最少加油次数测试
    public static void test_MinRefuel() {
	    int [][] stations = {{5,10},{10,5},{20,20},{30,10},{50,30},{80,10}};
	    int count = MinRefuel.generate(20, 100, stations);
        System.out.println(count);
	    count = MinRefuel.generate(50, 120, stations);
        System.out.println(count);
    }

    //最大股票利润测试
    public static void test_StockProfit() {
        System.out.println("买卖一次");
	    int [] array1 = {7,3,4,9,5,1,8,2,6};
        System.out.println(StockProfit.generate1(array1));
        int [] array2 = {2,5,8,4,1,7,3,9,6};
        System.out.println(StockProfit.generate1(array2));
        int [] array3 = {8,9,6,3,5,7,1,2,4};
        System.out.println(StockProfit.generate1(array3));

        System.out.println("买卖多次");
        int [] array4 = {7,3,4,9,5,1,8,2,6};
        System.out.println(StockProfit.generate2(array4));
        int [] array5 = {2,5,8,4,1,7,3,9,6};
        System.out.println(StockProfit.generate2(array5));
        int [] array6 = {8,9,6,3,5,7,1,2,4};
        System.out.println(StockProfit.generate2(array6));

        System.out.println("最多买卖两次");
        int [] array7 = {7,3,4,9,5,1,8,2,6};
        System.out.println("profit: " + StockProfit.generate3(array7));
        int [] array8 = {2,5,8,4,1,7,3,9,6};
        System.out.println("profit: " + StockProfit.generate3(array8));
        int [] array9 = {8,9,6,3,5,7,1,2,4};
        System.out.println("profit: " + StockProfit.generate3(array9));
    }

    //摆动序列测试
    public static void test_SwingSequence() {
        System.out.println("最长连续摆动序列");
        int [] array1 = {1,2,3,3,2,2,3,2,3,2,3,4,3};
        System.out.println("length: " + SwingSequence.generate1(array1));

        System.out.println("最长摆动序列");
	    int [] array3 = {1,8,2,4,5,6,4,2,7,3};
        System.out.println(SwingSequence.generate2(array3));
        int [] array4 = {1,2,6,6,3,3,4,2,2,1,3,3,5};
        System.out.println(SwingSequence.generate2(array4));
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

	//最长回文子序列测试
    public static void test_LPSEQ() {
	    String string = "abacadbca";
	    int length = LPSEQ.generate(string.toCharArray());
        System.out.println(length);
    }

	//最长回文子串测试
	public static void test_LPSTR() {
		String string = "abaabcdcbab";
		int length = LPSTR.generate1(string.toCharArray());
		System.out.println(length);

		length = LPSTR.generate2(string.toCharArray());
        System.out.println(length);

        length = LPSTR.generate3(string.toCharArray());
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

    //回文分割测试
    public static void test_PalindromeSeparate() {
	    //最少切分数
	    String string = "abacbcadac";
	    int count = PalindromeSeparate.generate1(string.toCharArray());
	    System.out.println(count);
	    //方法数
    }

    //回文填充测试
    public static void test_PalindromeFill() {
	    //在任意位置添加字符
	    String string1 = "a1bab2";
	    String string2 = "aabab1";
        int count1 = PalindromeFill.generate(string1.toCharArray());
        System.out.println(count1);
        int count2 = PalindromeFill.generate(string2.toCharArray());
        System.out.println(count2);
    }

    //最短公共超序列测试
    public static void test_SCSEQ() {
        String string1 = "ab12c345d67";
        String string2 = "a1b23c4d56e";
        int length = SCSEQ.generate(string1.toCharArray(),string2.toCharArray());
        System.out.println("length: " + length);
    }

    //子序列个数测试
    public static void test_SequenceNumber() {
        String string1 = "rabbbit";
        String string2 = "rabbit";
        int count = SequenceNumber.generate1(string1.toCharArray(), string2.toCharArray());
        System.out.println(count);
    }

    //KMP测试
	public static void test_StringMatch() {
		String source = "abcdeabcabcdabcdeabcabc";
		String match = "abcdabcdeabc";
		int index = StringMatch.generate(source.toCharArray(), match.toCharArray());
		System.out.println("index: " + index);
	}

}