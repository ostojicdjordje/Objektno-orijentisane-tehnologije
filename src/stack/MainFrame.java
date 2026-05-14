package stack;

import javax.swing.*;
import geometry.Circle;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MainFrame extends JFrame {
    private Stack<Circle> circleStack = new Stack<>();
    private DefaultListModel<Circle> dlm = new DefaultListModel<>();
    private JList<Circle> lstCircles = new JList<>(dlm);

    public MainFrame() {
       
        setTitle("Ostojic Djordje MH-22/2022");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton btnAdd = new JButton("Add (Push)");
        JButton btnRemove = new JButton("Remove (Pop)");
        JButton btnSort = new JButton("Sort (By Area)");

        JPanel pnlBottom = new JPanel();
        pnlBottom.add(btnAdd);
        pnlBottom.add(btnRemove);
        pnlBottom.add(btnSort);

        
        btnAdd.addActionListener(e -> {
            Dialog dlg = new Dialog(this, "Add New Circle", true);
            dlg.setVisible(true);

            if (dlg.isOk()) {
                Circle c = dlg.getCircle();
                circleStack.push(c);
                dlm.add(0, c);
            }
        });

        
        btnRemove.addActionListener(e -> {
            if (!circleStack.isEmpty()) {
                Circle topCircle = circleStack.peek(); 
                
                Dialog dlg = new Dialog(this, "Circle Details (Pop)", true);
                dlg.setCircleValues(topCircle); 
                dlg.setVisible(true);
                
                if (dlg.isOk()) {
                    circleStack.pop();
                    dlm.remove(0);
                    JOptionPane.showMessageDialog(this, "Circle successfully removed from stack.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "The stack is empty!", "Empty Stack", JOptionPane.WARNING_MESSAGE);
            }
        });

        
        btnSort.addActionListener(e -> {
            if (dlm.isEmpty()) return;

            ArrayList<Circle> listForSorting = new ArrayList<>();
            for (int i = 0; i < dlm.size(); i++) {
                listForSorting.add(dlm.get(i));
            }

           
            Collections.sort(listForSorting, (c1, c2) -> Double.compare(c2.area(), c1.area()));

            dlm.clear();
            for (Circle c : listForSorting) {
                dlm.addElement(c);
            }
        });

        add(new JScrollPane(lstCircles), BorderLayout.CENTER);
        add(pnlBottom, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mf = new MainFrame();
            mf.setVisible(true);
        });
    }
}
