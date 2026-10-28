package co.edu.uptc.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import co.edu.uptc.model.Member;

public class SearchPanelMember extends JPanel {
    private JTextField searchField;
    private final String lure = "Buscar miembros por ID, nombre...";
    private DefaultTableModel modelTable;

    public SearchPanelMember(DefaultTableModel modelTable) {
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
            @Override public void insertUpdate(DocumentEvent e) { searchMembers(); }
            @Override public void removeUpdate(DocumentEvent e) { searchMembers(); }
            @Override public void changedUpdate(DocumentEvent e) { searchMembers(); }
        });

        add(searchField);
    }

    private void searchMembers() {
        String query = searchField.getText().trim().toLowerCase();
        modelTable.setRowCount(0);
        
        List<Member> allMembers = Member.getMembersList();

        if (query.isEmpty() || searchField.getText().equals(lure)) {
            for (Member m : allMembers) {
                modelTable.addRow(new Object[]{ m.getIdUser(), m.getName(), m.getAge(), m.getIMC() });
            }
            return;
        }

        for (Member m : allMembers) {
            String id = String.valueOf(m.getIdUser()).toLowerCase();
            String name = m.getName().toLowerCase();
            
            if (id.contains(query) || name.contains(query)) {
                modelTable.addRow(new Object[]{ m.getIdUser(), m.getName(), m.getAge(), m.getIMC() });
            }
        }
    }
}