package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ContentMainPanel extends JPanel{

	private MainPanel mainPanel;
	private CardsPanel cardsPanel;
	private ChartsPanel chartsPanel;
	private ListsPanel listsPanel;

	public ContentMainPanel(MainPanel mainPanel) {
		this.mainPanel=mainPanel;
		initComponents();
	}

	private void initComponents() {
		setBackground(new Color(26, 29, 39));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 20, 20, 20));

		cardsPanel = new CardsPanel(this);
		chartsPanel = new ChartsPanel(this);
		listsPanel = new ListsPanel(this);

		add(cardsPanel);
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(chartsPanel);
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(listsPanel);
	}


}
