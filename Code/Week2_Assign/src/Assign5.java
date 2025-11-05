/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


import java.sql.Time;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import org.knowm.xchart.*;

/**
 * 
 * @author tom, Amandaleeanne Schock
 */
@SuppressWarnings("FieldMayBeFinal")
public class Assign5 {
    //For plotting data, within an array to conver all instances of Randomized, acending, and decending arrays.
    //Array index 0 : randomized, index 1: sorted acending, index 2: sorted decending
    private static PlottingData[] insertionSortData = {new PlottingData(), new PlottingData(), new PlottingData()};
    private static PlottingData[] shellSortData = {new PlottingData(), new PlottingData(), new PlottingData()};
    private static PlottingData[] heapSortData = {new PlottingData(), new PlottingData(), new PlottingData()};
    private static PlottingData[] mergeSortData = {new PlottingData(), new PlottingData(), new PlottingData()};
    private static PlottingData[] quickSortData = {new PlottingData(), new PlottingData(), new PlottingData()};


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
        plot();
    }
    /**
     * Plots all data gathered within Main()
     */
    private static void plot() {
        XYChart swapChart = new XYChartBuilder()
                                .width(800)
                                .height(800)
                                .title("Swaps taken for each Sorting algorithm")
                                .xAxisTitle("Array Size")
                                .yAxisTitle("Swaps taken")
                                .build();

        XYChart timeChart = new XYChartBuilder()
                            .width(800)
                            .height(800)
                            .title("Time (MilliSeconds) taken for each Sorting algorithm")
                            .xAxisTitle("Array Size")
                            .yAxisTitle("Time taken")
                            .build(); 
        timeChart.getStyler().setYAxisLogarithmic(true);                       
        swapChart.getStyler().setYAxisLogarithmic(true);
        
        timeChart.getStyler().setXAxisLogarithmic(true);                       
        swapChart.getStyler().setXAxisLogarithmic(true);

        addSeriesSwaps(swapChart, "InsertionSort", insertionSortData, 0, 2);
        addSeriesSwaps(swapChart, "ShellSort", shellSortData, 0, 2);
        addSeriesSwaps(swapChart, "HeapSort", heapSortData, 0, 2);
        addSeriesSwaps(swapChart, "MergeSort", mergeSortData, 0, 2);
        addSeriesSwaps(swapChart, "QuickSort", quickSortData, 0, 2);

        addSeriesTime(timeChart, "InsertionSort", insertionSortData, 0, 2);
        addSeriesTime(timeChart, "ShellSort", shellSortData, 0, 2);
        addSeriesTime(timeChart, "HeapSort", heapSortData, 0, 2);
        addSeriesTime(timeChart, "MergeSort", mergeSortData, 0, 2);
        addSeriesSwaps(timeChart, "QuickSort", quickSortData, 0, 2);
    
        new SwingWrapper<>(swapChart).displayChart();
        new SwingWrapper<>(timeChart).displayChart();
    }
        /**
     * takes an XYChart and grabs the swap data from the PlottingData object and places it on the XYChart for plotting 
     * @param chart the XYChart you are passing.
     * @param sortName the name of the series title you are using
     * @param sortDataArray the SortedData array (see the class) you are using
     * @param start which part of the data to grab (0-2)
     * @param limit where to stop on the data (0-2)
     * @return void
     */
    private static void addSeriesSwaps(XYChart chart, String sortName, PlottingData[] sortDataArray, int start, int limit) {
        double[] xData = {10, 100, 1000, 10000, 100000};
        String[] types = {"RandomData", "Ascended Sort", "Descended Sort"};
        for (int i = start; i < types.length && i <= limit; i++) {
            double[] yData = sortDataArray[i].swapCountsAsArray();
            yData = fitData(yData);
            chart.addSeries(sortName + " " + types[i], xData, yData);
        }
    }
    /**
     * takes an XYChart and grabs the time data from the PlottingData object and places it on the XYChart for plotting 
     * @param chart the XYChart you are passing.
     * @param sortName the name of the series title you are using
     * @param sortDataArray the SortedData array (see the class) you are using
     * @param start which part of the data to grab (0-2)
     * @param limit where to stop on the data (0-2)
     * @return void
     */
    private static void addSeriesTime(XYChart chart, String sortName, PlottingData[] sortDataArray, int start, int limit) {
        double[] xData = {10, 100, 1000, 10000, 100000};
        String[] types = {"RandomData", "Ascended Sort", "Descended Sort"};
        for (int i = start; i < types.length && i <= limit; i++) {
            double[] yData = sortDataArray[i].timeTakenAsArray();
            yData = fitData(yData);
            chart.addSeries(sortName + " " + types[i], xData, yData);
        }
    }
    /**
     * Fits array data so that any 0 numbers turn into a small nonzero number
     * for a pseudo-log graph.
     * @param data the array to pass
     * @return
     */
    private static double[] fitData(double[] data){
        
        for (int i = 0; i < data.length; i++) {
            if (data[i] <= 0) {
                data[i] = 1e-6;
            }
        }
        return data;
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
