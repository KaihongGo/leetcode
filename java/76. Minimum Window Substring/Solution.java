import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need, window;
        need = new HashMap<>();
        window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        for (char ch : t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (window.get(ch).equals(need.get(ch))) {
                    valid++;
                }
            }
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                ch = s.charAt(left);
                left++;
                if (need.containsKey(ch)) {
                    if (window.get(ch).equals(need.get(ch))) {
                        valid--;
                    }
                    window.put(ch, window.getOrDefault(ch, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, len + start);
    }

    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        String s = sin.nextLine();
        String t = sin.nextLine();
        String res = new Solution().minWindow(s, t);
        System.out.println(res);
        sin.close();
    }
}

// ADOBECODEBANC
// ABC