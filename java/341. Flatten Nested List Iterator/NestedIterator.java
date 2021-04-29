import java.util.*;

public class NestedIterator implements Iterator<Integer> {

    private LinkedList<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new LinkedList<>(nestedList);
    }

    @Override
    public Integer next() {
        return list.removeFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!list.isEmpty() && !list.getFirst().isInteger()) {
            List<NestedInteger> front = list.removeFirst().getList();
            for (int i = front.size() - 1; i >= 0; i--) {
                list.addFirst(front.get(i));
            }
        }
        return !list.isEmpty();
    }

}