import java.util.Stack;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        int n = nums.length;
        Stack<Integer> s = new Stack<>();
        for (int i = 2 * (n - 1); i >= 0; i--) {
            while (!s.empty() && nums[i % n] >= s.peek()) {
                s.pop();
            }
            result[i % n] = s.empty() ? -1 : s.peek();
            s.push(nums[i % n]);
        }
        return result;
    }
}