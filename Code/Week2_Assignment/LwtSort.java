
/**
 *
 * @author Tom
 */

public class LwtSort {

    public static long swapCount;
    public static long timeTaken;
    public static <T extends Comparable<T>> void insertionSort(T[] a) {
        swapCount = 0;
        timeTaken = System.currentTimeMillis();
        int j;
        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                swapCount++;
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
        timeTaken = (System.currentTimeMillis() - timeTaken);
    }
    public static <T extends Comparable<T>> void shellSort(T[] a) {
        swapCount = 0;
        timeTaken = System.currentTimeMillis();
        //Note, pulled straight from the book as I enjoy thier implementation. All other comments are comments for myself for studies.
        int arrayLength = a.length;
        int currHibbNum = 1; //initalize the hibbard number

        //Find the largest hibbard number that the array can take. O(n/3) <- i think
        while (currHibbNum < arrayLength / 3) 
        {
            currHibbNum = 3*currHibbNum + 1;
        } 
        
        // Sort the array untill the Hibbards number is less than 0
        while ( currHibbNum >= 1) {
            //Insertion sort starting, however use the current hibbard number as the start
            for (int i = currHibbNum; i < arrayLength; i++){
                //look through the array and only compare from the currentHibbardNumber plud the next increment of the hibbard number (compareTo)
                for (int j = i; j >= currHibbNum && a[j].compareTo(a[j-currHibbNum]) < 0;  j-= currHibbNum)
                {
                    //once found, swap if smaller
                    swapReferences(a, j, j-currHibbNum);
                    swapCount++;
                }
            }
            currHibbNum = currHibbNum / 3; //decrement hibbard
        }
        timeTaken = (System.currentTimeMillis() - timeTaken);
    }

    public static <T extends Comparable<T>> void heapSort(T[] a) {
        timeTaken = System.currentTimeMillis();
        BinaryMinHeap minHeap = new BinaryMinHeap();
        for (int i = 0; i < a.length; i++) {
            minHeap.insert(a[i]);
        }

        for (int i = 0; i < a.length; i++) {
            if (!minHeap.isEmpty()) {
                try {
                    a[i] = (T) minHeap.deleteMin();
                } catch (UnderflowException ex) {
                    System.out.println("underflow detected in heap sort");
                    return;
                }
            }
        }
        timeTaken = (System.currentTimeMillis() - timeTaken);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmp[tmpPos++] = a[leftPos++];
            } else {
                tmp[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd) // copy rest of first half
        {
            tmp[tmpPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd) // copy rest of right half
        {
            tmp[tmpPos++] = a[rightPos++];
        }

        // copy tmp back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }

    public static < T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tmp = (T[]) new Comparable[a.length];
        timeTaken = System.currentTimeMillis();
        mergeSort(a, tmp, 0, a.length - 1);
        timeTaken = (System.currentTimeMillis() - timeTaken);
    }
    
    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        swapCount = 0;
        timeTaken = System.currentTimeMillis();
        quickSort(a, 0, a.length - 1);
        timeTaken = (System.currentTimeMillis() - timeTaken);
    }
    //Logic is from the slides, edited to be more compact and in-line with what the assignment wanted.
    //Some edge case recursive logic was code-reviewed by ChatGPT using the promt: "Preform a code review" due to original logic crashing at very large array sizes.
    private static <T extends Comparable<? super T>> void quickSort(T[] a, int leftPointer, int rightPointer) {
        // Check the base case for recursion to prevent unnecessary calls
        if (leftPointer < rightPointer) {
            // median-of-three pivot selection
            T pivot = median3(a, leftPointer, rightPointer);
    
            int smallestEleInx = leftPointer, largestEleInx = rightPointer - 1;
    
            // Partition step
            while (smallestEleInx < largestEleInx) {
                // Increment until you find the next element greater than or equal to pivot, or run out of the array
                while (smallestEleInx < rightPointer && a[smallestEleInx].compareTo(pivot) < 0) {
                    smallestEleInx++;
                }
    
                // Increment until you find the next element smaller than or equal to pivot, or run out of the array
                while (largestEleInx > leftPointer && a[largestEleInx].compareTo(pivot) > 0) {
                    largestEleInx--;
                }
    
                // If indices haven't crossed, swap elements
                if (smallestEleInx < largestEleInx) {
                    swapReferences(a, smallestEleInx, largestEleInx);
                    swapCount++;
                }
            }
    
            // Restore pivot: place it in the correct position
            swapReferences(a, smallestEleInx, rightPointer - 1);
            swapCount++;
    
            // Recursively sort the two halves (ensure the subarray isn't too small)
            if (smallestEleInx - 1 > leftPointer) {
                quickSort(a, leftPointer, smallestEleInx - 1);
            }
            if (smallestEleInx + 1 < rightPointer) {
                quickSort(a, smallestEleInx + 1, rightPointer);
            }
        }
        // Use insertion sort for small subarrays (when the subarray size is <= 20)
        else if (rightPointer - leftPointer <= 20) {
            int j;
            for (int p = leftPointer + 1; p <= rightPointer; p++) {
                T tmp = a[p];
                for (j = p; j > leftPointer && tmp.compareTo(a[j - 1]) < 0; j--) {
                    swapCount++;
                    a[j] = a[j - 1];
                }
                a[j] = tmp;
            }
        }
    }    

    public static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }

        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static <T> void swapReferences(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
