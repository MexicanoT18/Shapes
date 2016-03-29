/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

/**
 *
 * @author Lucas
 */
public class Triangle extends Polygon{
    Point a, b, c;
    Triangle(String aName) {
        super(aName);
    }
    Triangle() { a = b = c = new Point(0,0); }
    Triangle(Point _a, Point _b, Point _c) {
        a = _a;
        b = _b;
        c = _c;
    }
    public double perimeter() { return a.dist(b) + b.dist(c) + c.dist(a); }
    public double semiPerimeter() { return perimeter()/2.0; }
    public double area() {
        double s = semiPerimeter(), ab = a.dist(b), bc = b.dist(c), ca = c.dist(a);
	return Math.sqrt(s*(s-ab)*(s-bc)*(s-ca));
    }
    public double inRadius() {
	return area()/semiPerimeter();
    }
    public Circle inCircle() {
        Circle ans = new Circle();
	ans.r = inRadius();
	if (Math.abs(ans.r) < EPS) return new Circle(b, 0);
            Line l1, l2;
            double ratio = a.dist(b) / a.dist(c);
            Point p = b.sum(c.subtract(b).multiply(ratio / (1 + ratio)));
            l1 = new Line(a, p);
            ratio = b.dist(a) / b.dist(c);
            p = a.sum(c.subtract(a).multiply(ratio / (1 + ratio)));
            l2 = new Line(b, p);
            ans.c = l1.intersection(l2);
	return ans;
    }
    public double circumRadius() {
        return a.dist(b)*b.dist(c)*c.dist(a)/(4.0*area());
    }
    public Circle circumCircle(){
	return new Circle(a,b,c);
    }
    public int pointRelative(Point p){
	double u = b.subtract(a).crossProduct(p.subtract(a))*b.subtract(a).crossProduct(c.subtract(a));
        double v = c.subtract(b).crossProduct(p.subtract(b))*c.subtract(b).crossProduct(a.subtract(b));
	double w = a.subtract(c).crossProduct(p.subtract(c))*a.subtract(c).crossProduct(b.subtract(c));
	if (Math.abs(u) < EPS || Math.abs(v) < EPS || Math.abs(w) < EPS) return 1;
	else if (u > 0.0 && v > 0.0 && w > 0.0) return 0;
	else return 2;
    } //0 = inside/ 1 = border/ 2 = outside
    public boolean isInside(Point p){
        return this.pointRelative(p)==0;
    }
    public boolean isAtBorder(Point p){
        return this.pointRelative(p)==1;
    }
    public boolean isOutside(Point p){
        return this.pointRelative(p)==2;
    }
}
