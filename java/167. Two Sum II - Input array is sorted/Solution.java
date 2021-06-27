class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int ans = numbers[left] + numbers[right];
            if (ans < target) {
                left++;
            } else if (ans > target) {
                right--;
            } else if (ans == target) {
                return new int[] { left + 1, right + 1 };
            }
        }
        return new int[] { -1, -1 };
    }
}