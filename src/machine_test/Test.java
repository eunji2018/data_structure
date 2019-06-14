/*
    @Author：eunji
 */
package machine_test;

public class Test {

	public static void main(String[] args) {
//		test_TwoDimensionSearch();
        test_MaxCommonDivisor();

//		test_EvenAndOdd();
	}
	
	//二维数组查找测试
	public static void test_TwoDimensionSearch() {
		int [][] array = {{1,2,8,9},
						  {2,4,9,12},
						  {4,7,10,13},
						  {6,8,11,15}};
		boolean flag1 = MachineTest.twoDimensionSearch(array, 7);
		System.out.println(flag1);
		boolean flag2 = MachineTest.twoDimensionSearch(array, 5);
		System.out.println(flag2);
	}

	//最大公约数测试
    public static void test_MaxCommonDivisor() {
	    System.out.println(MachineTest.maxCommonDivisor(42, 30));
	    System.out.println(MachineTest.maxCommonDivisor(42, 21));
	    System.out.println(MachineTest.maxCommonDivisor(42, 14));
	    System.out.println(MachineTest.maxCommonDivisor(42, 3));
    }
	
	//调整数组奇数在前偶数在后测试
	public static void test_EvenAndOdd() {
		int [] array1 = {0,1,2,3,4,5,6,7,8,9};
		int [] array2 = {9,8,7,6,5,4,3,2,1,0};
		MachineTest.evenAndOdd(array1);
		for(int i = 0; i < array1.length; i++)
			System.out.print(array1[i] + " ");
		System.out.println();
		MachineTest.evenAndOdd(array2);
		for(int i = 0; i < array2.length; i++)
			System.out.print(array2[i] + " ");
	}
}
