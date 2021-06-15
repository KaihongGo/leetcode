import java.util.*;

public class Solution {
    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return result;
    }

    void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        path.add(s);

        if (s == graph.length - 1) {
            result.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }

        for (int v : graph[s]) {
            traverse(graph, v, path);
        }

        path.removeLast();
    }
}
