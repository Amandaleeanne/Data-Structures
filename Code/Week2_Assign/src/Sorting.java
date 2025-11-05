/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


/**
 *
 * @author Thomas.Abbott
 */
public class Sorting {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // testHeapSort();
        // testMergeSort();
        testMedian3();
        testShellSort();
        System.out.println("...tada...");
    }
    private static void testHeapSort() {
        Integer a[] = {31, 41, 59, 26, 53, 58, 97};
        LwtSort.heapSort(a);
        displayArray("heap sorted:", a);
    }
    private static void testShellSort()
    {
        Integer a[] = {31, 41, 59, 26, 53, 58, 97};
        LwtSort.shellSort(a);
        displayArray("shell sorted:", a);
    }
    private static void testMergeSort() {
        Integer a[] = {24, 13, 26, 1, 2, 27, 38, 15};
        LwtSort.mergeSort(a);
        displayArray("merge sorted:", a);
    }

    private static <T> void displayArray( String title, T[] a) {
        System.out.print(title);
        for (T n : a) {
            System.out.print(" " + n);
        }
        System.out.println("");

    }
    
    private static void testMedian3() {
        Integer a[] = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("\t" + LwtSort.median3(a, 0, a.length-1));
        displayArray("med_of_3:", a);
         
    }
}
