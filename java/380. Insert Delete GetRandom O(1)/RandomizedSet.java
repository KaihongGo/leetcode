import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

class RandomizedSet {
    private ArrayList<Integer> nums;
    private HashMap<Integer, Integer> valToIndex;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<>();
        valToIndex = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        if (valToIndex.containsKey(val))
            return false;
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (!valToIndex.containsKey(val))
            return false;
        int idx = valToIndex.get(val);
        int lastElement = nums.get(nums.size() - 1);
        valToIndex.put(lastElement, idx);
        valToIndex.remove(val);
        nums.set(idx, lastElement);
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet(); boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val); int param_3 = obj.getRandom();
 */