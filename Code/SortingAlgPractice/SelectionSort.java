public class SelectionSort {
   public int[] FindMinimum(int[] array)
   {
    int smallest = 0;
    for (int i : array) {
        if ( i < smallest)
        {
            smallest = i;
        }
    }
    return array;
   }
   public int[] exhangeElements(int[] array, int position1, int position2) {
    
    int savePosition = array[position1];
    array[position1] = array[position2];
    array[position2] = savePosition;
    return array;
   }
}
