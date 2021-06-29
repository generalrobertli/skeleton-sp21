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
        AListNoResizing<Integer> works = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        works.addLast(5);
        works.addLast(15);
        works.addLast(25);

        broken.addLast(5);
        broken.addLast(15);
        broken.addLast(25);

        assertEquals(works.size(), broken.size());
        assertEquals(works.removeLast(), broken.removeLast());
        assertEquals(works.removeLast(), broken.removeLast());
        assertEquals(works.removeLast(), broken.removeLast());
  }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeM = M.size();
                assertEquals(sizeL,sizeM);
            } else if (operationNumber == 2) {
                //getLast
                if (L.size() > 0) {
                    int lastL = L.getLast();
                    int lastM = M.getLast();
                    assertEquals(lastL, lastM);
                }
            } else {
                //removeLast
                if (L.size() > 0) {
                    int lastL = L.removeLast();
                    int lastM = M.removeLast();
                    assertEquals(lastL,lastM);
                }
            }
        }
    }
}
