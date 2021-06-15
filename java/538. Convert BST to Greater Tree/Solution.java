public class Solution {
    public TreeNode convertBST(TreeNode root) {
        return convertBST(root, 0);
    }

    public TreeNode convertBST(TreeNode root, int parentValue) {
        // 如果当前节点是左子树，传递上层数据
        if (root == null)
            return null;
        root.val = root.val + getTreeSumValue(root.right) + parentValue;
        convertBST(root.left, root.val);
        convertBST(root.right, parentValue);
        return root;
    }

    public int getTreeSumValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getTreeSumValue(root.left);
        int right = getTreeSumValue(root.right);
        return left + right + root.val;
    }
}