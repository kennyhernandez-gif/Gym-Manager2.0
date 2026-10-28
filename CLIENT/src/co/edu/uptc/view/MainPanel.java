package co.edu.uptc.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private HeaderMainPanel headerPanel; 
    private ContentMainPanel contentPanel;
    private MainFrame frame;
    
    public MainPanel(MainFrame frame) {
        this.frame = frame;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false); 
        
        headerPanel = new HeaderMainPanel(this);    
        add(headerPanel, BorderLayout.NORTH);
        
        contentPanel = new ContentMainPanel(this);
        add(contentPanel, BorderLayout.CENTER);
        
        triggerCardsRefresh(contentPanel);
    }

    private void triggerCardsRefresh(Component comp) {
        if (comp instanceof CardsPanel) {
            ((CardsPanel) comp).refreshCards();
            return;
        }
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                triggerCardsRefresh(child);
            }
        }
    }
}