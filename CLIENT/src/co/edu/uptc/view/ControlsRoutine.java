package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ControlsRoutine extends JPanel{
	private RoutinePanel routinePanel;
	private TableRoutine tableRoutine;
	private ButtonsRoutinePanel buttonsRoutinePanel;
	private MainFrame mainFrame;
	public ControlsRoutine(
	        MainFrame mainFrame) {

	    this.mainFrame = mainFrame;
	}
	public ControlsRoutine(
	        RoutinePanel routinePanel) {

	    this.routinePanel = routinePanel;

	    this.mainFrame =
	            routinePanel.getMainFrame();

	    initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setBorder(new EmptyBorder(0,0,15,0));
		
		tableRoutine = new TableRoutine(this);
		add(tableRoutine,BorderLayout.CENTER);
		
		buttonsRoutinePanel = new ButtonsRoutinePanel(this);
		add(buttonsRoutinePanel,BorderLayout.EAST);
	}
	public TableRoutine getTableRoutine() {
    return this.tableRoutine;
}
	public MainFrame getMainFrame() {
	    return mainFrame;
	}
}
