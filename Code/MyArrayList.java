import java.util.Arrays;

public  class MyArrayList <T> implements Iterable<T> {
    private int capacity;
    private int usedLength;
    private Object[] baseArray;
    private final int DEFAULT_SIZE = 10;

    //Initalize instances of the array
    public MyArrayList(){
        baseArray = new Object[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
        usedLength = 0;
    }
    public MyArrayList(int length) {
        baseArray = new Object[length];
        this.capacity = length;
        usedLength = 0;
    }

    //Add onto the end of the array (resize if nessesary)
    public void append(T item)
    {
        maybeExpandArray();
        //update the usedLength
        baseArray[usedLength] = item;
        usedLength++;
    }

    //remove from the END of the array and resize
    public void removeEnd() {
    // Reset the last element
    usedLength--;
    baseArray[usedLength] = null;
}
    //Get the actual length of the MyArrayList
    public int getLength() 
    {
        return usedLength;
    }

    @SuppressWarnings("unchecked")
    public T getElement(int index){
        if (checkIvalidIndexBounds(index))
        {
            throw new IndexOutOfBoundsException(String.format("Cannot get index %d because it is out of the array bounds", index));   
        }else{
            return (T)baseArray[index];
        }
    }

    public void setElement(int index, T item) {
        if (checkIvalidIndexBounds(index)) {throw new IndexOutOfBoundsException(); }
        baseArray[index] = item;
    }
    
    
    //doubles the array when capacity is maxxed
    private void maybeExpandArray()
    {
        if (checkCapacity())
        {
            capacity = capacity * 2;
            Object[] newArray = new Object[capacity];
            System.arraycopy(baseArray, 0, newArray, 0, usedLength);
            baseArray = newArray;
        }
    }
    //check to see if adding to the current index will exceed capacity of the array
    private boolean checkCapacity()
    {
        return usedLength + 1 >= capacity;
    }
    private boolean checkIvalidIndexBounds(int index)
    {
        return index >= usedLength || index < 0;
    }
    //Overrides:

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(baseArray, usedLength));
    }
    //!! GPT Code below, I got lazy.
    @Override
    public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<T>() {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < usedLength;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return (T) baseArray[currentIndex++];
        }
    };
}
}
