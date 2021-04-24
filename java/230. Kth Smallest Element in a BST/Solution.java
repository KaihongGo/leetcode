public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return res;
    }

    int res = 0;
    int count = 0;

    void inOrder(TreeNode root, int k) {
        if (root == null)
            return;
        inOrder(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return;
        }
        inOrder(root.right, k);
    }
}