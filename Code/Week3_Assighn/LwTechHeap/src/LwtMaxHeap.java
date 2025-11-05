import java.util.*;

@SuppressWarnings("unchecked")
public class LwtMaxHeap<T extends Comparable<? super T>> {
    
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;    // number of elements in heap
    private T [] array;         // the underlying data structure, note to self: I am starting at the FIRST index and not the 0th
    private final int initializedCapacity; //for O(1) resetting while not losing client imput

    
    public LwtMaxHeap() {
      array = (T[])new Comparable[DEFAULT_CAPACITY];
      initializedCapacity = DEFAULT_CAPACITY;
      currentSize = 0;
    }
    public LwtMaxHeap(int capacity) {
        array = (T[])new Comparable[capacity + 1];
        currentSize = 0;
        initializedCapacity = array.length - 1;
    }
    public LwtMaxHeap(T[] items) {
        //Initalize underlying array DStruct
        array = (T[])new Comparable[items.length *2 ]; //immediately enlarging the array in order to account for the fact they are making a heap to use the heap.
        System.arraycopy(items, 0, array, 1, items.length);
        //Set class variubles
        currentSize = items.length - 1;
        initializedCapacity = items.length - 1;
        //Heapify array
        buildHeap();
    }
        /**
     * Goes throug the heap and returns all values the heap greater than given key. Will throw an error if there is nothing in the array.
     * @param key the value in which you are trying to find things greater than
     * @return The list generated containing all nodes greater than given key.
     */
    public List<T> allNodesGreaterThan( T key ) throws UnderflowException{
        LinkedList<T> returnList = new LinkedList<>();
        //note to self: compareTo returns -1 if compare is less than, 0 if equal to, and 1 if greater than
        if (currentSize != 0)
        {
            for (int index = 1; index <= currentSize; index++) { if (array[index].compareTo(key) > 0) returnList.add(array[index]); }
            return  returnList;
        }else
        {
            throw new UnderflowException("Heap is empty.");
        }
    }
    public void insert(T x) { 
        checkArrayCapacity();
        //from my own studies I learned there is a difference between ++variable and variuble++, 
        //apparently ++variableincreases the variable first and then since it is within something that is calling the array the order is:
        //it is first increased, and then passed to the array. Pretty cool.
        array[++currentSize] = x;
        percolateUp(currentSize);
    }
    public T deleteMax() {
        //The array should USUALLY stay the same size...but the HEAP gets smaller
        /*
        take the last element in the heap (aka last used element in the array) store the first node and then set the 
        it to null and then and swap the last element in the heap with that node
        decrement the current size 
        call the percolate up 
        */

        //swap Elements and null
        T temp = array[1];
        array [1] = array[currentSize]; //logic is that the current size of the heap is the last place in the array that something is found
        array[currentSize] = null;
        //Put the new value thats swapped into the correct position
        percolateDown(1);   
        currentSize--; 
        return temp;
    }
    /**
     * From the top of the heap moves the value into the correct postion by preforming swaps untill it settles in place. (recursive)
     * Credit of the logic from https://youtu.be/pAU21g-jBiE?si=h4jmy3Y653fd12cz (one of the videos you had us watch)
     * @param currNodeIndex the index of the array whe the value of the node that is being swapped lives
     */
    private void percolateDown(int currNodeIndex) {

        int leftChild = 2 * currNodeIndex;
        int rightChild = 2 * currNodeIndex + 1;
        int smallest = currNodeIndex;
        //For all, check existance, and then compare (either left or right)
        if (leftChild <= currentSize && array[leftChild].compareTo(array[smallest]) > 0) {
            smallest = leftChild;
        }
        if (rightChild <= currentSize && array[rightChild].compareTo(array[smallest]) > 0) {
            smallest = rightChild;
        }
        //for as long as the index with the smallest value is not the current node passed.....swap and call again.
        if (smallest != currNodeIndex) {
            swap(currNodeIndex, smallest);
            percolateDown(smallest);
        }
   
    }
    /**
     * From the bottom of the heap moves the value into the correct postion by preforming swaps untill it settles in place. 
     * (Non-recursive, because it was cleaner)
     * @param currNodeIndex the index of the array whe the value of the node that is being swapped lives
     */
    private void percolateUp(int currNodeIndex)
    {
        //go up the entire tree (non recursively)
        while (currNodeIndex > 1 && array[currNodeIndex].compareTo(array[currNodeIndex / 2]) > 0) 
        {
            swap(currNodeIndex, currNodeIndex / 2);
            currNodeIndex = currNodeIndex / 2; // Move up the tree
        }
    }

    /**
     * Build the heap using the Max Heap property
     * Credit of the python logic https://youtu.be/pAU21g-jBiE?si=h4jmy3Y653fd12cz and I translated it into Java.
     */
    private void buildHeap() //heapify
    {
        //for each nodes parent, percolateUp that parent/ max_heapify that parent
        //where the first parent is the currentSize / 2
        for (int parent = currentSize / 2; parent > 0; parent--) {
            percolateDown(parent);
        }
    }
    /**
     * Takes two indexes from an array and swaps the values, does not override the existing data.
     * @param source the index which the item being swapped originates from
     * @param destination the index in which the item being swapped will go to
     * @return nothing
     */
    private void swap(int source, int destination){
        T temp = array[destination];
        array[destination] = array[source];
        array[source] = temp;
    }
    /**
     * Checks the underrlying array data structure capacity and either enlarges or shrinks the array based on 
     * the current array capacity.
     */
    private void checkArrayCapacity(){
        if (currentSize + 1 == array.length)
        {
            enlargeArray(array.length*2);
        }
    }
    /**
     * Enlarges the underlying array data structure
     * @param newSize
     */
    private void enlargeArray(int newSize) {
        T[] enlargedArray = (T[])new Comparable[newSize];
        System.arraycopy(array, 0, enlargedArray, 0, array.length);
        array = enlargedArray;
    }

    /**
     * Checks to see if the MaxHeap is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }
    /**
     * Completetly empties the MaxHeap
     */
    public void makeEmpty() {
        array = (T[])new Comparable[initializedCapacity];
        currentSize = 0;
    }

    /**
     * Pulled from Thomas Abbot's findMax: finds and returns the maximum item in the MaxHeap without deleting it.
     * @return the maximum item in the heap
     * @throws UnderflowException
     */
    public T findMax() throws UnderflowException{
        if (isEmpty())
        {
            throw new UnderflowException( "Empty Heap");
        }
        return array[1];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Heap is empty.";
        }
    
        StringBuilder sb = new StringBuilder();
        sb.append("Heap contents (level order): [");
        for (int i = 1; i <= currentSize; i++) {
            sb.append(array[i]);
            if (i < currentSize) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}