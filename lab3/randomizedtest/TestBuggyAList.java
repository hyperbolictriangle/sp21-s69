package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> cLst = new AListNoResizing<>();
        BuggyAList<Integer> bLst = new BuggyAList<>();

        for (int i = 0; i < 3; i++) {
            cLst.addLast(i);
            bLst.addLast(i);
        }

        for (int i = 0; i < 3; i++) {
            int c = cLst.removeLast();
            int b = bLst.removeLast();
            assertEquals("Expected value: " + c + " from BuggyAList found value :" + b, c, b);
        }
    }

    @Test
    public void testRandomgMethods() {
        AListNoResizing<Integer> alnr = new AListNoResizing<>();
        BuggyAList<Integer> bal = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operation = StdRandom.uniform(0, 4);
            if (operation == 0) {
                int randomValue = StdRandom.uniform(0, 100);
                alnr.addLast(randomValue);
                bal.addLast(randomValue);
            } else if (operation == 1) {
                int sizeC = alnr.size();
                int sizeB = bal.size();
                assertEquals("Expected size to be: "
                        + sizeC + " found: " + sizeB, sizeC, sizeB);
            } else if (operation == 2 && alnr.size() > 0) {
                int lastC = alnr.getLast();
                int lastB = bal.getLast();
                assertEquals("Expected getLast() to return: "
                        + lastC + " found: " + lastB, lastC, lastB);
            } else if (operation == 3 && alnr.size() > 0) {
                int lastC = alnr.removeLast();
                int lastB = bal.removeLast();
                assertEquals("Expected removeLast() to return: "
                        + lastC + " found: " + lastB, lastC, lastB);
            }
        }
    }
}
