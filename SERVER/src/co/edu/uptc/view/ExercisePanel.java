package co.edu.uptc.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ExercisePanel extends JPanel{
	private MainFrame mainFrame;
	private HeaderPanel headerPanel;
	private ControlsExercise controlsExercise;
	

	    public ExercisePanel(MainFrame mainFrame) {

	        this.mainFrame = mainFrame;

	        initComponents();
	    }

	private void initComponents() {
		setBackground(new Color(26,29,39));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20,30,20,30));
		
		JPanel topContainer = new JPanel (new BorderLayout());
		topContainer.setOpaque(false);
		controlsExercise = new ControlsExercise(this);
		headerPanel = new HeaderPanel("Gestión de ejercicios", "Administra, filtra, agrega o edita ejercicios que existen en tu gimnasio.");
		topContainer.add(headerPanel,BorderLayout.NORTH);
		topContainer.add(controlsExercise,BorderLayout.CENTER);
		
		add(topContainer,BorderLayout.NORTH);
		add(topContainer,BorderLayout.CENTER);
	}
	public MainFrame getMainFrame() {
        return mainFrame;
    }
}
