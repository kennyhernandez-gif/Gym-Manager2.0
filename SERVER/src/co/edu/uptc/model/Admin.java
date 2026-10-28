package co.edu.uptc.model;
import java.util.ArrayList;

public class Admin {
    private String idManger;
    private String name;
    private int age;
    private String email;

    public Admin(String idManger, String name, int age, String email) {
        this.idManger = idManger;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getIdManger() {
        return idManger;
    }

    public void setIdManger(String idManger) {
        this.idManger = idManger;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    


    
}
