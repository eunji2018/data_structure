package demo;

import java.util.Arrays;

//力扣笔试专用类
class Solution {

    public static void main(String[] args) {
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i += 2)
            result += nums[i];

        return result;
    }

}

