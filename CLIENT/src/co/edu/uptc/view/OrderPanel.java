package co.edu.uptc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import co.edu.uptc.model.Member;

public class OrderPanel extends JPanel{
    private ControlsPanel controlsPanel;
    private JLabel lblOrder;
    private JTable table;
    private DefaultTableModel modelTable;
    private TableRowSorter<DefaultTableModel> clasificator;

    public OrderPanel(ControlsPanel controlsPanel) {
        this.controlsPanel = controlsPanel;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(0, 15)); 
        this.setOpaque(false);
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topPanel.setOpaque(false);
        
        lblOrder = new JLabel("Ordenar por:");
        lblOrder.setForeground(Color.LIGHT_GRAY);
        
        String[] orderOptions = {"ID", "Nombre", "Edad", "Sexo", "Peso"};
        JComboBox<String> comboOrder = new JComboBox<>(orderOptions);
        comboOrder.setBackground(new Color(37,40,54));
        comboOrder.setForeground(Color.WHITE);
        
        topPanel.add(lblOrder);
        topPanel.add(comboOrder);
        
        this.add(topPanel, BorderLayout.NORTH);

        String[] columnas = {"ID", "NOMBRE COMPLETO", "EDAD", "SEXO", "PESO"};
        modelTable = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(modelTable);
        table.setBackground(new Color(37, 40, 54));
        table.setForeground(Color.WHITE);
        table.setRowHeight(45);
        table.setGridColor(new Color(45, 48, 62));
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setBackground(new Color(30, 33, 45));
        table.getTableHeader().setForeground(Color.LIGHT_GRAY);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        table.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(250); // Nombre (más ancha)
        table.getColumnModel().getColumn(2).setPreferredWidth(50);  // Edad
        table.getColumnModel().getColumn(3).setPreferredWidth(50);  // Sexo
        table.getColumnModel().getColumn(4).setPreferredWidth(80);  // Peso

        clasificator = new TableRowSorter<>(modelTable);
        table.setRowSorter(clasificator);

        comboOrder.addActionListener(e -> {
            int columnaSeleccionada = comboOrder.getSelectedIndex();
            clasificator.toggleSortOrder(columnaSeleccionada);
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(45, 48, 62)));
        scrollPane.getViewport().setBackground(new Color(37, 40, 54));

        this.add(scrollPane, BorderLayout.CENTER);   
    }

   public void refreshTable() {
        modelTable.setRowCount(0); 
        
        java.util.List<Member> listaMiembros = Member.getMembersList();
        
        for (Member m : listaMiembros) {
            modelTable.addRow(new Object[]{
                m.getIdUser(),
                m.getName(),
                m.getAge(),
                m.getGender(),
                m.getWight() + " kg" 
            });
        }
    }
    public JTable getTabla() {
        return this.table;
    }
}