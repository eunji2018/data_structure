/**
 * @Author eunji
 */
package basic_algorithm.array;

//股票利润问题
//数组中的元素代表对应时间的股票价格，求解获得的最大利润
public class StockProfit {

    //股票只能买卖一次
    //正向求解：从前向后遍历数组，若元素小于等于当前最低价格，则更新最低价格
    //若元素大于当前最低价格，则比较两者之差与当前最大利润
    //反向求解：从后向前遍历数组，若元素大于等于当前最高价格，则更新最高价格
    //若元素小于当前最高价格，则比较两者之差与当前最大利润
    public static int generate1(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int profit = 0;
        int start = 0;
        int end = 0;
        int index = 0;
        int temp;
        for (int i = 0; i < array.length; i++) {
            temp = array[i] - array[index];
            if (temp <= 0) {
                index = i;
                continue;
            }
            if (temp > profit) {
                profit = temp;
                start = index;
                end = i;
            }
        }
        System.out.println("start: " + start + " " + array[start] + " end: " + end + " " + array[end]);
        return profit;
    }

    //股票可以买卖多次，但是不能同时持有多张股票
    //从前向后遍历，每个升序子数组可以作为一次买卖交易，和为最大利润
    public static int generate2(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int profit = 0;
        int temp;
        for (int i = 1; i < array.length; i++) {
            temp = array[i] - array[i-1];
            if (temp > 0)
                profit += temp;
        }
        return profit;
    }

    //股票最多可以买卖两次，但是不能同时持有两张股票
    //从前向后遍历数组，以当前元素为临界点划分出左右子数组，两个子数组分别进行一次最大利润的买卖交易
    //所有元素的情形中，两次交易的总和的最大值即为最终结果
    public static int generate3(int [] array) {
        if (array == null || array.length == 0)
            return -1;
        int length = array.length;
        int [] right = new int[length];
        int profit = 0;
        int max = 0;
        int min_index = 0, max_index = length - 1;
        int temp;
        //从后向前
        for (int i = length - 1; i >= 0; i--) {
            temp = array[max_index] - array[i];
            if (temp <= 0)
                max_index = i;
            if (temp > profit)
                profit = temp;
            right[i] = profit;
        }
        profit = 0;
        //从前向后
        for (int i = 0; i < length; i++) {
            temp = array[i] - array[min_index];
            if (temp <= 0)
                min_index = i;
            if (temp > profit)
                profit = temp;
            System.out.print("(" + profit + " " + right[i] + ") ");
            if (profit + right[i] > max)
                max = profit + right[i];
        }
        return max;
    }

    //股票最多可以买卖k次，但是不能同时持有多张股票

    //股票可以买卖多次，但是不能同时持有多张股票，并且包含一个时间单位的冷冻期（上一次的卖出与下一次的买入至少相隔两个时间单位）

    //股票可以买卖多次，但是不能同时持有多张股票，并且每次买卖需要支付给定的手续费

}
