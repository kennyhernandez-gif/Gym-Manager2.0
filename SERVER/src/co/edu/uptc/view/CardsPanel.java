package co.edu.uptc.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import co.edu.uptc.model.Member;
import co.edu.uptc.model.Routine;
import co.edu.uptc.model.Exercise;

public class CardsPanel extends JPanel {
    private ContentMainPanel contentPanel;
    private SummaryCard cardActiveMembers;
    private SummaryCard cardLastUser;
    private SummaryCard cardTotalExercises;
    private SummaryCard cardTotalRoutines;
    
    public CardsPanel(ContentMainPanel contentPanel) {
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(1, 4, 15, 0));
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        cardActiveMembers = new SummaryCard("Miembros activos", "0");
        cardLastUser = new SummaryCard("Último usuario", "Ninguno");
        cardTotalExercises = new SummaryCard("Total Ejercicios", "0");
        cardTotalRoutines = new SummaryCard("Total Rutinas", "0");

        add(cardActiveMembers);
        add(cardLastUser);
        add(cardTotalExercises);
        add(cardTotalRoutines);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        refreshCards();
    }

    public void refreshCards() {

        try {

            List<Member> members =
                    Member.getMembersList();

            List<Routine> routines =
                    Routine.getRoutineList();

            List<Exercise> exercises =
                    Exercise.getExerciseList();

            if (members == null) {
                members =
                        new java.util.ArrayList<>();
            }

            if (routines == null) {
                routines =
                        new java.util.ArrayList<>();
            }

            if (exercises == null) {
                exercises =
                        new java.util.ArrayList<>();
            }

            String totalMembers =
                    String.valueOf(members.size());

            String lastUserName =
                    "Ninguno";

            if (!members.isEmpty()) {

                lastUserName =
                        members.get(
                                members.size() - 1)
                                .getName();

                if (lastUserName.length() > 12) {

                    lastUserName =
                            lastUserName.substring(0, 10)
                                    + "...";
                }
            }

            String totalExercisesStr =
                    String.valueOf(
                            exercises.size());

            String totalRoutinesStr =
                    String.valueOf(
                            routines.size());

            if (cardActiveMembers != null) {
                cardActiveMembers.updateValue(
                        totalMembers);
            }

            if (cardLastUser != null) {
                cardLastUser.updateValue(
                        lastUserName);
            }

            if (cardTotalExercises != null) {
                cardTotalExercises.updateValue(
                        totalExercisesStr);
            }

            if (cardTotalRoutines != null) {
                cardTotalRoutines.updateValue(
                        totalRoutinesStr);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}