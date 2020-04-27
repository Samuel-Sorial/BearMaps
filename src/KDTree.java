import java.util.List;

public class KDTree implements PointSet {

    private Node root;

    public KDTree(List<Point> list){
        for(Point p : list){
            Node pendingNode = new Node(p.getX(),p.getY());
            root = addNode('x',pendingNode,root);
        }
    }

    private Node addNode(char xOrY, Node pending,Node curr){
        if(curr == null)
            return pending;
        else if(curr.x == pending.x && curr.y == pending.y){
            return curr;
        }
        else if(xOrY == 'x'){
            if(pending.x >= curr.x){
                curr.more = addNode(flipDimension(xOrY),pending,curr.more);
            }
            else{
                curr.less = addNode(flipDimension(xOrY),pending,curr.less);
            }
        }else{
            if(pending.y >= curr.y){
                curr.more = addNode(flipDimension(xOrY),pending,curr.more);
            }else{
                curr.less = addNode(flipDimension(xOrY),pending,curr.less);
            }
        }
        return curr;
    }

    private char flipDimension(char xOrY){
        if(xOrY == 'x')
            return 'y';
        else
            return 'x';
    }
    @Override
    public Point nearest(double x, double y) {
        return null;
    }
    private class Node{
        private double x;
        private double y;
        private Node less;
        private Node more;

        private Node(double x, double y){
            this.x = x;
            this.y = y;
            less = null;
            more = null;
        }
    }
}
