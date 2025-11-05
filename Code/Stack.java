
import java.util.NoSuchElementException;

//Most of the implementation comes from the book, however I made a few changes and removed some comments /code because I enjoy consice code.
//Linked list implementaion
 public class Stack<Item>{
     private Node<Item> first;     // top of stack
     private int size;
 
     // helper linked list class
     @SuppressWarnings("FieldMayBeFinal")
     private static class Node<Item> {
         private Item item;
         private Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
     }

     public Stack() {
         first = null;
         size = 0;
     }

     public boolean isEmpty() {
         return first == null;
     }

     public int size() {
         return size;
     }
 
     /**
      * Adds the item to this stack.
      *
      * @param  item the item to add
      */
     public void push(Item item) {
         Node<Item> oldfirst = first;
         first = new Node<>(item, oldfirst);
         size++;
     }
 
     /**
      * Removes and returns the item most recently added to this stack.
      *
      * @return the item most recently added
      * @throws NoSuchElementException if this stack is empty
      */
     public Item pop() {
         if (isEmpty()) throw new NoSuchElementException("Stack underflow");
         Item item = first.item;        // save item to return
         first = first.next;            // delete first node
         size--;
         return item;                   // return the saved item
     }
     /**
      * Returns (but does not remove) the item most recently added to this stack.
      *
      * @return the item most recently added to this stack
      * @throws NoSuchElementException if this stack is empty
      */
     public Item peek() {
         if (isEmpty()) throw new NoSuchElementException("Stack underflow");
         return first.item;
     }
 
 }
 
 