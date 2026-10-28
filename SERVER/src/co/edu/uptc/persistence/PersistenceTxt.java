package co.edu.uptc.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Exercise;
import co.edu.uptc.model.Member;
import co.edu.uptc.model.Routine;

public class PersistenceTxt implements Persistence {

    public PersistenceTxt() {}

    // ==========================================
    // MIEMBROS
    // ==========================================
    @Override
    public void saveMembers(List<Member> members) {
        try {
            checkFolder();
            FileWriter fw = new FileWriter(getFileMembers());
            BufferedWriter writer = new BufferedWriter(fw);

            for (Member m : members) {
                String line = m.getIdUser() + ";" +
                              m.getName() + ";" +
                              m.getAge() + ";" +
                              m.getWight() + ";" +
                              m.getHeight() + ";" +
                              m.getEmail() + ";" +
                              m.getGender() + ";" +
                              m.getIMC();
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> loadMembers() {
        try {
            File file = new File(getFileMembers());
            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            List<Member> members = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 8) {
                    Member m = new Member();
                    m.setIdUser(Integer.parseInt(data[0]));
                    m.setName(data[1]);
                    m.setAge(Integer.parseInt(data[2]));
                    m.setWight(Double.parseDouble(data[3]));
                    m.setHeight(Double.parseDouble(data[4]));
                    m.setEmail(data[5]);
                    m.setGender(data[6]);
                    m.setIMC(Double.parseDouble(data[7])); 
                    
                    members.add(m);
                }
            }
            reader.close();
            return members;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // ==========================================
    // RUTINAS
    // ==========================================
    @Override
    public void saveRoutines(List<Routine> routines) {
        try {
            checkFolder();
            FileWriter fw = new FileWriter("data/routines.txt");
            BufferedWriter writer = new BufferedWriter(fw);

            for (Routine r : routines) {
                String line = r.getIdRoutine() + ";" +
                              r.getName() + ";" +
                              r.getNameMember();
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Routine> loadRoutines() {
        try {
            File file = new File("data/routines.txt");
            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            List<Routine> routines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 3) {
                    Routine r = new Routine();
                    r.setIdRoutine(Integer.parseInt(data[0]));
                    r.setName(data[1]);
                    r.setNameMember(data[2]);
                    
                    routines.add(r);
                }
            }
            reader.close();
            return routines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // ==========================================
    // EJERCICIOS
    // ==========================================
    @Override
    public void saveExercises(List<Exercise> exercises) {
        try {
            checkFolder();
            FileWriter fw = new FileWriter("data/exercises.txt");
            BufferedWriter writer = new BufferedWriter(fw);

            for (Exercise e : exercises) {
                String line = e.getIdRoutine() + ";" +
                              e.getName() + ";" +
                              e.getDescription() + ";" +
                              e.getMuscularGroup();
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Exercise> loadExercises() {
        try {
            File file = new File("data/exercises.txt");
            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            List<Exercise> exercises = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 4) {
                    Exercise e = new Exercise();
                    e.setIdRoutine(Integer.parseInt(data[0]));
                    e.setName(data[1]);
                    e.setDescription(data[2]);
                    e.setMuscularGroup(data[3]);
                    
                    exercises.add(e);
                }
            }
            reader.close();
            return exercises;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // ==========================================
    // MÉTODOS AUXILIARES
    // ==========================================
    private String getFileMembers() {
        return "data/members.txt";
    }

    private void checkFolder() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}