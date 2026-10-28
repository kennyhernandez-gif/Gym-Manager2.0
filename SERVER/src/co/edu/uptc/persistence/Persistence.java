package co.edu.uptc.persistence;

import java.util.List;
import co.edu.uptc.model.Member;
import co.edu.uptc.model.Routine;
import co.edu.uptc.model.Exercise;

public interface Persistence {
    void saveMembers(List<Member> members);
    List<Member> loadMembers();
    
    void saveRoutines(List<Routine> routines);
    List<Routine> loadRoutines();
    
    void saveExercises(List<Exercise> exercises);
    List<Exercise> loadExercises();
}