package co.edu.uptc.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import co.edu.uptc.model.Member;

public class ButtonsPanel extends JPanel {

    private ControlsPanel controlsPanel;
    private WindowView window;
    private JButton btnDelete;
    private JButton btnModify;
    private JButton btnAdd;
    private OrderPanel orderPanel;

    public ButtonsPanel(ControlsPanel controlsPanel, OrderPanel orderPanel) {
        this.controlsPanel = controlsPanel;
        this.orderPanel = orderPanel; 
        window = new WindowView();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        this.setOpaque(false);

        btnDelete = createBtn("Eliminar", new Color(180, 50, 50));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderPanel == null) return;

                JTable tabla = orderPanel.getTabla();
                int filaSeleccionada = tabla.getSelectedRow();

                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un miembro en la tabla para eliminarlo.", 
                            "Sin Selección", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int idUserSelected = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());
                String nameSelected = tabla.getValueAt(filaSeleccionada, 1).toString();

                int confirm = JOptionPane.showConfirmDialog(null, 
                        "¿Está seguro de eliminar al miembro:\n" + nameSelected + " (ID: " + idUserSelected + ")?", 
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    Member.deleteMember(idUserSelected); 
                    
                    orderPanel.refreshTable();
                    orderPanel.revalidate();
                    orderPanel.repaint();

                    JOptionPane.showMessageDialog(null, "Miembro eliminado exitosamente.", 
                            "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                }
            } 
        }); 
        add(btnDelete);


     
        btnModify = createBtn("Modificar", new Color(45, 48, 62));
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderPanel == null) return;

                JTable tabla = orderPanel.getTabla();
                int filaSeleccionada = tabla.getSelectedRow();

                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un miembro en la tabla para modificarlo.", 
                            "Sin Selección", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int idOriginal = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());

                Member miembroAEditar = null;
                for (Member m : Member.getMembersList()) {
                    if (m.getIdUser() == idOriginal) {
                        miembroAEditar = m;
                        break;
                    }
                }

                if (miembroAEditar == null) return;

                JTextField idField = new JTextField(String.valueOf(miembroAEditar.getIdUser()), 20);
                JTextField nameField = new JTextField(miembroAEditar.getName(), 20);
                JTextField ageField = new JTextField(String.valueOf(miembroAEditar.getAge()), 20);
                JTextField weightField = new JTextField(String.valueOf(miembroAEditar.getWight()), 20); 
                JTextField heightField = new JTextField(String.valueOf(miembroAEditar.getHeight()), 20); 
                JTextField emailField = new JTextField(miembroAEditar.getEmail(), 20);
                
                String[] genderOptions = {"M", "F"};
                JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
                genderCombo.setSelectedItem(miembroAEditar.getGender());

                JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
                panelFormulario.add(new JLabel("Documento de identidad:"));
                panelFormulario.add(idField);
                panelFormulario.add(new JLabel("Nombre Completo:"));
                panelFormulario.add(nameField);
                panelFormulario.add(new JLabel("Edad:"));
                panelFormulario.add(ageField);
                panelFormulario.add(new JLabel("Peso (kg):"));
                panelFormulario.add(weightField);
                panelFormulario.add(new JLabel("Altura (cm):"));
                panelFormulario.add(heightField);
                panelFormulario.add(new JLabel("Correo Electrónico:"));
                panelFormulario.add(emailField);
                panelFormulario.add(new JLabel("Sexo:"));
                panelFormulario.add(genderCombo);

                int opcion = JOptionPane.showConfirmDialog(null, panelFormulario, 
                        "Modificar Datos del Miembro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (opcion == JOptionPane.OK_OPTION) {
                    try {
                        int newId = Integer.parseInt(idField.getText().trim());
                        String name = nameField.getText().trim();
                        int age = Integer.parseInt(ageField.getText().trim());
                        double weight = Double.parseDouble(weightField.getText().trim());
                        double height = Double.parseDouble(heightField.getText().trim());
                        String email = emailField.getText().trim();
                        String gender = (String) genderCombo.getSelectedItem();

                        double alturaMetros = height / 100.0;
                        double imc = weight / (alturaMetros * alturaMetros);

                        Member.updateMember(idOriginal, newId, name, age, weight, height, email, gender, imc);

                        orderPanel.refreshTable(); 
                        orderPanel.revalidate(); 
                        orderPanel.repaint();    

                        JOptionPane.showMessageDialog(null, "Miembro modificado exitosamente.", 
                                "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, 
                                "Error: Verifique que los campos numéricos sean válidos.", 
                                "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(btnModify);


        btnAdd = createBtn("+ Agregar miembro", new Color(107, 89, 204));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField idField = new JTextField(20);
                JTextField nameField = new JTextField(20);
                JTextField ageField = new JTextField(20);
                JTextField weightField = new JTextField(20);
                JTextField heightField = new JTextField(20);
                JTextField emailField = new JTextField(20);
                
                String[] genderOptions = {"M", "F"};
                JComboBox<String> genderCombo = new JComboBox<>(genderOptions);

                JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
                
                panelFormulario.add(new JLabel("Documento de identidad:"));
                panelFormulario.add(idField);
                panelFormulario.add(new JLabel("Nombre Completo:"));
                panelFormulario.add(nameField);
                panelFormulario.add(new JLabel("Edad:"));
                panelFormulario.add(ageField);
                panelFormulario.add(new JLabel("Peso (kg - ej. 70.5):"));
                panelFormulario.add(weightField);
                panelFormulario.add(new JLabel("Altura (cm - ej. 175):"));
                panelFormulario.add(heightField);
                panelFormulario.add(new JLabel("Correo Electrónico:"));
                panelFormulario.add(emailField);
                panelFormulario.add(new JLabel("Sexo:"));
                panelFormulario.add(genderCombo);

                int opcion = JOptionPane.showConfirmDialog(null, panelFormulario, 
                        "Registrar Nuevo Miembro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (opcion == JOptionPane.OK_OPTION) {
                    try {
                        int idUser = Integer.parseInt(idField.getText().trim());
                        String name = nameField.getText().trim();
                        int age = Integer.parseInt(ageField.getText().trim());
                        double weight = Double.parseDouble(weightField.getText().trim());
                        double height = Double.parseDouble(heightField.getText().trim());
                        String email = emailField.getText().trim();
                        String gender = (String) genderCombo.getSelectedItem();

                        double alturaMetros = height / 100.0;
                        double imc = weight / (alturaMetros * alturaMetros);

                        Member.registerMember(idUser, name, age, weight, height, email, gender, imc);

                        if (orderPanel != null) {
                            orderPanel.refreshTable(); 
                            orderPanel.revalidate(); 
                            orderPanel.repaint();    
                        }

                        JOptionPane.showMessageDialog(null, "Miembro registrado exitosamente.", 
                                "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, 
                                "Error: Verifique que los campos de ID, edad, peso y altura contengan solo números válidos.", 
                                "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(btnAdd);
    }

    private JButton createBtn(String text, Color back) {
        JButton btn = new JButton(text);
        btn.setBackground(back);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(back.brighter(), 1),
                new EmptyBorder(8, 15, 8, 15)));
        return btn;
    }
}