import java.util.*;

/**
 * 超出时间限制
 */
public class Solution {
    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    void traverse(TreeNode root) {
        if (root == null)
            return;
        if (!isBST(root.left) || !isBST(root.right)) {
            traverse(root.left);
            traverse(root.right);
            return;
        }
        TreeNode leftMax = getMaxNode(root.left);
        TreeNode rightMin = getMinNode(root.right);
        if ((leftMax != null && root.val <= leftMax.val) || (rightMin != null && root.val >= rightMin.val)) {
            traverse(root.left);
            traverse(root.right);
            return;
        }
        int rootSum = getSum(root.left) + getSum(root.right) + root.val;
        this.maxSum = Math.max(rootSum, maxSum);
        traverse(root.left);
        traverse(root.right);
    }

    boolean isBST(TreeNode root) {
        return isBST(root, null, null);
    }

    boolean isBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return true;
        if (min != null && root.val <= min.val)
            return false;
        if (max != null && root.val >= max.val)
            return false;
        return isBST(root.left, min, root) && isBST(root.right, root, max);
    }

    TreeNode getMinNode(TreeNode root) {
        TreeNode p = root;
        while (p != null && p.left != null) {
            p = p.left;
        }
        return p;
    }

    TreeNode getMaxNode(TreeNode root) {
        TreeNode p = root;
        while (p != null && p.right != null) {
            p = p.right;
        }
        return p;
    }

    int getSum(TreeNode root) {
        if (root == null)
            return 0;
        return getSum(root.left) + getSum(root.right) + root.val;
    }
}
