import java.util.HashMap;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nums = nextGreaterElement(nums2);
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nums.get(nums1[i]);
        }
        return result;
    }

    public HashMap<Integer, Integer> nextGreaterElement(int[] nums) {
        HashMap<Integer, Integer> result = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            result.put(nums[i], stack.empty() ? -1 : stack.peek());
            stack.push(nums[i]);
        }
        return result;
    }

}