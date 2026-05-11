package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class FrmDrawing extends JFrame {

    private final PnlDrawing pnlDrawing = new PnlDrawing();

    private int   mode           = 0;
    private Point firstClick     = null;
    private Color pendingLineEdge = Color.BLACK;
    private DrawingObject selected = null;

    public FrmDrawing() {
        setTitle("Ostojic Djordje MH-22/2022");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        addToolButton(pnlToolbar, "Point",     1);
        addToolButton(pnlToolbar, "Line",      2);
        addToolButton(pnlToolbar, "Rectangle", 3);
        addToolButton(pnlToolbar, "Circle",    4);
        addToolButton(pnlToolbar, "Donut",     5);
        addToolButton(pnlToolbar, "Select",    6);
        pnlToolbar.add(new JSeparator(SwingConstants.VERTICAL));
        JButton btnModify = new JButton("Modify");
        JButton btnDelete = new JButton("Delete");
        btnModify.addActionListener(e -> {
			try {
				modify();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        btnDelete.addActionListener(e -> delete());
        pnlToolbar.add(btnModify);
        pnlToolbar.add(btnDelete);

        add(pnlToolbar, BorderLayout.NORTH);
        add(pnlDrawing,  BorderLayout.CENTER);

        pnlDrawing.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { handleClick(e); }
        });

        setLocationRelativeTo(null);
    }

    private void addToolButton(JPanel panel, String label, int modeValue) {
        JButton btn = new JButton(label);
        btn.addActionListener(e -> { mode = modeValue; firstClick = null; });
        panel.add(btn);
    }

    private void handleClick(MouseEvent e) {
        ArrayList<DrawingObject> objects = pnlDrawing.getObjects();

        switch (mode) {

            case 1: { // Point
                DlgPoint dlg = new DlgPoint(this, "Add Point");
                dlg.setX(e.getX());
                dlg.setY(e.getY());
                dlg.setVisible(true);
                if (dlg.isConfirmed()) {
                    Point p = new Point(e.getX(), e.getY(), false);
                    objects.add(new DrawingObject(p, dlg.getEdgeColor(), dlg.getFillColor()));
                }
                break;
            }

            case 2: { // Line
                if (firstClick == null) {
                    DlgLine dlg = new DlgLine(this, "Add Line - choose color");
                    dlg.setX1(e.getX()); dlg.setY1(e.getY());
                    dlg.setVisible(true);
                    if (dlg.isConfirmed()) {
                        firstClick      = new Point(e.getX(), e.getY());
                        pendingLineEdge = dlg.getEdgeColor();
                    }
                } else {
                    Line l = new Line(firstClick, new Point(e.getX(), e.getY()), false);
                    objects.add(new DrawingObject(l, pendingLineEdge, pendingLineEdge));
                    firstClick = null;
                }
                break;
            }

            case 3: { // Rectangle
                DlgRectangle dlg = new DlgRectangle(this, "Add Rectangle");
                dlg.setX(e.getX()); dlg.setY(e.getY());
                dlg.setVisible(true);
                if (dlg.isConfirmed()) {
                    Rectangle r = new Rectangle(
                        new Point(dlg.getXValue(), dlg.getYValue()),
                        dlg.getWidthValue(), dlg.getHeightValue(), false
                    );
                    objects.add(new DrawingObject(r, dlg.getEdgeColor(), dlg.getFillColor()));
                }
                break;
            }

            case 4: { // Circle
                DlgCircle dlg = new DlgCircle(this, "Add Circle");
                dlg.setX(e.getX()); dlg.setY(e.getY());
                dlg.setVisible(true);
                if (dlg.isConfirmed()) {
                    Circle c = new Circle(dlg.getRadius(),
                        new Point(dlg.getXValue(), dlg.getYValue()), false);
                    objects.add(new DrawingObject(c, dlg.getEdgeColor(), dlg.getFillColor()));
                }
                break;
            }

            case 5: { // Donut
                DlgDonut dlg = new DlgDonut(this, "Add Donut");
                dlg.setX(e.getX()); dlg.setY(e.getY());
                dlg.setVisible(true);
                if (dlg.isConfirmed()) {
                    Donut d = new Donut(
                        new Point(dlg.getXValue(), dlg.getYValue()),
                        dlg.getOuterRadius(), dlg.getInnerRadius(), false
                    );
                    objects.add(new DrawingObject(d, dlg.getEdgeColor(), dlg.getFillColor()));
                }
                break;
            }

            case 6: { // Select
                if (selected != null) { selected.setSelected(false); selected = null; }
                for (int i = objects.size() - 1; i >= 0; i--) {
                    DrawingObject obj = objects.get(i);
                    if (obj.getShape().contains(e.getX(), e.getY())) {
                        selected = obj;
                        selected.setSelected(true);
                        break;
                    }
                }
                break;
            }
        }
        pnlDrawing.repaint();
    }

    private void modify() throws Exception {
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a shape first.",
                "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Shape s = selected.getShape();

        if (s instanceof Donut) {
            Donut d = (Donut) s;
            DlgDonut dlg = new DlgDonut(this, "Modify Donut");
            dlg.setX(d.getCenter().getX());
            dlg.setY(d.getCenter().getY());
            dlg.setOuterRadius(d.getRadius());
            dlg.setInnerRadius(d.getInnerRadius());
            dlg.setEdgeColor(selected.getEdgeColor());
            dlg.setFillColor(selected.getFillColor());
            dlg.setVisible(true);
            if (dlg.isConfirmed()) {
                d.getCenter().moveTo(dlg.getXValue(), dlg.getYValue());
                d.setRadius(dlg.getOuterRadius());
                d.setInnerRadius(dlg.getInnerRadius());
                selected.setEdgeColor(dlg.getEdgeColor());
                selected.setFillColor(dlg.getFillColor());
            }

        } else if (s instanceof Circle) {
            Circle c = (Circle) s;
            DlgCircle dlg = new DlgCircle(this, "Modify Circle");
            dlg.setX(c.getCenter().getX());
            dlg.setY(c.getCenter().getY());
            dlg.setRadius(c.getRadius());
            dlg.setEdgeColor(selected.getEdgeColor());
            dlg.setFillColor(selected.getFillColor());
            dlg.setVisible(true);
            if (dlg.isConfirmed()) {
                c.getCenter().moveTo(dlg.getXValue(), dlg.getYValue());
                try { c.setRadius(dlg.getRadius()); } catch (Exception ex) { /* validated */ }
                selected.setEdgeColor(dlg.getEdgeColor());
                selected.setFillColor(dlg.getFillColor());
            }

        } else if (s instanceof Rectangle) {
            Rectangle r = (Rectangle) s;
            DlgRectangle dlg = new DlgRectangle(this, "Modify Rectangle");
            dlg.setX(r.getUpperLeft().getX());
            dlg.setY(r.getUpperLeft().getY());
            dlg.setWidth(r.getWidth());
            dlg.setHeight(r.getHeight());
            dlg.setEdgeColor(selected.getEdgeColor());
            dlg.setFillColor(selected.getFillColor());
            dlg.setVisible(true);
            if (dlg.isConfirmed()) {
                r.getUpperLeft().moveTo(dlg.getXValue(), dlg.getYValue());
                r.setWidth(dlg.getWidthValue());
                r.setHeight(dlg.getHeightValue());
                selected.setEdgeColor(dlg.getEdgeColor());
                selected.setFillColor(dlg.getFillColor());
            }

        } else if (s instanceof Line) {
            Line l = (Line) s;
            DlgLine dlg = new DlgLine(this, "Modify Line");
            dlg.setX1(l.getStartPoint().getX());
            dlg.setY1(l.getStartPoint().getY());
            dlg.setX2(l.getEndPoint().getX());
            dlg.setY2(l.getEndPoint().getY());
            dlg.setEdgeColor(selected.getEdgeColor());
            dlg.setVisible(true);
            if (dlg.isConfirmed()) {
                l.getStartPoint().moveTo(dlg.getX1Value(), dlg.getY1Value());
                l.getEndPoint().moveTo(dlg.getX2Value(), dlg.getY2Value());
                selected.setEdgeColor(dlg.getEdgeColor());
                selected.setFillColor(dlg.getFillColor());
            }

        } else if (s instanceof Point) {
            Point p = (Point) s;
            DlgPoint dlg = new DlgPoint(this, "Modify Point");
            dlg.setX(p.getX());
            dlg.setY(p.getY());
            dlg.setEdgeColor(selected.getEdgeColor());
            dlg.setVisible(true);
            if (dlg.isConfirmed()) {
                p.moveTo(dlg.getXValue(), dlg.getYValue());
                selected.setEdgeColor(dlg.getEdgeColor());
                selected.setFillColor(dlg.getFillColor());
            }
        }

        pnlDrawing.repaint();
    }

    private void delete() {
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a shape first.",
                "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int answer = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the selected shape?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            pnlDrawing.getObjects().remove(selected);
            selected = null;
            pnlDrawing.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmDrawing().setVisible(true));
    }
}
