package co.edu.uptc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfilePanel extends JPanel {
	private MainFrame mainFrame;
	private HeaderPanel headerPanel;
	private ContentProfilePanel contentProfilePanel;
	private ButtonsProfile buttonsProfile;

	public ProfilePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;	
		initComponents();
	}

	private void initComponents() {
		
		setBackground(new Color(26, 29, 39));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(30, 40, 30, 40));
		
		headerPanel = new HeaderPanel("Perfil de Admin","Visualiza y edita tu información personal.");
		add(headerPanel, BorderLayout.NORTH);
		
		contentProfilePanel = new ContentProfilePanel(this);
		add(contentProfilePanel,BorderLayout.CENTER);
		
		buttonsProfile = new ButtonsProfile(this);
		add(buttonsProfile,BorderLayout.SOUTH);
	}


}