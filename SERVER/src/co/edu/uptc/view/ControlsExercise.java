package co.edu.uptc.view;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ControlsExercise extends JPanel{
	private TableExercise tableExercise;
	private ButtonsExercise buttonsExercise;
	private ExercisePanel exercisePanel;
	private MainFrame mainFrame;

	public ControlsExercise(
            ExercisePanel exercisePanel) {

        this.exercisePanel = exercisePanel;

        this.mainFrame =
                exercisePanel.getMainFrame();

        initComponents();
    }

	private void initComponents() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setBorder(new EmptyBorder(0,0,15,0));

		tableExercise = new TableExercise(this);
		add(tableExercise,BorderLayout.CENTER);

		buttonsExercise = new ButtonsExercise(this);
		add(buttonsExercise, BorderLayout.EAST);
	}
	public TableExercise getTableExercise() {
		return this.tableExercise;
	}
	 public MainFrame getMainFrame() {
	        return mainFrame;
	    }
}
