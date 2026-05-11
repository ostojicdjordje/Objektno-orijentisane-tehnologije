package geometry;

import java.awt.Graphics;

public class Line extends Shape{
	
     private Point startPoint;
     private Point endPoint;
     
     
     @Override
     public String toString() {
    	 return "Line from (" + this.startPoint.getX() + ", " + this.startPoint.getY() + ")" + " --> " + "(" + this.endPoint.getX() + ", " + this.endPoint.getY() + ")";
     }
     
     @Override
     public boolean equals(Object o) {
    	 if(o instanceof Line) {
    		 Line temp = (Line) o;
    		 if(startPoint.equals(temp.getStartPoint()) && endPoint.equals(temp.getEndPoint())) {
    			 return true;
    		 }
    		 
    	 }
    	 return false;
     }
     
     @Override
 	public int compareTo(Shape o) {
 		if(o instanceof Line) {
 		 Line temp = (Line) o;
 		 return (int)(this.length() - temp.length());
 		}
 		return 0;
 	}
     
     public Line() {
    	 
     }
     
     public Line(Point startPoint, Point endPoint) {
    	 this.startPoint = startPoint;
    	 this.endPoint = endPoint;
     }
     
     public Line(Point startPoint, Point endPoint, boolean selected) {
    	 this(startPoint,endPoint);
    	 this.selected = selected;
     }
     
     public double length() {
    	 return startPoint.distance(endPoint);
     }
     
     @Override
 	public void draw(Graphics g) {
 		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
 		
 	}
     
     @Override
 	public void moveTo(int x, int y) {
 		return;
 	}

 	@Override
 	public void moveBy(int byX, int byY) {
 		startPoint.moveBy(byX, byY);
 		endPoint.moveBy(byX, byY);
 	}
     
     public boolean contains(int x, int y) {
    	 Point click = new Point(x,y);
    	 return(startPoint.distance(click) + endPoint.distance(click)) - length()<=2;
     }
     
     public Point getStartPoint() {
    	 return startPoint;
     }
     
     public void setStartPoint(Point startPoint) {
    	 this.startPoint = startPoint;
     }

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	} 
     
}
