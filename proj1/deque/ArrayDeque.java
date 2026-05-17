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
            resize();
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
        size -= 1;
        if(size <= capacity / (resizeFactor * resizeFactor)) {
            resize();
        }
        return v;
    }

    public T get(int ind) {
        if(ind < 0 || ind >= size) {
            return null;
        }
        return deque[translateIndex(ind)];
    }

    private int translateIndex(int ind) {
        int first = (nextFirst + 1) % capacity;
        return (first + ind) % capacity;
    }

    public void printDeque(){
        for(int i=0; i < size; i++){
           System.out.print(deque[translateIndex(i)] + " ");
        }
        System.out.println();
    }

    public void addFirst(T i) {
        if(size == capacity) {
            resize();
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
        if(size <= capacity / (resizeFactor * resizeFactor)) {
            resize();
        }
        return v;
    }

    /* reimplement this */
    private void resize() {
        int newCapacity;
        if(size == capacity) {
            newCapacity = capacity * resizeFactor;
        } else {
            newCapacity = capacity / resizeFactor;
        }
        T[] temp = (T[])new Object[newCapacity];
        arrayCopy(temp);
        deque = temp;
        capacity = newCapacity;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private void arrayCopy(T[] temp) {
        for(int i=0; i < size; i++) {
            temp[i] = get(i);
        }
    }
}
