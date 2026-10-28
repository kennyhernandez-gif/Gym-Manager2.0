package co.edu.uptc.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ChartsPanel extends JPanel{
	private ContentMainPanel contentPanel;
	private BarGraphicPanel barPanel;
	private PieGraphicPanel piePanel;
	
	public ChartsPanel(ContentMainPanel contentPanel) {
		this.contentPanel=contentPanel;
		initComponents();
	}
	private void initComponents() {
		this.setLayout(new GridLayout(1,2,15,0));
		this.setOpaque(false);
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE,250));
		
		barPanel = new BarGraphicPanel(this);
		add(barPanel);
		
		piePanel = new PieGraphicPanel(this);
		add(piePanel);
	}
}
