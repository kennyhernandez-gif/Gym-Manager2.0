package co.edu.uptc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import co.edu.uptc.model.Member;

public class PieGraphicPanel extends JPanel {

    private ChartsPanel chartsPanel;
    private JLabel titleLabel;
    private String[] text = {"Mujeres", "Hombres"};
    
    private Color[] coloresDePrueba = {
            new Color(107, 89, 204), // Morado principal (Mujeres)
            new Color(38, 222, 129)  // Verde Esmeralda (Hombres)
    };

    public PieGraphicPanel(ChartsPanel chartsPanel) {
        this.chartsPanel = chartsPanel;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(37, 40, 54));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(45, 48, 62)),
                new EmptyBorder(10, 10, 10, 10)
        ));
        
        titleLabel = new JLabel("Estadística hombres y mujeres");
        titleLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titleLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // REQUERIMIENTO: Llamamos a la lógica para obtener los totales reales actualizados
        int[] datosReales = Member.getGenderStatistics();
        
        // Renderizamos el gráfico usando la información procesada por el modelo
        createData(datosReales, g, coloresDePrueba, text);
    }

    private void createData(int[] data, Graphics g, Color[] colors, String[] text) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total += data[i];
        }
        
        // Si no hay usuarios registrados en el sistema, evitamos división por cero y pintamos un aviso
        if (total == 0) {
            g2.setColor(Color.GRAY);
            g2.setFont(new Font("Arial", Font.ITALIC, 14));
            g2.drawString("No hay datos de miembros registrados", getWidth() / 2 - 110, getHeight() / 2);
            return; 
        }

        int padding = 30;
        int diameter = Math.min(getWidth(), getHeight()) - (padding * 2); 

        int x = (getWidth() - diameter) / 2 - 40; 
        int y = (getHeight() - diameter) / 2;

        int startAngle = 0;
        int legendX = x + diameter + 30;
        int legendY = getHeight() / 2 - (data.length * 25) / 2;

        for (int i = 0; i < data.length; i++) {
            // Si una de las listas está vacía (ej. 0 mujeres), evitamos dibujar arcos vacíos
            if (data[i] == 0) continue; 

            int angle = (int) Math.round(((double) data[i] / total) * 360);

            g2.setColor(colors[i % colors.length]);
            g2.fillArc(x, y, diameter, diameter, startAngle, angle);    
            startAngle += angle;
        }

        // Dibujamos las leyendas en un segundo ciclo para que siempre se muestren ambos géneros en la lista
        for (int i = 0; i < data.length; i++) {
            g2.setColor(colors[i % colors.length]);
            g2.fillRect(legendX, legendY + (i * 25), 12, 12);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 13));

            int porcentaje = (int) Math.round(((double) data[i] / total) * 100);
            String textLegend = porcentaje + "% (" + data[i] + ") - " + text[i];

            g2.drawString(textLegend, legendX + 20, legendY + (i * 25) + 11);
        }
    }
}