package gui;

import geometry.Shape;
import java.awt.Color;

public class DrawingObject {

    private Shape shape;
    private Color edgeColor;
    private Color fillColor;
    private boolean selected;

    public DrawingObject(Shape shape, Color edgeColor, Color fillColor) {
        this.shape     = shape;
        this.edgeColor = edgeColor;
        this.fillColor = fillColor;
        this.selected  = false;
    }



    public Shape getShape()             { return shape; }
    public void  setShape(Shape shape)  { this.shape = shape; }

    public Color getEdgeColor()                { return edgeColor; }
    public void  setEdgeColor(Color edgeColor) { this.edgeColor = edgeColor; }

    public Color getFillColor()                { return fillColor; }
    public void  setFillColor(Color fillColor) { this.fillColor = fillColor; }

    public boolean isSelected()               { return selected; }
    public void    setSelected(boolean sel)   { this.selected = sel; }
}
