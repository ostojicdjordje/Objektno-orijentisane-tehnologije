package geometry;

import java.awt.Graphics;

public class Circle extends Shape{
	
	protected int radius;
	protected Point center;
	
	
	@Override
	public String toString() {
		return "Center: (" + this.center.getX() + ", " + this.center.getY() + "), radius = "
				+ this.radius;
	}
	
	@Override
	public boolean equals(Object o) {
	    	 if(o instanceof Circle) {
	    		 Circle temp = (Circle) o;
	    		 if(radius == temp.getRadius()) {
	    			 return true;
	    		 }
	    	 }
	    	 return false;
	}
	
	public boolean contains(int x, int y) {
		Point click = new Point(x,y);
		return center.distance(click)<= radius;
	}
	
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	

	@Override
	public void draw(Graphics g) {
		g.drawOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
		
	}
	
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}
	
	@Override
	public int compareTo(Shape o) {
		if(o instanceof Circle) {
		 Circle temp = (Circle) o;
		 return (int)(this.area() - temp.area());
		}
		return 0;
	}
	
	public Circle() {
		
	}
	
	public Circle(int radius, Point center) {
		this.radius = radius;
		this.center = center;
	}
	
	public Circle(int radius, Point center, boolean selected) {
		this(radius,center);
		this.selected = selected;
	}
	
	public double area() {
		return this.radius * this.radius * Math.PI;
	}
	
	public double circumference() {
		return 2 * this.radius * Math.PI;
	}


	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) throws Exception {
		if(radius<=0) {
			throw new Exception("Radius ne moze biti manji ili jednak nuli");
		}else {
			this.radius = radius;
		}
	}


	public Point getCenter() {
		return center;
	}


	public void setCenter(Point center) {
		this.center = center;
	}
}
