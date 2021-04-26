import java.util.ArrayList;
import java.util.LinkedList;

public class Codec {

    String SEP = ",";
    String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    public TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty())
            return null;
        String front = nodes.pollFirst();
        if (front.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(front));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}