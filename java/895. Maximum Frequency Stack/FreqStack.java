import java.util.HashMap;
import java.util.Stack;

class FreqStack {
    private int maxFreq;
    private HashMap<Integer, Integer> valToFreq;
    private HashMap<Integer, Stack<Integer>> freqToVals;

    public FreqStack() {
        maxFreq = 0;
        valToFreq = new HashMap<>();
        freqToVals = new HashMap<>();
    }

    public void push(int val) {
        int freq = valToFreq.getOrDefault(val, 0) + 1;
        valToFreq.put(val, freq);
        freqToVals.putIfAbsent(freq, new Stack<>());
        freqToVals.get(freq).push(val);
        maxFreq = Math.max(freq, maxFreq);
    }

    public int pop() {
        Stack<Integer> vals = freqToVals.get(maxFreq);
        int val = vals.pop();
        valToFreq.put(val, maxFreq - 1);
        if (vals.isEmpty()) {
            maxFreq--;
        }
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such: FreqStack obj
 * = new FreqStack(); obj.push(val); int param_2 = obj.pop();
 */