public class Solution1 {
    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    /**
     * 
     * @param root
     * @return [isBST, minValue, maxValue, rootSum]
     */
    public int[] traverse(TreeNode root) {
        if (root == null) {
            return new int[] { 1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        }
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        int[] res = new int[4];
        if (left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]) {
            res[0] = 1;
            res[1] = Math.min(root.val, left[1]);
            res[2] = Math.max(root.val, right[2]);
            res[3] = left[3] + right[3] + root.val;
            maxSum = Math.max(res[3], maxSum);
        } else {
            res[0] = 0;
        }
        return res;
    }
}
