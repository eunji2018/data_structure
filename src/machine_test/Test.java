/**
 * @Author eunji
 */
package machine_test;

public class Test {

	public static void main(String[] args) {
//		test_EvenAndOdd1();
//		test_EvenAndOdd2();
//		test_MinSortLength();
//		test_Tuple();
//		test_MaxLength();
//		test_NaturalNumberSort();
//		test_Median2();
//		test_MaxCommonDivisor();
//		test_OddTimes();
//		test_TwoDimensionSearch();

//		test_NRandom();
//		test_SquareRandom();
//		test_NMRandom();
//		test_01Random1();
//		test_01Random2();



	}

/**********数组相关**********/

	//调整数组奇数在前偶数在后测试
	public static void test_EvenAndOdd1() {
		int [] array1 = {0,1,2,3,4,5,6,7,8,9};
		int [] array2 = {9,8,7,6,5,4,3,2,1,0};
		Array.evenAndOdd1(array1);
		for(int i = 0; i < array1.length; i++)
			System.out.print(array1[i] + " ");
		System.out.println();
		Array.evenAndOdd1(array2);
		for(int i = 0; i < array2.length; i++)
			System.out.print(array2[i] + " ");
	}

	//调整数组奇数在奇数位置，偶数在偶数位置
	public static void test_EvenAndOdd2() {
		int [] array1 = {0,1,2,3,4,5,6,7,8,9};
		int [] array2 = {0,1,2,3,4,5,6,7,8};
		Array.evenAndOdd2(array1);
		for (int i = 0; i < array1.length; i++)
			System.out.print(array1[i] + " ");
		System.out.println();
		Array.evenAndOdd2(array2);
		for (int i = 0; i < array2.length; i++)
			System.out.print(array2[i] + " ");
	}

	//需要排序的最短子数组测试
	public static void test_MinSortLength() {
		int [] array = {0,1,2,5,6,3,4,7,8,9};
		int length = Array.minSortLength(array);
		System.out.println();
		System.out.println(length);
	}

	//和为给定值的二元组，三元组测试
	public static void test_Tuple() {
		int [] array1 = {1,2,2,3,3,4,5,6,6,7,8,8,9};
		Array.binaryTuple(array1,10);
		System.out.println();
		int [] array2 = {1,1,2,2,3,3,4,4,5,6,7,8,8};
		Array.ternaryTuple(array2,13);
	}

	//和为给定值的最长子数组测试
	public static void test_MaxLength() {
		int [] array = {1,2,2,3,1,2,1,2,2,1,3,1,2};
		Array.maxLength(array,8);
		System.out.println();
		Array.maxLength(array,10);
	}

	//自然数排序测试
	public static void test_NaturalNumberSort() {
		int [] array = {2,9,1,5,3,6,4,0,7,8};
		Array.naturalNumberSort(array);
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	//长度相等的有序数组的中位数测试
	public static void test_Median2() {
		int [] one = {0,2,4,6,8};
		int [] other = {1,3,5,7,9};
		int median = Array.median2(one,other);
		System.out.println(median);
	}



/**********位运算相关**********/

	//最大公约数测试
	public static void test_MaxCommonDivisor() {
		System.out.println(BitOperation.maxCommonDivisor(42, 30));
		System.out.println(BitOperation.maxCommonDivisor(42, 21));
		System.out.println(BitOperation.maxCommonDivisor(42, 14));
		System.out.println(BitOperation.maxCommonDivisor(42, 3));
	}

	//数字出现奇数次测试
	public static void test_OddTimes() {
		int [] array1 = {1,2,2,3,3,4,4};
		BitOperation.oneOdd(array1);
		int [] array2 = {1,1,2,2,3,3,4};
		BitOperation.oneOdd(array2);
		int [] array3 = {1,2,2,3,3,4,5,5};
		BitOperation.twoOdd(array3);
		int [] array4 = {1,1,2,3,3,4,4,5};
		BitOperation.twoOdd(array4);
	}








/**********矩阵相关**********/

	//二维数组查找测试
	public static void test_TwoDimensionSearch() {
		int [][] array = {{1,2,8,9},
						  {2,4,9,12},
						  {4,7,10,13},
						  {6,8,11,15}};
		boolean flag1 = Matrix.twoDimensionSearch(array, 7);
		System.out.println(flag1);
		boolean flag2 = Matrix.twoDimensionSearch(array, 5);
		System.out.println(flag2);
	}


/**********随机数相关**********/

	//等可能N随机数测试
	public static void test_NRandom() {
		int seed = 5;
		int [] array = new int[seed];
		int result = 0;
		for (int i = 0; i < 100000; i++) {
			result = RandomNumber.generate1(seed);
			array[result]++;
		}
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	//平方随机数测试
	public static void test_SquareRandom() {
		int seed = 5;
		int [] array = new int[seed*seed];
		int result = 0;
		for (int i = 0; i < 100000; i++) {
			result = RandomNumber.generate2(seed);
			array[result]++;
		}
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	//N-M随机数测试
	public static void test_NMRandom() {
		int from = 5;
		int to = 10;
		int result = 0;
		int [] array = new int[to];
		for (int i = 0; i < 100000; i++) {
			result = RandomNumber.generate3(from,to);
			array[result]++;
		}
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
		to = 20;
		array = new int[to];
		for (int i = 0; i < 100000; i++) {
			result = RandomNumber.generate3(from,to);
			array[result]++;
		}
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	//不等可能0-1随机数测试
	public static void test_01Random1() {
		float p = 0.8f;
		int result = 0;
		int [] array = new int[2];
		for (int i = 0; i < 100000; i++) {
			result = RandomNumber.generate6(p);
			array[result]++;
		}
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	//等可能0-1随机数测试
	public static void test_01Random2() {
		float p = 0.7f;
		int result = 0;
		int [] array = new int[2];
		for (int i = 0; i < 100000; i++) {
			result = RandomNumber.generate7(p);
			array[result]++;
		}
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

}
