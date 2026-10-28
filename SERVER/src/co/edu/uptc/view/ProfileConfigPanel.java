package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class ProfileConfigPanel extends JPanel{
	private ContentConfigPanel content;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblNumber;
	private JTextField txtNumber;

	public ProfileConfigPanel(ContentConfigPanel content) {
		this.content= content;
		initComponents();
	}
	private void initComponents() {
		this.setLayout(new GridLayout(3,2,15,10));
		this.setBackground(new Color(37, 40, 54));
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(45, 48, 62)), "Perfil del gimnasio");
		border.setTitleColor(Color.LIGHT_GRAY);
		border.setTitleFont(new Font("Arial", Font.BOLD, 14));
		this.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(15, 15, 15, 15)));

		lblName = createLabel("Nombre del gimnasio");
		txtName = createTextField("FitManager");
		add(lblName);
		add(txtName);

		lblEmail = createLabel("Correo electrónico de soporte");
		txtEmail = createTextField("cesar.sierra01@uptc.edu.co");
		add(lblEmail);
		add(txtEmail);

		lblNumber = createLabel("Teléfono:");
		txtNumber = createTextField("3142133264");
		add(lblNumber);
		add(txtNumber);


	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		return label;
	}

	private JTextField createTextField(String text) {
		JTextField campo = new JTextField(text);
		campo.setBackground(new Color(26, 29, 39));
		campo.setForeground(Color.WHITE);
		campo.setCaretColor(Color.WHITE);
		campo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(45, 48, 62)),
				new EmptyBorder(5, 8, 5, 8)
				));
		return campo;
	}

}
