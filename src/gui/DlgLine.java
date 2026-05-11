package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DlgLine extends JDialog {

    private JTextField txtX1         = new JTextField(8);
    private JTextField txtY1         = new JTextField(8);
    private JTextField txtX2         = new JTextField(8);
    private JTextField txtY2         = new JTextField(8);
    private JButton    btnEdgeColor  = new JButton("Choose...");
    private Color      edgeColor     = Color.BLACK;
    private boolean    confirmed     = false;

    public DlgLine(Frame parent, String title) {
        super(parent, title, true);
        buildUi();
    }

    private void buildUi() {
        JPanel pnlFields = new JPanel(new GridLayout(5, 2, 8, 6));
        pnlFields.setBorder(new EmptyBorder(12, 12, 8, 12));

        pnlFields.add(new JLabel("Start X:"));  pnlFields.add(txtX1);
        pnlFields.add(new JLabel("Start Y:"));  pnlFields.add(txtY1);
        pnlFields.add(new JLabel("End X:"));    pnlFields.add(txtX2);
        pnlFields.add(new JLabel("End Y:"));    pnlFields.add(txtY2);
        pnlFields.add(new JLabel("Color:"));
        updateColorButton(btnEdgeColor, edgeColor);
        btnEdgeColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Line Color", edgeColor);
            if (c != null) { edgeColor = c; updateColorButton(btnEdgeColor, edgeColor); }
        });
        pnlFields.add(btnEdgeColor);

        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOk     = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        btnOk.addActionListener(e -> { confirmed = true; dispose(); });
        btnCancel.addActionListener(e -> dispose());
        pnlBtns.add(btnOk);
        pnlBtns.add(btnCancel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnlFields, BorderLayout.CENTER);
        getContentPane().add(pnlBtns,   BorderLayout.SOUTH);
        pack();
        setMinimumSize(new Dimension(280, 220));
        setLocationRelativeTo(getParent());
    }

    private void updateColorButton(JButton btn, Color c) {
        btn.setBackground(c);
        btn.setForeground(isDark(c) ? Color.WHITE : Color.BLACK);
    }
    private boolean isDark(Color c) {
        return (0.299*c.getRed()+0.587*c.getGreen()+0.114*c.getBlue()) < 128;
    }

    public void setX1(int v)          { txtX1.setText(String.valueOf(v)); }
    public void setY1(int v)          { txtY1.setText(String.valueOf(v)); }
    public void setX2(int v)          { txtX2.setText(String.valueOf(v)); }
    public void setY2(int v)          { txtY2.setText(String.valueOf(v)); }
    public void setEdgeColor(Color c) { edgeColor = c; updateColorButton(btnEdgeColor, c); }

    public boolean isConfirmed()  { return confirmed; }
    public int     getX1Value()   { return Integer.parseInt(txtX1.getText().trim()); }
    public int     getY1Value()   { return Integer.parseInt(txtY1.getText().trim()); }
    public int     getX2Value()   { return Integer.parseInt(txtX2.getText().trim()); }
    public int     getY2Value()   { return Integer.parseInt(txtY2.getText().trim()); }
    public Color   getEdgeColor() { return edgeColor; }
    public Color   getFillColor() { return edgeColor; }
}
