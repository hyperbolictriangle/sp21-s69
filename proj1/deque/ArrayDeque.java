package deque;

import java.util.Iterator;
import java.util.Objects;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] deque;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    private static final int RESIZE_FACTOR = 2;

    public ArrayDeque() {
        deque = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        capacity = 8;
    }

    @Override
    public int size() {
        return size;
    }

//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }

    @Override
    public void addLast(T i) {
        if (size == capacity) {
            resize();
        }
        deque[nextLast] = i;
        nextLast = (nextLast + 1) % capacity;
        size += 1;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = ((nextLast - 1) % capacity + capacity) % capacity;
        T v = deque[nextLast];
        deque[nextLast] = null;
        size -= 1;
        resize();
        return v;
    }

    @Override
    public T get(int ind) {
        if (ind < 0 || ind >= size) {
            return null;
        }
        return deque[translateIndex(ind)];
    }

    private int translateIndex(int ind) {
        int first = (nextFirst + 1) % capacity;
        return (first + ind) % capacity;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(deque[translateIndex(i)] + " ");
        }
        System.out.println();
    }

    @Override
    public void addFirst(T i) {
        if (size == capacity) {
            resize();
        }
        deque[nextFirst] = i;
        nextFirst = ((nextFirst - 1) % capacity + capacity) % capacity;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % capacity;
        T v = deque[nextFirst];
        deque[nextFirst] = null;
        size -= 1;
        resize();
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque<T> unknownDeque = (Deque<T>) o;
            int sizeAd = unknownDeque.size();
            if (this.size == sizeAd) {
                for (int i = 0; i < sizeAd; i++) {
                    if (!Objects.equals(unknownDeque.get(i), this.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int itr;

        ArrayDequeIterator() {
            itr = 0;
        }

        @Override
        public boolean hasNext() {
            return itr < size();
        }

        @Override
        public T next() {
            T ret = get(itr);
            itr += 1;
            return ret;
        }
    }

    private boolean sequentialEqualsSameSize(ArrayDeque<T> ad) {
        for (int i = 0; i < size; i++) {
            if (!(ad.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }

    private void resize() {
        int newCapacity;
        if (size == capacity) {
            newCapacity = capacity * RESIZE_FACTOR;
        } else if (size <= capacity / (RESIZE_FACTOR * RESIZE_FACTOR) && size > 8) {
            newCapacity = capacity / RESIZE_FACTOR;
        } else {
            return;
        }
        T[] temp = (T[]) new Object[newCapacity];
        arrayCopy(temp);
        deque = temp;
        capacity = newCapacity;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private void arrayCopy(T[] temp) {
        for (int i = 0; i < size; i++) {
            temp[i] = get(i);
        }
    }
}
