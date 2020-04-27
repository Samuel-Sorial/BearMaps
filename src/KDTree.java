import java.util.List;

public class KDTree implements PointSet {

    private Node root;
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;

    public KDTree(List<Point> list){
        for(Point p : list){
            root = addNode(p,root,HORIZONTAL);
        }
    }

    private Node addNode(Point pending,Node curr, boolean orientation){
        if(curr == null){
            return new Node(pending,orientation);
        }
        if(curr.p.equals(pending)){
            return curr;
        }
        int cmp = comparePoints(pending,curr.p,orientation);
        if(cmp < 0 ){
            curr.leftDown = addNode(pending,curr.leftDown,!orientation);
        }else{
            curr.rightUp = addNode(pending,curr.rightUp,!orientation);
        }
        return curr;
    }
    private int comparePoints(Point a, Point b, boolean orientation){
        if(orientation){
            return Double.compare(a.getY(),b.getY());
        }else{
            return Double.compare(a.getX(),b.getX());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return null;
    }
    private class Node{
        Point p;
        boolean orientation;
        private Node leftDown;
        private Node rightUp;

        private Node(Point p, boolean orentation){
            this.p = p;
            this.orientation = orentation;
        }
    }
}
