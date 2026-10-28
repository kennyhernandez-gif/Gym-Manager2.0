package co.edu.uptc.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import co.edu.uptc.model.Member;

public class TableMembers extends JPanel {
    private ControlsMember controlsMember;
    private JLabel lblOrder;
    private JTable table;
    private DefaultTableModel modelTable;
    private TableRowSorter<DefaultTableModel> clasificator;

    public TableMembers(ControlsMember controlsMember) {
        this.controlsMember = controlsMember;
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
        
        String[] orderOptions = {"ID", "Nombre", "Edad", "IMC"};
        JComboBox<String> comboOrder = new JComboBox<>(orderOptions);
        comboOrder.setBackground(new Color(37, 40, 54));
        comboOrder.setForeground(Color.WHITE);
        
        topPanel.add(lblOrder);
        topPanel.add(comboOrder);
        
        String[] columnas = {"ID", "NOMBRE", "EDAD", "IMC"};
        modelTable = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // INCLUYE EL BUSCADOR MANUAL
        SearchPanelMember searchPanel = new SearchPanelMember(modelTable);
        
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
            clasificator.toggleSortOrder(columnaSeleccionada);
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(45, 48, 62)));
        scrollPane.getViewport().setBackground(new Color(37, 40, 54));

        add(scrollPane, BorderLayout.CENTER);   
        refreshTable();
    }

    public void refreshTable() {
        modelTable.setRowCount(0);
        List<Member> lista = Member.getMembersList();
        for (Member m : lista) {
            modelTable.addRow(new Object[]{
                m.getIdUser(),
                m.getName(),
                m.getAge(),
                m.getIMC()
            });
        }
    }

    public JTable getTable() {
        return this.table;
    }
}