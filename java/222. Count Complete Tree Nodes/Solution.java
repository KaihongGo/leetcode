public class Solution {
    public int countNodes(TreeNode root) {
        TreeNode left = root, right = root;
        int hl = 0, hr = 0;
        while (left != null) {
            left = left.left;
            hl++;
        }
        while (right != null) {
            right = right.right;
            hr++;
        }
        if (hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
