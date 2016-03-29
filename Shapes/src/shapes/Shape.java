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
public class Shape {
    public double EPS = 0.00001;
    private String name;
    Shape() {
        
    }
    Shape(String aName) {
        name = aName;
    }
    public void setName(String str){
        name = str;
    }
    public String getName(){
        return name;
    }
    public double area(){
        return 0.0;
    }
    public double perimeter(){
        return 0.0;
    }
}
