package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DlgDonut extends JDialog {

    private JTextField txtX          = new JTextField(8);
    private JTextField txtY          = new JTextField(8);
    private JTextField txtOuter      = new JTextField(8);
    private JTextField txtInner      = new JTextField(8);
    private JButton    btnEdgeColor  = new JButton("Choose...");
    private JButton    btnFillColor  = new JButton("Choose...");
    private Color      edgeColor     = Color.BLACK;
    private Color      fillColor     = Color.WHITE;
    private boolean    confirmed     = false;

    public DlgDonut(Frame parent, String title) {
        super(parent, title, true);
        buildUi();
    }

    private void buildUi() {
        JPanel pnlFields = new JPanel(new GridLayout(6, 2, 8, 6));
        pnlFields.setBorder(new EmptyBorder(12, 12, 8, 12));

        pnlFields.add(new JLabel("Center X:"));      pnlFields.add(txtX);
        pnlFields.add(new JLabel("Center Y:"));      pnlFields.add(txtY);
        pnlFields.add(new JLabel("Outer radius:"));  pnlFields.add(txtOuter);
        pnlFields.add(new JLabel("Inner radius:"));  pnlFields.add(txtInner);

        pnlFields.add(new JLabel("Edge color:"));
        updateColorButton(btnEdgeColor, edgeColor);
        btnEdgeColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Edge Color", edgeColor);
            if (c != null) { edgeColor = c; updateColorButton(btnEdgeColor, edgeColor); }
        });
        pnlFields.add(btnEdgeColor);

        pnlFields.add(new JLabel("Fill color:"));
        updateColorButton(btnFillColor, fillColor);
        btnFillColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Fill Color", fillColor);
            if (c != null) { fillColor = c; updateColorButton(btnFillColor, fillColor); }
        });
        pnlFields.add(btnFillColor);

        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOk     = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        btnOk.addActionListener(e -> { if (validateInput()) { confirmed = true; dispose(); } });
        btnCancel.addActionListener(e -> dispose());
        pnlBtns.add(btnOk);
        pnlBtns.add(btnCancel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnlFields, BorderLayout.CENTER);
        getContentPane().add(pnlBtns,   BorderLayout.SOUTH);
        pack();
        setMinimumSize(new Dimension(300, 280));
        setLocationRelativeTo(getParent());
    }

    private boolean validateInput() {
        try {
            int outer = Integer.parseInt(txtOuter.getText().trim());
            int inner = Integer.parseInt(txtInner.getText().trim());
            Integer.parseInt(txtX.getText().trim());
            Integer.parseInt(txtY.getText().trim());
            if (outer <= 0 || inner <= 0) {
                JOptionPane.showMessageDialog(this, "Both radii must be > 0!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (inner >= outer) {
                JOptionPane.showMessageDialog(this, "Inner radius must be smaller than outer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid integer values!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void updateColorButton(JButton btn, Color c) {
        btn.setBackground(c);
        btn.setForeground(isDark(c) ? Color.WHITE : Color.BLACK);
    }
    private boolean isDark(Color c) {
        return (0.299*c.getRed()+0.587*c.getGreen()+0.114*c.getBlue()) < 128;
    }

    public void setX(int v)            { txtX.setText(String.valueOf(v)); }
    public void setY(int v)            { txtY.setText(String.valueOf(v)); }
    public void setOuterRadius(int r)  { txtOuter.setText(String.valueOf(r)); }
    public void setInnerRadius(int r)  { txtInner.setText(String.valueOf(r)); }
    public void setEdgeColor(Color c)  { edgeColor = c; updateColorButton(btnEdgeColor, c); }
    public void setFillColor(Color c)  { fillColor = c; updateColorButton(btnFillColor, c); }

    public boolean isConfirmed()    { return confirmed; }
    public int     getXValue()      { return Integer.parseInt(txtX.getText().trim()); }
    public int     getYValue()      { return Integer.parseInt(txtY.getText().trim()); }
    public int     getOuterRadius() { return Integer.parseInt(txtOuter.getText().trim()); }
    public int     getInnerRadius() { return Integer.parseInt(txtInner.getText().trim()); }
    public Color   getEdgeColor()   { return edgeColor; }
    public Color   getFillColor()   { return fillColor; }
}
