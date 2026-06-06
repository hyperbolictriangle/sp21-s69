package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /** LinkedListDeque - sentinal node makes it easy to deal
     *  with keeping track of start and end of list. */
    private Node sentinal;
    private int size;

    public LinkedListDeque() {
        sentinal = new Node(null, null, null);
        sentinal.next = sentinal.prev = sentinal;
        size = 0;
    }

    /** Returns size of Deque */
    @Override
    public int size() {
        return size;
    }

//    /** Returns true if Deque is empty */
//    @Override
//    public boolean isEmpty(){
//        return size==0;
//    }

    /** Adds item of type T to start of Deque, does not return */
    @Override
    public void addFirst(T item) {
        sentinal.next = new Node(item, sentinal, sentinal.next);
        sentinal.next.next.prev = sentinal.next;
        size += 1;
    }

    /** Removes and returns the item at the front of the deque.
     * Returns `null` if no such item exists */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T value = sentinal.next.item;
        sentinal.next = sentinal.next.next;
        sentinal.next.prev = sentinal;
        size -= 1;
        return value;
    }

    /** Adds item of type T to end of Deque, does not return */
    @Override
    public void addLast(T item) {
        sentinal.prev = new Node(item, sentinal.prev, sentinal);
        sentinal.prev.prev.next = sentinal.prev;
        size += 1;
    }

    /** Removes and returns the item at the end of the deque.
     * Returns `null` if no such item exists */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T value = sentinal.prev.item;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.next = sentinal;
        size -= 1;
        return value;
    }

    /** Prints elements of Deque separated by spaces, prints end line character at end. */
    @Override
    public void printDeque() {
        Node itr = sentinal.next;
        while (itr != sentinal) {
            System.out.print(itr.item + " ");
            itr = itr.next;
        }
        System.out.println();
    }

    /** Returns item located at given index in Deque, returns `null` if index out of bounds.
     * @param index: int
     * */
    @Override
    public T get(int index) {
        /* Return null if index is out of bounds */
        if ((index + 1) > size || index < 0) {
            return null;
        }
        Node itr = sentinal.next;
        while (index > 0) {
            itr = itr.next;
            index--;
        }
        return itr.item;
    }

    /** Returns true is Object o is LinkedListDeque and contains same elements as this deque. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque<T> unknownDeque = (Deque<T>) o;
            int sizeLld = unknownDeque.size();

            if (sizeLld == this.size()) {
                for (int i = 0; i < sizeLld; i++) {
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
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node itr;

        LinkedListDequeIterator() {
            itr = sentinal.next;
        }

        @Override
        public boolean hasNext() {
            return itr != sentinal;
        }

        @Override
        public T next() {
            T ret = itr.item;
            itr = itr.next;
            return ret;
        }
    }

    /** Returns item at index if found in recursive search
     * null if not found.
     */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinal.next, index);
    }

    private T getRecursiveHelper(Node n, int i) {
        if (n == sentinal) {
            return null;
        }
        if (i == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, i - 1);
    }

    /** Compares this with another LinkedListDeque of same size,
     * Returns true if all elements are equal when checked in order of insertion.
     * @param lld
     * @return boolean
     */
    private boolean sequentialEqualsSameSize(LinkedListDeque<T> lld) {
        Node itr1 = sentinal.next;
        Node itr2 = lld.sentinal.next;
        while (itr1 != sentinal && itr2 != lld.sentinal) {
            if (!(itr1.item.equals(itr2.item))) {
                return false;
            }
            itr1 = itr1.next;
            itr2 = itr2.next;
        }
        return true;
    }
}
