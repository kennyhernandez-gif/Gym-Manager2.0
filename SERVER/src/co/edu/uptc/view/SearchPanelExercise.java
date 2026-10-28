package co.edu.uptc.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import co.edu.uptc.model.Exercise;

public class SearchPanelExercise extends JPanel {
    private JTextField searchField;
    private final String lure = "Buscar ejercicios por ID rutina, nombre...";
    private DefaultTableModel modelTable;

    public SearchPanelExercise(DefaultTableModel modelTable) {
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
            @Override public void insertUpdate(DocumentEvent e) { searchExercises(); }
            @Override public void removeUpdate(DocumentEvent e) { searchExercises(); }
            @Override public void changedUpdate(DocumentEvent e) { searchExercises(); }
        });

        add(searchField);
    }

    private void searchExercises() {
        String query = searchField.getText().trim().toLowerCase();
        modelTable.setRowCount(0);
        
        List<Exercise> allExercises = Exercise.getExerciseList();

        for (Exercise ex : allExercises) {
            String idRoutine = String.valueOf(ex.getIdRoutine()).toLowerCase();
            String name = ex.getName().toLowerCase();
            String group = ex.getMuscularGroup().toLowerCase();

            if (query.isEmpty() || searchField.getText().equals(lure) || 
                idRoutine.contains(query) || name.contains(query) || group.contains(query)) {
                
                modelTable.addRow(new Object[]{ ex.getIdRoutine(), ex.getName(), ex.getDescription(), ex.getMuscularGroup() });
            }
        }
    }
}