package hashAssignment;

public class HashTable {
    private double numEntries; //the combined number of all of the search tree's elements
    private BinarySearchTree[] bucketArray;
    private final double MAX_LOAD_FACTOR = 1.0;
    private int activeBucketCount ;

    /**
     * A constructor that takes a single int parameter
     * @param sz
     */
    public HashTable( int sz ){
        if (sz <= 0) {
            throw new IllegalArgumentException();
        }
        bucketArray = new BinarySearchTree[sz];
        numEntries = 0;
        activeBucketCount  = 0;
    }
    /**
     * Gets the hashVaue
     * @param key
     * @return
     */
    public Integer getHash(Integer key) { return (key & 0x7fffffff) % bucketArray.length;}
    /**
     * Takes an Integer parameter and stores it in the Hash Table
     * @param key
     */
    public void insert(Integer key){
        //when adding, make sure to check if you are adding to an existing bucket, or creating a new bucket
        //if you are creating a new bucket, make sure to update the activeBucketCount 
        //somewhere here update the numEntries to recalculate the total number of entries (prolly just plus one)
        Integer hashCode = getHash(key);
        if (bucketArray[hashCode] == null)
        {
            bucketArray[hashCode] = new BinarySearchTree();
            activeBucketCount ++;
        }

        bucketArray[hashCode].add(key);
        numEntries++;
        if (loadFactor() >= MAX_LOAD_FACTOR)
        {
            rehash();
        }
    }
    /**
     * Is an insert method specifically for when you are rehashing as to not double re-hash while rehashing....
     * @param key
     */
    private void insertForRehash(Integer key)
    {
        Integer hashCode = getHash(key);
        if (bucketArray[hashCode] == null)
        {
            bucketArray[hashCode] = new BinarySearchTree();
            activeBucketCount ++;
            
        }

        bucketArray[hashCode].add(key);
    }
    /**
     * Find an item in the hash table and remove it
     * @param key
     */
    public void remove(Integer key){
        Integer hashCode = getHash(key);
        if (bucketArray[hashCode] == null || !bucketArray[hashCode].contains(key))
        {
            throw new IllegalArgumentException("Key given does not exist in hashTable");
        }

        bucketArray[hashCode].remove(key);
        numEntries--;
        
        //cleanup
        if (bucketArray[hashCode].isEmpty()) {
            bucketArray[hashCode] = null;
            activeBucketCount--;
        }
    }

    /**
     * Searches for an item in the hash table and if found, return a reference to it (don't delete it). 
     * Otherwise, return null
     * @param key
     * @return
     */
    public Integer search(Integer key) {
        Integer hashCode = getHash(key);
        if (bucketArray[hashCode] == null) {
            return null;
        }
    
        if (bucketArray[hashCode].contains(key)) {
            return key; // Return a reference to the found item
        }
    
        return null;
    }
    
    /**
     * return the number of all of the keys in Hash Table
     * @return an int that is a count of all of the top-level keys in the table
     */ public int size(){ return activeBucketCount ;}
    /**
     * return the number of elements in the bucket specified by the parameter, "index"
     * @param index
     * @return
     */ public int size(int index){ return bucketArray[index].getSize(); }
    /**
     * the loading factor of the hash table to the caller
     * @return the load factor of the table
     */ public double loadFactor(){ return (numEntries / (double) bucketArray.length);} 
    /**
     * Resizes the binary search tree array to the next prime number greater than twice the current size.  
     * Then rehash each element in the current hash list to the new, larger hash list.
     */
    private void rehash(){
        //calculate next prime number
        int newSize = calculateNextPrime();
        //make a new array of that size and re-initalize variubles
        HashTable enlargedHash = new HashTable(newSize);
        //for EACH element in the bucketArray AND EACH NODE within those elements of the bucketArray, place it in a NEW posiiton.
        for (BinarySearchTree tree : bucketArray) {
            //once a non-null is found ->
            if (tree !=null)
            {
                //go through that binary tree and insert all notes to a new location within the enlargedHashArray
                while (!tree.isEmpty()) {
                    enlargedHash.insertForRehash(tree.removeAndShare().value);
                }
            }

        }   
        //re-initalize this object instance:
        this.bucketArray = enlargedHash.bucketArray;
        this.activeBucketCount  = enlargedHash.activeBucketCount ;
        this.numEntries = enlargedHash.numEntries;
    }

    private int calculateNextPrime(){
        int newSize = bucketArray.length*2;
        while (!isPrime(newSize)) newSize++;
        return newSize;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Outputs the contents of the Hash Table, one list per row in the Bucket List.
     * The Binary Search Tree for a bucket in the list is an in order traversal of the collision list 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int bucketCounter = 1;
        for (BinarySearchTree tree : bucketArray) {
            //once a non-null is found ->
            if (tree !=null)
            {
                sb.append("Bucket ").append(bucketCounter).append(": ");
                sb.append(tree.treeAsLinkedList().toString()).append("\n");
                bucketCounter++;
            }

        }   
        return sb.toString();
    }

    //For testing:
    /**
     * Returns the number of items in the hashMap
     * @return
     */
    public double getEntryNum() {return numEntries;}
    /**
     * Returns the numbers of trees (or buckets) there are in the list.
     * @return
     */
    public int getactiveBucketCount () {return activeBucketCount ;}

}
