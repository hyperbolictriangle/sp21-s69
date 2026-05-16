package deque;

public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /** LinkedListDeque - sentinal.next is first item if it exists. */
    private Node sentinal;
    private int size;

    public LinkedListDeque(){
       sentinal = new Node(null, null, null);
       sentinal.next = sentinal.prev = sentinal;
       size = 0;
    }

    public LinkedListDeque(T i){
        sentinal = new Node(null, null, null);
        sentinal.next = new Node(i, sentinal, sentinal);
        sentinal.prev = sentinal.next;
        size = 1;
    }

    /** Returns size of Deque */
    public int size(){
        return size;
    }

    /** Returns true if Deque is empty */
    public boolean isEmpty(){
        return size==0;
    }

    /** Adds item of type T to start of Deque, does not return */
    public void addFirst(T item){
        sentinal.next = new Node(item, sentinal, sentinal.next);
        sentinal.next.next.prev = sentinal.next;
        size += 1;
    }

    /** Removes and returns the item at the front of the deque. Returns `null` if no such item exists */
    public T removeFirst(){
        if(size == 0) {
            return null;
        }
        T value = sentinal.next.item;
        sentinal.next = sentinal.next.next;
        sentinal.next.prev = sentinal;
        size -= 1;
        return value;
    }

    /** Adds item of type T to end of Deque, does not return */
    public void addLast(T item){
        sentinal.prev = new Node(item, sentinal.prev, sentinal);
        sentinal.prev.prev.next = sentinal.prev;
        size += 1;
    }

    /** Removes and returns the item at the end of the deque. Returns `null` if no such item exists */
    public T removeLast(){
        if(size == 0) {
            return null;
        }
        T value = sentinal.prev.item;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.next = sentinal;
        size -= 1;
        return value;
    }

    /** Prints elements of Deque separated by spaces, prints end line character at end. */
    public void printDeque(){
        Node itr = sentinal.next;
        while(itr != sentinal){
            System.out.print(itr.item + " ");
            itr = itr.next;
        }
        System.out.println();
    }

    /** Returns item located at given index in Deque, returns `null` if index out of bounds.
     * @param index: int
     * */
    public T get(int index){
        /** Return null if index is out of bounds */
        if((index+1) > size || index < 0){
            return null;
        }
        Node itr = sentinal.next;
        while(index > 0){
            itr = itr.next;
            index--;
        }
        return itr.item;
    }
}
