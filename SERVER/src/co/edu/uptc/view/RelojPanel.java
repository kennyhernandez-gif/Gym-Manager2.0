package co.edu.uptc.view;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RelojPanel extends JPanel {
	
	private MainPanel mainPanel;
    private JLabel lblTiempo;

    public RelojPanel(MainPanel mainPanel) {
    	this.mainPanel=mainPanel;
        // Configuración básica del panel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        // Crear el JLabel que mostrará el tiempo
        lblTiempo = new JLabel();
        lblTiempo.setFont(new Font("Arial", Font.BOLD, 40));
        add(lblTiempo);

        // Configurar el Timer para actualizarse cada 1000 milisegundos (1 segundo)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHora();
            }
        });
        
        // Iniciar el temporizador
        timer.start();
    }

    private void actualizarHora() {
        // Obtener la hora actual del sistema
        LocalTime ahora = LocalTime.now();
        
        // Darle formato a la hora (HH:mm:ss)
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        // Actualizar el texto del JLabel
        lblTiempo.setText(ahora.format(formato));
    }
}
