package co.edu.uptc.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.persistence.Persistence;
import co.edu.uptc.persistence.PersistenceJson;
import co.edu.uptc.persistence.PersistenceTxt;

import co.edu.uptc.model.Member;
import co.edu.uptc.model.Routine;
import co.edu.uptc.model.Exercise;
import co.edu.uptc.view.MainFrame; 

public class Presenter {

	private MainFrame mainFrame;

	private Persistence persistence;

	public Presenter(String type) {

		if (type.equalsIgnoreCase("txt")) {
			persistence = new PersistenceTxt();
		} else {
			persistence = new PersistenceJson();
		}
		Member.setMembersList(persistence.loadMembers());
		Routine.setRoutineList(persistence.loadRoutines());
		Exercise.setExerciseList(persistence.loadExercises());

		mainFrame = new MainFrame(this);
	}

	public void init() throws IOException {
		mainFrame.setVisible(true);
	}

	public Persistence getPersistence() {
		return persistence;
	}

	public void registerMember(int id,
			String name,
			int age,
			double weight,
			double height,
			String email,
			String gender,
			double imc) {

		Member newMember = new Member();

		newMember.setIdUser(id);
		newMember.setName(name);
		newMember.setAge(age);
		newMember.setWight(weight);
		newMember.setHeight(height);
		newMember.setEmail(email);
		newMember.setGender(gender);
		newMember.setIMC(imc);

		List<Member> currentMembers =
				Member.getMembersList();

		if (currentMembers == null) {
			currentMembers =
					new ArrayList<>();
		}

		currentMembers.add(newMember);

		Member.setMembersList(currentMembers);

		persistence.saveMembers(currentMembers);

		mainFrame.refreshUI();
	}

	public void updateMember(int oldId, int newId, String name, int age, double weight, double height, String email, String gender, double imc) {
		List<Member> currentMembers = Member.getMembersList();
		if (currentMembers != null) {
			for (Member m : currentMembers) {
				if (m.getIdUser() == oldId) {
					m.setIdUser(newId);
					m.setName(name);
					m.setAge(age);
					m.setWight(weight);
					m.setHeight(height);
					m.setEmail(email);
					m.setGender(gender);
					m.setIMC(imc);
					break;
				}
			}
			Member.setMembersList(currentMembers);
			persistence.saveMembers(currentMembers);
		}
	}

	public void deleteMember(int id) {
		List<Member> currentMembers = Member.getMembersList();
		if (currentMembers != null) {
			currentMembers.removeIf(m -> m.getIdUser() == id);
			Member.setMembersList(currentMembers);
			persistence.saveMembers(currentMembers);
		}
	}

	public void registerRoutine(
			int id,
			String name,
			String nameMember) {

		Routine newRoutine =
				new Routine();

		newRoutine.setIdRoutine(id);
		newRoutine.setName(name);
		newRoutine.setNameMember(nameMember);

		List<Routine> currentRoutines =
				Routine.getRoutineList();

		if (currentRoutines == null) {
			currentRoutines =
					new ArrayList<>();
		}

		currentRoutines.add(newRoutine);

		Routine.setRoutineList(currentRoutines);

		persistence.saveRoutines(currentRoutines);

		mainFrame.refreshUI();
	}

	public void updateRoutine(int oldId, int newId, String name, String nameMember) {
		List<Routine> currentRoutines = Routine.getRoutineList();
		if (currentRoutines != null) {
			for (Routine r : currentRoutines) {
				if (r.getIdRoutine() == oldId) {
					r.setIdRoutine(newId);
					r.setName(name);
					r.setNameMember(nameMember);
					break;
				}
			}
			Routine.setRoutineList(currentRoutines);
			persistence.saveRoutines(currentRoutines);
		}
	}

	public void deleteRoutine(int id) {
		List<Routine> currentRoutines = Routine.getRoutineList();
		if (currentRoutines != null) {
			currentRoutines.removeIf(r -> r.getIdRoutine() == id);
			Routine.setRoutineList(currentRoutines);
			persistence.saveRoutines(currentRoutines);
		}
	}
	public void registerExercise(
			int id,
			String name,
			String description, String muscularGroup) {

		Exercise newExercise =
				new Exercise();

		newExercise.setIdRoutine(id);
		newExercise.setName(name);
		newExercise.setDescription(description);
		newExercise.setDescription(muscularGroup);

		List<Exercise> currentExercises =
				Exercise.getExerciseList();

		if (currentExercises == null) {
			currentExercises =
					new ArrayList<>();
		}

		currentExercises.add(newExercise);

		Exercise.setExerciseList(currentExercises);

		persistence.saveExercises(currentExercises);

		mainFrame.refreshUI();
	}
	public void updateExercise(
			int oldId,
			String oldName,
			int newId,
			String newName,
			String newDescription,
			String newGroup) {

		List<Exercise> currentExercises =
				Exercise.getExerciseList();

		if (currentExercises != null) {

			for (Exercise e : currentExercises) {

				if (e.getIdRoutine() == oldId &&
						e.getName().equals(oldName)) {

					e.setIdRoutine(newId);
					e.setName(newName);
					e.setDescription(newDescription);
					e.setMuscularGroup(newGroup);

					break;
				}
			}

			Exercise.setExerciseList(
					currentExercises);

			persistence.saveExercises(
					currentExercises);

			mainFrame.refreshUI();
		}
	}
	public void deleteExercise(
			int idRoutine,
			String exerciseName) {

		List<Exercise> currentExercises =
				Exercise.getExerciseList();

		if (currentExercises != null) {

			currentExercises.removeIf(
					e -> e.getIdRoutine() == idRoutine
					&& e.getName()
					.equals(exerciseName));

			Exercise.setExerciseList(
					currentExercises);

			persistence.saveExercises(
					currentExercises);

			mainFrame.refreshUI();
		}
	}


}