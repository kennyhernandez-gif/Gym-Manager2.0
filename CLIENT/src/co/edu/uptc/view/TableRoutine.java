package co.edu.uptc.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import co.edu.uptc.model.Routine;
import co.edu.uptc.model.Exercise;

public class TableRoutine extends JPanel {
    private ControlsRoutine controlsRoutine;
    private JLabel lblOrder;
    private JTable table;
    private DefaultTableModel modelTable;
    private TableRowSorter<DefaultTableModel> clasificator;

    public TableRoutine(ControlsRoutine controlsRoutine) {
        this.controlsRoutine = controlsRoutine;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(0, 15));
        this.setOpaque(false);
        
        JPanel topContainer = new JPanel(new BorderLayout(10, 0));
        topContainer.setOpaque(false);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topPanel.setOpaque(false);
        
        lblOrder = new JLabel("Ordenar por:");
        lblOrder.setForeground(Color.LIGHT_GRAY);
        
        String[] orderOptions = {"ID", "Nombre Rutina", "Nombre miembro"};
        JComboBox<String> comboOrder = new JComboBox<>(orderOptions);
        comboOrder.setBackground(new Color(37,40,54));
        comboOrder.setForeground(Color.WHITE);
        
        topPanel.add(lblOrder);
        topPanel.add(comboOrder);
        
        String[] columnas = {"ID", "NOMBRE RUTINA", "MIEMBRO", "EJERCICIOS"};
        modelTable = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // INCLUYE EL BUSCADOR MANUAL
        SearchPanelRoutine searchPanel = new SearchPanelRoutine(modelTable);
        
        topContainer.add(topPanel, BorderLayout.WEST);
        topContainer.add(searchPanel, BorderLayout.EAST);
        add(topContainer, BorderLayout.NORTH);

        table = new JTable(modelTable);
        table.setBackground(new Color(37, 40, 54));
        table.setForeground(Color.WHITE);
        table.setRowHeight(45);
        table.setGridColor(new Color(45, 48, 62));
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setBackground(new Color(30, 33, 45));
        table.getTableHeader().setForeground(Color.LIGHT_GRAY);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        clasificator = new TableRowSorter<>(modelTable);
        table.setRowSorter(clasificator);

        comboOrder.addActionListener(e -> {
            int columnaSeleccionada = comboOrder.getSelectedIndex();
            if(columnaSeleccionada < 3) {
                clasificator.toggleSortOrder(columnaSeleccionada);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(45, 48, 62)));
        scrollPane.getViewport().setBackground(new Color(37, 40, 54));

        add(scrollPane, BorderLayout.CENTER);   
        refreshTable();
    }

    public void refreshTable() {
        modelTable.setRowCount(0);
        List<Routine> lista = Routine.getRoutineList();
        for (Routine r : lista) {
            StringBuilder sb = new StringBuilder();
            List<Exercise> ejercicios = r.getExercisesOfThisRoutine();
            for (int i = 0; i < ejercicios.size(); i++) {
                sb.append(ejercicios.get(i).getName());
                if (i < ejercicios.size() - 1) sb.append(", ");
            }
            String ejerciciosStr = sb.toString().isEmpty() ? "Sin ejercicios asignados" : sb.toString();

            modelTable.addRow(new Object[]{
                r.getIdRoutine(),
                r.getName(),
                r.getNameMember(),
                ejerciciosStr
            });
        }
    }

    public JTable getTable() {
        return table;
    }
}