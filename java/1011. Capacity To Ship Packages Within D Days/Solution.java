import java.util.*;

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canFinish(int[] weight, int days, int capacity) {
        int day = 0;
        int i = 0;
        while (i < weight.length) {
            int cap = capacity;
            while (i < weight.length && weight[i] <= cap) {
                cap -= weight[i];
                i++;
            }
            day++;
        }
        return day <= days;
    }
}