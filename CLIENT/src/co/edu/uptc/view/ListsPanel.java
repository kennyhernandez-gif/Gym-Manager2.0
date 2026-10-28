package co.edu.uptc.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import co.edu.uptc.model.Member;
import co.edu.uptc.model.Routine;
import co.edu.uptc.model.Exercise;
import co.edu.uptc.persistence.PersistenceTxt;

public class ListsPanel extends JPanel {
    private ContentMainPanel contentPanel;
    
    private DefaultListModel<String> membersModel;
    private DefaultListModel<String> routinesModel;
    private DefaultListModel<String> exercisesModel;
    
    public ListsPanel(ContentMainPanel contentPanel) {
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(1, 3, 15, 0));
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        membersModel = new DefaultListModel<>();
        routinesModel = new DefaultListModel<>();
        exercisesModel = new DefaultListModel<>();
        
        this.add(createPanelForList("👤 Miembros recientes", membersModel));
        this.add(createPanelForList("📋 Rutinas recientes", routinesModel)); 
        this.add(createPanelForList("💪 Ejercicios recientes", exercisesModel)); 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        refreshLists();
    }

    public void refreshLists() {

        try {

            List<Member> members =
                    Member.getMembersList();

            List<Routine> routines =
                    Routine.getRoutineList();

            List<Exercise> exercises =
                    Exercise.getExerciseList();

            if (members == null) {
                members = new java.util.ArrayList<>();
            }

            if (routines == null) {
                routines = new java.util.ArrayList<>();
            }

            if (exercises == null) {
                exercises = new java.util.ArrayList<>();
            }



            membersModel.clear();

            int startM =Math.max(0,members.size() - 4);

            for (int i = members.size() - 1;i >= startM;i--) {

                Member m =members.get(i);

                membersModel.addElement("ID: "+ m.getIdUser()+ " - "+ m.getName());
            }

            if (membersModel.isEmpty()) {
                membersModel.addElement("No hay miembros registrados");
            }

            routinesModel.clear();

            int startR =Math.max(0,routines.size() - 4);

            for (int i = routines.size() - 1;i >= startR;i--) {
                Routine r =routines.get(i);
                routinesModel.addElement(r.getName()+ " ("+ r.getNameMember()+ ")");
            }

            if (routinesModel.isEmpty()) {
                routinesModel.addElement("No hay rutinas registradas");
            }

            exercisesModel.clear();

            int startE =Math.max(0,exercises.size() - 4);

            for (int i = exercises.size() - 1;i >= startE;i--) {

                Exercise ex =exercises.get(i);

                exercisesModel.addElement(ex.getName()+ " ["+ ex.getMuscularGroup()+ "]");
            }

            if (exercisesModel.isEmpty()) {

                exercisesModel.addElement("No hay ejercicios registrados");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private JPanel createPanelForList(String title, DefaultListModel<String> model) {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(new Color(37, 40, 54));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(45, 48, 62), 1),
                new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        panel.add(createStyledList(model), BorderLayout.CENTER);
        return panel;
    }

    private JScrollPane createStyledList(DefaultListModel<String> model) {
        JList<String> list = new JList<>(model);
        list.setBackground(new Color(37, 40, 54));
        list.setForeground(Color.LIGHT_GRAY);
        list.setFont(new Font("Arial", Font.PLAIN, 13));
        list.setSelectionBackground(new Color(107, 89, 204));
        list.setSelectionForeground(Color.WHITE);
        list.setFixedCellHeight(35);
        
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return label;
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }
}