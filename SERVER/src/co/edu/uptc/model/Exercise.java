package co.edu.uptc.model;  

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.persistence.PersistenceTxt;
import co.edu.uptc.structures.SimpleList;

public class Exercise {

    private int idRoutine;
    private String name;
    private String description;
    private String muscularGroup;
    private SimpleList exerciseList;

    public Exercise() {
    }

    public Exercise(int idRoutine, String name, String description, String muscularGroup) {
        this.idRoutine = idRoutine;
        this.name = name;
        this.description = description;
        this.muscularGroup = muscularGroup;
    }

    public int getIdRoutine() {
        return idRoutine;
    }

    public void setIdRoutine(int idRoutine) {
        this.idRoutine = idRoutine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(String muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

    // --- MÉTODOS DEL CRUD Y ESTRUCTURA ESTÁTICA ---

    public static void createExercise(int idRoutine, String name, String description, String muscularGroup) {
        Exercise newExercise = new Exercise(idRoutine, name, description, muscularGroup);
        exerciseList.add(newExercise);
        new PersistenceTxt().saveExercises(exerciseList);
    }

    public static void deleteExercise(int idRoutine, String nameKey) {
        exerciseList.removeIf(e -> e.getIdRoutine() == idRoutine && e.getName().equalsIgnoreCase(nameKey));
        new PersistenceTxt().saveExercises(exerciseList);
    }

    public static void updateExercise(int idRoutineOriginal, String nameOriginal, int newIdRoutine, String newName, String newDesc, String newGroup) {
        for (Exercise e : exerciseList) {
            if (e.getIdRoutine() == idRoutineOriginal && e.getName().equalsIgnoreCase(nameOriginal)) {
                e.setIdRoutine(newIdRoutine);
                e.setName(newName);
                e.setDescription(newDesc);
                e.setMuscularGroup(newGroup);
                break;
            }
        }
        new PersistenceTxt().saveExercises(exerciseList);
    }

    public static List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public static void setExerciseList(List<Exercise> list) {
        exerciseList = list;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "idRoutine=" + idRoutine +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscularGroup='" + muscularGroup + '\'' +
                '}';    
    }
}