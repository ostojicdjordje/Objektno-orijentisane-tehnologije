package stack;

import javax.swing.*;
import java.awt.*;
import geometry.Circle;
import geometry.Point;

public class Dialog extends JDialog {
    private JTextField txtX = new JTextField();
    private JTextField txtY = new JTextField();
    private JTextField txtR = new JTextField();
    private Circle circle = null;
    private boolean isOk = false;

    public Dialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        setLayout(new BorderLayout());

        
        JPanel pnlCenter = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlCenter.add(new JLabel("X coordinate:"));
        pnlCenter.add(txtX);
        pnlCenter.add(new JLabel("Y coordinate:"));
        pnlCenter.add(txtY);
        pnlCenter.add(new JLabel("Radius:"));
        pnlCenter.add(txtR);

        JPanel pnlButtons = new JPanel();
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        btnOk.addActionListener(e -> {
            try {
                int x = Integer.parseInt(txtX.getText());
                int y = Integer.parseInt(txtY.getText());
                int r = Integer.parseInt(txtR.getText());

             
                if (r <= 0 || x <= 0 || y <= 0) {
                    JOptionPane.showMessageDialog(this, "Values must be greater than zero!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

               
                circle = new Circle(r, new Point(x, y));
                isOk = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
        
        pnlButtons.add(btnOk);
        pnlButtons.add(btnCancel);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(parent);
    }

   
    public void setCircleValues(Circle c) {
        txtX.setText(String.valueOf(c.getCenter().getX()));
        txtY.setText(String.valueOf(c.getCenter().getY()));
        txtR.setText(String.valueOf(c.getRadius()));
        
        txtX.setEditable(false);
        txtY.setEditable(false);
        txtR.setEditable(false);
    }

    public Circle getCircle() { return circle; }
    public boolean isOk() { return isOk; }
}