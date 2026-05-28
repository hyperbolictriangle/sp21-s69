package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /** Returns maximum value in Deque based on comparator passed
     * during MaxArrayDeque Initialization. */
    public T max() {
        if (size() == 0) {
            return null;
        }
        T max = get(0);
        for (T item: this) {
            if (comparator.compare(max, item) < 0) {
                max = item;
            }
        }
        return max;
    }

    /** Returns maximum value in Deque based on custom comparator c
     * passed as argument.*/
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T max = get(0);
        for (T item: this) {
            if (c.compare(max, item) < 0) {
                max = item;
            }
        }
        return max;
    }
}
