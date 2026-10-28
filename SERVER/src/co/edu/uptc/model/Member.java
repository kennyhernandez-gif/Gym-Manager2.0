package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.persistence.PersistenceTxt;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.structures.SimpleList;

public class Member {

    private int idUser;
    private String name;
    private int age;
    private double imc;
    private double wight; // Respetando el nombre de variable de tu persistencia
    private double height;
    private String email;
    private String gender;
    private String membershipType;
    private String status;
    private Trainer trainer;
    private SimpleList membersList;


    public Member() {
    }

    public Member(int idUser, String name, int age, double imc, double wight, double height, String email, String gender) {
        this.idUser = idUser;
        this.name = name;
        this.age = age;
        this.imc = imc;
        this.wight = wight;
        this.height = height;
        this.email = email;
        this.gender = gender;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getIMC() {
        return imc;
    }

    public void setIMC(double imc) {
        this.imc = imc;
    }

    public double getWight() {
        return wight;
    }

    public void setWight(double wight) {
        this.wight = wight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // --- MÉTODOS DEL CRUD RECONSTRUIDOS Y RECUPERADOS ---
    
    public static List<Member> getMembersList() {
        return membersList;
    }

    public static void setMembersList(List<Member> list) {
        membersList = list;
    }

    // 1. REGISTRAR MIEMBRO
    public static void registerMember(int idUser, String name, int age, double wight, double height, String email, String gender, double imc) {
        Member newMember = new Member(idUser, name, age, imc, wight, height, email, gender);
        membersList.add(newMember);
        new PersistenceTxt().saveMembers(membersList);
    }

    // 2. ELIMINAR MIEMBRO
    public static void deleteMember(int idUser) {
        membersList.removeIf(m -> m.getIdUser() == idUser);
        new PersistenceTxt().saveMembers(membersList);
    }

    // 3. ACTUALIZAR MIEMBRO
    public static void updateMember(int idOriginal, int newIdUser, String newName, int newAge, double newWight, double newHeight, String newEmail, String newGender, double newImc) {
        for (Member m : membersList) {
            if (m.getIdUser() == idOriginal) {
                m.setIdUser(newIdUser);
                m.setName(newName);
                m.setAge(newAge);
                m.setWight(newWight);
                m.setHeight(newHeight);
                m.setEmail(newEmail);
                m.setGender(newGender);
                m.setIMC(newImc);
                break;
            }
        }
        new PersistenceTxt().saveMembers(membersList);
    }

    // --- MÉTODO DE LÓGICA ESTADÍSTICA (GÉNERO) ---
    public static int[] getGenderStatistics() {
        List<Member> femaleList = new ArrayList<>();
        List<Member> maleList = new ArrayList<>();

        for (Member m : membersList) {
            if (m.getGender() != null && m.getGender().equalsIgnoreCase("f")) {
                femaleList.add(m);
            } else if (m.getGender() != null && m.getGender().equalsIgnoreCase("m")) {
                maleList.add(m);
            }
        }
        return new int[]{ femaleList.size(), maleList.size() };
    }

    @Override
    public String toString() {
        return "Member{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", imc=" + imc +
                ", wight=" + wight +
                ", height=" + height +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}