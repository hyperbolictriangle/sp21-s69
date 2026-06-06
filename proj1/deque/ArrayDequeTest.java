package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Check if we can get element at index i from deque. */
    public void getIndexTest(){
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        int[] expected = new int[]{ 4, 3, 2, 1, 0, 5, 6, 7};

        for(int i = 0; i < 5; i++){
            lld1.addFirst(i);
        }
        for(int i=5; i < 8; i++){
            lld1.addLast(i);
        }
        lld1.printDeque();
        for(int i = 0; i < 8; i++){
            int v = lld1.get(i);
            String errorMsg = "Incorrect value returned at index " + i + "\n";
            errorMsg += " get(" + i + ") value returned: " + v + "\n";
            errorMsg += " expected value: " + expected[i] + "\n";
            assertEquals(errorMsg, expected[i], v);
        }
    }
    @Test
    /* Check if we can get element at index i from deque for larger test cases. */
    public void getIndexTestLarge(){
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        for(int i = 0; i < 100000; i++){
            lld1.addLast(i);
        }
        for(int i = 0; i < 8; i++){
            int v = lld1.get(i);
            String errorMsg = "Incorrect value returned at index " + i + "\n";
            errorMsg += " get(" + i + ") value returned: " + v + "\n";
            errorMsg += " expected value: " + i + "\n";
            assertEquals(errorMsg, i, v);
        }
    }

    @Test
    /* Check if equals function returns true for same address object. */
    public void equalsItselfTest() {
        ArrayDeque<Integer> lld = new ArrayDeque<>();
        lld.addLast(3);

        assertTrue("Should return true when deque compared with itself", lld.equals(lld));
    }

    @Test
    /* Check if deque equals another deque based on their contents. */
    public void equalsTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        ArrayDeque<String> lld3 = new ArrayDeque<>();
        LinkedListDeque<Integer> ad = new LinkedListDeque<>();
        for(int i=0; i<100000; i++){
            lld1.addLast(i);
            lld2.addLast(i);
            ad.addLast(i);
            lld3.addLast(Integer.toString(i));
        }
        assertTrue("Should return true when equals is called on another Deque with same contents.", lld1.equals(lld2));
        lld2.removeLast();
        assertTrue("Should return true when equals is called on " +
                "types implementing Deque interface.", lld1.equals(ad));
        assertFalse("Should return false when equals is called on ArrayDeque type object containing " +
                " different type of data.", lld1.equals(lld3));
        assertFalse("Should return false when equals is called on another Deque with different contents.", lld1.equals(lld2));
    }

    @Test
    /* Check if deque equals another deque based on their contents. */
    public void dogDequeEqualsTest() {
        ArrayDeque<ArrayDequeTest.TestDog> lld1 = new ArrayDeque<>();
        ArrayDeque<ArrayDequeTest.TestDog> lld2= new ArrayDeque<>();
        for(int i=0; i<100000; i++){
            lld1.addLast(new ArrayDequeTest.TestDog("Test1", i));
            lld2.addLast(new ArrayDequeTest.TestDog("Test2", i));
        }
        assertTrue("Should return true when equals is called on another Deque with same contents.", lld1.equals(lld2));
        lld2.removeLast();
        assertFalse("Should return false when equals is called on another Deque with different contents.", lld1.equals(lld2));
    }

    /** Private class for testing ArrayDeque.equals implementation. */
    private static class TestDog {
        public String name;
        public int age;

        public TestDog(String n, int a) {
            name = n;
            age = a;
        }

        @Override
        public boolean equals(Object d){
            if(d instanceof ArrayDequeTest.TestDog) {
                return this.age == ((ArrayDequeTest.TestDog) d).age;
            }
            return false;
        }
    }

    @Test
    /* Test iterator implementation */
    public void iteratorTest() {
      ArrayDeque<String> sad = new ArrayDeque<>();
      for (int i=0; i < 26; i++) {
          sad.addLast(Integer.toString(i));
      }

      for (String s: sad) {
          System.out.print(s + " ");
      }
      System.out.println();
      sad.printDeque();
    }

    @Test
    public void bigInsertionRemovalTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 100000; i++) {
            if (i%4 == 0) {
                ad.addLast(i);
                ad.removeFirst();
            } else if (i%4 == 1) {
                ad.addFirst(i);
                ad.removeFirst();
            } else if (i%4 == 2) {
                ad.addLast(i);
                ad.removeLast();
            } else {
                ad.addFirst(i);
                ad.removeLast();
            }
            assertTrue("Expected isEmpty to return true", ad.isEmpty());
        }
    }
}
