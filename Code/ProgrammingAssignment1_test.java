import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.*;

//Notes to teacher: I use VSCode and will not be installing a diffeerent IDE, therefore these tests will pass in VSCode but I am not sure how they work with your IDE.
public class ProgrammingAssignment1_test {
//TODO: See if i will need a method in order to show the efficency of my code. (Yes, stopwatch class)
    @Test
    public void RandomQueue_test(){
        RandomQueue<Integer> newQueue = new RandomQueue<Integer>();
        Integer[] itemArray = {1 , 2, 3, 4};
        String itemArrayString = "{1, 2, 3, 4}";
        for (Integer item : itemArray)
        {
            newQueue.enqueue(item);
        }
        //equeue works?
        assertEquals(itemArrayString, newQueue.toString());
        //sample works?
        System.out.print(String.valueOf(newQueue.sample()));
        //dequeue works?
        Integer dequeued = newQueue.dequeue();
        System.out.print(String.valueOf(dequeued));
        //no way to check WHICH was dequeued, only that something *was*:
        assertEquals(itemArray.length - 1, newQueue.getSize());

        //what about dequeue almost everything:
        for (int i = 0; i < newQueue.getSize(); i++) {
            newQueue.dequeue();
        }
        assertEquals(1, newQueue.getSize());
    }
    //Test Programming problems:
    @Test
    public void rotate_array_test(){
        Integer[] intTest = {1, 2, 3, 4, 5, 6, 7};
        Integer[] expecIntegers = {5, 6, 7, 1, 2, 3, 4};
        //Basic test
        Integer[] actualIntegers = ProgrammingProblems.rotate_array(intTest , 3);
        assertArrayEquals(expecIntegers, actualIntegers);
    }
    @Test
    public void rotate_array_test2(){
        Integer[] intTest = {1, 2, 3, 4, 5};
        Integer[] expecIntegers = {1, 2, 3, 4, 5};
        //Basic test
        Integer[] actualIntegers = ProgrammingProblems.rotate_array(intTest , 5);
        assertArrayEquals(expecIntegers, actualIntegers);
    }
    @Test
    public void rotate_array_test3(){
        Integer[] intTest = {1, 2, 3, 4, 5};
        Integer[] expecIntegers = {4, 5, 1, 2, 3};
        //Basic test
        Integer[] actualIntegers = ProgrammingProblems.rotate_array(intTest , 7);
        assertArrayEquals(expecIntegers, actualIntegers);
    }
    @Test
    public void next_greater_element_test1(){
        Integer[] intTest = {4, 5, 2, 10, 8};
        Integer[] expectedIntegers = {5, 10, 10, -1, -1};
        Integer[] actualIntegers = ProgrammingProblems.next_greater_element(intTest);
        assertArrayEquals(expectedIntegers, actualIntegers);

    }
    @Test
    public void next_greater_element_test2(){
        Integer[] intTest = {};
        Integer[] expectedIntegers = {};
        Integer[] actualIntegers = ProgrammingProblems.next_greater_element(intTest);
        assertArrayEquals(expectedIntegers, actualIntegers);

    }
    @Test
    public void next_greater_element_test3(){
        Integer[] intTest = {6, 8, 0, 1, 3};
        Integer[] expectedIntegers = {8, -1, 1, 3, -1};
        Integer[] actualIntegers = ProgrammingProblems.next_greater_element(intTest);
        assertArrayEquals(expectedIntegers, actualIntegers);

    }
    @Test
    public void next_greater_element_test4(){
        Integer[] intTest = {2, 1, 2, 3, 5, 4, 7};
        Integer[] expectedIntegers = {3, 2, 3, 5, 7, 7, -1};
        Integer[] actualIntegers = ProgrammingProblems.next_greater_element(intTest);
        assertArrayEquals(expectedIntegers, actualIntegers);

    }
    @Test
    public void next_greater_element_test5(){
        Integer[] intTest = {9, 7, 6, 5, 3};
        Integer[] expectedIntegers = {-1, -1, -1, -1, -1};
        Integer[] actualIntegers = ProgrammingProblems.next_greater_element(intTest);
        assertArrayEquals(expectedIntegers, actualIntegers);

    }
}
