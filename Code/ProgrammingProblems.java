public class ProgrammingProblems {
    public static Integer[] next_greater_element(Integer[] inputArray){
        if(inputArray.length == 0)
        {
            return inputArray;
        }
        Integer[] returnArray = new Integer[inputArray.length];
        Stack<Integer> numStack = new Stack<>();
        for (int i = inputArray.length - 1 ; i >= 0; i--) {
            
            while (!numStack.isEmpty() && numStack.peek() <= inputArray[i])
            {
                numStack.pop();
            }
            //We can assume that if the stack is empty, no higher number than the current one was found
            if (numStack.isEmpty())
            {
                returnArray[i] = -1;
            }else{ // otherwise, the last thing on the stack is the next highest number in the array relitive to the current number
                returnArray[i] = numStack.peek();
            }
            numStack.push(inputArray[i]);
             
        }
        
        return returnArray;
    }
    //Wanted to make it generic since the type of array wasn't specified
    @SuppressWarnings("unchecked")
    public static <T> T[] rotate_array(T[] array, int r){
        int len = array.length;
        if (len == 0 || r == 0) {
            return array;    // for Omega of 1 :)
        }
        if (r < 0) {
            throw new IndexOutOfBoundsException("Cannot use a negitive rotation.");
        }
        r = r % len; // Added after seeing python code using the flat assignment instead of an if statement. Handles both edge cases of r = len and r > len
        int firstHalf = len - r;
        
        T[] firstHalfArr = (T[]) new Object[firstHalf]; //Generic casting code from google search/Gemini answer thing: T[] array = (T[]) new Object[size];
        T[] secondHalfArr = (T[]) new Object[r];

        //Copy to temp arrays
        System.arraycopy(array, firstHalf, secondHalfArr, 0, r); //intelliSense -> Made me go down the System.arraycopy Rabbit hole
        System.arraycopy(array, 0, firstHalfArr, 0, firstHalf);

        //Copy back into array:
        System.arraycopy(secondHalfArr, 0, array, 0, secondHalfArr.length);
        System.arraycopy(firstHalfArr, 0, array, secondHalfArr.length, firstHalfArr.length);
        return array;
    }
}
