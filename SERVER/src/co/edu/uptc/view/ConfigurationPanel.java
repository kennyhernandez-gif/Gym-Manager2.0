package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ConfigurationPanel extends JPanel{
	private HeaderPanel header;
	public ConfigurationPanel() {
		initComponents();
	}

	private void initComponents() {
		this.setBackground(new Color(26,29,39));
		this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 30, 20, 30));
        header = new HeaderPanel("Configuración General","ersonaliza los ajustes básicos del sistema y preferencias del gimnasio.");
        
	}
}
