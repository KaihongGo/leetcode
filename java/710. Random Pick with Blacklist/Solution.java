import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

class Solution {
    private int wlen;
    private HashMap<Integer, Integer> map;

    public Solution(int n, int[] blacklist) {
        map = new HashMap<>();
        wlen = n - blacklist.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = wlen; i < n; i++) {
            set.add(i);
        }
        for (int e : blacklist) {
            set.remove(e);
        }
        Iterator<Integer> iterator = set.iterator();
        for (int x : blacklist) {
            if (x < wlen) {
                map.put(x, iterator.next());
            }
        }
    }

    public int pick() {
        Random rand = new Random();
        int k = rand.nextInt(wlen);
        return map.containsKey(k) ? map.get(k) : k;

    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(n, blacklist); int param_1 = obj.pick();
 */