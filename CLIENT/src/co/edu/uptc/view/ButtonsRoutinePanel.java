package co.edu.uptc.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import co.edu.uptc.model.Routine;

public class ButtonsRoutinePanel extends JPanel {

	private ControlsRoutine controlsRoutine;
	private JButton btnDelete;
	private JButton btnModify;
	private JButton btnAdd;

	public ButtonsRoutinePanel(ControlsRoutine controlsRoutine) {
		this.controlsRoutine = controlsRoutine; 
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		this.setOpaque(false);

		// --- BOTÓN ELIMINAR ---
		btnDelete = createBtn("Eliminar", new Color(180, 50, 50));
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableRoutine orderPanel = controlsRoutine.getTableRoutine();
				if (orderPanel == null) return;

				JTable table = orderPanel.getTable();
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una rutina en la tabla para eliminarla.", 
							"Sin Selección", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int idRoutine = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
				String nameSelected = table.getValueAt(selectedRow, 1).toString();

				int confirm = JOptionPane.showConfirmDialog(null, 
						"¿Está seguro de eliminar la rutina:\n" + nameSelected + " (ID: " + idRoutine + ")?", 
						"Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					controlsRoutine
			        .getMainFrame()
			        .getPresenter()
			        .deleteRoutine(idRoutine);
					orderPanel.refreshTable();
					JOptionPane.showMessageDialog(null, "Rutina eliminada exitosamente.", 
							"Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		add(btnDelete);

		// --- BOTÓN MODIFICAR ---
		btnModify = createBtn("Modificar", new Color(45, 48, 62));
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableRoutine orderPanel = controlsRoutine.getTableRoutine();
				if (orderPanel == null) return;

				JTable table = orderPanel.getTable();
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una rutina para modificarla.", 
							"Sin Selección", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int idOriginal = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
				Routine rutinaEditar = null;
				for (Routine r : Routine.getRoutineList()) {
					if (r.getIdRoutine() == idOriginal) {
						rutinaEditar = r;
						break;
					}
				}

				if (rutinaEditar == null) return;

				JTextField idField = new JTextField(String.valueOf(rutinaEditar.getIdRoutine()), 20);
				JTextField nameField = new JTextField(rutinaEditar.getName(), 20);
				JTextField memberField = new JTextField(rutinaEditar.getNameMember(), 20);

				JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
				panelFormulario.add(new JLabel("ID Rutina:"));
				panelFormulario.add(idField);
				panelFormulario.add(new JLabel("Nombre Rutina:"));
				panelFormulario.add(nameField);
				panelFormulario.add(new JLabel("Nombre Miembro:"));
				panelFormulario.add(memberField);

				int opcion = JOptionPane.showConfirmDialog(null, panelFormulario, 
						"Modificar Datos de la Rutina", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (opcion == JOptionPane.OK_OPTION) {
					try {
						int newId = Integer.parseInt(idField.getText().trim());
						String name = nameField.getText().trim();
						String memberName = memberField.getText().trim();

						controlsRoutine
				        .getMainFrame()
				        .getPresenter()
				        .updateRoutine(
				                idOriginal,
				                newId,
				                name,
				                memberName);
						orderPanel.refreshTable(); 

						JOptionPane.showMessageDialog(null, "Rutina modificada exitosamente.", 
								"Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número válido.", 
								"Error de Formato", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		add(btnModify);

		// --- BOTÓN AGREGAR ---
		btnAdd = createBtn("+ Agregar rutina", new Color(107, 89, 204));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField idField = new JTextField(20);
				JTextField nameField = new JTextField(20);
				JTextField memberField = new JTextField(20);

				JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
				panelFormulario.add(new JLabel("ID Rutina:"));
				panelFormulario.add(idField);
				panelFormulario.add(new JLabel("Nombre Rutina:"));
				panelFormulario.add(nameField);
				panelFormulario.add(new JLabel("Nombre Miembro:"));
				panelFormulario.add(memberField);

				int opcion = JOptionPane.showConfirmDialog(null, panelFormulario, 
						"Registrar Nueva Rutina", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (opcion == JOptionPane.OK_OPTION) {
					// Validación: Verificar que los campos no estén vacíos
					if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || memberField.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Error: Todos los campos son obligatorios.", 
								"Campos Vacíos", JOptionPane.ERROR_MESSAGE);
						return;
					}

					try {
						int idRoutine = Integer.parseInt(idField.getText().trim());
						String name = nameField.getText().trim();
						String memberName = memberField.getText().trim();

						// 1. Guardar en el modelo y persistencia
						controlsRoutine
				        .getMainFrame()
				        .getPresenter()
				        .registerRoutine(
				                idRoutine,
				                name,
				                memberName);

						// 2. Forzar la actualización visual buscando la tabla directamente
						TableRoutine orderPanel = controlsRoutine.getTableRoutine();
						if (orderPanel != null) {
							orderPanel.refreshTable(); 
							orderPanel.revalidate();
							orderPanel.repaint();
						} else {
							System.out.println("Alerta: ¡La referencia a 'orderPanel' llegó nula!");
						}

						// 3. Mostrar confirmación inequívoca
						JOptionPane.showMessageDialog(null, "¡Rutina registrada exitosamente!", 
								"Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número entero válido (sin letras ni espacios).", 
								"Error de Formato", JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						// Captura cualquier otro error imprevisto para que sepamos qué pasa
						JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + ex.getMessage(), 
								"Error", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
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
				new EmptyBorder(8, 15, 8, 15)
				));
		return btn;
	}
}