package geometry;

import java.awt.Graphics;

public abstract class Shape implements Moveable,Comparable<Shape>{
   
	protected boolean selected;
	
	public Shape() {
		
	}
	
	public abstract boolean contains(int x, int y);
	
	public abstract void draw(Graphics g);
	
	public Shape(boolean selected){
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
