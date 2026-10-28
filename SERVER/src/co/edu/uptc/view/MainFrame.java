package co.edu.uptc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import co.edu.uptc.presenter.Presenter;
import java.awt.Container;
import java.awt.Component;

public class MainFrame extends JFrame {
    
    private Presenter presenter;
    private JPanel dynamicPanel;
    private SidebarPanel sidebarPanel;
    
    public MainFrame(Presenter presenter) {
        this.presenter = presenter;
        setTitle("FitManager");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(26,29,39));
        
        sidebarPanel = new SidebarPanel(this); 
        add(sidebarPanel, BorderLayout.WEST);

        dynamicPanel = new JPanel(new BorderLayout());
        dynamicPanel.setOpaque(false);
        
        MainPanel inicio = new MainPanel(this);
        dynamicPanel.add(inicio, BorderLayout.CENTER);
        
        add(dynamicPanel, BorderLayout.CENTER);
    }
    
    public Presenter getPresenter() {
        return this.presenter;
    }

    public void showPanel(JPanel newView) {
        dynamicPanel.removeAll();
        dynamicPanel.add(newView, BorderLayout.CENTER);
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    public void refreshUI() {
        if (dynamicPanel != null) {
            scanAndRefresh(dynamicPanel);
        }
        this.revalidate();
        this.repaint();
    }

    private void scanAndRefresh(Component comp) {
        String className = comp.getClass().getSimpleName();

        if (comp instanceof CardsPanel) {
            ((CardsPanel) comp).refreshCards();
        } else if (className.equals("PieGraphicPanel") || className.equals("ChartsPanel")) {
            comp.repaint();
        } else if (className.equals("TableMembers") || className.equals("TableMember") || 
                   className.equals("TableRoutine") || className.equals("TableExercise")) {
            try { comp.getClass().getMethod("refreshTable").invoke(comp); } catch (Exception e) {}
        }

        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                scanAndRefresh(child);
            }
        }
    }
}