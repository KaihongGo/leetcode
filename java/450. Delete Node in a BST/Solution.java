public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {
            if (root.left == null && root.right == null)
                return null;
            else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.right != null && root.left == null) {
                return root.right;
            } else {
                TreeNode min = getMinNode(root.right);
                root.val = min.val;
                root.right = deleteNode(root.right, min.val);
            }
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    TreeNode getMinNode(TreeNode root) {
        TreeNode p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }
}
