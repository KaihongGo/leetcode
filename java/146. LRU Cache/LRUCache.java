import java.util.LinkedHashMap;

class LRUCache {
    private LinkedHashMap<Integer, Integer> cache;
    private int capacity;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        int value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key, value);
            return;
        }
        if (cache.size() >= capacity) {
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */