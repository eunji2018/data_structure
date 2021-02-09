package demo;


//力扣笔试专用类
class Solution {

    public static void main(String[] args) {
    	Solution solution = new Solution();
    	int[] nums = {10,9,2,5,3,7,101,18};
		System.out.println(solution.lengthOfLIS(nums));
    }

	public int lengthOfLIS(int[] nums) {
    	if (nums == null || nums.length == 0)
    		return 0;
		int length = nums.length;
		int result = 1;
		int max = 0;
		int[] temp = new int[length];
		temp[0] = 1;
		for (int i = 1; i < length; i++) {
			max = 0;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i])
					max = Math.max(max, temp[j]);
			}
			temp[i] = max + 1;
			result = Math.max(result, temp[i]);
		}

    	return result;
	}
}

