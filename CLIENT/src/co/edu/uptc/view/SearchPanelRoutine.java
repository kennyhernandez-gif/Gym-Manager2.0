package co.edu.uptc.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import co.edu.uptc.model.Routine;
import co.edu.uptc.model.Exercise;

public class SearchPanelRoutine extends JPanel {
    private JTextField searchField;
    private final String lure = "Buscar rutinas por ID, nombre...";
    private DefaultTableModel modelTable;

    public SearchPanelRoutine(DefaultTableModel modelTable) {
        this.modelTable = modelTable;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setOpaque(false);

        searchField = new JTextField(30);
        searchField.setBackground(new Color(37, 40, 54));
        searchField.setForeground(Color.GRAY);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        searchField.setText(lure);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(lure)) {
                    searchField.setText("");
                    searchField.setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().trim().isEmpty()) {
                    searchField.setText(lure);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { searchRoutines(); }
            @Override public void removeUpdate(DocumentEvent e) { searchRoutines(); }
            @Override public void changedUpdate(DocumentEvent e) { searchRoutines(); }
        });

        add(searchField);
    }

    private void searchRoutines() {
        String query = searchField.getText().trim().toLowerCase();
        modelTable.setRowCount(0);
        
        List<Routine> allRoutines = Routine.getRoutineList();

        for (Routine r : allRoutines) {
            String id = String.valueOf(r.getIdRoutine()).toLowerCase();
            String name = r.getName().toLowerCase();
            String member = r.getNameMember().toLowerCase();

            if (query.isEmpty() || searchField.getText().equals(lure) || 
                id.contains(query) || name.contains(query) || member.contains(query)) {
                
                StringBuilder sb = new StringBuilder();
                List<Exercise> ejercicios = r.getExercisesOfThisRoutine();
                for (int i = 0; i < ejercicios.size(); i++) {
                    sb.append(ejercicios.get(i).getName());
                    if (i < ejercicios.size() - 1) sb.append(", ");
                }
                String ejerciciosStr = sb.toString().isEmpty() ? "Sin ejercicios asignados" : sb.toString();

                modelTable.addRow(new Object[]{ r.getIdRoutine(), r.getName(), r.getNameMember(), ejerciciosStr });
            }
        }
    }
}