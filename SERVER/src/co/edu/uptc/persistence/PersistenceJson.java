package co.edu.uptc.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.model.Member;
import co.edu.uptc.model.Routine;
import co.edu.uptc.model.Exercise;

public class PersistenceJson implements Persistence {

    private Gson gson;
    private final String FILE_MEMBERS = "data/members.json";
    private final String FILE_ROUTINES = "data/routines.json";
    private final String FILE_EXERCISES = "data/exercises.json";

    public PersistenceJson() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        new File("data").mkdirs();
    }

    @Override
    public void saveMembers(List<Member> members) {
    	
    	try {

            FileWriter writer = new FileWriter(FILE_MEMBERS);

            gson.toJson(members, writer);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> loadMembers() {
        try {
            File file = new File(FILE_MEMBERS);
            if (!file.exists() || file.length() == 0) return new ArrayList<>();
            FileReader reader = new FileReader(file);
            Type listType = new TypeToken<ArrayList<Member>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) { 
            return new ArrayList<>(); 
        }
    }

    @Override
    public void saveRoutines(List<Routine> routines) {
        try (FileWriter writer = new FileWriter(FILE_ROUTINES)) {
            gson.toJson(routines, writer);
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }

    @Override
    public List<Routine> loadRoutines() {
        try {
            File file = new File(FILE_ROUTINES);
            if (!file.exists() || file.length() == 0) return new ArrayList<>();
            FileReader reader = new FileReader(file);
            Type listType = new TypeToken<ArrayList<Routine>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) { 
            return new ArrayList<>(); 
        }
    }

    @Override
    public void saveExercises(List<Exercise> exercises) {
        try (FileWriter writer = new FileWriter(FILE_EXERCISES)) {
            gson.toJson(exercises, writer);
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }

    @Override
    public List<Exercise> loadExercises() {
        try {
            File file = new File(FILE_EXERCISES);
            if (!file.exists() || file.length() == 0) return new ArrayList<>();
            FileReader reader = new FileReader(file);
            Type listType = new TypeToken<ArrayList<Exercise>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) { 
            return new ArrayList<>(); 
        }
    }
}
