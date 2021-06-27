import java.util.Arrays;

import java.util.*;

class Solution {
    public int splitArray(int[] nums, int m) {
        int lo = Arrays.stream(nums).max().getAsInt();
        int hi = Arrays.stream(nums).sum() + 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int n = split(nums, mid);
            if (n <= m) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * 若限制最大子数组和(<=)max，计算 nums 至少可以被分割成几个子数组
     * 
     * @param nums
     * @param max
     * @return
     */
    private int split(int[] nums, int max) {
        int count = 1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > max) {
                count++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return count;
    }
}