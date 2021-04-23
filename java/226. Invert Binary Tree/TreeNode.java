import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    static void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 
     * Build a binary tree by level order traversal sequence
     * 
     * @param data an array obtained by level order traversal of binary tree
     * @return TreeNode: root node of the tree
     */
    static TreeNode levelOrderBuilder(int data[]) {
        int index = 0;
        TreeNode root = new TreeNode(data[index++]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode front = queue.poll();
            if (index < data.length && front.left == null) {
                front.left = new TreeNode(data[index++]);
                queue.add(front.left);
            }
            if (index < data.length && front.right == null) {
                front.right = new TreeNode(data[index++]);
                queue.add(front.right);
            }
        }
        return root;
    }

    static void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode front = queue.poll();
            System.out.print(front.val + " ");
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }
    }
}