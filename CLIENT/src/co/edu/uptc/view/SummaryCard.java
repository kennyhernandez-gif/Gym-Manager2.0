package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class SummaryCard extends JPanel {
    private JLabel valueLabel;
    private JLabel titleLabel;

    public SummaryCard(String title, String value) {
        setBackground(new Color(37, 40, 54)); 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(45, 48, 62)),
                new EmptyBorder(15, 15, 15, 15)
        ));
        
        valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

        titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        add(valueLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(titleLabel);
    }

    public void updateValue(String newValue) {
        if (valueLabel != null) {
            valueLabel.setText(newValue);
            this.revalidate();
            this.repaint();
        }
    }
}