package co.edu.uptc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ControlsPanel extends JPanel{
    private OrderPanel orderPanel;
    private ButtonsPanel buttonsPanel;
    private MemberPanel memberPanel;
    
    public ControlsPanel(MemberPanel memberPanel) {
        this.memberPanel = memberPanel;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(0,0,15,0));
        
        
        orderPanel = new OrderPanel(this);       
        add(orderPanel, BorderLayout.CENTER); 
        
        buttonsPanel = new ButtonsPanel(this, orderPanel); 
        add(buttonsPanel, BorderLayout.EAST);
    }
}