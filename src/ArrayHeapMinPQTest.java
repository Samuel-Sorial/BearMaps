import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void testBasics(){
        ExtrinsicMinPQ<String> test = new ArrayHeapMinPQ<>();

        assertEquals(0,test.size());

        test.add("Samuel",2);
        assertEquals(1,test.size());

        // Testing removeSamllest and getSmallet
        test.add("Ebraam",1);
        test.add("Sam",4);
        assertEquals("Ebraam", test.getSmallest());
        test.removeSmallest();
        assertEquals("Samuel",test.removeSmallest());

        // Testing change priority
        test.add("Remoon",3);
        assertEquals(2,test.size());
        test.changePriority("Remoon",5);
        assertEquals("Sam",test.getSmallest());
    }
}
