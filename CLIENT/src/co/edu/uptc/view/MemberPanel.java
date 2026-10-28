package co.edu.uptc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MemberPanel extends JPanel {

    private ControlsMember controlsPanel;
    private MainFrame mainFrame;
    private HeaderPanel headerPanel;

    public MemberPanel(MainFrame mainFrame){ 
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(26, 29, 39));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.setOpaque(false);
        controlsPanel = new ControlsMember(this);
        headerPanel = new HeaderPanel("Gestión de Miembros","Administra, filtra, agrega o edita los usuarios registrados en tu gimnasio.");
        topContainer.add(headerPanel, BorderLayout.NORTH);
        topContainer.add(controlsPanel, BorderLayout.CENTER);

        add(topContainer, BorderLayout.NORTH);
        add(topContainer, BorderLayout.CENTER);
    }

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}