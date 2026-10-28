package co.edu.uptc.view;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;

public class SearchPanel extends JPanel{
	private HeaderMainPanel header;
	private JTextField searchField;
	private String lure;
	
	public SearchPanel(HeaderMainPanel header) {
		this.header = header;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(false);
		lure="Buscar miembros, rutinas, planes...";
		searchField = new JTextField(30);
		searchField.setBackground(new Color(37,40,54));
		searchField.setForeground(Color.GRAY);
		searchField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		searchField.setText(lure);
		searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(lure)) {
                    searchField.setText("");
                    searchField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(lure);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
		add(searchField);
	}
}
