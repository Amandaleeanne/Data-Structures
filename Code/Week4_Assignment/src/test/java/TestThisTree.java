
import java.util.Arrays;

import org.assignment4.MyAvlTree;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class TestThisTree {
    //test case: the tree can have one node
    @Test
    public void testInsert() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>();
        biTree.Insert(10);
        String expectedString = "10";
        assertTrue(biTree.toString().equals(expectedString));
    }
    //test case: the tree, with one node cannot add the same exact node
    @Test
    public void testInsertSame() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>();
        biTree.Insert(10);
        //for how to setup the assertThrows by GPT (I didn't know I had to use a lambda function)
        assertThrows(IllegalArgumentException.class, () -> {
            biTree.Insert(10);
        });
    }
    //test case: the tree can have two nodes
    @Test
    public void testInsert2() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>();
        biTree.Insert(10);
        biTree.Insert(8);
        String expectedString = "8 10";
        assertTrue(biTree.toString().equals(expectedString));
    }
    //the tree can have three nodes (with left and right)
    @Test
    public void testInsert3() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>();
        biTree.Insert(10);
        biTree.Insert(8);
        biTree.Insert(12);
        String expectedString = "8 10 12";
        assertTrue(biTree.toString().equals(expectedString));
    }
    //the tree can be constructed with a list
    @Test
    public void testListConstructor(){
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10,8,12));
        String expectedString = "8 10 12";   
        assertTrue(biTree.toString().equals(expectedString));
    }
    //the tree can correctly remove a leaf node
    @Test
    public void testRemoval() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10,8));
        String expectedString = "8 10";
        assertTrue(biTree.toString().equals(expectedString));
        biTree.Remove(8);
        expectedString = "10";
        assertTrue(biTree.toString().equals(expectedString));
    }
    //the tree can balance on the left
    @Test
    public void testInsertAndLeftOneBalance() {
        MyAvlTree<Integer> biTree = new MyAvlTree<Integer>();
        biTree.Insert(10);
        biTree.Insert(9);
        biTree.Insert(8);        
        String expectedString = "8 9 10"; //In-order sequence should be the same
        assertTrue(biTree.toString().equals(expectedString));
        assertTrue(biTree.getRootData().equals(9)); //confirmed with an online tree visualizer
    }
    //the tree can balance on the right
    @Test
    public void testInsertAndRightOneBalance() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10, 12, 13));
        String expectedString = "10 12 13"; //In-order sequence should be the same
        assertTrue(biTree.getRootData() == 12);
        assertTrue(biTree.toString().equals(expectedString));
    }
    //the tree can balance a left-right problem
    @Test
    public void testInsertAndLeftRightBalance() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(6, 5, 4, 2, 3));
        String expectedString = "2 3 4 5 6"; //In-order sequence should be the same
        assertTrue(biTree.toString().equals(expectedString));
    }
    //the tree can balance a right-left problem
    @Test
    public void testInsertAndRightLeftBalance() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10, 12, 14, 16, 15));
        String expectedString = "10 12 14 15 16"; //In-order sequence should be the same
        assertTrue(biTree.toString().equals(expectedString));
    }

    //the tree can remove a node that has 1 child and re-assign the other node to new parent
    @Test
    public void testRemovalOneChild() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10, 12, 14, 13));
        String expectedString = "10 12 13 14"; //In-order sequence should be the same
        assertTrue(biTree.toString().equals(expectedString));
        biTree.Remove(14);
        expectedString = "10 12 13";
        assertTrue(biTree.toString().equals(expectedString));
    }

    //the tree can remove a node that has two children and have the correct sucsessor
    @Test
    public void testRemovalTwoChildren() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10, 12, 14, 13, 15));
        String expectedString = "10 12 13 14 15"; //In-order sequence should be the same
        assertTrue(biTree.toString().equals(expectedString));
        biTree.Remove(14);
        expectedString = "10 12 13 15";
        assertTrue(biTree.toString().equals(expectedString));
    }
    @Test
    public void testLargeTree()
    {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(15, 34, 100, 10, 95, 77, 29, 82, 91, 97, 69, 8, 16, 31, 86, 6, 40, 26, 74, 38, 62, 64, 50, 18, 12, 23, 48, 85, 36, 35));
        String expectedString = "6 8 10 12 15 16 18 23 26 29 31 34 35 36 38 40 48 50 62 64 69 74 77 82 85 86 91 95 97 100";
        assertTrue(biTree.toString().equals(expectedString));
    }

    //the tree can find a node
    @Test
    public void testFindNode() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10, 12, 14, 16, 15));
        String expecString = "10 12 14 15 16";
        assertTrue(biTree.toString().equals(expecString));
        assertTrue(biTree.findNodeData(16).equals(16));
        assertTrue(biTree.toString().equals(expecString));
    }
    //the tree can findNext node
    @Test
    public void testfindNext() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10, 12, 14, 16, 15));
        String expecString = "10 12 14 15 16";
        assertTrue(biTree.toString().equals(expecString)); 
        assertTrue(biTree.findNextData(15).equals(16));
        assertTrue(biTree.toString().equals(expecString)); //assert tree did not change
        assertTrue(biTree.findNextData(13).equals(14));
        assertTrue(biTree.toString().equals(expecString)); //assert tree did not change
        assertTrue(biTree.FindNext(16) == null); //for cases of when it is the biggest and there is no next number
        assertTrue(biTree.toString().equals(expecString)); //assert tree did not change
    }
    //the tree can findPrevious node
    @Test
    public void testfindPrevious() {
        MyAvlTree<Integer> biTree = new MyAvlTree<>(Arrays.asList(10, 12, 14, 16, 15));
        String expecString = "10 12 14 15 16";
        assertTrue(biTree.toString().equals(expecString)); //assert tree exists
        assertTrue(biTree.findPreviousData(15).equals(14));
        assertTrue(biTree.toString().equals(expecString)); //assert tree did not change
        assertTrue(biTree.findPreviousData(13).equals(12));
        assertTrue(biTree.toString().equals(expecString)); //assert tree did not change
    }
}
