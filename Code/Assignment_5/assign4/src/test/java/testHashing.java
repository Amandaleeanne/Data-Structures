
import org.junit.*;
import hashAssignment.HashTable;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class testHashing {

    @Test
    public void testLoadFactorCalculation() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(100);
        assertEquals(1.0 / 5, hashTable.loadFactor());
    }
    @Test
    public void testInsertNewElementCreatesBucket() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(10);
        assertEquals(1, hashTable.getactiveBucketCount());
        assertEquals(1.0 / 5, hashTable.loadFactor());
    }
    @Test 
    public void testInsertNewElementAddsToTree() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(7);
        hashTable.insert(12);
        assertEquals(1, hashTable.getactiveBucketCount());
        String output = hashTable.toString();
        assertTrue(output.contains("7") && output.contains("12") );
    }
    @Test
    public void testSearchExistingElement() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(15);
        assertEquals(15, hashTable.search(15));
    }
    @Test
    public void testSearchNonExistingElement() {
        HashTable hashTable = new HashTable(5);
        assertNull(hashTable.search(100));
    }
    @Test
    public void testRemoveElement() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(25);
        hashTable.remove(25);
        assertNull(hashTable.search(25));
        assertEquals(0, hashTable.getEntryNum());
        assertEquals(0, hashTable.getactiveBucketCount());
    }
    @Test
    public void testRehashingWithBuckets() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(1);
        hashTable.insert(2);
        hashTable.insert(3);
        hashTable.insert(4);
        hashTable.insert(5); // should trigger rehash
        assertTrue(hashTable.loadFactor() < 1.0);
    }
    @Test
    public void testRehashingWithEntries() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(1);
        hashTable.insert(6);
        hashTable.insert(11);
        hashTable.insert(2);
        hashTable.insert(7);
        hashTable.insert(12); // should trigger rehash
        assertTrue(hashTable.loadFactor() < 1.0);
    }

    @Test
    public void testSizeAndBucketSize() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(5);
        hashTable.insert(10);
        double totalSize = hashTable.getEntryNum();
        assertEquals(totalSize, 2.0);
        assertEquals(hashTable.size(), hashTable.getactiveBucketCount());
    }

    @Test
    public void testMultipleBuckets() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(1);
        hashTable.insert(6);
        hashTable.insert(11);
        assertTrue(hashTable.getactiveBucketCount() >= 1); // Could now have different buckets.
    }
}
