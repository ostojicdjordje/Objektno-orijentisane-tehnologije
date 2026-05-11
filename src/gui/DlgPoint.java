package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DlgPoint extends JDialog {

    private JTextField txtX          = new JTextField(8);
    private JTextField txtY          = new JTextField(8);
    private JButton    btnEdgeColor  = new JButton("Choose...");
    private Color      edgeColor     = Color.BLACK;
    private boolean    confirmed     = false;

    public DlgPoint(Frame parent, String title) {
        super(parent, title, true);
        buildUi();
    }

    private void buildUi() {
        JPanel pnlFields = new JPanel(new GridLayout(3, 2, 8, 6));
        pnlFields.setBorder(new EmptyBorder(12, 12, 8, 12));

        pnlFields.add(new JLabel("X:"));
        pnlFields.add(txtX);
        pnlFields.add(new JLabel("Y:"));
        pnlFields.add(txtY);
        pnlFields.add(new JLabel("Color:"));
        updateColorButton(btnEdgeColor, edgeColor);
        btnEdgeColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Color", edgeColor);
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
        setMinimumSize(new Dimension(280, 160));
        setLocationRelativeTo(getParent());
    }

    private void updateColorButton(JButton btn, Color c) {
        btn.setBackground(c);
        btn.setForeground(isDark(c) ? Color.WHITE : Color.BLACK);
    }
    private boolean isDark(Color c) {
        return (0.299*c.getRed()+0.587*c.getGreen()+0.114*c.getBlue()) < 128;
    }

    public void setX(int x)           { txtX.setText(String.valueOf(x)); }
    public void setY(int y)           { txtY.setText(String.valueOf(y)); }
    public void setEdgeColor(Color c) { edgeColor = c; updateColorButton(btnEdgeColor, c); }

    public boolean isConfirmed()  { return confirmed; }
    public int     getXValue()    { return txtX.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtX.getText().trim()); }
    public int     getYValue()    { return txtY.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtY.getText().trim()); }
    public Color   getEdgeColor() { return edgeColor; }
    public Color   getFillColor() { return edgeColor; }
}
