package org.assignment4;
import java.util.Collection;
/**
 * An AVL Tree that DOES NOT support two of the same value within the tree.
 * @author Amandaleeanne Schock with much credit to the book 
 * FindNext, FindNode, and FindPrevious are all my code
 */
public class MyAvlTree<T extends Comparable<T>>{
    AvlNode<T> root = null;
    private static final int ALLOWED_IMBALANCE = 1;
    /**
     * Credit: Weiss book
     */
    private static class AvlNode<T extends Comparable<T>>
    {
        T data;
        AvlNode<T> leftNode;
        AvlNode<T> rightNode;
        int height; //also known as subtree "size" -> MIT

        AvlNode(T data) { this(data, null, null);}

        AvlNode (T data, AvlNode<T> left, AvlNode<T> right)
        {
            this.data = data;
            this.leftNode = left;
            this.rightNode = right;
            this.height = 0;
        }
        public boolean hasLeft()
        {
            return leftNode != null;
        }
        public boolean hasRight()
        {
            return rightNode != null;
        }
        public boolean hasTwoChildren()
        {
            return hasLeft() && hasRight();
        }
    }
    
    //Note to teacher: I did not understand what you meant by the book constructors other than the AVlnodes, so these are mine on the AVLTree class.
    public MyAvlTree() { this.root = null; }

    public MyAvlTree(Collection<T> items) {
        for (T item : items) Insert(item);
    }

    /**
     * inserts into the tree
     * credit: book
     * @param value the thing you wish to add into the tree
     */
    public void Insert(T value) throws IllegalArgumentException{
        if (root == null) //tree is empty, make this node the new root.
        {
            root = Insert(value, root);
        } else if (FindNode(value) == null){
            root = Insert(value, root);
        }else throw new IllegalArgumentException("Node already exists");

    }
    //recursive helper method -> see above
    private AvlNode<T> Insert(T value, AvlNode<T> node){
        if (node == null)
            return new AvlNode<>(value, null, null);
        
        int path = value.compareTo(node.data);

        if (path < 0) { node.leftNode = Insert(value, node.leftNode); }
        else if (path > 0) {node.rightNode = Insert(value, node.rightNode); }
        else ; //do nothing
        return Balance(node);
    }
    /**
     * Removes from the tree
     * credit: book
     * @param value the thing you wish to remove from the tree
     */
    public void Remove(T value){
        root = Remove(value, root);
    }
    //recursive helper method -> see above:
    private AvlNode<T> Remove( T value, AvlNode<T> node )
    {
        //Find the node to remove:
        if( node == null )
            return node;   // Item not found; do nothing
            
        int compareResult = value.compareTo(node.data);
            
        if( compareResult < 0 )
            node.leftNode = Remove(value, node.leftNode);
        else if( compareResult > 0 )
            node.rightNode = Remove(value, node.rightNode);
        //compareTo must be equal to 0 since the above cases were false, therefore, check the node to see which case it is and remove
        else if( node.hasTwoChildren() )
        {
            //find the sucsessor, replace, and remove:
            node.data = FindMin( node.rightNode ).data;
            node.rightNode = Remove( node.data, node.rightNode);
        }
        else
            node = ( node.hasLeft() ) ? node.leftNode : node.rightNode;
        return Balance(node);
    }
    /**
     * Balances the AVL tree from given subtree.
     * credit: book
     */
    private AvlNode<T> Balance( AvlNode<T> node){
        if (node == null)
        {
            return node;
        }

        int calculatedSubtreeSizeLeft = height(node.leftNode) - height(node.rightNode);
        int calculatedSubtreeSizeRight = height(node.rightNode) - height(node.leftNode);

        if ( calculatedSubtreeSizeLeft > ALLOWED_IMBALANCE)
        {
            if (height(node.leftNode.leftNode) >= height(node.leftNode.rightNode) )
                node = RotateWithLeftChild(node);
            else
                node = DoubleWithLeftChild(node);
        }
        else if(calculatedSubtreeSizeRight > ALLOWED_IMBALANCE)
        {
            if(height(node.rightNode.rightNode) >= height(node.rightNode.leftNode))
                node = RotateWithRightChild(node);
            else
                node = DoubleWithRightChild(node);
        }

        node.height = Math.max(height(node.leftNode), height(node.rightNode)) + 1;
        return node;
    }

    private AvlNode<T> RotateWithLeftChild( AvlNode<T> prevParentNode)
    {
        AvlNode<T> prevChildNode = prevParentNode.leftNode;
        prevParentNode.leftNode = prevChildNode.rightNode; 
        prevChildNode.rightNode = prevParentNode;

        //update heights:
        prevParentNode.height = Math.max(height(prevParentNode.leftNode), height(prevParentNode.rightNode)) + 1;
        prevChildNode.height = Math.max(height(prevChildNode.leftNode), prevParentNode.height) + 1;

        return prevChildNode;
    }
    //note to self, this is the left dog leg, so you will preform right rotation, and then rotate with left child
    private AvlNode<T> DoubleWithLeftChild(AvlNode<T> node){
        node.leftNode = RotateWithRightChild(node.leftNode);
        return  RotateWithLeftChild(node);
    }

