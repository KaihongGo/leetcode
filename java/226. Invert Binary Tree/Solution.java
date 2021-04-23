public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        int[] data = { 4, 2, 7, 1, 3, 6, 9 };
        root = TreeNode.levelOrderBuilder(data);
        Solution solution = new Solution();

        TreeNode.levelOrder(root);
        System.out.println();
        root = solution.invertTree(root);
        System.out.println();
        TreeNode.levelOrder(root);
    }
}
