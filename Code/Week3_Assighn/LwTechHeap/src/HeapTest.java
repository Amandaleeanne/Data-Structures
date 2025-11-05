import java.util.*;
import org.junit.Assert.*;
import org.hamcrest.*;

public class HeapTest {
    LwtMaxHeap<Integer> heap = new LwtMaxHeap<>();
    @Test
    public void testAllNodesGreaterThanAndInserting() {
        try {
            heap.insert(50);
            heap.insert(30);
            heap.insert(40);
            heap.insert(20);
            
            List<Integer> greaterThan30 = heap.allNodesGreaterThan(30);
            assertTrue(greaterThan30.contains(40));
            assertTrue(greaterThan30.contains(50));
            assertFalse(greaterThan30.contains(20));
        } catch (UnderflowException ex) {
            System.out.println(ex);
        }
    }
    @Test
    public void testDeleteMaxAndFindMax()
    {
        assertTrue(heap.deleteMax() == 50);
        assertTrue(40 == heap.findMax());
    }
    @Test 
    public void testMakeEmpty()
    {
        heap.makeEmpty();
        assertTrue(heap.isEmpty());
    }
    @Test 
    public void testArrayInitalization()
    {
        Integer[] array = {50, 30, 40, 20};
        LwtMaxHeap<Integer> newHeap = new LwtMaxHeap<>(array);
        String expected = "[50, 30, 40, 20]";
        assertTrue(newHeap.toString().equals(expected));
    }
   
}
