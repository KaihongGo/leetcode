import java.util.Arrays;

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    /**
     * 
     * @param nums  [start, end)
     * @param start
     * @param end
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int maxValueIndex = findMaxValueIndex(nums, start, end);
        TreeNode root = new TreeNode(nums[maxValueIndex]);
        root.left = constructMaximumBinaryTree(nums, start, maxValueIndex);
        root.right = constructMaximumBinaryTree(nums, maxValueIndex + 1, end);
        return root;
    }

    public int findMaxValueIndex(int[] nums, int start, int stop) {
        int maxValueIndex = start;
        int index = start;
        while (index < stop) {
            if (nums[maxValueIndex] < nums[index]) {
                maxValueIndex = index;
            }
            index++;
        }
        return maxValueIndex;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 6, 0, 5 };
        int maxNums = Arrays.stream(nums).max().getAsInt();
        System.out.println(maxNums);
    }
}