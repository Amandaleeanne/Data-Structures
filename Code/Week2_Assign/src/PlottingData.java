
//A class to store all of the data that would need to be captured by each instance of the Sorting algorithms
// per arraySize and array orientaion.
/**
 * @author Amandaleeanne Schock
 */
public class PlottingData {
    //Swap counts for each data point
    private double ten_swapCount;
    private double hundred_swapCount;
    private double thounsand_swapCount;
    private double tenthousand_swapCount;
    private double hundredthousand_swapCount;
    //Time for each data point
    private double ten_time;
    private double hundred_time;
    private double thounsand_time;
    private double tenthousand_time;
    private double hundredthousand_time;

//note: i am FULLY aware i could have used a HashMap, 
//however due to how Xchart works, it was easier/faster to use a flat variuble assignment and thne pass that as an array.

    public PlottingData()
    {
        this.ten_time = 0;
        this.hundred_time = 0;
        this.thounsand_time = 0;
        this.tenthousand_time = 0;
        this.hundredthousand_time = 0;

        this.ten_swapCount = 0;
        this.hundred_swapCount = 0;
        this.thounsand_swapCount = 0;
        this.tenthousand_swapCount = 0;
        this.hundredthousand_swapCount = 0;
    }

    /**
     * Takes the imput parameters and "captures" (sets) the data to the appropriate value within the PlottingData object
     * @param swapCount the number for the number of swaps a sort took
     * @param timeTaken the number for the time it took the sort (in microseconds preferably)
     * @param variubleType how long the array size was(up to 100,000)
     */
    public void captureData(double swapCount, double timeTaken, int arraySize)
    {
        switch (arraySize) {
            case 10 -> {
                this.ten_swapCount = swapCount;
                this.ten_time = timeTaken;
            }
            case 100 -> {
                this.hundred_swapCount = swapCount;
                this.hundred_time = timeTaken;
            }
            case 1000 -> {
                this.thounsand_swapCount = swapCount;
                this.thounsand_time = timeTaken;
            }
            case 10000 -> {
                this.tenthousand_swapCount = swapCount;
                this.tenthousand_time = timeTaken;
            }
            case 100000 -> {
                this.hundredthousand_swapCount = swapCount;
                this.hundredthousand_time = timeTaken;
            }
            default -> throw new Error("Invalid int on vairubleType: " + arraySize + " can only do numbers of 10*1 -> 10*5");
        }
    }
    /**
     * Returns the timeTaken values within the PLottingData object as a double array.
     * @return double[]
     */
    public double[] timeTakenAsArray()
    {

        double[] returnArray = {ten_time, hundred_time, thounsand_time, tenthousand_time, hundredthousand_time};
        return returnArray;
    }
    /**
     * Returns the swapCount values within the PLottingData object as a double array.
     * @return double[]
     */
    public double[] swapCountsAsArray()
    {
        double[] returnArray = {ten_swapCount, hundred_swapCount, thounsand_swapCount, tenthousand_swapCount, hundredthousand_swapCount};
        return returnArray;
    }
}