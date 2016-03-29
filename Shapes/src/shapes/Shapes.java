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
public class Shapes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Point A = new Point(0,0);
        Point B = new Point(4,0);
        Point C = new Point(0,3);
        Circle cir = new Circle(A, B, C);
        System.out.println("circumcentro = (" + cir.c.x + "," + cir.c.y + ") circumraio = " + cir.r);
        Triangle tri = new Triangle(A,B,C);
        cir = tri.inCircle();
        System.out.println("incentro = (" + cir.c.x + "," + cir.c.y + ") inraio = " + cir.r);
        System.out.println("dist = " + A.distToLineSegment(C, B));
    }
    
}
