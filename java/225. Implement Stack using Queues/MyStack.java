import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> q;
    private int topElement;

    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
        topElement = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = q.size();
        while (size > 1) {
            if (size == 2) {
                topElement = q.peek();
            }
            q.offer(q.poll());
            size--;
        }
        return q.poll();
    }

    /** Get the top element. */
    public int top() {
        return topElement;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such: MyStack obj =
 * new MyStack(); obj.push(x); int param_2 = obj.pop(); int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */