package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoutinePanel extends JPanel{
	private MainFrame frame;
	private HeaderPanel headerPanel;
	private ControlsRoutine controlsRoutine;

	public RoutinePanel(MainFrame frame) {
		this.frame = frame;
		initComponents();
	}

	private void initComponents() {
		setBackground(new Color(26,29,39));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20,30,20,30));

		JPanel topContainer = new JPanel(new BorderLayout());
		topContainer.setOpaque(false);
		controlsRoutine = new ControlsRoutine(this);
		headerPanel = new HeaderPanel("Gestión de rutinas","Administra, filtra, agrega o edita las rutinas registradas");
		topContainer.add(headerPanel,BorderLayout.NORTH);
		topContainer.add(controlsRoutine,BorderLayout.CENTER);

		add(topContainer,BorderLayout.NORTH);
		add(topContainer,BorderLayout.CENTER);
	}
	public void updateTableData() {
		if (controlsRoutine != null && controlsRoutine.getTableRoutine() != null) {
			controlsRoutine.getTableRoutine().refreshTable();
		}
	}
	public MainFrame getMainFrame() {
	    return frame;
	}
}
