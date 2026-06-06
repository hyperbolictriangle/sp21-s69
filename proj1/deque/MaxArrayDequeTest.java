package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    // Setup to run tests on MaxArrayDeque
    private static class TestDog {
        public String name;
        public int age;

        public TestDog(String n, int a) {
            name = n;
            age = a;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof TestDog) {
                TestDog d = (TestDog) o;
                return d.age == this.age && d.name.equals(this.name);
            }
            return false;
        }
    }

    private static class DogComparator implements Comparator<TestDog> {
        @Override
        public int compare(TestDog d1, TestDog d2) {
           return d1.age - d2.age;
        }
    }

    private static class DogNameComparator implements Comparator<TestDog> {
        @Override
        public int compare(TestDog d1, TestDog d2) {
            return d1.name.compareTo(d2.name);
        }
    }

    // Test Cases:
    @Test
    public void maxAgeTest() {
        Comparator<TestDog> dogComparator = new DogComparator();
        MaxArrayDeque<TestDog> mad = new MaxArrayDeque<>(dogComparator);

        mad.addLast(new TestDog("Max", 25));
        mad.addLast(new TestDog("Dex", 11));
        mad.addLast(new TestDog("Hex", 34));
        mad.addLast(new TestDog("Zex", 1));

        TestDog maxDog = new TestDog("Aax", 240);
        mad.addLast(maxDog);

        TestDog returned = mad.max();

        assertEquals("Expect dog { Name: " + maxDog.name +
                " , Age: " + maxDog.age + " } " +
                " on calling max function got " +
                " dog { Name: " + returned.name +
                " , Age: " + returned.age + " } ", returned, maxDog);
    }

    @Test
    public void lexicographicallyLastTest() {
        Comparator<TestDog> dogComparator = new DogNameComparator();
        MaxArrayDeque<TestDog> mad = new MaxArrayDeque<>(dogComparator);

        mad.addLast(new TestDog("Max", 25));
        mad.addLast(new TestDog("Dex", 11));
        mad.addLast(new TestDog("Hex", 34));
        mad.addLast(new TestDog("Zex", 1));
        mad.addLast(new TestDog("Aax", 240));

        TestDog maxDog = new TestDog("Zzz", 0);
        mad.addLast(maxDog);

        TestDog returned = mad.max();

        assertEquals("Expect dog { Name: " + maxDog.name +
                " , Age: " + maxDog.age + " } " +
                " on calling max function got " +
                " dog { Name: " + returned.name +
                " , Age: " + returned.age + " } ", returned, maxDog);
    }
}
