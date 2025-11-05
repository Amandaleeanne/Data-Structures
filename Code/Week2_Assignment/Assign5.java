/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


import java.sql.Time;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import org.knowm.xchart.*;

/**
 * TODO - replace my name with your name 
 * @author tom
 */
@SuppressWarnings("FieldMayBeFinal")
public class Assign5 {
    //For plotting data, within an array to conver all instances of Randomized, acending, and decending arrays.
    //Array index 0 : randomized, index 1: sorted acending, index 2: sorted decending
    private static PlottingData[] insertionSortData = new PlottingData[3];
    private static PlottingData[] shellSortData = new PlottingData[3];
    private static PlottingData[] heapSortData = new PlottingData[3];
    private static PlottingData[] mergeSortData = new PlottingData[3];
    private static PlottingData[] quickSortData = new PlottingData[3];

    //@TODO: NOw that you have an array of PlottingData objects and you can assgn them as you are timing stuff all you need to do is take the data you have put in and plot it using Xchart ( think i decided on a category chart)
    public static void main(String[] args) {
        DataSet dataSet;
        System.out.println("\nrandomized");
        //for (int sz = 10; sz <= 10; sz *= 10) {
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);

            insertionSort(dataSet.getRandomizedArray(), sz, 0);
            shellSort(dataSet.getRandomizedArray(), sz, 0);
            heapSort(dataSet.getRandomizedArray(), sz, 0);
            mergeSort(dataSet.getRandomizedArray(), sz, 0);
            quickSort(dataSet.getRandomizedArray(), sz, 0);
            System.out.println("");
        }

        System.out.println("\nsorted ascending");
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            
            insertionSort(dataSet.getSortedAscendingArray(), sz, 1);
            shellSort(dataSet.getSortedAscendingArray(), sz, 1);
            heapSort(dataSet.getSortedAscendingArray(), sz, 1);
            mergeSort(dataSet.getSortedAscendingArray(), sz, 1);
            quickSort(dataSet.getSortedAscendingArray(), sz, 1);
            System.out.println("");
        }

        System.out.println("\nsorted descending");
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            
            insertionSort(dataSet.getSortedDescendingArray(), sz, 2);
            shellSort(dataSet.getSortedDescendingArray(), sz, 2);
            heapSort(dataSet.getSortedDescendingArray(), sz, 2);
            mergeSort(dataSet.getSortedDescendingArray(), sz, 2);
            quickSort(dataSet.getSortedDescendingArray(), sz, 2);
            System.out.println("");
        }
        plotData();
    }

    private static void plotData()
    {
        //Initalize plotting:
        int[] dataSetSizes = {10, 100, 1000, 10000, 100000};
        String[] sortType = {"InsertionSort", "ShellSort", "HeapSort", "MergeSort", "QuickSort"};
        String[] arrayType = {"randomized", "sorted acending", "sorted decending"};

        CategoryChart catChart = new CategoryChart();


    }
    private static void insertionSort(Integer[] dataSet, int sz, int index) {
        LwtSort.insertionSort(dataSet);
        System.out.println("\tinsertion sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount + " Time in milliseconds= " + LwtSort.timeTaken);
        insertionSortData[index].captureData(LwtSort.swapCount, LwtSort.timeTaken, sz);
    }

    private static void shellSort(Integer[] dataSet, int sz, int index) {
        LwtSort.shellSort(dataSet);
        System.out.println("\tshell sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount + " Time in milliseconds= " + LwtSort.timeTaken);
        shellSortData[index].captureData(LwtSort.swapCount, LwtSort.timeTaken, sz);
    }

    private static void heapSort(Integer[] dataSet, int sz, int index) {
        LwtSort.heapSort(dataSet);
        System.out.println("\theap sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount + " Time in milliseconds= " + LwtSort.timeTaken);
        heapSortData[index].captureData(LwtSort.swapCount, LwtSort.timeTaken, sz);
    }

    private static void mergeSort(Integer[] dataSet, int sz, int index) {
        LwtSort.mergeSort(dataSet);
        System.out.println("\tmerge sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount + " Time in milliseconds= " + LwtSort.timeTaken);
        mergeSortData[index].captureData(LwtSort.swapCount, LwtSort.timeTaken, sz);
    }

    private static void quickSort(Integer[] dataSet, int sz, int index) {
        LwtSort.quickSort(dataSet);
        System.out.println("\tquick sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount + " Time in milliseconds= " + LwtSort.timeTaken);
        quickSortData[index].captureData(LwtSort.swapCount, LwtSort.timeTaken, sz);
    }
}
