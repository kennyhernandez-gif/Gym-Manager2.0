package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;

public class ContentConfigPanel extends JPanel{
	private ConfigurationPanel config;
	private ProfileConfigPanel profile;
	
	public ContentConfigPanel(ConfigurationPanel config) {
		this.config = config;
		initComponents();
	}
	private void initComponents() {
		this.setBackground(new Color(26, 29, 39));
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		profile = new ProfileConfigPanel(this);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		
	}
}
