package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ContentProfilePanel extends JPanel{
	private ProfilePanel profilePanel;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtEmail;

	
	public ContentProfilePanel(ProfilePanel profilePanel) {
		this.profilePanel=profilePanel;
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridBagLayout());
		setBackground(new Color(37, 40, 54));
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(45, 48, 62), 1),new EmptyBorder(30, 30, 30, 30)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(12, 12, 12, 12);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0; 
		gbc.gridy = 0;
		add(createLabel("Documento de Identidad:"), gbc);
		
		gbc.gridx = 1;
		txtId = createTextField("1122513725");
		add(txtId, gbc);

		
		gbc.gridx = 0; 
		gbc.gridy = 1;
		add(createLabel("Nombre Completo:"), gbc);
		
		gbc.gridx = 1;
		txtName = createTextField("Cesar Esteban Sierra Baquero");
		add(txtName, gbc);

		
		gbc.gridx = 0; 
		gbc.gridy = 2;
		add(createLabel("Edad:"), gbc);
		
		gbc.gridx = 1;
		txtAge = createTextField("19");
		add(txtAge, gbc);
		

		gbc.gridx = 0; 
		gbc.gridy = 3;
		add(createLabel("Correo Electrónico:"), gbc);
		
		gbc.gridx = 1;
		txtEmail = createTextField("admin@fitmanager.com");
		add(txtEmail, gbc);

		
	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		return label;
	}

	private JTextField createTextField(String startText) {
		JTextField field = new JTextField(startText, 25);
		field.setBackground(new Color(26, 29, 39));
		field.setForeground(Color.WHITE);
		field.setCaretColor(Color.WHITE);
		field.setFont(new Font("Arial", Font.PLAIN, 14));
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(55, 58, 72), 1),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)
		));
		return field;
	}


}
