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
public class Line extends Shape {
    public double a, b, c;
    Line(String aName) {
        super(aName);
    }
    Line() {
        a = b = c = 0;
    }
    Line(double _a, double _b, double _c) {
        a = _a;
        b = _b;
        c = _c;
    }
    Line(Point p1, Point p2){
	if (Math.abs(p1.x - p2.x) < EPS && Math.abs(p1.y - p2.y) < EPS){
		a = b = c = 0;
	}
	else if (Math.abs(p1.x - p2.x) < EPS) {
		a = 1.0;
                b = 0.0;
                c = -p1.x;
	}
	else {
		a = -(p1.y - p2.y) / (p1.x - p2.x);
		b = 1.0;
		c = -(a * p1.x) - p1.y;
	}
    }
    public boolean areParallel(Line l) {
	return (Math.abs(a-l.a) < EPS) && (Math.abs(b-l.b) < EPS);
    }
    public boolean areSame(Line l) {
            return areParallel(l) && (Math.abs(c - l.c) < EPS);
    }
    public Point intersection(Line l) {
	if (this.areParallel(l)) return null;
	Point p = new Point();
	p.x = (l.b * c - b * l.c) / (l.a * b - a * l.b);
	if (Math.abs(b) > EPS) p.y = -(a * p.x + c);
	else p.y = -(l.a * p.x + l.c);
	return p;
    }
    public Point projPointToLine(Point u){
	Point p1, p2;
	if (Math.abs(b-1.0)<EPS){
		p1 = new Point(-c/a, 0.0);
		p2 = new Point(-c/a, 1.0);
	}
	else{
		p1 = new Point(0, -c/b);
		p2 = new Point(1, -(c+1.0)/b);
	}
	return p1.sum(u.subtract(p1).proj(p2.subtract(p1)));
    }
    public double distToLine(Point p) {
	return p.dist(this.projPointToLine(p));
    }
}
