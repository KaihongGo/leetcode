import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> s = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!s.empty() && temperatures[i] >= temperatures[s.peek()]) {
                s.pop();
            }
            result[i] = s.empty() ? 0 : s.peek() - i;
            s.push(i);
        }
        return result;
    }
}