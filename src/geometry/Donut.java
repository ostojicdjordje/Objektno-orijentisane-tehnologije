package geometry;

import java.awt.Graphics;

public class Donut extends Circle{
	
	private int innerRadius;
	
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(radius, center);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.selected = selected;
	}
	
	public double area() {
		return super.area() - this.innerRadius*this.innerRadius*Math.PI;
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		g.drawOval(center.getX()-innerRadius, center.getY()-innerRadius, innerRadius*2, innerRadius*2);
	}
	
	@Override
	public String toString() {
	   return  super.toString() + ", inner radius = " + this.innerRadius;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Donut) {
			Donut temp = (Donut) o;
			if(this.radius == temp.radius && this.innerRadius == temp.innerRadius) {
				return true;
			}
		}
	  return false;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return super.contains(x,y) && center.distance(new Point(x,y)) >= innerRadius;
	}
	
	@Override
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	

}
