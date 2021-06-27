import java.lang.reflect.Array;
import java.util.*;

class Solution {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[256];
        Arrays.fill(inStack, false);
        int[] count = new int[256];
        Arrays.fill(count, 0);
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        for (char ch : s.toCharArray()) {
            count[ch]--;
            if (inStack[ch] == false) {
                while (!stack.isEmpty() && stack.peek() > ch) {
                    if (count[stack.peek()] == 0)
                        break;
                    inStack[stack.pop()] = false;
                }
                stack.push(ch);
                inStack[ch] = true;
            }
        }
        StringBuilder sBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            sBuilder.append(stack.pop());
        }
        return sBuilder.reverse().toString();
    }
}