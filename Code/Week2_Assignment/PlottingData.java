
//A class simmilar to Enum 
public class PlottingData {
    //Swap counts for each data point
    private long ten_swapCount;
    private long hundred_swapCount;
    private long thounsand_swapCount;
    private long tenthousand_swapCount;
    private long hundredthousand_swapCount;
    //Time for each data point
    private long ten_time;
    private long hundred_time;
    private long thounsand_time;
    private long tenthousand_time;
    private long hundredthousand_time;

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

    //Accepting a long[] array of size 2 in order to place into the correct variuble.
    public void captureData(long swapCount, long timeTaken, int variubleType)
    {
        switch (variubleType) {
            case 10 -> {
                setTen_swapCount(swapCount);
                setTen_time(timeTaken);
            }
            case 100 -> {
                setHundred_swapCount(swapCount);
                setHundred_time(timeTaken);
            }
            case 1000 -> {
                setThounsand_swapCount(swapCount);
                setThounsand_time(timeTaken);
            }
            case 10000 -> {
                setTenthousand_swapCount(swapCount);
                setTenthousand_time(timeTaken);
            }
            case 100000 -> {
                setHundredthousand_swapCount(swapCount);
                setHundredthousand_time(timeTaken);
            }
            default -> throw new Error("Invalid int on vairubleType:" + variubleType);
        }
    }
    //Auto-generated setters and getters (IntelliJ)
    public void setTen_swapCount(long ten_swapCount) {
        this.ten_swapCount = ten_swapCount;
    }

    public void setHundred_swapCount(long hundred_swapCount) {
        this.hundred_swapCount = hundred_swapCount;
    }

    public void setThounsand_swapCount(long thounsand_swapCount) {
        this.thounsand_swapCount = thounsand_swapCount;
    }

    public void setTenthousand_swapCount(long tenthousand_swapCount) {
        this.tenthousand_swapCount = tenthousand_swapCount;
    }

    public void setHundredthousand_swapCount(long hundredthousand_swapCount) {
        this.hundredthousand_swapCount = hundredthousand_swapCount;
    }

    public void setTen_time(long ten_time) {
        this.ten_time = ten_time;
    }

    public void setHundred_time(long hundred_time) {
        this.hundred_time = hundred_time;
    }

    public void setThounsand_time(long thounsand_time) {
        this.thounsand_time = thounsand_time;
    }

    public void setTenthousand_time(long tenthousand_time) {
        this.tenthousand_time = tenthousand_time;
    }

    public void setHundredthousand_time(long hundredthousand_time) {
        this.hundredthousand_time = hundredthousand_time;
    }

    public long getTen_swapCount() {
        return ten_swapCount;
    }

    public long getHundred_swapCount() {
        return hundred_swapCount;
    }

    public long getThounsand_swapCount() {
        return thounsand_swapCount;
    }

    public long getTenthousand_swapCount() {
        return tenthousand_swapCount;
    }

    public long getHundredthousand_swapCount() {
        return hundredthousand_swapCount;
    }

    public long getTen_time() {
        return ten_time;
    }

    public long getHundred_time() {
        return hundred_time;
    }

    public long getThounsand_time() {
        return thounsand_time;
    }

    public long getTenthousand_time() {
        return tenthousand_time;
    }

    public long getHundredthousand_time() {
        return hundredthousand_time;
    }
    
}