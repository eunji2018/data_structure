/**
 * @Author eunji
 */
package basic_algorithm.array;

//蓄水问题
//array中所有元素都为非负数
public class WaterStorage {

    /* array[i]表示对应位置上宽为1的矩形高度，求解所有矩形的上方能够蓄水的最大值
     */
    //比较当前位置的高度，左右两侧最大高度的较小值
    //若当前位置的高度小于两侧最大高度的较小值，则当前位置蓄水的最大高度为两者之差
    //否则，当前位置蓄水的最大高度为0
    public static int generate1(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int length = array.length;
        int [] right = new int[length];
        int max = Integer.MIN_VALUE;
        int result = 0;
        //从右到左遍历
        for (int i = length - 1; i >= 0; i--) {
            max = Math.max(max, array[i]);
            right[i] = max;
        }
        max = array[0];
        int min;
        int [] temp = new int[length];
        //从左到右遍历
        for (int i = 1; i < length - 1; i++) {
            min = Math.min(max, right[i+1]);
            temp[i] = Math.max(min-array[i], 0);
            result += temp[i];
            max = Math.max(max, array[i]);
        }
        //打印数组
        for (int i = 0; i < length; i++)
            System.out.print(right[i] + " ");
        System.out.println();
        for (int i = 0; i < length; i++)
            System.out.print(temp[i] + " ");
        System.out.println();
        return result;
    }

    //从两端向中间遍历，左右当前位置分别记为left，right
    //leftMax表示array[0 - left-1]中的最大高度，rightMax表示array[right+1 - length-1]中的最大高度
    //若leftMax <= rightMax，则leftMax即为left位置两侧最大高度的较小值
    //若leftMax > rightMax，则rightMax即为right位置两侧最大高度的较小值
    //每次循环可以确定一个位置的最大蓄水高度
    public static int generate2(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int length = array.length;
        int leftMax = array[0], rightMax = array[length-1];
        int left = 1, right = length - 2;
        int result = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                result += Math.max(leftMax-array[left], 0);
                leftMax = Math.max(leftMax, array[left]);
                left++;
            }else {
                result += Math.max(rightMax-array[right], 0);
                rightMax = Math.max(rightMax, array[right]);
                right--;
            }
        }
        return result;
    }

    /* array[i]表示对应位置上宽度不计的挡板高度，求解所有挡板的中间能够蓄水的最大值
     */
    //先从左向右遍历，index表示向右遍历到的位置，current表示当前位置
    //若array[index] >= array[current]，则当前位置的挡板刚好可以蓄满水
    //再从右向左遍历，index表示向左遍历到的位置，current表示当前位置
    //若array[index] >= array[current]，则当前位置的挡板刚好可以蓄满水
    public static int generate3(int [] array) {
    	if (array == null || array.length == 0)
    		return -1;
    	int length = array.length;
    	int result = 0;
    	int current = 0, index = 1;
        //从左向右
        while (index < length) {
        	//当前位置的挡板刚好可以蓄满水
			if (array[index] >= array[current]) {
				result += array[current] * (index - current);
				current = index;
			}
			index++;
		}
        int position = current;//最高挡板的位置
        //从右向左
        if (position < length - 1) {
        	current = length - 1;
        	index = current - 1;
			while (index >= position) {
				//当前位置的挡板刚好可以蓄满水
				if (array[index] >= array[current]) {
					result += array[current] * (current - index);
					current = index;
				}
				index--;
			}
		}
        return result;
    }

}
