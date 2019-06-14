/*
    @Author：eunji
 */
package machine_test;

public class MachineTest {

	/* 矩阵相关
	 */
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
	
	
	
	/* 随机数相关，由等可能或不等可能的一个随机数表示出另一个等可能或不等可能的随机数
	 */
	
	/* 位运算相关
	 */
	//二进制数字中1的个数
	//使用2^n和二进制数字从低位到高位做与运算，记录结果为1的个数
	public static int numberOfOne1(int n) {
		int count = 0;
		int temp = 1;
		while(temp != 0) {
			if((temp & n) != 0)//按位与，结果为1表示当前位为1
				count++;
			temp = temp << 1;//左移一位，从低位到高位
		}
		return count;
	}
	
	//优化：表达式(n-1) & n的结果等于将n最低位的1变为0
	public static int numberOfOne2(int n) {
		int count = 0;
		while(n != 0) {
			count++;
			n = (n - 1) & n;
		}
		return count;
	}

	//不使用额外变量交换两个整数

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
	
	/* 数组相关
	 */
	//调整数组使奇数在前偶数在后
	public static void evenAndOdd(int [] array) {
		if(array == null || array.length == 0)
			return;
		int left = 0;
		int right = array.length - 1;
		int temp;
		while(left < right) {
			while(left < right && (array[left] % 2) == 1)//从左开始找到偶数或者未找到
				left++;
			while(left < right && (array[right] % 2) == 0)//从右开始找到奇数或者未找到
				right--;
			if(left < right) {
				temp = array[left];
				array[left] = array[right];
				array[right] = temp;
			}
		}
		return;
	}
	
	//数组中可能存在的出现次数超过一半的元素

    //数组中出现次数最多的元素
	
	
	/* 数据流的中位数：使用两个大小之差不超过1的大根堆和小根堆
	 * 大根堆表示当前子数组较小的一半，小根堆表示当前子数组较大的一半
	 * 从数据流中依次取数据，交替放入大根堆和小根堆中，保证两个堆大小之差不超过1
	 * 当放入大根堆中时，若元素不大于小根堆的堆顶元素，直接放入，否则将小根堆的堆顶元素放入大根堆中，元素放入小根堆中
	 * 当放入小根堆中时，若元素不小于大根堆的堆顶元素，直接放入，否则将大根堆的堆顶元素放入小根堆中，元素放入大根堆中
	 * 最后两个堆的堆顶元素的平均值或其中一个就是中位数 
	 */
	public static int median(int [] array) {
		return 0;
	}
	
	//1-n整数中1出现的次数
	
	//数组元素能拼接出的最小值

    //数组中只出现一次的一个数字，其他数字都出现两次

    //数组中只出现一次的两个数字，其他数字都出现两次

    /* 有序数组中和为指定值的两个数字
       假设数组递增有序，使用两个变量left，right分别指向第一个和最后一个元素，left大于等于right时表示不存在
       若元素的和等于指定值，则返回，若小于指定值，则left向后移动，若大于指定值，则right向前移动
     */

    /* 数组中差值最大的两个数字，要求较小的数在前，较大的数在后（或者较大的数在前，较小的数在后）

     */

    /* 窗口最大值

     */



	/* 单链表相关
	 */
	//单链表中可能存在环：找出是否存在环，若存在，求出环的长度以及入口节点
	 
	
	/* 字符串相关
	 */
	//第一个（最后一个）只出现一次的字符

	//子串替换

    //判断两个字符串是否互为变位词
    //遍历第一个字符串，统计每个字符出现的次数，再遍历第二个字符串，递减每个字符出现的次数
    //若最终某个字符出现的次数不为0（大于0，表示第二个字符串含有较少的字符，小于0，表示含有较多的字符），则返回false

    //判断两个字符串是否互为旋转词
    //将第一个字符串与自身拼接成一个新字符串，判断第二个字符串在新字符串中出现的位置（字符串匹配）
    //若存在，则返回true

	//字符的所有排列
	
	
	//字符的所有组合

    //最长不含重复字符子串
    //维护一个哈希表，保存所有字符最近一次出现的位置，从左到右遍历字符串
    //维护一个变量，保存以当前字符的前一个字符结尾的最长不含重复字符子串的开始位置的前一个位置
    //比较上面的前一个位置与当前字符最近一次出现的位置，得到以当前字符结尾的最长不含重复字符子串的长度
    //更新上面的前一个位置，当前字符最近一次出现的位置，最长不含重复字符子串的长度以及结尾字符的位置
	
	
}
