import java.util.List;

import org.assign3.LwtMaxHeap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class LwtMaxHeapTest {

    @Test
    public void testHeapProperty() {
        LwtMaxHeap<Integer> heap = new LwtMaxHeap<>();
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        try {
            assertEquals(20, heap.findMax());   
        } catch (Exception e) {
        }
    }
    @Test
    public void testHeapPropertyAlphabet() {
        LwtMaxHeap<String> heap = new LwtMaxHeap<>();
        heap.insert("B");
        heap.insert("A");
        heap.insert("C");
        //C should be greater due to the ASCII of "C" being > "A"
        try {
            assertEquals("C", heap.findMax());   
        } catch (Exception e) {
        }
    }
    @Test
    public void testDeleteMax() {
        LwtMaxHeap<Integer> heap = new LwtMaxHeap<>();
        heap.insert(30);
        heap.insert(10);
        heap.insert(20);
        assertEquals(30, heap.deleteMax());
        try {
            assertEquals(20, heap.findMax());   
        } catch (Exception e) {
        }
        assertEquals(20, heap.deleteMax());
        try {
            assertEquals(10, heap.findMax());   
        } catch (Exception e) {
        }
        heap.deleteMax();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testAllNodesGreaterThan() {
        LwtMaxHeap<Integer> heap = new LwtMaxHeap<>();
        heap.insert(5);
        heap.insert(15);
        heap.insert(25);
        heap.insert(10);

        try {
            
            List<Integer> result = heap.allNodesGreaterThan(10);
            assertTrue(result.contains(15));
            assertTrue(result.contains(25));
            assertFalse(result.contains(10));
            assertFalse(result.contains(5));
        } catch (Exception e) {
        }
    }
    @Test
    public void testConstructorWithArray() {
        Integer[] initial = {10, 20, 5, 30};
        LwtMaxHeap<Integer> heap = new LwtMaxHeap<>(initial);
        try {
            assertEquals(30, heap.findMax());            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    //If the above test passes the below putting into an array should also pass.
    @Test
    public void testIsEmptyAndMakeEmpty() {
        Integer[] initial = {10, 20};
        LwtMaxHeap<Integer> heap = new LwtMaxHeap<>(initial);
        assertFalse(heap.isEmpty());
        heap.makeEmpty();
        assertTrue(heap.isEmpty());
    }
    @Test
    public void testToStringWhenEmpty() {
        LwtMaxHeap<Integer> heap = new LwtMaxHeap<>();
        assertEquals("Heap is empty.", heap.toString());
    }
}
