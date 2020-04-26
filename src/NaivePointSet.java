import java.util.HashSet;
import java.util.List;
import java.util.List.*;
public class NaivePointSet implements  PointSet{

    private HashSet<Point> pointSet;

    public NaivePointSet(List<Point> points){
        pointSet = new HashSet<>();
        this.pointSet.addAll(points);
    }

    @Override
    public Point nearest(double x, double y) {
        double nearestDistance = 0;
        Point nearestPoint = null;
        for(Point p : this.pointSet){
            double deltaXSquared = (p.getX() - x) * (p.getX() - x);
            double deltaYSquared = (p.getY() - y) * (p.getY() - y);
            double currDistance = Math.abs(Math.sqrt(deltaXSquared + deltaYSquared));
            if(nearestPoint == null){
                nearestDistance = currDistance;
                nearestPoint = p;
            }
            if(currDistance < nearestDistance){
                nearestDistance = currDistance;
                nearestPoint = p;
            }
        }
        return nearestPoint;
    }
}
