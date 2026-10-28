package co.edu.uptc.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HeaderMainPanel extends JPanel{
	private JLabel welcomeLabel;
	private JLabel profileLabel;
	private SearchPanel searchPanel;
	private MainPanel mainPanel;
	private JMenuItem viewProfileItem;
	private JMenuItem editProfileItem;

	private MainFrame mainFrame;
	private ProfilePanel profilePanel;

	public HeaderMainPanel(MainPanel mainPanel) {
		this.mainPanel=mainPanel;
		initComponents();
	}

	private void initComponents() {

		setBackground(new Color(26, 29, 39));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 20, 15, 20));

		welcomeLabel = new JLabel("¡Bienvenido de vuelta, Admin! 💪🔥");
		welcomeLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD,18));
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		add(welcomeLabel, BorderLayout.WEST);

		searchPanel = new SearchPanel(this);
		add(searchPanel, BorderLayout.CENTER);
		profileLabel = new JLabel("Admin (Administrador)");
		profileLabel.setForeground(Color.WHITE);
		profileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JPopupMenu profileMenu = new JPopupMenu();

		viewProfileItem = new JMenuItem("Ver perfil");
		editProfileItem = new JMenuItem("Editar perfil");

		profileMenu.add(viewProfileItem);
		profileMenu.add(editProfileItem);

		viewProfileItem.addActionListener(e -> abrirPanelDePerfil());
		editProfileItem.addActionListener(e -> abrirPanelDePerfil());

		profileLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				profileMenu.show(profileLabel, 0, profileLabel.getHeight());}});
		add(profileLabel, BorderLayout.EAST);
	}
	private void abrirPanelDePerfil() {
		Window window = SwingUtilities.getWindowAncestor(this);
		if (window instanceof MainFrame) {
			MainFrame framePrincipal = (MainFrame) window;
			framePrincipal.showPanel(new ProfilePanel(framePrincipal));
		}

	}
}
