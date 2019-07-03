/**
 * @Author eunji
 */
package machine_test;

//数组相关
public class Array {

    //调整数组使奇数在前偶数在后
    public static void evenAndOdd1(int [] array) {
        if(array == null || array.length == 0)
            return;
        int left = 0, right = array.length - 1;
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

    //调整数组使奇数在奇数位置，偶数在偶数位置
    public static void evenAndOdd2(int [] array) {
        if (array == null || array.length == 0)
            return;
        int odd = 0, even = 1;//odd指向奇数位置，even指向偶数位置
        int length = array.length;
        int temp;
        while (true) {
            while (odd < length && array[odd] % 2 == 1)//找到剩余奇数位置上的第一个偶数或未找到
                odd += 2;
            while (even < length && array[even] % 2 == 0)//找到剩余偶数位置上的第一个奇数或未找到
                even += 2;
            if (odd >= length || even >= length)//未找到
                break;
            temp = array[odd];
            array[odd] = array[even];
            array[even] = temp;
        }
        return;
    }

    //数组长度为N，保存了所有0 - N-1的自然数，调整数组使其升序
    //从左到右遍历数组，若元素与位置相等，则向后移动
    //若元素与位置不相等，则交换元素值的对应位置的元素，重复上述过程
    public static void naturalNumberSort(int [] array) {
        if (array == null || array.length == 0)
            return;
        int temp;
        for (int i = 0; i < array.length; i++) {
            while (array[i] != i) {
                //temp应当保存array[array[i]]，而不是array[i]
                temp = array[array[i]];
                array[array[i]] = array[i];
                array[i] = temp;
            }
        }
        return;
    }

    //数组中出现次数大于N/K的元素
    //每次删除K个不同的数，直到剩下的数的种类少于K
    //如果某些数出现的次数大于N/K，则这些数最后一定会被剩余下来
    //第二次遍历数组，验证被选出来的所有候选有哪些出现次数大于N/K，筛选出符合条件的候选

    //数组中出现次数最多的元素（众位数）


    /* 数据流的中位数：使用两个大小之差不超过1的大根堆和小根堆
     * 大根堆保存当前子数组较小的一半，小根堆保存当前子数组较大的一半
     * 从数据流中依次取数据，交替放入大根堆和小根堆中，保证两个堆大小之差不超过1
     * 当放入大根堆中时，若元素不大于小根堆的堆顶元素，直接放入，否则将小根堆的堆顶元素放入大根堆中，元素放入小根堆中
     * 当放入小根堆中时，若元素不小于大根堆的堆顶元素，直接放入，否则将大根堆的堆顶元素放入小根堆中，元素放入大根堆中
     * 最后两个堆的堆顶元素的平均值或其中一个就是中位数
     */
    public static int median1(int [] array) {
        return 0;
    }

    //两个长度相等的有序数组的中位数
    //比较两个数组的中位数，若相等则返回，若不相等，则根据情况对两个数组进行二分，减小问题规模
    public static int median2(int [] one, int [] other) {
        if (one == null || one.length == 0 ||
            other == null || other.length == 0)
            return -1;
        int left1 = 0, right1 = one.length - 1;
        int left2 = 0, right2 = other.length - 1;
        int mid1 = 0, mid2 = 0;
        int offset = 0;
        while (left1 < right1) {
            mid1 = (left1 + right1) / 2;
            mid2 = (left2 + right2) / 2;
            offset = (right1 - left1 + 1) % 2 == 0 ? 1 : 0;
            if (one[mid1] > other[mid2]) {
                right1 = mid1;
                left2 = mid2 + offset;
            }else if (one[mid1] < other[mid2]) {
                left1 = mid1 + offset;
                right2 = mid2;
            }else {
                return one[mid1];
            }
        }
        return Math.min(one[left1], other[left2]);
    }


    //两个有序数组的中位数


    //两个有序数组的第K小的数


    //1-n整数中1出现的次数

    //数组元素能拼接出的最小值



    /* 数组中差值最大的两个数字，要求较小的数在前，较大的数在后（或者较大的数在前，较小的数在后）
     */
    //买卖股票

    /* 数组中的逆序对
       把数组分为左右两部分，先统计子数组内部的逆序对，再统计子数组之间的逆序对
       统计子数组之间的逆序对时，从后向前使用归并排序
       若左子数组当前最大元素小于或等于右子数组当前最大元素，则将右边元素归并
       否则左边最大元素与右边当前剩余元素都构成逆序对，统计个数，将左边元素归并
       子数组归并完后，当前数组有序
     */

    //需要排序的最短子数组
    //从后向前遍历，查找逆序对中最左边的第一个元素，作为需要排序子数组的起始元素
    //从前向后遍历，查找逆序对中最右边的第二个元素，作为需要排序子数组的终止元素
    //若数组本身有序，则不存在逆序对
    public static int minSortLength(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int length = array.length;
        //从后向前遍历
        int min = array[length-1];
        int left = -1;
        for (int i = length - 1; i >= 0; i--) {
            if (array[i] > min) {
                left = i;
            }else {
                min = array[i];
            }
        }
        if (left == -1)//有序数组
            return 0;
        //从前向后遍历
        int max = array[0];
        int right = -1;
        for (int i = 0; i < length; i++) {
            if (array[i] < max) {
                right = i;
            }else {
                max = array[i];
            }
        }
        //打印需要排序的最短子数组
        for (int i = left; i <= right; i++)
            System.out.print(array[i] + " ");
        return right - left + 1;
    }


    /* 窗口最大值

     */

    //有序数组中和为给定值的所有不重复的二元组和三元组
    //二元组
    //假设数组递增有序，使用两个变量left，right分别指向第一个和最后一个元素，left大于等于right时结束
    //若元素的和等于指定值，则打印，left向后移动，right向前移动，若小于指定值，则left向后移动，若大于指定值，则right向前移动
    public static void binaryTuple(int [] array, int sum) {
        if (array == null || array.length == 0)
            return;
        int left = 0, right = array.length - 1;
        while (left < right) {
            if (array[left] + array[right] > sum) {
                right--;
            }else if (array[left] + array[right] < sum) {
                left++;
            }else {
                if (left == 0 || array[left-1] != array[left])//不重复打印
                    System.out.print("(" + array[left] + " " + array[right] + ")");
                left++;
                right--;
            }
        }
        return;
    }

    //三元组
    //先确定第一个的元素，再在右边的子数组中查找其他两个元素
    public static void ternaryTuple(int [] array, int sum) {
        if (array == null || array.length == 0)
            return;
        int left = 0, middle, right;
        while (left < array.length - 2) {
            if (left == 0 || array[left-1] != array[left]) {//不重复打印
                middle = left + 1;
                right = array.length - 1;
                while (middle < right) {
                    if (array[middle] + array[right] > sum - array[left]) {
                        right--;
                    }else if (array[middle] + array[right] < sum - array[left]) {
                        middle++;
                    }else {
                        if (middle == left + 1 || array[middle-1] != array[middle])//不重复打印
                            System.out.print("(" + array[left] + " " + array[middle] + " " + array[right] + ")");
                        middle++;
                        right--;
                    }
                }
            }
            left++;
        }
        return;
    }

    //无序正数数组中和为给定值的最长子数组
    //left，right表示子数组两端元素位置，temp表示子数组的和
    //若和等于给定值时，更新length，left向后移动
    //若和大于给定值时，left向后移动
    //若和小于给定值时，right向后移动
    //left - right <= 1
    public static void maxLength(int [] array, int sum) {
        if (array == null || array.length == 0 || sum <= 0)
            return;
        int left = 0, right = 0, length = 0;
        int temp = array[0];
        int index1 = 0, index2 = 0;
        while (right < array.length) {
            if (temp == sum) {
                if (right - left + 1 > length) {
                    length = right - left + 1;
                    index1 = left;
                    index2 = right;
                }
                temp = temp - array[left];
                left++;
            }else if (temp < sum) {
                right++;
                if (right == array.length)//越界
                    break;
                temp = temp + array[right];
            }else {
                temp = temp - array[left];
                left++;
            }
        }
        System.out.println(length);
        for (int i = index1; i <= index2; i++)
            System.out.print(array[i] + " ");
        return;
    }

    //无序数组中和为给定值的最长子数组

    //无序数组中和小于或等于给定值的最长子数组

    //数组的小和
    //某元素左边所有小于或等于其的元素之和为小和，数组的小和等于所有元素的小和之和
    //使用归并排序，在对两个有序子数组进行归并时，left指向左边的当前元素，right指向右边的当前元素
    //若left <= right，则产生小和，和为left与右边剩余元素个数的乘积，left向后移动
    //若left > right，不产生小和，right向后移动
    //归并完后，两个子数组间的小和计算完毕，整个数组的小和也计算完毕

    //在有序旋转数组中查找最小的数或指定的数
}
