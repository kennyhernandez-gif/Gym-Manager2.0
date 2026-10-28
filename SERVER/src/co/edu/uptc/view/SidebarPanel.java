package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.border.EmptyBorder;

import co.edu.uptc.presenter.Presenter;

public class SidebarPanel extends JPanel {
	private JLabel titleLabel;
	private MainPanel mainPanel;
	private MainFrame mainFrame;
	private MemberPanel memberPanel;
	private ExercisePanel exercisePanel;
	private RoutinePanel routinePanel;
	private JLabel lblDate;
	private JLabel lblTime;
	
	private JButton btnMain;
	private JButton btnMembers;
	private JButton btnRoutines;
	private JButton btnExercises;

	public SidebarPanel(MainFrame mainFrame) {
		this.mainFrame=mainFrame;
		mainPanel = new MainPanel(mainFrame);
		memberPanel = new MemberPanel(mainFrame);
		exercisePanel = new ExercisePanel(mainFrame);
		routinePanel = new RoutinePanel(mainFrame);
		
		initComponents();
	
		}
	private void initComponents() {
		setBackground(new Color(26, 29, 39));
		setPreferredSize(new Dimension(220, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(20, 10, 10, 10));

		titleLabel = new JLabel("FitManager");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		add(titleLabel);
		add(Box.createRigidArea(new Dimension(0, 30)));

		
		
		//INICIO//////
		btnMain = createButton("🏠 Inicio");
		btnMain.setBackground(new Color(107, 89, 204));
		btnMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPanel(mainPanel);
				selectedButton(btnMain);
			}
			
		});
		add(btnMain);
		add(Box.createRigidArea(new Dimension(0,5)));
		
		
		//MIEMBROS//////
		btnMembers = createButton("👥 Miembros");
		btnMembers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPanel(memberPanel);
				selectedButton(btnMembers);
			}
			
		});
		add(btnMembers);
		add(Box.createRigidArea(new Dimension(0,5)));
		
		
		//RUTINAS/////
		btnRoutines = createButton("📋 Rutinas");
		btnRoutines.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPanel(routinePanel);
				selectedButton(btnRoutines);
			}
			
		});
		add(btnRoutines);
		add(Box.createRigidArea(new Dimension(0,5)));
		
		
		//EJERCICIOS
		btnExercises = createButton("💪 Ejercicios");
		btnExercises.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPanel(exercisePanel);
				selectedButton(btnExercises);
			}
			
		});
		add(btnExercises);
		add(Box.createRigidArea(new Dimension(0,200)));
		
		

		lblDate = new JLabel();
		lblTime = new JLabel();

		lblDate.setForeground(Color.WHITE);
		lblTime.setForeground(Color.WHITE);
		
		lblDate.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblTime.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setVerticalAlignment(SwingConstants.CENTER);
		lblTime.setVerticalAlignment(SwingConstants.CENTER);
		//lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		//lblTime.setAlignmentX(Component.CENTER_ALIGNMENT);

		add(lblDate);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(lblTime);
		add(Box.createRigidArea(new Dimension(0, 10)));

		initClock();
		
		
	}
	private JButton createButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btn.setForeground(Color.LIGHT_GRAY);
		btn.setBackground(new Color(26, 29, 39));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setMaximumSize(new Dimension(220, 45));
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		return btn;
	}
	private void selectedButton(JButton selectedButton) {

        btnMain.setBackground(new Color(26, 29, 39));
        btnMembers.setBackground(new Color(26, 29, 39));
        btnRoutines.setBackground(new Color(26,29,39));
        btnExercises.setBackground(new Color(26,29,39));
        
        selectedButton.setBackground(new Color(107, 89, 204));
    }
	private void initClock() {

	    Timer timer = new Timer(1000, e -> {

	        LocalDateTime now = LocalDateTime.now();

	        lblDate.setText(
	                now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
	        );

	        lblTime.setText(
	                now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
	        );
	    });

	    timer.setInitialDelay(0);
	    timer.start();
	}

}