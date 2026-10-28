package co.edu.uptc.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import co.edu.uptc.model.Exercise;

public class ButtonsExercise extends JPanel {
	private ControlsExercise controlsExercise;
	private JButton btnDelete;
	private JButton btnModify;
	private JButton btnAdd;

	public ButtonsExercise(ControlsExercise controlsExercise) {
		this.controlsExercise = controlsExercise; 
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		this.setOpaque(false);

		// --- ELIMINAR EJERCICIO ---
		btnDelete = createBtn("Eliminar", new Color(180, 50, 50));
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableExercise orderPanel = controlsExercise.getTableExercise();
				if (orderPanel == null) return;

				JTable table = orderPanel.getTable();
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un ejercicio de la tabla para eliminarlo.", 
							"Sin Selección", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int idRoutine = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
				String exerciseName = table.getValueAt(selectedRow, 1).toString();

				int confirm = JOptionPane.showConfirmDialog(null, 
						"¿Desea eliminar el ejercicio '" + exerciseName + "' de la rutina ID: " + idRoutine + "?", 
						"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {

					controlsExercise
					.getMainFrame()
					.getPresenter()
					.deleteExercise(
							idRoutine,
							exerciseName);

					orderPanel.refreshTable();

					JOptionPane.showMessageDialog(
							null,
							"Ejercicio eliminado exitosamente.");
				}
			}
		});
		add(btnDelete);

		// --- MODIFICAR EJERCICIO ---
		btnModify = createBtn("Modificar", new Color(45, 48, 62));
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableExercise orderPanel = controlsExercise.getTableExercise();
				if (orderPanel == null) return;

				JTable table = orderPanel.getTable();
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un ejercicio para modificarlo.", 
							"Sin Selección", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int idOriginal = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
				String nameOriginal = table.getValueAt(selectedRow, 1).toString();
				String descOriginal = table.getValueAt(selectedRow, 2).toString();
				String groupOriginal = table.getValueAt(selectedRow, 3).toString();

				JTextField idField = new JTextField(String.valueOf(idOriginal), 20);
				JTextField nameField = new JTextField(nameOriginal, 20);
				JTextField descField = new JTextField(descOriginal, 20);
				JTextField groupField = new JTextField(groupOriginal, 20);

				JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
				panelForm.add(new JLabel("ID Rutina:")); panelForm.add(idField);
				panelForm.add(new JLabel("Nombre:")); panelForm.add(nameField);
				panelForm.add(new JLabel("Descripción:")); panelForm.add(descField);
				panelForm.add(new JLabel("Grupo Muscular:")); panelForm.add(groupField);

				int option = JOptionPane.showConfirmDialog(null, panelForm, 
						"Modificar Ejercicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					try {
						int newId = Integer.parseInt(idField.getText().trim());
						String newName = nameField.getText().trim();
						String newDesc = descField.getText().trim();
						String newGroup = groupField.getText().trim();

						controlsExercise
						.getMainFrame()
						.getPresenter()
						.updateExercise(
								idOriginal,
								nameOriginal,
								newId,
								newName,
								newDesc,
								newGroup);
						orderPanel.refreshTable();
						JOptionPane.showMessageDialog(null, "Ejercicio actualizado de inmediato.");
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "El ID de rutina debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		add(btnModify);

		// --- AGREGAR EJERCICIO ---
		btnAdd = createBtn("+ Agregar ejercicio", new Color(107, 89, 204));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField idField = new JTextField(20);
				JTextField nameField = new JTextField(20);
				JTextField descField = new JTextField(20);
				JTextField groupField = new JTextField(20);

				JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
				panelForm.add(new JLabel("ID de Rutina Asociada:")); panelForm.add(idField);
				panelForm.add(new JLabel("Nombre Ejercicio:")); panelForm.add(nameField);
				panelForm.add(new JLabel("Descripción:")); panelForm.add(descField);
				panelForm.add(new JLabel("Grupo Muscular:")); panelForm.add(groupField);

				int option = JOptionPane.showConfirmDialog(null, panelForm, 
						"Registrar Ejercicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					if(idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "ID de Rutina y Nombre son obligatorios.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						int idRoutine = Integer.parseInt(idField.getText().trim());
						String name = nameField.getText().trim();
						String desc = descField.getText().trim();
						String group = groupField.getText().trim();

						controlsExercise
				        .getMainFrame()
				        .getPresenter()
				        .registerExercise(
				                idRoutine,
				                name,
				                desc,
				                group);

						TableExercise orderPanel = controlsExercise.getTableExercise();
						if (orderPanel != null) {
							orderPanel.refreshTable();
						}
						JOptionPane.showMessageDialog(null, "¡Ejercicio registrado con éxito!");
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "El ID de rutina debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
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