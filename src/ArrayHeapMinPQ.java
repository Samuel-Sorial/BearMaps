import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author : Samuel-Sorial
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private List<Node> minHeap;
    private HashMap<T, Integer> items;
    private int lastItem;

    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>();
        items = new HashMap<>();
    }

    /**
     * find it's parent
     * @param index
     * @return the index of the parent
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * find the index of the left child
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * find the index of the right child
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * swap the two elements at these indices
     * @param index
     * @param anotherIndex
     */
    private void swap(int index, int anotherIndex) {
        Node<T> currNode = minHeap.get(index);
        minHeap.set(index, minHeap.get(anotherIndex));
        minHeap.set(anotherIndex, currNode);
        items.put((T) minHeap.get(index).item, index);
        items.put((T) minHeap.get(anotherIndex).item, anotherIndex);
    }

    /**
     * evaluate if the first element is less than the second
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return minHeap.get(i).priority < minHeap.get(j).priority;
    }

    /**
     * re-arrange the heap from bottom to up
     * @param index
     */
    private void swimUp(int index) {
        if (index == 0) {
            return;
        }
        int p = parent(index);
        if (less(p, index)) {
            return;
        }
        swap(index, p);
        swimUp(p);
    }

    /**
     * re-arrange the heap from the top to bottom
     * @param index
     */
    private void swimDown(int index) {
        if (leftChild(index) > size() || index + 1 > size()) {
            return;
        }
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        if (rightChild < size() && less(rightChild, index)) {
            swap(rightChild, index);
            swimDown(rightChild);
        } else if (less(leftChild, index)) {
            swap(leftChild, index);
            swimDown(leftChild);
        }
    }

    /**
     * add's an item with a specific priority
     * @param item
     * @param priority
     */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        minHeap.add(new Node(item, priority));
        items.put(item, minHeap.size() - 1);
        swimUp(size() - 1);
    }

    /**
     * check if the heap contains a given element or not
     * @param item
     * @return
     */
    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    /**
     * find the smallest element in the heap
     * @return
     */
    @Override
    public T getSmallest() {
        if(minHeap.size()==0)
            throw new NoSuchElementException();
        return (T) minHeap.get(0).item;
    }

    /**
     * find the smallest element and remove it
     * @return
     */
    @Override
    public T removeSmallest() {
        if(minHeap.size() == 0)
            throw new NoSuchElementException();
        T current = (T) minHeap.get(0).item;
        swap(0,size()-1);
        minHeap.remove(size()-1);
        if(size() != 1)
            swimDown(0);
        items.remove(current);
        return current;
    }

    /**
     * find the size of the heap
     * @return
     */
    @Override
    public int size() {
        return minHeap.size();
    }

    /**
     * change the priority of a given element
     * @param item
     * @param priority
     */
    @Override
    public void changePriority(T item, double priority) {
        if(!contains(item))
            throw new NoSuchElementException();
        int i = items.get(item);
        double oldPriority = minHeap.get(i).priority;
        minHeap.get(i).priority = priority;
        if(oldPriority < priority)
            swimDown(i);
        else
            swimUp(i);
    }

    /**
     * represents the node inside the heap.
     */
    private class Node<T>{
        T item;
        double priority;

        public Node(T s, double pr){
            this.item = s;
            this.priority = pr;
        }
    }
}
