public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 
     * max.val > root.val > min.val
     * 
     * @param root
     * @param min
     * @param max
     * @return
     */
    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val)
            return false;
        if (max != null && max.val <= root.val)
            return false;
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
}
