public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int preL, int preR, int inL, int inR) {
        if (preL > preR)
            return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int k = inL;
        while (inorder[k] != root.val) {
            k++;
        }
        int leftNodeNum = k - inL;
        root.left = buildTree(preorder, inorder, preL + 1, preL + leftNodeNum, inL, k - 1);
        root.right = buildTree(preorder, inorder, preL + leftNodeNum + 1, preR, k + 1, inR);
        return root;
    }
}