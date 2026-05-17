package deque;

public class ArrayDeque<T> {
    private T[] deque;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    private static final int resizeFactor = 2;

    public ArrayDeque() {
        deque = (T[])new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        capacity = 8;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(T i) {
        if(size == capacity){
           // implement resize later
        }
        deque[nextLast] = i;
        nextLast = (nextLast + 1) % capacity;
        size += 1;
    }

    public T removeLast() {
        if(size == 0){
            return null;
        }
        nextLast = ((nextLast - 1) % capacity + capacity) % capacity;
        T v = deque[nextLast];
        deque[nextLast] = null;
        size--;
        return v;
    }

    public T get(int ind) {
        return deque[translateIndex(ind)];
    }

    private int translateIndex(int ind) {
        int first = (nextFirst + 1) % capacity;
        return (first + ind) % capacity;
    }

    public void printDeque(){
        for(int i=0; i < size; i++){
           System.out.print(deque[i] + " ");
        }
        System.out.println();
    }

    public void addFirst(T i) {
        if(size == capacity) {
            // implement resize later on
        }
        deque[nextFirst] = i;
        nextFirst = ((nextFirst - 1) % capacity + capacity ) % capacity;
        size += 1;
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % capacity;
        T v = deque[nextFirst];
        deque[nextFirst] = null;
        size -= 1;
        return v;
    }

    /* reimplement this */
    private void resize(int newCapacity) {
        T[] temp = (T[])new Object[newCapacity];
        System.arraycopy(deque, 0, temp, 0, size);
        deque = temp;
    }
}
