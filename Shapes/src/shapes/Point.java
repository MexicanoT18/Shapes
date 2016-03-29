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
public class Point {
    public double EPS = 0.00001;
    public double x, y;
    public Point() { x = y = 0.0; }
    public Point(double _x, double _y){
        x = _x;
        y = _y;
    }
    public double norm(){
	return Math.sqrt(x*x + y*y);
    }
    public Point multiply(double k){
        return new Point(x*k, y*k);
    }
    public Point sum(Point b){
        return new Point(x + b.x, y + b.y);
    }
    public Point subtract(Point b){
        return new Point(x - b.x, y - b.y);
    }
    public Point normalized(){
	return new Point(x,y).multiply(1.0/norm());
    }
    public double dist(Point b) {
	return this.subtract(b).norm();
    }
    public double innerProduct(Point b) { 
        return x*b.x + y*b.y;
    }
    public double crossProduct(Point b) {
        return x*b.y - y*b.x;
    }
    public boolean ccw(Point q, Point r) {  
        return q.subtract(this).crossProduct(r.subtract(this)) > 0.0;
    }
    public boolean collinear(Point q, Point r) {
        return Math.abs(q.subtract(this).crossProduct(r.subtract(this))) < EPS;
    }
    public Point rotate(double rad) {
            return new Point(x * Math.cos(rad) - y * Math.sin(rad),
            x * Math.sin(rad) + y * Math.cos(rad));
    }
    public double angle(Point a, Point b) {
        return Math.acos(a.subtract(this).innerProduct(b.subtract(this))) / (this.dist(a)*this.dist(b));
    }
    public Point proj(Point v){
        return v.multiply(this.innerProduct(v)/v.innerProduct(v));
    }
    public Point pointClosestToLineSegment(Point a, Point b) {
        double u = (this.subtract(a)).innerProduct(b.subtract(a)) / b.subtract(a).innerProduct(b.subtract(a));
        if (u < 0.0) return a;
        if (u > 1.0) return b;
        return a.sum(b.subtract(a).multiply(u));
    }
    double distToLineSegment(Point a, Point b) {
	return this.dist(pointClosestToLineSegment(a, b));
    }
}
