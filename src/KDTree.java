import java.util.List;
import java.util.Map;

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
        Point p = new Point(x,y);
        return nearest(root, p, root).p;
    }

    private Node nearest(Node curr, Point goal, Node best) {
        if (curr == null) {
            return best;
        }
        if (best.distanceTo(goal) > curr.distanceTo(goal)) {
            best = curr;
        }
        Node goodSide = null;
        Node badSide = null;
        int comp = comparePoints(goal, curr.p, curr.orientation);
        if (comp < 0) {
            goodSide = curr.leftDown;
            badSide = curr.rightUp;
        } else {
            goodSide = curr.rightUp;
            badSide = curr.leftDown;
        }
        best = nearest(goodSide, goal, best);
        best = nearest(badSide,goal,best);
        return best;
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
        private double distanceTo(Point another){
            double deltaX = another.getX() - this.p.getX();
            double deltaY = another.getY() - this.p.getY();
            double distance = Math.sqrt(Math.abs(deltaX*deltaX - deltaY * deltaY));
            return distance;
        }
    }
}
