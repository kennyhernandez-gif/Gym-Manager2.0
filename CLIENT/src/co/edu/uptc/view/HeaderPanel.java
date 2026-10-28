package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class HeaderPanel extends JPanel{
	private JLabel lblTitle;
	private JLabel lblSubtitle;
	
	public HeaderPanel(String title, String subtitle) {
		initComponents(title,subtitle);
	}

	private void initComponents(String title, String subtitle) {
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		
		lblTitle = new JLabel(title);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial",Font.BOLD,26));
		
		lblSubtitle = new JLabel("Administra, agrega o edita los usuarios registrados en tu gimnasio.");
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        
        add(lblTitle);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(lblSubtitle);
        add(Box.createRigidArea(new Dimension(0, 20)));
        
	}
}
