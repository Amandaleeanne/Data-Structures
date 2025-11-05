package org.assign3;

public class heapTest2 {
    public static void main(String[] args) {
        LwtMaxHeap<Integer> heap1 = new LwtMaxHeap<>();
        heap1.insert(10);
        heap1.insert(40);
        heap1.insert(30);
        heap1.insert(20);
        
        Integer[] arr = {10, 40, 30, 20};
        LwtMaxHeap<Integer> heap2 = new LwtMaxHeap<>(arr);
        
        System.out.println("From insert: " + heap1);
        System.out.println("From array:  " + heap2);
        
    }
}
