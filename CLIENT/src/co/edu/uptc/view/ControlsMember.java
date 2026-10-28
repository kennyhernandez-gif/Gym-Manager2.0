package co.edu.uptc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ControlsMember extends JPanel {
    private TableMembers orderPanel;
    private ButtonsMember buttonsPanel;
    private MemberPanel memberPanel;
    
    public ControlsMember(MemberPanel memberPanel) {
        this.memberPanel = memberPanel;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        orderPanel = new TableMembers(this);       
        add(orderPanel, BorderLayout.CENTER); 
        
        buttonsPanel = new ButtonsMember(this, orderPanel, memberPanel.getMainFrame().getPresenter()); 
        add(buttonsPanel, BorderLayout.EAST);
    }
}