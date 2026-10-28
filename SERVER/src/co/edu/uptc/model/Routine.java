package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.persistence.PersistenceTxt;
import co.edu.uptc.structures.SimpleList;

public class Routine {

    private int idRoutine;
    private String name;
    private String nameMember;
    private SimpleList routineList;


    public Routine() {
    }

    public Routine(int idRoutine, String name, String nameMember) {
        this.idRoutine = idRoutine;
        this.name = name;
        this.nameMember = nameMember;
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

    public String getNameMember() {
        return nameMember;
    }

    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    // CONECTA LOS EJERCICIOS DINÁMICAMENTE CON LA RUTINA EN TIEMPO REAL
    public List<Exercise> getExercisesOfThisRoutine() {
        List<Exercise> currentExercises = new ArrayList<>();
        for (Exercise e : Exercise.getExerciseList()) {
            if (e.getIdRoutine() == this.idRoutine) {
                currentExercises.add(e);
            }
        }
        return currentExercises;
    }

    // --- MÉTODOS DEL CRUD RECUPERADOS Y SINCRONIZADOS ---

    public static List<Routine> getRoutineList() {
        return routineList;
    }

    public static void setRoutineList(List<Routine> list) {
        routineList = list;
    }

    // 1. REGISTRAR RUTINA
    public static void registerRoutine(int idRoutine, String name, String nameMember) {
        Routine newRoutine = new Routine(idRoutine, name, nameMember);
        routineList.add(newRoutine);
        new PersistenceTxt().saveRoutines(routineList);
    }

    // 2. ELIMINAR RUTINA
    public static void deleteRoutine(int idRoutine) {
        routineList.removeIf(r -> r.getIdRoutine() == idRoutine);
        new PersistenceTxt().saveRoutines(routineList);
    }

    // 3. ACTUALIZAR RUTINA
    public static void updateRoutine(int idOriginal, int newIdRoutine, String newName, String newNameMember) {
        for (Routine r : routineList) {
            if (r.getIdRoutine() == idOriginal) {
                r.setIdRoutine(newIdRoutine);
                r.setName(newName);
                r.setNameMember(newNameMember);
                break;
            }
        }
        new PersistenceTxt().saveRoutines(routineList);
    }

    @Override
    public String toString() {
        return "Routine{" +
                "idRoutine=" + idRoutine +
                ", name='" + name + '\'' +
                ", nameMember='" + nameMember + '\'' +
                '}';
    }
}