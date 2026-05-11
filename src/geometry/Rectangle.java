package geometry;

import java.awt.Graphics;

public class Rectangle extends Shape{
	 
	private Point upperLeft;
	private int width;
	private int height;
	
	
	@Override
	public String toString() {
		return "Upper left point: (" + this.upperLeft.getX() + ", " + this.upperLeft.getY() + "), width = " + this.width + ", height = " + this.height;
	}
	
	@Override
	public boolean equals(Object o) {
	    	 if(o instanceof Rectangle) {
	    		 Rectangle temp = (Rectangle) o;
	    		 if(width == temp.getWidth() && height == temp.getHeight()) {
	    			 return true;
	    		 }
	    		 
	    	 }
	    	 return false;
	}
	
	@Override
	public void moveTo(int x, int y) {
		upperLeft.moveTo(x, y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
	}
	
	@Override
	public int compareTo(Shape o) {
		if(o instanceof Rectangle) {
		 Rectangle temp = (Rectangle) o;
		 return this.area() - temp.area();
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
	    return((x>=this.upperLeft.getX() && x<=(this.upperLeft.getX()+width)) && 
	    		(y>=this.upperLeft.getY() && y<=(this.upperLeft.getY()+height)));
	}
	
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeft, int width, int height) {
		this.upperLeft = upperLeft;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Point upperLeft, int width, int height, boolean selected) {
		this(upperLeft, width, height);
		this.selected = selected;
	}
	
	public int area() {
		return this.width * this.height;
	}
	
	public double circumference() {
	   return 2 * this.width + 2 * this.height;	
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, height);
		
	}
	
	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
}
