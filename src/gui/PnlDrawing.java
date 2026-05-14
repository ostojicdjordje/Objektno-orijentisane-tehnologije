package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import geometry.*;
import geometry.Point;
import geometry.Rectangle;


public class PnlDrawing extends JPanel {

    private ArrayList<DrawingObject> objects = new ArrayList<>();

    public PnlDrawing() {
        setBackground(Color.WHITE);
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (DrawingObject obj : objects) {
            Shape s = obj.getShape();

            
            g.setColor(obj.getFillColor());
            if (s instanceof Donut) {
                Donut d = (Donut) s;
                g.fillOval(
                    d.getCenter().getX() - d.getRadius(),
                    d.getCenter().getY() - d.getRadius(),
                    d.getRadius() * 2,
                    d.getRadius() * 2
                );
                g.setColor(getBackground());
                g.fillOval(
                    d.getCenter().getX() - d.getInnerRadius(),
                    d.getCenter().getY() - d.getInnerRadius(),
                    d.getInnerRadius() * 2,
                    d.getInnerRadius() * 2
                );
            } else if (s instanceof Circle) {
                Circle c = (Circle) s;
                g.fillOval(
                    c.getCenter().getX() - c.getRadius(),
                    c.getCenter().getY() - c.getRadius(),
                    c.getRadius() * 2,
                    c.getRadius() * 2
                );
            } else if (s instanceof Rectangle) {
                Rectangle r = (Rectangle) s;
                g.fillRect(
                    r.getUpperLeft().getX(),
                    r.getUpperLeft().getY(),
                    r.getWidth(),
                    r.getHeight()
                );
            }

          
            g.setColor(obj.getEdgeColor());
            s.draw(g);   

           
            if (obj.isSelected()) {
                g.setColor(Color.BLUE);
                if (s instanceof Circle) {
                    Circle c = (Circle) s;
                    int pad = 4;
                    g.drawOval(
                        c.getCenter().getX() - c.getRadius() - pad,
                        c.getCenter().getY() - c.getRadius() - pad,
                        (c.getRadius() + pad) * 2,
                        (c.getRadius() + pad) * 2
                    );
                } else if (s instanceof Rectangle) {
                    Rectangle r = (Rectangle) s;
                    int pad = 4;
                    g.drawRect(
                        r.getUpperLeft().getX() - pad,
                        r.getUpperLeft().getY() - pad,
                        r.getWidth()  + pad * 2,
                        r.getHeight() + pad * 2
                    );
                } else if (s instanceof Line) {
                    Line l = (Line) s;
                    g.fillOval(l.getStartPoint().getX() - 4, l.getStartPoint().getY() - 4, 8, 8);
                    g.fillOval(l.getEndPoint().getX()   - 4, l.getEndPoint().getY()   - 4, 8, 8);
                } else if (s instanceof Point) {
                    Point p = (Point) s;
                    g.drawOval(p.getX() - 6, p.getY() - 6, 12, 12);
                }
            }
        }
    }

    public ArrayList<DrawingObject> getObjects() { return objects; }
    public void setObjects(ArrayList<DrawingObject> objects) { this.objects = objects; }
}
