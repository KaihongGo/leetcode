import java.util.HashMap;
import java.util.LinkedHashSet;

class LFUCache {
    private HashMap<Integer, Integer> keyToVal;
    private HashMap<Integer, Integer> keyToFreq;
    private HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    private int minFreq;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (this.capacity <= 0)
            return;

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            increaseFreq(key);
            return;
        }
        if (keyToVal.size() >= this.capacity) {
            removeMinFreqKey();
        }
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        this.minFreq = 1;
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> list = freqToKeys.get(this.minFreq);
        int removedKey = list.iterator().next();
        list.remove(removedKey);
        if (list.isEmpty()) {
            freqToKeys.remove(this.minFreq);
        }

        keyToFreq.remove(removedKey);
        keyToVal.remove(removedKey);
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);

        freqToKeys.get(freq).remove(key);
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such: LFUCache obj =
 * new LFUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */