package vezbe;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

public class Vezbe {

	public static void main(String[] args) {
		
		
		Point p1 = new Point();    
        Point p2 = new Point();               
        p1.setX(40);
        p2.setX(20);
        p1.setY(80);
        p2.setY(0);
        Rectangle r1 = new Rectangle();
        r1.setWidth(10);
        r1.setHeight(30);
        System.out.println(r1.area());
        System.out.println(r1.circumference());
        
        Circle c1 = new Circle();
        try {
			c1.setRadius(20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(c1.area());
        System.out.println(c1.circumference());
        Point p3 = new Point(40,80,true);
        System.out.println(p3.getX());
        Line l3 = new Line(p3,p1,true);
        System.out.println(l3.length());
        Rectangle r2 = new Rectangle(p3, 100, 200, false);
        Circle c2 = new Circle(50, new Point(30, 20), false);
        System.out.println(p3);
        System.out.println(l3);
        System.out.println(r2);  
        System.out.println(c2);
        Object testObject = new Circle(70, p3);
        Object testObject1 = new Rectangle(p2, 200, 200, false);
        System.out.println(testObject1);
        Line l4 = new Line();
        l4.setStartPoint(p1);
        l4.setEndPoint(p2);
        System.out.println(l4);
        System.out.println(p1.equals(l4));
        Donut d1 = new Donut(p1, 20, 30, true);
        System.out.println(d1 instanceof Circle);
        System.out.println(d1.getCenter());
        System.out.println(d1.getRadius());
        System.out.println(d1);
        System.out.println(d1.equals(c1));
        
        
	}

}
