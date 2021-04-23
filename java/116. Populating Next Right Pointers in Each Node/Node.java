import java.util.*;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    static Node levelOrderBuilder(int data[]) {
        int index = 0;
        Node root = new Node(data[index++]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            if (index < data.length && front.left == null) {
                front.left = new Node(data[index++]);
                queue.add(front.left);
            }
            if (index < data.length && front.right == null) {
                front.right = new Node(data[index++]);
                queue.add(front.right);
            }
        }
        return root;
    }

    static void levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            System.out.print(front.val + " ");
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }
    }

    static void levelOrderStar(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            System.out.print(front.val + " ");
            if (front.next == null)
                System.out.print("# ");
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }
    }
};