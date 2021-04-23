public class Solution {
    public Node connect(Node root) {
        if (root == null)
            return null;
        connectTwoNodes(root.left, root.right);
        return root;
    }

    public void connectTwoNodes(Node left, Node right) {
        if (left == null || right == null)
            return;
        left.next = right;
        connectTwoNodes(left.left, left.right);
        connectTwoNodes(left.right, right.left);
        connectTwoNodes(right.left, right.right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] data = { 1, 2, 3, 4, 5, 6, 7 };
        Node root = Node.levelOrderBuilder(data);
        Node.levelOrder(root);
        System.out.println();
        root = solution.connect(root);
        Node.levelOrderStar(root);
    }
}
