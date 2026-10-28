package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsProfile extends JPanel{
	private ProfilePanel profilePanel;
	private JButton btnSave;
	private JButton btnCancel;
	
	public ButtonsProfile(ProfilePanel profilePanel) {
		this.profilePanel=profilePanel;
		initComponents();
	}

	private void initComponents() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		setOpaque(false);
		
		btnCancel = new JButton("Cancelar");
		styleButton(btnCancel, new Color(45, 48, 62));
		//btnCancelar.addActionListener(e->); //CANCELAR. AGREGAR ACTION LISTENER

		btnSave = new JButton("Guardar Cambios");
		styleButton(btnSave, new Color(107, 89, 204));
		//btnCancelar.addActionListener(e->); //GUARDAR. AGREGAR ACTION LISTENER
		
		add(btnCancel);
		add(btnSave);
	}
	private void styleButton(JButton btn, Color back) {
		btn.setBackground(back);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setFont(new Font("Arial", Font.BOLD, 13));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(back.brighter(), 1),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)
		));
	}
}