    private AvlNode<T> RotateWithRightChild(AvlNode<T> prevParNode)
    {
        AvlNode<T> prevChiNode = prevParNode.rightNode; //assign for swapping and storage
        prevParNode.rightNode = prevChiNode.leftNode; //preform the swap/rotation
        prevChiNode.leftNode = prevParNode; //assign the parent node to be the child node
        
        //update heights:
        prevParNode.height = Math.max( height( prevParNode.leftNode ), height( prevParNode.rightNode ) ) + 1;
        prevChiNode.height = Math.max( height( prevChiNode.rightNode ), prevParNode.height ) + 1;
        return prevChiNode;
    }
    //Note to self: this is the right dog leg, so preform LEFT rotation and then rotate again with left child
    private AvlNode<T> DoubleWithRightChild(AvlNode<T> node){
        node.rightNode = RotateWithLeftChild(node.rightNode);
        return  RotateWithRightChild(node);
    }

    /**
     * returns the first node that contains that value.  
     * If the node doesn't exist, it should return null.
     * Credit: meee
     * @param value the value you are looking for
     * @return null or Node
     */
    public AvlNode<T> FindNode(T value){
        return findNodeHelper(value, root);
    }
    //recursive helper class to find the node
    private AvlNode<T> findNodeHelper(T value, AvlNode<T> node)
    {
        if (node == null) return null;
        if (value.compareTo(node.data) == 0) return node;
        //go left
        if(value.compareTo(node.data) < 0)  return findNodeHelper(value, node.leftNode);
        //go right
        else return findNodeHelper(value, node.rightNode);

    }
    /**
     * Given a value, returns the value within the node which comes immediately before it. 
     * If it doesn't exist in the tree, it should return the value of the node that would come immediately before it if it was in the tree.
     * credit: me
     * @param value the value passed to find the next smallest value
     */
    public AvlNode<T> FindPrevious(T value){ 
        return FindPrevious(value, root, null); //Might not be null, if doesnt work check that case
    }
    private AvlNode<T> FindPrevious (T value, AvlNode<T> currentNode, AvlNode<T> lastSmallestNode)
    {   //base case:
        if (currentNode == null)
        {
            return lastSmallestNode;
        }
        // stop when you find the currentNode.data >= value, but still explore that subtree (if exists)
        if (currentNode.data.compareTo(value) >= 0)
        {
            return FindPrevious(value, currentNode.leftNode, lastSmallestNode);
        }else{  //else it is less than (and therefore a candidate) so search the right subtree to see of there is a smaller value within the next subtree
            lastSmallestNode = currentNode;
            return FindPrevious(value, currentNode.rightNode, lastSmallestNode);
        }
    }
    /**
     * Given a value, returns the value within the node which comes immediately before it. 
     * If it doesn't exist in the tree, it should return the value of the node that would come immediately before it if it was in the tree.
     * credit: me
     * @param value the value passed to find the next smallest value
     */
    public AvlNode<T> FindNext(T value){ 
        return FindNext(value, root, null); //Might not be null, if doesnt work check that case
    }
    private AvlNode<T> FindNext (T value, AvlNode<T> currentNode, AvlNode<T> lastSmallestNode)
    {   //base case:
        if (currentNode == null)
        {
            return lastSmallestNode;
        }
        // stop when you find the currentNode.data <= value, but still explore that subtree (if exists)
        if (currentNode.data.compareTo(value) <= 0)
        {
            return FindNext(value, currentNode.rightNode, lastSmallestNode);
        }else{  //else it is less than (and therefore a candidate) so search the right subtree to see of there is a smaller value within the next subtree
            lastSmallestNode = currentNode;
            return FindNext(value, currentNode.leftNode, lastSmallestNode);
        }
    }
    /**
     * Return the height of this node, or -1 if null
     * credit: book
     */
    private int height(AvlNode<T> node)
    {
        return node == null ? -1 : node.height;
    }
    //Credit me, though when i looked at the book its basically the same code, so i geuss both?
    private AvlNode<T> FindMin(AvlNode<T> node) {
        if (!node.hasLeft())
        {
            return node;
        }else return FindMin(node.leftNode);
    }

    //For testing:

    @Override
    /**
     * Prints the tree IN ORDER (In-Order traversal)
     */
    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        inOrder(root, strBuild);
        return strBuild.toString().trim();
    }

    private void inOrder(AvlNode<T> node, StringBuilder strBuild) {
        if (node != null) {
            inOrder(node.leftNode, strBuild);
            strBuild.append(node.data).append(" ");
            inOrder(node.rightNode, strBuild);
        }
    }

    //so I don't have to export the AvlNode class.
    public T getRootData()
    {
        return root.data;
    }

    public T findNodeData(T value)
    {
        return FindNode(value).data;
    }

    public T findNextData(T value)
    {
        return FindNext(value).data;
    }

    public T findPreviousData(T value)
    {
        return FindPrevious(value).data;
    }

}
