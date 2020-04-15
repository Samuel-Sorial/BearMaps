import java.util.ArrayList;
import java.util.List;

/**
 * @author : Samuel-Sorial
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private List<Node> minHeap;
    private int lastItem;

    public ArrayHeapMinPQ(){
        minHeap = new ArrayList<>(16);
        minHeap.add(null); // There's no need for the first element to be able to use the parent equations.
        lastItem = 1;
    }

    @Override
    public void add(T item, double priority) {
        Node<T> currNode = new Node<>(item,priority);
        minHeap.add(lastItem,currNode);
        swimUp(lastItem);
        lastItem++;
    }

    private void swimUp(int index){
        if(index == 1)
            return;
        Node<T> currNode = minHeap.get(index);
        Node<T> father = minHeap.get(parent(index));
        if(father.priority > currNode.priority){
            swap(parent(index),index);
            swimUp(parent(index));
        }
    }

    private void swap(int parent, int index) {
        Node<T> currNode = minHeap.get(index);
        Node<T> parentNode = minHeap.get(parent);
        minHeap.set(index,parentNode);
        minHeap.set(parent,currNode);
    }

    private int parent(int index) {
        return index / 2;
    }

    @Override
    public boolean contains(T item) {
        T smallest = getSmallest();
        while (smallest != null){
            if(smallest.equals(item))
                return true;
            smallest = getSmallest();
        }
        return false;
    }

    @Override
    public T getSmallest() {
        return (T) minHeap.get(1).item;
    }

    @Override
    public T removeSmallest() {
        Node<T> current = minHeap.get(1);
        Node<T> latest = minHeap.get(lastItem-1);
        minHeap.set(1,latest);
        swimDown(1);
        lastItem--;
        return current.item;
    }

    private void swimDown(int index) {
        Node<T> firstChild = minHeap.get(index*2);
        Node<T> secondChild = minHeap.get(index*2+1);
        Node<T> curr = minHeap.get(index);
        if(curr.priority> firstChild.priority){
            swap(index,index*2);
            swimDown(index*2);
        }else if(curr.priority > secondChild.priority){
            swap(index,index*2+1);
            swimDown(index*2+1);
        }else
            return;
    }

    @Override
    public int size() {
        return lastItem-1;
    }

    @Override
    public void changePriority(T item, double priority) {

    }

    /**
     * @author : Samuel-Sorial
     * Class represents the node inside the heap.
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
