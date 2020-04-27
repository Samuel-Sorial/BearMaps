import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void testingAdd(){
        Point A = new Point(2,3);
        Point Z = new Point(4,2);
        Point B = new Point(4,2);
        Point C = new Point(4,5);
        Point D = new Point(3,3);
        Point E = new Point(1,5);
        Point F = new Point(4,4);
        List<Point> testList = new ArrayList<>();
        testList.add(A);
        testList.add(Z);
        testList.add(B);
        testList.add(C);
        testList.add(D);
        testList.add(E);
        testList.add(E);
        testList.add(F);
        KDTree testingTree = new KDTree(testList);
        Point  nearestPoint = testingTree.nearest(0,7);
        assertEquals(nearestPoint.getX(),1,.001);
        assertEquals(nearestPoint.getY(),5,.001);

    }
}
