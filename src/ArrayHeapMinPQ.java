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
        lastItem = 1;
    }

    @Override
    public void add(T item, double priority) {

    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public T getSmallest() {
        return null;
    }

    @Override
    public T removeSmallest() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void changePriority(T item, double priority) {

    }

    /**
     * @author : Samuel-Sorial
     * Class represents the node inside the heap.
     */
    private class Node{
        private Node left;
        private Node right;
        String item;
        double priority;

        public Node(Node l, Node r, String s, double pr){
            this.left = l;
            this.right = r;
            this.item = s;
            this.priority = pr;
        }
    }
}
