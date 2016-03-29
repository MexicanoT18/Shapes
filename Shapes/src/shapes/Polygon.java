/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;
import java.util.Vector;

/**
 *
 * @author Lucas
 */
public class Polygon extends Shape{
    private Vector<Point> P;
    Polygon(){
        P.clear();
    }
    Polygon(String aName) {
        super(aName);
        P.clear();
    }
    Polygon(Vector<Point> Q){
        P.clear();
        for(int i=0; i<Q.size(); i++){
            P.addElement(Q.elementAt(i));
	}
	if (!Q.isEmpty() && !(Q.lastElement() == Q.firstElement()))
            P.addElement(Q.elementAt(0));
    }
    public double perimeter(){
	double result = 0.0;
	for (int i = 0; i < P.size() - 1; i++) result += P.elementAt(i).dist(P.elementAt(i+1));
	return result;
    }
    public double area() {
	double result = 0.0;
	for (int i = 0; i < P.size()-1; i++) {
            result += (P.elementAt(i).x*P.elementAt(i+1).y - P.elementAt(i+1).x*P.elementAt(i).y);
	}
	return Math.abs(result) / 2.0;
    }
    public boolean isConvex() {
	int sz = P.size();
	if (sz <= 3) return false;
	boolean isLeft = P.elementAt(0).ccw(P.elementAt(1), P.elementAt(2));
	for (int i = 1; i < sz-1; i++){
            if (P.elementAt(i).ccw(P.elementAt(i+1), P.elementAt((i+2) == sz ? 1 : i+2)) != isLeft)
		return false;
        }
        return true;
    }
    public boolean inPolygon(Point pt) {
	if (P.isEmpty()) return false;
	double sum = 0;
	for (int i = 0; i < P.size()-1; i++) {
            if (pt.ccw(P.elementAt(i), P.elementAt(i+1))) sum += pt.angle(P.elementAt(i), P.elementAt(i+1));
		else sum -= pt.angle(P.elementAt(i), P.elementAt(i+1));
	}
	return Math.abs(Math.abs(sum) - 2*Math.PI) < EPS;
    }
    public Point lineIntersectSeg(Point p, Point q, Point A, Point B) {
	double a = B.y - A.y;
	double b = A.x - B.x;
	double c = B.x * A.y - A.x * B.y;
	double u = Math.abs(a * p.x + b * p.y + c);
	double v = Math.abs(a * q.x + b * q.y + c);
	return new Point((p.x * v + q.x * u) / (u+v), (p.y * v + q.y * u) / (u+v));
    }
    public Polygon cutPolygon(Point a, Point b) {
        Vector<Point> R = new Vector<>();
        double left1, left2;
        for (int i = 0; i < (int)P.size(); i++) {
            left1 = b.subtract(a).crossProduct(P.elementAt(i).subtract(a)); left2 = 0;
            if (i != (int)P.size()-1) left2 = b.subtract(a).crossProduct(P.elementAt(i+1).subtract(a));
            if (left1 > -EPS) P.addElement(P.elementAt(i));
            if (left1 * left2 < -EPS)
            	R.addElement(lineIntersectSeg(P.elementAt(i), P.elementAt(i+1), a, b));
	}
	return new Polygon(R);
    }
}
