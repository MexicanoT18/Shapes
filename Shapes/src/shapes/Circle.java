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
public class Circle extends Shape{
    Point c;
    double r;
    Circle(){
        c = new Point();
        r = -1;
    }
    Circle(String aName) {
        super(aName);
    }
    Circle(Point _c, double _r){
        c = _c;
        r = _r;
    }
    Circle (Point _a, Point _b, Point _c){
	Point u = new Point(_b.subtract(_a).y, -_b.subtract(_a).x);
	Point v = new Point(_c.subtract(_a).y, -_c.subtract(_a).x);
	Point n = _c.subtract(_b).multiply(0.5);
	double t = u.crossProduct(n)/v.crossProduct(u);
        c = _a.sum(_c).multiply(0.5).sum(v.multiply(t));
	r = c.dist(_a);
    }
    public double area(){
        return Math.PI*r*r;
    }
    public double perimeter(){
        return 2.0*Math.PI*r;
    }
    public double chord(double rad) {
        return  2*r*Math.sin(rad/2.0);
    }
    public double sector(double rad) {
        return 0.5*rad*area()/Math.PI;
    }
}
