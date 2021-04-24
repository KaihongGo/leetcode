public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR) {
        if (inL > inR)
            return null;
        TreeNode root = new TreeNode(postorder[postR]);
        int k = inL;
        while (inorder[k] != root.val) {
            k++;
        }
        int leftSize = k - inL;
        root.left = buildTree(inorder, inL, k - 1, postorder, postL, postL + leftSize - 1);
        root.right = buildTree(inorder, k + 1, inR, postorder, postL + leftSize, postR - 1);
        return root;
    }
}